#lang plai

(require "grammars.rkt")

;; Función que toma una s-expression y regresa el
;; árbol de sintaxis abstracta correspondiente.
;; parse: s-expression - > WAE
(define (parse sexp)
    (match sexp
     [(? symbol?) (id sexp)]
     [(? number?) (num sexp)]
     [(list 'with (list name named-expr) body) (with name (parse named-expr) (parse body))]
     [(list op l r)
        (case op
          [(+) (binop + (parse l)(parse r))]
          [(-) (binop - (parse l)(parse r))]
          [(*) (binop * (parse l)(parse r))]
          [(/) (binop / (parse l)(parse r))]
            )]))
