module Cracks_Sesion7 where

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

-- TERMINOS --

-- Devuelve la lista con todos los nombres de constante que figuran en t
consT:: Termino ->[Nombre]
consT (V n) = []
consT (F n []) = [n]
consT (F n l)= consTAux l  

-- Funcion auxiliar para consT
consTAux::[Termino] -> [Nombre]
consTAux [] = []
consTAux ((V _):xs) = consTAux xs
consTAux ((F n []):xs) = [n] ++ consTAux xs
consTAux ((F n ls):xs) = consTAux ls ++ consTAux xs 

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

-- Devuelve la lista con todos los nombres de funcion que figuran en t
funT:: Termino ->[Nombre]
funT (V n) = []
funT (F n []) = []
funT (F n ls) = [n] ++ funTAux ls 

-- Funcion auxiliar para funT
funTAux:: [Termino] -> [Nombre]
funTAux [] = []
funTAux ((V n):xs) = funTAux xs
funTAux ((F n []):xs) = funTAux xs
funTAux ((F n l):xs) = [n]++funTAux l ++ funTAux xs

-- FORMULAS --

-- Devuelve la lista con todos los nombres de constante que figuran en f
consF:: FORM ->[Nombre]
consF TrueF = []
consF FalseF = []
consF (Pr n l) = consTAux l
consF (Eq t1 t2) = consT t1 ++ consT t2
consF (Neg n) = consF n
consF (Conj p q) = consF p ++ consF q
consF (Disy p q) = consF p ++ consF q
consF (Impl p q) =  consF p ++ consF q
consF (Equi p q) = consF p ++ consF q
consF (PT _ f) = consF f
consF (EX _ f) = consF f

-- Devuelve la lista con todos los nombres de variables que figuran en f
varF:: FORM -> [Nombre]
varF TrueF = []
varF FalseF = []
varF (Pr n l) = varTAux l
varF (Eq t1 t2) = varT t1 ++ varT t2
varF (Neg n) = varF n
varF (Conj p q) = varF p ++ varF q
varF (Disy p q) = varF p ++ varF q
varF (Impl p q) =  varF p ++ varF q
varF (Equi p q) = varF p ++ varF q
varF (PT _ f) = varF f
varF (EX _ f) = varF f

-- Devuelve la lista con todos los nombres de funcion que figuran en f
funF:: FORM ->[Nombre]
funF f = case f of
         TrueF -> []
         FalseF -> []
         Pr _ ts -> concat [ funT t | t<-ts]
         Eq t1 t2 -> funT t1 ++ funT t2
         Neg f1 -> funF f1
         Conj f1 f2 -> funF f1 ++ funF f2
         Disy f1 f2 -> funF f1 ++ funF f2
         Impl f1 f2 -> funF f1 ++ funF f2
         Equi f1 f2 -> funF f1 ++ funF f2
         PT _ f -> funF f
         EX _ f -> funF f

-- Devuelve la lista de subterminos
subT:: Termino -> [Termino]
subT (V n) = [(V n)]
subT (F n []) = [(F n [])]
subT (F n l) = union [(F n l)] (subTAux l)

-- Funcion auxiliar para subT
subTAux:: [Termino] -> [Termino]
subTAux [] = []
subTAux ((V n):xs) = [(V n)] ++ subTAux xs
subTAux ((F n []):xs) = [(F n [])] ++ subTAux xs
subTAux ((F n l):xs) = (union [(F n l)] (subTAux l)) ++ (subTAux xs)

union:: Eq a => [a] -> [a] -> [a]
union [] b = b
union (x:xs) b
    | elem x b = union xs b
    | otherwise = x:(union xs b)

-- Devuelve la lista de subformulas
subF:: FORM -> [FORM]
subF TrueF = []
subF FalseF = []
subF (Pr n []) = []
subF (Pr n ts) = [(Pr n ts)]
subF (Eq t1 t2) = [(Eq t1 t2)]
subF (Neg n) = subF n
subF (Conj p q) = subF p ++ subF q 
subF (Disy p q) = subF p ++ subF q
subF (Impl p q) = subF p ++ subF q
subF (Equi p q) = subF p ++ subF q
subF (PT _ f) = subF f
subF (EX _ f) = subF f

subFAux::[Termino] -> [Nombre]
subFAux [] = []
subFAux ((V _):xs) = subFAux xs



esAtomica::FORM ->Bool
esAtomica subF (Pr n ts) = True
esAtomica  (Eq t1 t2) = True
esAtomica _ = False

-- Devuelve la lista de cuantificaciones de A, es decir, la lista de subfórmulas de A que son cuantificaciones
cuantF:: FORM -> [FORM]
cuantF f = [x | x<-sub f , esCuantificador x]

esCuantificador::FORM -> Bool
esCuantificador (PT _ _) = True
esCuantificador (EX _ _) = True
esCuantificador _ = False

-- Devuelve las variables libres 
fv:: FORM -> [Nombre]
fv v = case v of
       TrueF -> []
       FalseF -> []
       Pr _ ts -> concat [ varT t | t<-ts]
       Eq t1 t2 -> varT t1 ++ varT t2
       Neg f -> fv f
       Conj f1 f2 -> fv f1 ++ fv f2
       Disy f1 f2 -> fv f1 ++ fv f2
       Impl f1 f2 -> fv f1 ++ fv f2
       Equi f1 f2 -> fv f1 ++ fv f2
       PT x f -> filter (x/=) $ fv f
       EX x f -> filter (x/=) $ fv f

-- Devuelve las variables ligadas
bv:: FORM -> [Nombre]
bv v = case v of
       TrueF -> []
       FalseF -> []
       Pr _ ts -> []
       Eq t1 t2 -> []
       Neg f -> bv f
       Conj f1 f2 -> bv f1 ++ bv f2
       Disy f1 f2 -> bv f1 ++ bv f2
       Impl f1 f2 -> bv f1 ++ bv f2
       Equi f1 f2 -> bv f1 ++ bv f2
       PT x f -> x:(bv f)
       EX x f -> x:(bv f)
