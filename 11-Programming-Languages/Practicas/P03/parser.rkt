#lang plai

(require "grammars.rkt")

;; Analizador sintáctico para WAE.
;; Dada una s-expresión, regresa el árbol de sintaxis abstracta correspondiente, es decir, construye 
;; expresiones del tipo de dato abstracto definido en el archivo grammars.rkt
;; parse: s-expresion -> WAE.
(define (parse sexp)
   	(match sexp
		[(? symbol?) (id sexp)]
		[(? number?) (num sexp)]
                [(list 'with bindings body) (with (map aux-binding bindings) (parse body))]
                [(list 'with* bindings body) (with* (map aux-binding bindings) (parse body))]
                [(? list?) (op (elige (car sexp)) (map parse (cdr sexp) ))]))

(define (aux-binding list)
  (binding (car list) (parse (second list))))

;; Funcion para definir el operador
;; elige: exp -> symbol
(define (elige exp)
	(match exp
          ['+ +]
          ['- -]
          ['* *]
          ['/ /]
          ['% modulo]
          ['min min]
          ['max max]
          ['pow mexpt]
          
          ['and and]
          ['or or]
          ['< <]
          ['<= <=]
          ['> >]
          ['>= >=]
          ['= =]
          ['!= not-equal-aux]
          ['neg not]))

;; Funcion auxiliar para la potencia
(define (mexpt lst)
  (letrec ([exponer (lambda (elem lista)
                      (cond
                         [(empty? lista) elem]
                         [else (exponer (expt elem (car lista))(cdr lista))]))])
         (exponer (car lst) (cdr lst))))

(define (not-equal-aux . lst)
  (not (apply = lst)))

;; Función que dice si un símbolo es una operación soportada
(define (op-valida? op)
  (match op
    ['+ true]
    ['- true]
    ['* true]
    ['/ true]
    ['% true]
    ['min true]
    ['max true]
    ['pow true]
    ['or true]
    ['and true]
    ['< true]
    ['<= true]
    ['> true]
    ['>= true]
    ['= true]
    ['!= true]
    ['neg true]
    [else false]))
