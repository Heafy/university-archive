#lang plai

;; Tipo de dato abstracto FWAE/L para representar el árbol de sintaxis abstracta.
;; El lenguaje FWAE/L reconoce expresiones numéricas, operaciones binarias,
;; asignaciones locales, identificadores, funciones anónimas (lambdas), 
;; aplicación de funciones y un condicional if0 que evalúa si una expresión es
;; cero.
(define-type FWAE/L
   [numS (n number?)]
   [binopS (f procedure?) (l FWAE/L?) (r FWAE/L?)]
   [withS (name symbol?) (named-expr FWAE/L?) (body FWAE/L?)]
   [idS (name symbol?)]
   [if0S (expr FWAE/L?) (then-expr FWAE/L?) (else-expr FWAE/L?)]
   [funS (param symbol?) (body FWAE/L?)]
   [appS (fun-expr FWAE/L?) (arg-expr FWAE/L?)])

;; Tipo de dato abstracto FAE/L para representar el árbol de sintaxis abstracta.
;; El lenguaje FAE/L reconoce expresiones numéricas, operaciones binarias,
;; identificadores, funciones anónimas (lambdas), aplicación de funciones y un
;; condición if0 que evalúa si una expresión es 0.
;; Es la versión sin azúcar sintáctica de FWAE/L.
(define-type FAE/L
   [num (n number?)]
   [binop (f procedure?) (l FAE/L?) (r FAE/L?)]
   [id (name symbol?)]
   [if0 (expr FAE/L?) (then-expr FAE/L?) (else-expr FAE/L?)]
   [fun (param symbol?) (body FAE/L?)]
   [app (fun-expr FAE/L?) (arg-expr FAE/L?)])

;; Tipo de dato abstracto para representar el ambiente.
;; Un ambiente guarda el resto del ambiente y los identificadores con su valor, 
;; el identificador se representa con un símbolo y el valor como una expresión 
;; FAE/L-Value.
(define-type Env
   [mtSub]
   [aSub (name symbol?) (value FAE/L-Value?) (env Env?)])

;; Tipo de dato abstracto para representar los resultados devueltos por el 
;; intérprete. El intérprete devuelve números y funciones; agregamos el tipo
;; exprV para postergar la evaluación. Para representar las funciones usamos 
;; cerraduras (closures). Una cerradura agrupa los datos necesarios para poder 
;; aplicar una función: parámetro, cuerpo y el ambiente donde evaluar.
(define-type FAE/L-Value
   [numV (n number?)]
   [closureV (param symbol?) (body FAE/L?) (env Env?)]
   [exprV (expr FAE/L?) (env Env?)])
