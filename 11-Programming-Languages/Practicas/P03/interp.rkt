#lang plai

(require "grammars.rkt")
(require "parser.rkt")

;; Función encargada de interpretar el árbol de sintaxis abstracta generado por el parser.
;; interp: WAE -> number
(define (interp exp)
  (match exp
    [(id i) (error 'interp "Free identifier")]
    [(num n) n]
    [(op f elements) (apply f (map interp elements))]
    [(with bindings body-with) (interp (foldl (lambda (bin exp)
                                         (subst exp
                                                (binding-name bin)
                                                (num (interp (binding-value bin)))))
                                       body-with
                                       bindings))]
    [(with* bindings body-with) (interp (foldr (lambda (bin exp)
                                         (subst exp
                                                (binding-name bin)
                                                (binding-value bin)))
                                       body-with
                                       bindings))]))
  

;; Función que implementa el algoritmo de sustitución.
;; subst: WAE symbol WAE -> WAE
(define (subst expr sub-id val)
   (match expr
     [(id i) (if (symbol=? i sub-id)
                val
                expr)]
     [(num n) expr]
     ;; Reduce los parametros del subst
     [(op f elements) (op f (map (lambda (exp)
                                  (subst exp sub-id val)) elements))]
     [(with bindings body-with)
      (if (contiene? sub-id (get-bindings bindings))
          (with
           (sust-bindings sub-id val bindings)
           body-with)
          (with
           (sust-bindings sub-id val bindings)
           (subst body-with sub-id val)))]
     [(with* bindings body-with)
     (if (contiene? sub-id (get-bindings bindings))
         (with*
                (sust-bindings sub-id val bindings)
                body-with)
         (with*
                (sust-bindings sub-id val bindings)
                (subst body-with sub-id val)))]
     ))

;; Función auxiliar que nos dice si un elemento está en una lista
;; contiene: elem lst -> boolean
(define (contiene? elem lst)
            (match lst
                ['() #f]
                [(cons x xs) (if (symbol=? x elem)
                                 #t
                                 (contiene? elem xs))]))

;; Función auxiliar que ontiene los id's de una lista de bindings
;; get-bindings: bindings -> bindings
(define (get-bindings bindings)
  (match bindings
    ['() '()]
    [(cons x xs) (cons (binding-name x) (get-bindings xs))]
                        ))

;; Función auxiliar que realiza una sustitución en una lista de bindings.
;; sust-bindings: sub-id val lst -> lst
(define (sust-bindings sub-id val lst)
  (match lst
    ['() '()]
    [(cons x xs) (cons (binding (binding-name x)
                      (subst (binding-value x) sub-id val))
             (sust-bindings sub-id val xs))]))