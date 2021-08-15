#lang plai

(require "grammars.rkt")

;; Función que toma una expresión en sintaxis concreta y regresa el
;; árbol de sintaxis abstracta correspondiente.
;; parse : s-expression -> WAE
(define (parse sexp)
	(match sexp
		[(? symbol?) (id sexp)]
		[(? number?) (num sexp)]
		[(list 'with (list id val) body)
			(with id (parse val) (parse body))]
		[( list op l r)
			(binop (elige op) (parse l) (parse r))]))

;; [Auxiliar]. Función que hace un mapeo entre los operadores en
;; sintaxis concreta y los operadores de Racket. Esto con el fin de
;; aplicar la operación más adelante.
;; elige : symbol -> procedure
(define (elige sexp)
	(match sexp
		['+ +]
		['- -]
		['* *]
		['/ /]))
