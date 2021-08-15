#lang plai

;; TDA para representar los árboles de sintaxis abstracta del
;; lenguaje WAE.
(define-type WAE
    [id (id symbol?)]
    [num (n number?)]
    [binop (f procedure?) (izq WAE?) (der WAE?)]
    [with (name symbol?) (named-expr WAE?) (body WAE?)])
