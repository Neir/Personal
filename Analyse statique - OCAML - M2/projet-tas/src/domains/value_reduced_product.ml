(*
  Cours "Typage et Analyse Statique"
  Université Pierre et Marie Curie
  Antoine Miné 2015
*)

open Value_domain
open Value_reduction

module ReducedProduct(R : VALUE_REDUCTION) =
  (struct

    module A = R.A

    module B = R.B

    type t = R.t (* A.t * B.t *)

    let top = B.top

    let unary ((a,b):t) (op:int_unary_op) : t =
      R.reduce ((A.unary a op),(B.unary b op))

    let binary ((a,b):t) ((c,d):t) (op:int_unary_op) : t = 
      R.reduce ((A.binary a b op),(B.binary c d op))

    let compare ((a,b):t) ((c,d):t) (op:int_unary_op) : t = 
      R.reduce ((A.compare a c op),(B.compare b d op))
    
end : VALUE_DOMAIN)