#lang plai

(require "grammars.rkt")
(require "parser.rkt")

;; Análisis semántico del lenguaje. El analizador semántico se encarga de dar
;; un significado al árbol de sintaxis abstracta.

;; binopf: numV numV -> numV
(define (binopf f l r)
   (numV (f (numV-n (strict l)) (numV-n (strict r)))))

(define (num-zero? n)
   (zero? (numV-n (strict n))))

;; lookup: symbol DefrdSub -> FAE-Value
(define (lookup name env)
   (match env
      ; Si el ambiente está vacío reportamos que o se encontró el identificador
      [(mtSub) (error 'lookup "no binding for identifier")]
      ; Si no está vacío comparamos el identificador actual con el que estamos
      ; buscando.
      [(aSub bound-name bound-value rest-env)
         (if (symbol=? bound-name name)
            bound-value ; si son iguales regresamos el valor
            ; si son distintos seguimos buscando en el resto del ambiente 
            ; recurvivamente
            (lookup name rest-env))]))


;; Función que fuerza la evaluación de una expresión FBAE/L-Value.
;; strict: FAE/L-Value -> FAE/L-Value
(define (strict e)
   (match e
      [(exprV expr env) (strict (interp expr env))]
      [else e]))

;; Función encargada de interpretar el árbol de sintaxis abstracta generador por
;; el parser.
;; interp: FAE -> FAE-Value
(define (interp exp env)
   (match exp
      ; Números
      [(num n) (numV n)]
      ; Operaciones binarias
      [(binop f l r) (binopf f (interp l env) (interp r env))]
      ; Identificadores, este es el caso base más importante al aplicar 
      ; funciones pues es el encargado de buscar el valor de los identificadores
      ; en el ambiente
      [(id v) (strict (lookup v env))]
      [(if0 expr then-expr else-expr)
         (if (num-zero? (interp expr env))
            (interp then-expr env)
            (interp else-expr env))]
      ; Representamos a una función mediante un closure
      [(fun bound-id bound-body)
         (closureV bound-id bound-body env)]
      ; Para interpretar la aplicación de funciones, primero forzamos la 
      ; interpretación de la función. Esto nos regresa un closure. La 
      ; interpretación de la aplicación resulta de interpretar cuerpo de la 
      ; función con un ambiente formado por el parámetro formal con el valor del 
      ; parámetro real postergado. Recursivamente se
      ; llegará en algún momento a (id v) y se podrá evaluar el cuerpo de la 
      ; función.
      [(app fun-expr arg-expr)
         (let ([fun-val (interp (strict fun-expr) env)]) ; calculamos el closure -> Hay que forzar esto
            ;; Interpretamos el cuerpo de la función
            (interp (closureV-body fun-val) ; obtenemos el cuerpo del closure
                    ;; Ambiente de la función
                    (aSub (closureV-param fun-val) ; obtenemos el parámetro
                          (exprV arg-expr env) ; interpretamos los argumentos -> Hay que postergar esto
                          (closureV-env fun-val))))]))

;; Función encargada de ejecutar el interprete para que el usuario interactúe
;; con el lenguaje. Para diferenciar el prompt de Racket del nuestro, usamos
;; "(λ)". Aprovechamos los aspectos imperativos del lenguaje.
(define (ejecuta)
   (begin
      (display "(λ) ")  ; imprimimos el prompt
      (define x (read)) ; pedimos la expresión
      (if (equal? x '{exit}) ; si nos piden salir del intérprete
         (display "") ; no hacemos nada
         (begin ; en otro caso realizamos la interpretación
            ; interpretamos el árbol de sintaxis abstracta
            (let ([result (interp (desugar (parse x)) (mtSub))])
               (if (numV? result)
                  ; si es un número sólo devolvemos el valor que almacena
                  (display (numV-n result))
                  ; si es una función mostramos un mensaje
                  (display "#<function>")))
            (display "\n") ; da un salto de línea
            (ejecuta))))) ; repite el procesos hasta que se lea {exit}.

;; Llamada a función encargada de iniciar la ejecución del interprete
(display "Bienvenido a FWAE v1.0.\n") ; bienvenida al usuario
(ejecuta) ; llama a ejecución al intérprete
