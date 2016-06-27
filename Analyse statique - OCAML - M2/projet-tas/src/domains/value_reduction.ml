(*
  Cours "Typage et Analyse Statique"
  Université Pierre et Marie Curie
  Antoine Miné 2015
*)

open Value_domain

module type VALUE_REDUCTION =
  sig

    module A : VALUE_DOMAIN with type t = BOT | TOP | PAIR | IMPAIR

    module B : VALUE_DOMAIN with type t = Itv of bound * bound | BOT

    type t = A.t * B.t

    val reduce: t -> t

end