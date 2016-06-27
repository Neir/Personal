(*
  Cours "Typage et Analyse Statique"
  Université Pierre et Marie Curie
  Antoine Miné 2015
*)

(* 
  Parity Domain
*)


open Abstract_syntax_tree
open Value_domain

  
module Parity = (struct

  
  (* types *)
  (* ***** *)


  (* type of abstract values *)
  type t =
    | PAIR   	(* the set is pair *)
    | IMPAIR	(* the set is impair *)
    | BOT       (* the set is empty (not reachable) *)
    | TOP       (* the set is pair or impair *)


  (* utilities *)
  (* ********* *)

  (* lift unary arithmetic operations, from integers to t *)
  let lift1 f x =
    match x with
    | BOT -> BOT
    | TOP -> TOP
    | a -> f a

  (* lift binary arithmetic operations *)
  let lift2 f x y =
    match x,y with
    | BOT,_ | _,BOT -> BOT
    | TOP,_ | _,TOP -> TOP
    | a, b -> f a b


  (* interface implementation *)
  (* ************************ *)


  (* unrestricted value *)
  let top = TOP

  (* bottom value *)
  let bottom = BOT

  let parity (c:Z.t) : t =
    if ((Z.erem c (Z.add Z.one Z.one)) = Z.zero) then PAIR
    else IMPAIR

  (* constant *)
  let const c = parity c

  (* interval *)
  let rand x y =
    if x=y then const x 
    else if x<y then TOP
    else BOT


  (* arithmetic operations *)

  let neg (x:t) : t = x

  let add (x:t) (y:t) : t = match x,y with
    | BOT,_ | _,BOT -> BOT
    | TOP,_ | _,TOP -> TOP
    | PAIR,PAIR | IMPAIR,IMPAIR -> PAIR
    | PAIR,IMPAIR | IMPAIR,PAIR -> IMPAIR

  let sub (x:t) (y:t) : t = match x,y with
    | BOT,_ | _,BOT -> BOT
    | TOP,_ | _,TOP -> TOP
    | PAIR,PAIR | IMPAIR,IMPAIR -> PAIR
    | PAIR,IMPAIR | IMPAIR,PAIR -> IMPAIR

  let mul (x:t) (y:t) : t = match x,y with
    | BOT,_ | _,BOT -> BOT
    | TOP,_ | _,TOP -> TOP
    | PAIR,_ | _,PAIR -> PAIR
    | IMPAIR,IMPAIR -> IMPAIR

  let div (x:t) (y:t) : t = match x,y with
    | BOT,_ | _,BOT -> BOT
    | TOP,_ | _,TOP -> TOP
    | PAIR,IMPAIR -> PAIR
    | _ -> TOP

  let mdl (x:t) (y:t) : t = match x,y with
    | BOT,_ | _,BOT -> BOT
    | TOP,_ | _,TOP -> TOP
    | PAIR,PAIR -> PAIR
    | IMPAIR,PAIR -> IMPAIR
    | _,IMPAIR -> TOP

  (* set-theoretic operations *)
  
  let join a b = match a,b with
    | BOT,x | x,BOT -> x
    | TOP,_ | _,TOP -> TOP
    | _ -> if a=b then a
           else TOP

  let meet a b = match a,b with
    | BOT,_ | _,BOT -> BOT
    | TOP,x | x,TOP -> x
    | _ -> if a=b then a
           else BOT


  (* no need for a widening as the domain has finite height; we use the join *)
  let widen = join


  (* comparison operations (filters) *)

  let eq a b = match a,b with
    | BOT,_ | _,BOT -> BOT,BOT
    | TOP,x | x,TOP -> x,x
    | _ -> if a=b then a,b
           else BOT,BOT

  let neq a b = match a,b with
    | BOT,_ | _,BOT -> BOT,BOT
    | TOP,PAIR | IMPAIR,TOP -> IMPAIR,PAIR
    | PAIR,TOP | TOP,IMPAIR -> PAIR,IMPAIR
    | _ -> if a=b then BOT,BOT
           else a,b
      
  let geq a b = match a,b with
    | BOT,_ | _,BOT -> BOT,BOT
    | _ -> TOP,TOP
      
  let gt a b = match a,b with
    | BOT,_ | _,BOT -> BOT,BOT
    | _ -> TOP,TOP


  (* subset inclusion of concretizations *)
  let subset a b = match a,b with
  | BOT,_ | _,TOP -> true
  | PAIR,PAIR | IMPAIR,IMPAIR -> true
  | _ -> false

  (* check the emptyness of the concretization *)
  let is_bottom a =
    a=BOT

  (* prints abstract element *)
  let print fmt x = match x with
  | BOT -> Format.fprintf fmt "bottom"
  | TOP -> Format.fprintf fmt "top"
  | PAIR -> Format.fprintf fmt "{even}"
  | IMPAIR -> Format.fprintf fmt "{odd}"


  (* operator dispatch *)
        
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

    
