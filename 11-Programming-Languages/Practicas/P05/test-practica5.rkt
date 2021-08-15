#lang plai

(require "grammars.rkt")
(require "parser.rkt")
(require "interp.rkt")

(print-only-errors)

#| Módulo para pruebas unitarias de la práctica 5 |#

;; Pruebas para  parse

(test (parse '{if {< 2 3} 4 5})
      (ifS (opS < (list (numS 2) (numS 3))) (numS 4) (numS 5)))

(test (parse '{with* {{x 1} {y 1} {z 2} {v 2}}{- x y z v}})
      (withS*(list (binding 'x (numS 1)) (binding 'y (numS 1)) (binding 'z (numS 2)) (binding 'v (numS 2)))
             (opS - (list (idS 'x) (idS 'y) (idS 'z) (idS 'v)))))

(test (parse '{cond {{< 2 3} 1} {{> 10 2} 2} {else 3}})
      (condS(list
             (condition (opS < (list (numS 2) (numS 3))) (numS 1))
             (condition (opS > (list (numS 10) (numS 2))) (numS 2))
             (else-cond (numS 3)))))

(test (parse '{{ fun { x } {{ fun { y } y } {pow x y }}} 10})
      (appS (funS '(x)
                  (appS (funS '(y) (idS 'y)) (list (opS potencia (list (idS 'x) (idS 'y))))))
            (list (numS 10))))

(test (parse '{fun {x y} {with {{v 2}} {% x y z}}})
      (funS '(x y)
            (withS (list (binding 'v (numS 2)))
                   (opS modulo (list (idS 'x) (idS 'y) (idS 'z))))))

;; Pruebas para desugar

(test (desugar (parse '{cond {{< (+ 2 2) 3} 1} {{> 10 (% 2014 2)} 2} {else 3}}))
      (iF (op < (list (op + (list (num 2) (num 2))) (num 3)))
          (num 1)
          (iF (op > (list (num 10) (op modulo (list (num 2014) (num 2)))))
              (num 2)
              (num 3))))

(test (desugar (parse '{fun {x y} {with {{v 2}} {% x y z}}}))
      (fun '(x y)
           (app (fun '(v) (op modulo (list (id 'x) (id 'y) (id 'z)))) (list (num 2)))))

(test (desugar (parse '{with* {{x 1} {y 1} {z 2} {v 2}}{- x y z v}}))
      (app (fun '(x)
                (app (fun '(y)
                          (app (fun '(z)
                                    (app (fun '(v)
                                              (op - (list (id 'x) (id 'y) (id 'z) (id 'v))))
                                         (list (num 2))))
                               (list (num 2))))
                     (list (num 1))))
           (list (num 1))))

(test (desugar (parse '{with* {{x 1} {y 1} {z 2} }{/ x y (* 2 z)}}))
      (app (fun '(x)
                (app (fun '(y)
                          (app (fun '(z)
                                    (op / (list (id 'x) (id 'y) (op * (list (num 2) (id 'z))))))
                               (list (num 2))))
                     (list (num 1))))
           (list (num 1))))

(test (desugar (parse '{fun {x} {+ {+ x x} {/ 2 9}}}))
      (fun '(x) (op +
                    (list (op + (list (id 'x) (id 'x)))
                          (op / (list (num 2) (num 9)))))))

;; Pruebas para interp

(test (interp (desugar (parse '{with {{a 1729} {b 9271}} {* a b}})) (mtSub))
      (numV 16029559))

(test (interp (desugar (parse '{with* {{a 10} {b {/ a a}}} b})) (mtSub))
      (exprV (op / (list (id 'a) (id 'a))) (aSub 'a (exprV (num 10) (mtSub)) (mtSub))))

(test (interp (desugar (parse '{fun {x} {pow x 256}})) (mtSub))
      (closureV '(x) (op potencia (list (id 'x) (num 256))) (mtSub)))

(test (interp (desugar (parse '{if {and (> 2 3) (< 1729 (pow 2 25))} 1729 9721})) (mtSub))
      (numV 9721))

(test (interp (desugar (parse '{fun {x y} {with {{v 2}} {% x y z}}})) (mtSub))
      (closureV '(x y) (app (fun '(v) (op modulo (list (id 'x) (id 'y) (id 'z)))) (list (num 2))) (mtSub)))

