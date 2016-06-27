(*
  Cours "Typage et Analyse Statique"
  Université Pierre et Marie Curie
  Antoine Miné 2015
*)

(* 
   The interval domain
 *)

open Abstract_syntax_tree
open Value_domain


module Intervals = (struct

  
  (* types *)
  (* ***** *)

  type bound =
    | Int of Z.t
    | PINF (* positive infinite *)
    | MINF (* negative infinite *)

  let bound_to_string (b:bound) = match b with
  | MINF -> "-inf"
  | PINF -> "+inf"
  | Int x -> (Z.to_string x)

  (* compare a et b, retourne -1, 0 ou 1 *)
  let bound_cmp (a:bound) (b:bound) : int = match a,b with
    | MINF,MINF | PINF,PINF -> 0
    | MINF,_ | _,PINF -> -1
    | PINF,_ | _,MINF -> 1
    | Int i, Int j -> Z.compare i j

  (* { [a, b] | a <= b } U {BOT} *)
  type t = Itv of bound * bound | BOT

  let print_my_interval x = match x with
  | BOT -> Printf.printf "bottom"
  | Itv(a,b) ->
    Printf.printf "{[%s,%s]}\n" (bound_to_string a) (bound_to_string b)

  let bound_neg (a:bound) : bound = match a with
    | MINF -> PINF | PINF -> MINF | Int i -> Int (Z.neg i)

  (* a + b *)
  let bound_add (a:bound) (b:bound) : bound = match a,b with
    | MINF,PINF | PINF,MINF -> invalid_arg "bound_add"
    | MINF,_ | _,MINF -> MINF
    | PINF,_ | _,PINF -> PINF
    | Int i, Int j -> Int (Z.add i j)

  let bound_sub (a:bound) (b:bound) : bound =
    bound_add a (bound_neg b)

  let bound_mul (a:bound) (b:bound) : bound = match a,b with
    | PINF,c | c,PINF -> 
      if((bound_cmp c (Int Z.zero)) = 1) then PINF
      else if((bound_cmp c (Int Z.zero)) = -1) then MINF
      else Int Z.zero
    | MINF,c | c,MINF ->
      if((bound_cmp c (Int Z.zero)) = 1) then MINF
      else if((bound_cmp c (Int Z.zero)) = -1) then PINF
      else Int Z.zero
    | Int i, Int j -> 
      if(i = Z.zero || j = Z.zero) then Int Z.zero  
      else Int (Z.mul i j)

  let bound_div (a:bound) (b:bound) : bound = match a,b with
    | c,PINF | c,MINF -> Int Z.zero
    | PINF, c ->
      if((bound_cmp c (Int Z.zero)) = 1) then PINF
      else if((bound_cmp c (Int Z.zero)) = -1) then MINF
      else invalid_arg "bound_div"
    | MINF, c ->
      if((bound_cmp c (Int Z.zero)) = 1) then PINF
      else if((bound_cmp c (Int Z.zero)) = -1) then MINF
      else invalid_arg "bound_div"
    | Int i, Int j -> 
      if(i = Z.zero) then Int Z.zero
      else if (j = Z.zero) then invalid_arg "bound_div" 
      else Int (Z.div i j)

  let bound_mdl (a:bound) (b:bound) : bound = match a,b with
    | Int i, Int j -> 
      if(i = Z.zero && j <> Z.zero) then Int Z.zero
      else if(i = Z.zero && j = Z.zero) then invalid_arg "bound_mdl"
      else Int (Z.erem i j)
    | Int x, y  -> if(x = Z.zero) then Int Z.zero
                   else invalid_arg "bound_mdl"
    | x , Int y -> if(y = Z.zero) then x
                   else invalid_arg "bound_mdl"
    | MINF,_ -> invalid_arg "bound_mdl"
    | PINF,_ -> invalid_arg "bound_mdl"

  let lift1 f x = match x with
    | Itv (a,b) -> f a b
    | BOT -> BOT

  let lift2 f x y = match x,y with
    | BOT,_ | _,BOT -> BOT
    | Itv (a,b), Itv (c,d) -> f a b c d

  let subset (x:t) (y:t) : bool = match x,y with
    | BOT,_ -> true
    | _,BOT -> false
    | Itv (a,b), Itv (c,d) -> bound_cmp a c >= 0 && bound_cmp b d <= 0

  (* interface implementation *)
  (* ************************ *)


  (* unrestricted value *)
  let top = Itv (MINF, PINF)

  (* bottom value *)
  let bottom = BOT

  (* constant *)
  let const (c:Z.t) = Itv (Int c, Int c)

  (* interval *)
  let rand x y =
    if x=y then const x
    else if x<y then Itv (Int x, Int y)
    else bottom

  (* arithmetic operations *)

  let neg (x:t) : t =
    lift1 (fun a b -> Itv (bound_neg b, bound_neg a)) x

  let add (x:t) (y:t) : t =
    lift2 (fun a b c d -> Itv (bound_add a c, bound_add b d)) x y

  let sub (x:t) (y:t) : t =
    lift2 (fun a b c d -> Itv (bound_sub a d, bound_sub b c)) x y

  let mul (x:t) (y:t) : t =
    (* cmp = 1 for min and cmp = -1 for max *)
    let min_max (a:bound) (b:bound) (c:bound) (d:bound) (cmp:int) : bound = 
      if ( (bound_cmp a b) = cmp ) then 
        if ( (bound_cmp b c) = cmp ) then
          if ( (bound_cmp c d) = cmp ) then d
	  else c
        else if ( (bound_cmp b d) = cmp ) then d
	else b
      else if ( (bound_cmp a c) = cmp ) then
	if( (bound_cmp c d) = cmp ) then d
	else c
      else if ( (bound_cmp a d) = cmp ) then d
      else a
    in
    lift2 (fun a b c d -> Itv (
	min_max (bound_mul a c) (bound_mul a d) (bound_mul b c) (bound_mul b c) 1,
	min_max (bound_mul b d) (bound_mul a c) (bound_mul a c) (bound_mul b c) (-1)
      )) x y

  let div (x:t) (y:t) : t =
    (* cmp = 1 for min and cmp = -1 for max *)
    let min_max a b cmp = 
      if ( (bound_cmp a b) = cmp ) then b
      else a
    in
    lift2 (fun a b c d ->
      if(c = d && (bound_cmp c (Int Z.zero)) = 0) then bottom
      else
        let bound_inf = 
	  if( (bound_cmp c (Int Z.zero)) > 0 ) then min_max (bound_div a c) (bound_div a d) 1
	  else min_max (bound_div b c) (bound_div b d) 1
	in
	let bound_sup =
	  if( (bound_cmp d (Int Z.zero)) > 0 ) then min_max (bound_div b c) (bound_div b d) (-1)
	  else min_max (bound_div a c) (bound_div a d) (-1)
	in
	Itv(bound_inf,bound_sup)
    ) x y

  let mdl  (x:t) (y:t) : t =
    lift2 (fun a b c d -> Itv (bound_mdl a c, bound_mdl b d)) x y

  (* set-theoretic operations *)
  
  (* [bi1,bs1] n [bi2,bs2] *)
  let meet x y = match x,y with
  | BOT,_ | _,BOT -> BOT
  | Itv (bi1,bs1), Itv (bi2,bs2) ->
    let bi3 =
    if ( (bound_cmp bi1 bi2) >= 0) then bi1
      else bi2
    in
    let bs3 =
    if ( (bound_cmp bs1 bs2) <= 0) then bs1
      else bs2
    in
    if ((bound_cmp bi3 bs3) > 0) then BOT
    else Itv(bi3,bs3)

let join x y = match x,y with
  | i,BOT | BOT,i -> i
  | Itv (bi1,bs1), Itv (bi2,bs2) ->
    let bi3 =
    if ( (bound_cmp bi1 bi2) <= 0) then bi1
      else bi2
    in
    let bs3 =
    if ( (bound_cmp bs1 bs2) >= 0) then bs1
      else bs2
    in
    if ((bound_cmp bi3 bs3) > 0) then BOT
    else Itv(bi3,bs3)

let widen a b = match a,b with
  | i,BOT | BOT,i -> i
  | Itv (bi1,bs1), Itv (bi2,bs2) ->
    let bi3 =
    if ( (bound_cmp bi1 bi2) <= 0) then bi1
      else MINF
    in
    let bs3 =
    if ( (bound_cmp bs1 bs2) >= 0) then bs1
      else PINF
    in
    if ((bound_cmp bi3 bs3) > 0) then BOT
    else Itv(bi3,bs3)

(*
  | Itv(a,b),Itv(c,d) ->
    if((bound_cmp a c) <= 0) then 
      if((bound_cmp b d) >= 0)then
        Itv(a,b)
      else Itv(a,PINF)
    else
      if((bound_cmp b d) >= 0)then
        Itv(MINF,b)
      else Itv(MINF,PINF)
*)

  (* comparison operations (filters) *)

  let eq a b = (meet a b),(meet b a)

  let sub_one b = bound_sub b (Int Z.one)

  let add_one b = bound_add b (Int Z.one)

  let neq a b = match a,b with  
  | BOT,_ | _,BOT -> BOT,BOT
  | Itv(bia, bsa),Itv(bib,bsb) ->
    if(bound_cmp bia bsa = 0) then				(* a constante *)
      if(bound_cmp bib bsb = 0 && bound_cmp bib bia = 0) then	(* b constante et a=b *)
	BOT,BOT
      else if(bound_cmp bib bia = 0) then			(* a = bib *)
	a, Itv(add_one bib, bsb)
      else if(bound_cmp bsb bia = 0) then			(* a = bsb *)
	a, Itv(bib, sub_one bsb)
      else a,b
    else if(bound_cmp bib bsb = 0) then 			(* b constante *)
      if(bound_cmp bia bib = 0) then				(* b = bia *)
	Itv(add_one bia, bsa), b
      else if(bound_cmp bsa bib = 0) then			(* b = bsa *)
	Itv(bia, sub_one bsa), b
      else a,b
    else a,b

  let gt a b = match a,b with
  | BOT,_ | _,BOT -> BOT,BOT
  | Itv(bia, bsa),Itv(bib,bsb) ->
    if(bound_cmp bia bsa = 0) then			(* a constante *)
      if(bound_cmp bib bsb = 0) then			(* b constante *)
	if(bound_cmp bia bib <= 0) then			(* a <= b *)
	  BOT,BOT
	else
	  a,b
      else if(bound_cmp bia bib <= 0) then		(* a <= bib *)
	BOT,BOT
      else if(bound_cmp bia bsb <= 0) then		(* a > bib && a <= bsb *)
	a, Itv(bib, sub_one bia)
      else a,b
    else if(bound_cmp bib bsb = 0) then			(* b constante *)
      if(bound_cmp bib bsa >= 0) then			(* b >= bsa *)
	BOT,BOT
      else if(bound_cmp bib bia >= 0) then		(* b < bsa b >= bia *)
	Itv(add_one bib,bsa), b
      else a,b
    else						(* a,b intervales *)
      if(bound_cmp bia bib = 0 && bound_cmp bsa bsb = 0) then Itv(add_one bia, bsa),Itv(bib, sub_one bsb)
      else if(bound_cmp bia bib < 0 && bound_cmp bsa bsb > 0) then Itv(add_one bib, bsa),b
      else if(bound_cmp bia bib > 0 && bound_cmp bsa bsb < 0) then a,Itv(bib, sub_one bsa)
      else if(bound_cmp bia bib < 0 && bound_cmp bsa bsb < 0) then Itv(add_one bib, bsa),Itv(bib, sub_one bsa)
      else if(bound_cmp bia bib > 0 && bound_cmp bsa bsb > 0) then a,b
      else if(bound_cmp bsa bib < 0) then BOT,BOT
      else a,b

  let geq a b = match a,b with
  | BOT,_ | _,BOT -> BOT,BOT
  | Itv(bia, bsa),Itv(bib,bsb) ->
    if(bound_cmp bia bsa = 0) then			(* a constante *)
      if(bound_cmp bib bsb = 0) then			(* b constante *)
	a,b
      else if(bound_cmp bia bib < 0) then		(* a < bib *)
	BOT,BOT
      else if(bound_cmp bia bsb <= 0) then		(* a => bib && a <= bsb *)
	a, Itv(bib, bia)
      else a,b
    else if(bound_cmp bib bsb = 0) then			(* b constante *)
      if(bound_cmp bib bsa > 0) then			(* b > bsa *)
	BOT,BOT
      else if(bound_cmp bib bia >= 0) then		(* b <= bsa b >= bia *)
	Itv(bib,bsa), b
      else a,b
    else
  	if(bound_cmp bia bib = 0 && bound_cmp bsa bsb = 0) then a,b
	else
	if(bound_cmp bia bib <= 0 && bound_cmp bsa bsb >= 0) then Itv(bib, bsa),b
	else
	if(bound_cmp bia bib >= 0 && bound_cmp bsa bsb <= 0) then a,Itv(bib, bsa)
	else
	if(bound_cmp bia bib <= 0 && bound_cmp bsa bsb <= 0) then Itv(bib, bsa),Itv(bib, bsa)
	else
	if(bound_cmp bia bib >= 0 && bound_cmp bsa bsb >= 0) then a,b
	else
	if(bound_cmp bsa bib < 0) then BOT,BOT
	else a,b

  (* check the emptyness of the concretization *)
  let is_bottom a =
    a=BOT

  (* operator dispatch *)
(* prints abstract element *)
  let print fmt x = match x with
  | BOT -> Format.fprintf fmt "bottom"
  | Itv(a,b) ->
    Format.fprintf fmt "{[%s,%s]}" (bound_to_string a) (bound_to_string b)
        
  let unary x op = match op with
  | AST_UNARY_PLUS  -> x
  | AST_UNARY_MINUS -> neg x

  let binary x y op = match op with
  | AST_PLUS     -> add x y
  | AST_MINUS    -> sub x y
  | AST_MULTIPLY -> mul x y
  | AST_DIVIDE   -> div x y
  | AST_MODULO   -> mdl x y

  let compare x y op = match op with
  | AST_EQUAL         -> eq x y
  | AST_NOT_EQUAL     -> neq x y
  | AST_GREATER_EQUAL -> geq x y
  | AST_GREATER       -> gt x y
  | AST_LESS_EQUAL    -> let y',x' = geq y x in x',y'
  | AST_LESS          -> let y',x' = gt y x in x',y'
        
  let contains_zero o = subset (const Z.zero) o

  let bwd_unary x op r = match op with
  | AST_UNARY_PLUS  -> meet x r
  | AST_UNARY_MINUS -> meet x (neg r)

        
  let bwd_binary x y op r = 
  Printf.printf "bwd_binary\n";
  match op with

  | AST_PLUS ->
      (* r=x+y => x=r-y and y=r-x *)      
      meet x (sub r y), meet y (sub r x)

  | AST_MINUS ->
      (* r=x-y => x=y+r and y=x-r *)
      meet x (add y r), meet y (sub y r)
        
  | AST_MULTIPLY ->
      (* r=x*y => (x=r/y or y=r=0) and (y=r/x or x=r=0)  *)
      Printf.printf "astmul\n";
      (if contains_zero y && contains_zero r then x else meet x (div r y)),
      (if contains_zero x && contains_zero r then y else meet y (div r x))
        
  | AST_DIVIDE ->
      (* r=x/y+q, q=x%y => (x=y*(r-q) and y=x/(r-q) or y=0 => BOT *)
      Printf.printf "astdivide\n";
      if contains_zero y then BOT, BOT
      else 
	let q = mdl x y in
	(meet x (mul y (sub r q))),
	(meet y (div x (sub r q)))

  | AST_MODULO ->
      (* r=x%y => x=(y*n)+r and y=(x-r)/n *)
      (* meet x (add (mul y TOP) r), meet y (div (sub x r) TOP) *)
      if contains_zero y then BOT, BOT
      else
	x, y
      
end : VALUE_DOMAIN)

    
