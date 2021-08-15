#lang plai

#| Práctica 2: Tipos de datos abstractos |#

;; TDA para representar funciones.
;; Se tienen constructores para la variable independiente, constantes, suma de funciones, 
;; multiplicación de funciones, división de funciones y potencia.
(define-type funcion
  [x]
  [cte (n integer?)]
  [sum (f funcion?)(g funcion?)]
  [mul (f funcion?)(g funcion?)]
  [div (f funcion?)(g funcion?)]
  [pot (f funcion?)(n integer?)])

;; Función que regresa una cadena de caracteres, representando a la función que recibe como parámetro.
;; funcion->string: funcion -> string
(define (funcion->string f)
   (cond
     [(x? f) "x"]
     [(cte? f) (number->string (cte-n f))]
     [(sum? f) (string-append (funcion->string(sum-f f)) " + " (funcion->string(sum-g f)))]
     [(mul? f) (string-append (funcion->string(mul-f f)) " * " (funcion->string(mul-g f)))]
     [(div? f) (string-append (funcion->string(div-f f)) " ÷ " (funcion->string(div-g f)))]
     [(pot? f) (string-append (funcion->string(pot-f f)) " ^ " (number->string(pot-n f)))]))

;; Función que devuelve el resultado de evaluar la función pasada como parámetro con el valor v, es
;; decir, regresa f(v).
(define (evalua f v)
   (match f
     [(x) (cte v)]
     [(cte v) (cte v)]
     [(sum f1 f2) (sum (evalua f1 v) (evalua f2 v))]
     [(mul f1 f2) (mul (evalua f1 v) (evalua f2 v))]
     [(div f1 f2) (div (evalua f1 v) (evalua f2 v))]
     [(pot f n) (pot (evalua f v) n)]))

;; Función que regresa la derivada de una función.
;; deriva: funcion -> funcion
(define (deriva f)
  (match f
    [(x) (cte 1)]
    [(cte v) (cte 0)]
    [(sum f1 f2) (sum (deriva f1) (deriva f2))]
    [(mul f1 f2) (mul (deriva f1) (deriva f2))]
    [(div f1 f2) (div (deriva f1) (deriva f2))]
    [(pot f n)((deriva f)(sub1 n))]
    ))

; ------------------------------------------------------------------------------------------------ ;

;; TDA para representar autómatas finitos deterministas.
;; Se tiene un único constructor para representar autómatas finitos deterministas como la quintúpla
;; correspondiente.
(define-type automata
  [afd (estados list?) (alfabeto list?) (inicial symbol?) (transicion procedure?) (finales list?)]
  )

;; Funcion de transicion
;; transicion: symbol symbol -> symbol
(define (transicion estado simbolo)
  (match estado
    ['p (if (symbol=? 'a simbolo) 'q 'r)]
    ['q (if (symbol=? 'a simbolo) 'q 'r)]
    ['r 'r]))

;; Función que verifica que un afd está bien construido. Verifica que el símbolo inicial y el conjunto
;; de símbolos finales pertenecen al conjunto de estados.
;; verifica: automata -> boolean
(define (verifica afd)
   (letrec ( [recorrer2 (lambda (lfinales lestados)
                 (cond
                    [(empty? lfinales) true]
                    [else (and (recorrer (car lfinales) lestados)
                          (recorrer2 (cdr lfinales) lestados))]))])
    (and (recorrer (afd-inicial afd) (afd-estados afd))
         (recorrer2 (afd-finales afd) (afd-estados afd)))))

;; Predicado que dado un afd y una lista de símbolos que representan una expresión, deterimina si es
;; aceptada por el autómata.
;; acepta?: automata list -> boolean
(define (acepta? afd lst)
   (if(empty? lst) false
      (recorrer (aux-acepta lst afd) (afd-finales afd))))

