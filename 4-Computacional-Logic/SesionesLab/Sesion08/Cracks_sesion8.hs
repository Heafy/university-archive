module Cracks_Sesion8 where

-- Sinónimo para representar los nombres de variables y funciones.
type Nombre = String

-- Sinónimo para representar sustituciones.
type Sust = [(Nombre,Termino)]

-- Gramática para representar términos.
data Termino = V Nombre
             | F Nombre [Termino] deriving (Eq)

-- Gramática para representar fórmulas de la Lógica de Primer Orden.
data FORM = TrueF
             | FalseF
             | Pr Nombre [Termino]
             | Eq Termino Termino
             | Neg FORM
             | Conj FORM FORM
             | Disy FORM FORM
             | Impl FORM FORM
             | Equi FORM FORM
             | PT Nombre FORM
             | EX Nombre FORM deriving(Eq)

instance Show Termino where
    show (V a) = show a
    show (F n a) = show n ++ "(" ++ show a ++ ")"

instance Show FORM where
    show TrueF = "⊤"
    show FalseF = "⊥"
    show (Pr n l) =  show n ++ "(" ++ show l ++")"
    show (Eq t1 t2) = show t1 ++ "∼" ++ show t2
    show (Neg f) = "¬(" ++ show f ++ ")"
    show (Conj p q) = show p ++ " ∧ " ++ show q
    show (Disy p q) = show p ++ " ∨ " ++ show q
    show (Impl p q) = show p ++ " => " ++ show q
    show (Equi p q) = show p ++ " <=> " ++ show q
    show (PT n f) = "∀" ++ show n ++ "(" ++ show f ++ ")"
    show (EX n f) = "∃" ++ show n ++ "(" ++ show f ++ ")"

-- Devuelve la lista con todos los nombres de variables que figuran en t
varT:: Termino -> [Nombre]
varT (V n) = [n]
varT (F n []) = []
varT (F n ls) = varTAux ls

-- Funcion auxiliar para varT
varTAux:: [Termino] -> [Nombre]
varTAux [] = []
varTAux ((V n):xs) = [n]
varTAux ((F n []:xs)) = []
varTAux ((F n ls):xs) = varTAux ls ++ varTAux xs

-- Función que aplica sustitución sobre un término
apsubsT:: Termino -> Sust -> Termino
apsubsT (V n) ((x, t): xs)
    | n == x = t
    | otherwise = (V n)
apsubsT (F n []) ((x,t):xs) = (F n [])
apsubsT (F n l) s = (F n (map (\x -> apsubsT x s)l)) 

-- Función que aplica sustitución sobre una fórmula
apsubsF:: FORM -> Sust -> FORM
apsubsF TrueF _ = TrueF
apsubsF FalseF _ = FalseF
apsubsF (Pr p ts) sust = Pr p [apsubsT t sust | t<-ts]
apsubsF (Eq t1 t2) sust = Eq (apsubsT t1 sust) (apsubsT t2 sust)
apsubsF (Neg t) sust = Neg (apsubsF t sust)
apsubsF (Conj t1 t2) sust = Conj (apsubsF t1 sust) (apsubsF t2 sust)
apsubsF (Disy t1 t2) sust = Disy (apsubsF t1 sust) (apsubsF t2 sust)
apsubsF (Impl t1 t2) sust = Impl (apsubsF t1 sust) (apsubsF t2 sust)
apsubsF (Equi t1 t2) sust = Equi (apsubsF t1 sust) (apsubsF t2 sust)
apsubsF (PT n f) s
  | not (elem n (nombreSust s) && elem n (termSust s) ) = PT n (apsubsF f s)
  | otherwise = (PT n f)
apsubsF (EX n f) s
  | not (elem n (nombreSust s) && elem n (termSust s) ) = EX n (apsubsF f s)
  | otherwise = (EX n f)

nombreSust :: Sust -> [Nombre]
nombreSust [] = []
nombreSust ((n,t): xs) = n:(nombreSust xs)

termSust :: Sust -> [Nombre]
termSust [] = []
termSust ((n,(V t)): xs) = t:(termSust xs)

{-
*** EJEMPLOS ***
∀"x1"("P"(["h"(["x1","x2","x3"])]) <=> "Q"(["g"(["y1","y2","y3"])]))
apsubsF (EX "x1" (Equi (Pr "P" [F "h" [V "x1", V "x2", V "x3"]]) (Pr "Q" [F "g" [V "y1", V "y2", V "y3"]]))) ([("x2",V "z")])
-}
