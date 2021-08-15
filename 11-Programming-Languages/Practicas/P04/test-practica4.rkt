#lang plai

(require "grammars.rkt")
(require "parser.rkt")
(require "interp.rkt")

(print-only-errors)

#| Módulo para pruebas unitarias de la práctica 4 |#

;; Pruebas para  parse

(test (parse '{with {{x 1}} {% x 4}})
      (withS
       (list (binding 'x (numS 1)))
       (opS modulo (list (idS 'x) (numS 4)))))

(test (parse '{with* {{x 1} {y 1} {z 2} {v 2}}{- x y z v}})
      (withS*
       (list
        (binding 'x (numS 1))
        (binding 'y (numS 1))
        (binding 'z (numS 2))
        (binding 'v (numS 2)))
       (opS - (list (idS 'x) (idS 'y) (idS 'z) (idS 'v)))))

(test (parse '{whit* {{x 3} {y 5}} {+ x 4}})
      (appS (idS 'whit*)
            (list
             (appS
              (appS (idS 'x) (list (numS 3)))
              (list (appS (idS 'y) (list (numS 5)))))
             (opS + (list (idS 'x) (numS 4))))))

(test (parse '{fun {x} {+ {+ x x} {/ 2 9}}})
      (funS  '(x)
             (opS +
                  (list
                   (opS + (list (idS 'x) (idS 'x)))
                   (opS / (list (numS 2) (numS 9)))))) )

(test (parse '{fun {x y} {with {{v 2}} {% x y z}}})
      (funS '(x y)
            (withS
             (list (binding 'v (numS 2)))
             (opS modulo (list (idS 'x) (idS 'y) (idS 'z))))))


;; Pruebas para  desugar
(test (desugar (parse '{with {{x 1}} {% x 4}}))
      (app
       (fun '(x) (op modulo (list (id 'x) (num 4))))
       (list (num 1))))

(test (desugar (parse '{with* {{x 1} {y 1} {z 2} {v 2}}{- x y z v}}))
      (app(fun '(x)
               (app(fun '(y)
                        (app(fun '(z)
                                 (app(fun '(v)
                                          (op - (list (id 'x) (id 'y) (id 'z) (id 'v))))
                                     (list (num 2))))
                            (list (num 2))))
                   (list (num 1))))
          (list (num 1))))

(test (desugar (parse '{whit* {{x 3} {y 5}} {+ x 2}}))
      (app (id 'whit*)
           (list
            (app
             (app (id 'x) (list (num 3)))
             (list (app (id 'y) (list (num 5)))))
            (op + (list (id 'x) (num 2))))))

(test (desugar (parse '{fun {x} {+ {+ x x} {/ 2 9}}}))
      (fun '(x)(op +(list
                     (op + (list (id 'x) (id 'x)))
                     (op / (list (num 2) (num 9)))))))

(test (desugar (parse '{fun {x y} {with {{v 2}} {% x y z}}}))
      (fun '(x y)
           (app
            (fun '(v) (op modulo (list (id 'x) (id 'y) (id 'z))))
            (list (num 2)))))

;; Pruebas para  interp

(test (interp (desugar (parse '{with {{x 1}} {% x 4}})) (mtSub))
      (numV 1))

(test (interp (desugar (parse '{with* {{x 1} {y 1} {z 2} {v 2}}{- x y z v}})) (mtSub))
      (numV -4))

(test (interp (desugar (parse '{with* {{x 3} {y 5}} {+ x 2}})) (mtSub))
      (numV 5))

(test (interp  (desugar (parse '{fun {x} {+ {+ x x} {/ 2 9}}})) (mtSub))
      (closureV '(x) (op +
                         (list
                          (op + (list (id 'x) (id 'x)))
                          (op / (list (num 2) (num 9)))))
                (mtSub)))

(test (interp (desugar (parse '{fun {x y} {with {{v 2}} {% x y z}}})) (mtSub))
      (closureV '(x y)
                (app
                 (fun '(v) (op modulo (list (id 'x) (id 'y) (id 'z))))
                 (list (num 2)))
                (mtSub))
      )