#lang plai

(require "grammars.rkt")
(require "parser.rkt")

(print-only-errors)

#| Módulo para pruebas unitarias de la actividad 5 |#

;; Pruebas para  parse

;; 1. Identificador
(test (parse 'foo)
      (id 'foo))

;; 2. Numero
(test (parse '1729)
      (num 1729))

;; 3. Operacion binaria
(test (parse '{+ 17 {* 35 {/ 10 {- 8 2}}}})
      (binop #<procedure:+> (num 17)
             (binop #<procedure:*> (num 35)
                    (binop #<procedure:/> (num 10)
                           (binop #<procedure:-> (num 8) (num 2))))))

;; 4. Asignacion local
(test (parse '{ with { a 2}{ with { b 3}{ with { c 4}{+ a {+ b c }}}}})
      (with 'a (num 2)
            (with 'b (num 3)
                  (with 'c (num 4)
                        (binop #<procedure:+> (id 'a)
                               (binop #<procedure:+> (id 'b) (id 'c)))))))

