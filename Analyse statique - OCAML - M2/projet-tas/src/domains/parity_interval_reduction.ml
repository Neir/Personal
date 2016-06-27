(*
  Cours "Typage et Analyse Statique"
  Université Pierre et Marie Curie
  Antoine Miné 2015
*)

open Parity_domain
open Interval_domain
open Value_reduction

module ParityIntervalsReduction =
  (struct
    
    module A = Parity

    module B = Intervals

    type t = A.t * B.t

    let reduce ((a,b):t) : t = match a,b with
      | BOT,_ | _,BOT -> BOT,BOT
      | TOP,x -> TOP,x
      | p,Itv(bi,bs) ->
	let newbi bnd p = match bnd with
	  | Int i -> if((A.parity i) != p) then B.add_one bnd else bnd
	  | _ -> bnd
	in
	  let newbs bnd p = match bnd with
	  | Int i -> if((A.parity i) != p) then B.sub_one bnd else bnd
	  | _ -> bnd
	in
	p,Itv(newbi bi p, newbs bs p)
	

end : VALUE_REDUCTION)