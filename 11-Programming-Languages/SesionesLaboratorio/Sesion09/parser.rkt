#lang plai

(require "grammars.rkt")

;; Análisis sintáctico del lenguaje. En analizador sintáctico se encarga de
;; construir el árbol de sintaxis abstracta.

;; Función elige auxiliar.
;; Permite elegir el operador correspondiente en Racket para las operaciones
;; binarias.
;; elige: symbol -> procedure
(define (elige s)
   (case s
      [(+) +]
      [(-) -]
      [(*) *]
      [(/) /]
      [(%) modulo]
      [(max) max]
      [(min) min]
      [(pow) expt]))

;; Analizador sintáctico para FWAE.
;; Dada una expresión en sintaxis concreta, regresa el árbol de sintaxis
;; abstractca correspondiente. Es decir, construye expresiones del tipo de
;; dato abstracto definido anteriormente.
;; parse: symbol -> FWAE
(define (parse sexp)
   (cond
      [(symbol? sexp) (idS sexp)] ; para identificadores
      [(number? sexp) (numS sexp)] ; para números
      [(list? sexp)
         (case (car sexp)
            [(+ - * - / % max min pow) ; para operaciones binarias
               (binopS
                  (elige (car sexp))
                  (parse (cadr sexp))
                  (parse (caddr sexp)))]
            [(with) ; para asignaciones locales
               (withS
                  (caadr sexp)
                  (parse (cadadr sexp))
                  (parse (caddr sexp)))]
            [(if0) ; para el condicional if0
               (if0S
                  (parse (cadr sexp))
                  (parse (caddr sexp))
                  (parse (cadddr sexp)))]
            [(fun) ; para lambdas
               (funS
                  (caadr sexp)
                  (parse (caddr sexp)))]
            [else ; para aplicación de funciones
               (appS
                  (parse (car sexp))
                  (parse (cadr sexp)))])]))

;; Función que toma una expresión en FWAE y le quita la azúcar sintáctica.
;; desugar: FWAE -> FAE
(define (desugar sexp)
   (match sexp
      ; Un número sigue siendo un número
      [(numS n) (num n)]
      ; Convertimos a la operación binaria quitando el azúcar sintactica a sus
      ; lados izquierdo y derecho recursivamente.
      [(binopS f l r) (binop f (desugar l) (desugar r))]
      ; A with le quitamos el azúcar sintáctica convirtiéndolo en una aplicación
      ; de función, donde la función recibe como parámetro el identificador,
      ; el cuerpo no se modifica y realizamos la aplicación con el valor del
      ; identificador.
      [(withS name named-expr body) 
         (app (fun name (desugar body)) (desugar named-expr))]
      ; Un identificador sigue siendo un identificador
      [(idS name) (id name)]
      ; if0 se mantiene igual
      [(if0S expr then-expr else-expr) 
         (if0 (desugar expr) (desugar then-expr) (desugar else-expr))]
      ; Convertimos las funciones quitando el azúcar sintáctica a su cuerpo
      [(funS param body) (fun param (desugar body))]
      ; Convertimos las aplicaciones quitando el azúcar sintáctica a la función
      ; y al argumento recursivamente.
      [(appS fun-expr arg-expr) (app (desugar fun-expr) (desugar arg-expr))]))
