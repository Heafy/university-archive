#lang plai

#| Práctica 1: Introducción a Racket |#

;; Función que toma dos números enteros positivos y eleva uno al otro, para luego sumar las raíces
;; cuadradas de éstos.
;; rps: number number -> number
(define (rps a b)
   (+ (sqrt(expt a b)) (sqrt(expt b a))))

;; Función que encuentra el área de un tirángulo dados sus lados, usando la fórmula de Herón. Se usa
;; la primitiva let para evitar cálculos repetitivos.
;; area-heron: number number number -> number
(define (area-heron a b c)
  (let([s (/ (+ a b c) 2)])
    (sqrt(* s (- s a)(- s b)(- s c)))))

;; Predicado que determina si la pareja a b entará en el antro usando condicionales. La respuesta de 
;; el predicado está dada de acuerdo a lo siguiente:
;; "Si el estilo de los asistentes es de ocho o más, el predicado responderá 'si con la excepción de
;;  que si el estilo de alguno de los asistentes es de dos o menos, responderá 'no. En otro caso,
;;  responderá 'quiza."
;; entra?: number number -> symbol
(define (entra? a b)
   (cond
     [(and (or (>= a 8) (>= b 8)) (and (> a 2) (> b 2))) 'si]  
     [(or (<= a 2) (<= b 2)) 'no]
     [else 'quiza]))

;; Función recursiva que regresa el número de apariciones del dígito m como digito en el número 
;; entero positivo n.
;; apariciones: number number -> number
(define (apariciones n m)
  (apariciones-aux (number->string n) (number->string m)))

;; Funcion auxiliar para poder mostrar las apariciones
;; apariciones-aux: string -> number -> number
(define (apariciones-aux n m)
  (cond
    [(equal? n "") 0]
    [(equal? (substring n 0 1) m) (+ 1 (apariciones-aux (substring n 1) m))]
    [else (apariciones-aux (substring n 1) m)]))

;; Función recursiva que calcula el número de pares de una cadena. Decimos que un par en una cadena
;; son dos caracteres idénticos, separados por un tercero. Por ejemplo "AxA" es el par de "A". Los
;; pares, además, pueden anidarse, por ejemplo.
;; cuenta-pares: string -> number
(define (cuenta-pares c)
   (cuenta-pares-aux (string->list c)))

;; Funcion auxiliar que cuenta los pares en una lista
;; cuenta-pares-aux: list -> number
(define (cuenta-pares-aux lst)
  (cond
    [(equal? lst empty) 0]
    [(= (longitud lst) 3) (if (equal? (first lst) (third lst))
                            1
                            0)]
    [else (if (equal? (first lst) (third lst))
              (+ 1 (cuenta-pares-aux (cdr lst)))
              (cuenta-pares-aux (cdr lst)))]))

;; Función que imprime una piramide con n pisos haciendo uso de alguna función de impresión.
;; piramide: number -> void
(define (piramide n)
  (display (piramide-aux n 0)))

;; Funcion auxiliar para poder mostrar la piramide
;; piramide-aux: number -> number -> string
(define (piramide-aux n m)
    (cond
      [(= n 1) (string-append (make-string m) "*")]
      [(= n 2) (string-append (piramide-aux 1 (add1 m))
                              "\n" (make-string m)
                              (make-string 3 #\*))]
      [else (string-append (piramide-aux (sub1 n) (add1 m))
                           "\n" (make-string m)
                           (make-string (sub1 (* 2 n)) #\*))]))

;; Función que recibe dos listas y construye una nueva lista con listas de longitud 2 formadas a 
;; partir de los elementos de ambas listas.
;; arma-pares: list list -> (listof list)
(define (arma-pares lst1 lst2)
  (match lst1
    ['() empty]
    [(cons x xs) (cons (list x (car lst2)) (arma-pares xs (cdr lst2)))])) 

;; Función que recibe una lista con elementos de la forma '(id value) y regresa el valor asociado al
;; id que fue pasado como parámetro.
;; lookup: (listof list) -> any
(define (lookup id lst)
   (match lst
    ['() null]
    [(cons x xs) (if (eq? id (car x))
                     (second x)
                     (lookup id xs))]))

;; Función que compara la longitud de las listas lst1 y lst2. El valor de regreso son alguno de los 
;; siguientes:
;; · 'lista1-mas-grande
;; · 'lista2-mas-grande
;; · 'listas-iguales
;; compara-longitud: list list -> symbol
(define (compara-longitud lst1 lst2)
  (cond
    [(> (longitud lst1) (longitud lst2)) 'lista1-mas-grande]
    [(< (longitud lst1) (longitud lst2)) 'lista2-mas-grande]
    [else 'listas-iguales]))

;; Función que obtiene la longitud de una lista.
;; longitud: list -> number
(define(longitud lst)
  (match lst
    ['() 0]
    [(cons x xs) (+ 1 (longitud xs))]))

;; Función que entierra el símbolo nombre, n número de veces. Es decir, se anidan n - 1 listas hasta
;; que se llega a la lista que tiene al símbolo nombre.
;; entierra: symbol number -> list
(define (entierra nombre n)
   (cond
     [(= n 0) nombre]
     [(> n 0) (list (entierra nombre (- n 1)))]))

;; Función que que mezcla dos listas ordenadas obtieniendo una nueva, ordenada de manera ascendente.
;; mezcla: list list -> list
(define (mezcla lst1 lst2)
   (cond
    [(eq? lst1 '()) lst2]
    [(eq? lst2 '()) lst1]
    [else (if (< (car lst1) (car lst2))
              (cons (car lst1) (mezcla (cdr lst1) lst2))
              (cons (car lst2) (mezcla lst1 (cdr lst2))))]))

;; Función que recibe una lista de números y regresa una nueva lista de cadenas que representan al
;; número binario asociado a estos números.
;; binarios: (listof number) -> (listof string)
(define (binarios lst)
  (letrec ([convierte-binario? (lambda (n)
                            (if (equal? "0" (number->string n 2))
                                (number->string n 2)
                                (string-append "0" (number->string n 2))))])
    (map convierte-binario? lst)))

;; Función que recibe una lista y regresa una nueva conteniendo únicamente aquellos que son 
;; triangulares.
;; triangulares: (listof number) -> (listof number)
(define (triangulares lst)
  (letrec ([es-cuadrado? (lambda (n)
                            (cond
                              [(exact-integer? (sqrt n)) #t]
                              [else #f]))]
           [es-triangular? (lambda (n)
                      (es-cuadrado? (+ (* 8 n) 1)))])
    (filter es-triangular? lst)))

;; Función que, usando foldr, intercala un símbolo dado entre los elementos de una lista.
;; intercalar: list symbol -> list
(define (intercalar lst s)
    (letrec ([reversa (lambda (lst)
                            (cond
                              [(null? lst) empty]
                              [else (append (reversa (cdr lst)) (list (car lst)))]))])
    (cond
      [(null? lst) empty]
      [else (reversa
             (cdr
              (reversa
               (foldr (lambda (v l)
                        (cond
                          [(equal? v null) '()]
                          [else  (append (list v s) l)]))
                      empty lst))))])))

;; Función que, usando foldl, intercala un símbolo dado entre los elementos de una lista.
;; intercalal: list symbol -> list
(define (intercalal lst s)
  (letrec ([reversa (lambda (lst)
                            (cond
                              [(null? lst) empty]
                              [else (append (reversa (cdr lst)) (list (car lst)))]))])
    (cond
      [(null? lst) empty]
      [else (reversa
             (cdr
              (reversa
               (foldl (lambda (v l)
                        (cond
                          [(equal? v '()) '()]
                          [else  (append l (list v s))]))
                      empty lst))))])))