;;Funcion auxiliar para acepta
;;aux-acepta: list automata -> estado
(define(aux-acepta cadena afd)
  (letrec ([verif (lambda (estado lst)
                    (cond
                      [(empty? lst) estado]
                      [else (verif (transicion estado (car lst)) (cdr lst))]))])
      (if (recorrer (car cadena)(afd-alfabeto afd))
          (verif (transicion (afd-inicial afd) (car cadena)) (cdr cadena)) 'k)))

;; funcion auxiliar que me permite saber si el simbolo a se encuentra en la lista l
;; recorrer: symbol list -> boolean
(define (recorrer a l)
                        (cond
                          [(empty? l) false]
                          [(equal? a (car l)) true]
                          [else (recorrer a (cdr l))]))

; ------------------------------------------------------------------------------------------------ ;

;; TDA para representar una gramática de arreglos.
;; Se tienen constructores que permiten definir un arreglo, para especificar la operación de agregar 
;; un elemento y un tercero para obtener elemento.
(define-type arreglo
	[arrg (tipo procedure?) (dim number?) (elems list?)]
        [agrega-a (e any?) (a arreglo?) (i number?)]
        [obten-a (a arreglo?) (i number?)])

;; Funcion auxiliar para agregar un elemento
(define (any? a) #t)

;; Función que evalúa expresiones de tipo arreglo.
;; calc-a: arreglo -> arreglo
(define (calc-a arr)
   (match arr
    [(arrg t d e) (if (bd t d e)
                         (arrg t d e)
                         (error "El arreglo no esa bien definido"))]
    [(agrega-a e (arrg tipo dim elems) i) (arrg tipo dim (agr-lista e elems i))]
    [(obten-a (arrg tipo dim elems) i) (if (or (< i 0) (>= i dim))
                                             (error "Índice inválido")
                                             (list-ref elems i))]))

;; Funcion auxilar que  un elemento e a una lista l en la posición i
(define (agr-lista e l i)
	(if (or (> i (length l)) (< i 0))
    (error "Índice inválido")
	(if (zero? i)
	   (cons e l)
	   (cons (car l) (agr-lista e (cdr l) (- i 1))))))

;;Funcion axiliar que verifica que un arreglo esté bien definido.
(define (bd t d e)
  (cond
      [(not (revisa t e)) (error "Los elementos no son del tipo especificado")]
      [(not (equal? d (length e))) (error "Dimensión inválida")]
      [else #t]))

;; Funcion auxiliar que verifica que todos los elementos de una lista sean de tipo tipo
;; revisa: type lst -> bool
(define (revisa tipo lst)
	(if (empty? lst)
		#t
		(and (tipo (car lst)) (revisa tipo (cdr lst)))))

; ------------------------------------------------------------------------------------------------ ;

;; TDA para representar una gramática de conjuntos.
;; Se tienen constructores que permiten definir un conjunto, determinar si el conjunto es vacío, 
;; determinar si un elemento está contenido en el conjunto, agregar un elemento, unir conjunto, 
;; intersectar conjunto y calcular la diferencia.
(define-type conjunto
  (cjto (l list?))
  (esvacio (c empty?))
  (contiene (c conjunto?) (e any?))
  (agrega-c (c conjunto?) (e any?))
  (union (c1 conjunto?) (c2 conjunto?))
  (interseccion (c1 conjunto?) (c2 conjunto?))
  (diferencia (c1 conjunto?) (c2 conjunto?)))

;; Función que evalúa expresiones de tipo conjunto.
;; calc-c: conjunto -> conjunto
(define (calc-c cto)
  (match cto
    [(cjto l) (cjto (repeticiones-aux (cjto-l cto)))]
    [(esvacio c) (empty? (cjto-l c))]
    [(contiene c e) (contiene-aux e (cjto-l c))]
    [(agrega-c c e) (cjto (repeticiones (cons e (cjto-l c))))]
    [(union c1 c2) (cjto (repeticiones-aux (append (cjto-l c1)(cjto-l c2))))]
    [(interseccion c1 c2)(cjto (repeticiones-aux(intersecta(cjto-l c1)(cjto-l c2))))]))

(define (contiene-aux a lst)
  (match lst
    ['() false]
    [(cons x xs) (if (equal? a x)
                     true
                     (contiene-aux a xs))]))

(define (repeticiones-aux lst)
  (match lst
    ['() '()]
    [(cons x xs) (if(contiene x xs)
                     (repeticiones-aux xs)
                     (cons x (repeticiones-aux xs)))]))

(define (intersecta lst1 lst2)
  (match lst1
    ['() '()]
    [(cons x xs) (if (contiene x lst2)
                     (cons x (intersecta xs lst2))
                     (intersecta xs lst2))]))