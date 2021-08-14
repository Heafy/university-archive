module Cracks_sesion5 where

-- Sinónimo para representar estados
type Estado = (VarP, Booleano)

-- Gramática para contantes lógicas
data Booleano = V | F deriving (Show,Eq)

-- Gramática para variables proposicionales
data VarP = A|B|C|D|E|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|W|X|Y|Z deriving (Show, Eq)

-- Gramática para representar átomos
data Atomo = Var VarP | Cte Booleano deriving (Eq)

-- Gramática para representar a los operadores binarios.
data OpBin = Conj | Disy | Impl | Syss deriving(Eq)

-- Gramática para representar expresiones del lenguaje Prop.
data Prop = FA Atomo
   | Neg Prop
   | Op Prop OpBin Prop deriving(Eq)

-- Hace parte de la familia Show al tipo Atomo.
instance Show Atomo where
   show (Var v) = show v
   show (Cte b) = show b

-- Hace parte de la familia Show al tipo OpBin.
instance Show OpBin where
   show (Conj) = " ∧ "
   show (Disy) = " ∨ "
   show (Impl) = " => "
   show (Syss) = " <=> "

-- Hace parte de la familia Show al tipo Prop.
instance Show Prop where
   show (FA a) = show a
   show (Neg p) = "¬(" ++ show p ++ ")"
   show (Op p o q) = "(" ++ show p ++ show o ++ show q ++ ")"

-- Función que dada una fórmula dice si está en Forma Normal Negativa
estaFNN :: Prop -> Bool
estaFNN (FA (Cte x)) = True
estaFNN (FA (Var x)) = True
estaFNN (Neg (FA a)) = True
estaFNN (Op p Impl q) = False
estaFNN (Op p Syss q) = False
estaFNN (Op p Conj q) = estaFNC (p) && estaFNC (q)
estaFNN (Op p Disy q) = estaFNC (p) && estaFNC (q)

-- Función que dada una fórmula dice si es Cláusula
esClausula :: Prop -> Bool
esClausula (FA (Cte x)) = True
esClausula (FA (Var x)) = True
esClausula (Neg (x)) = esClausula (x)
esClausula (Op a Conj b) = esClausula (a) && esClausula (b)
esClausula (Op a Disy b) = False
esClausula (Op a Impl b) = False
esClausula (Op a Syss b) = False

-- Función que dada una fórmula dice si está en Forma Normal Conjuntiva
estaFNC :: Prop -> Bool
estaFNC (FA (Cte x)) = True
estaFNC (FA (Var x)) = True
estaFNC (Neg (x)) = estaFNC (x)
estaFNC (Op p Conj q) = estaFNC (p) && estaFNC (q)
estaFNC (Op p Disy q) = estaFNC (p) && estaFNC (q)
estaFNC (Op p Impl q) = False
estaFNC (Op p Syss q) = False

--- PARTE DE LITERAL Y CLAUSULA ---

data Literal = LI Atomo
   |NE Atomo deriving (Eq)

data Clausula = Cl Literal
   |Ope Clausula Bin Clausula deriving (Eq)

data Bin = Con
   |Dis deriving(Eq)

instance Show Bin where
   show (Dis) = " v "
   show (Con) = " ∧ "

instance Show Literal where
   show (LI a) = show a
   show (NE a) = "¬("++show a++")"

instance Show Clausula where
   show (Cl (LI l)) = show l
   show (Cl (NE l)) = "¬("++show l++")"
   show (Ope c1 o c2) = show c1 ++ show o ++ show c2

-- Función que dada una literal y la transforma en fórmula proposicional
litToProp :: Literal -> Prop
litToProp (LI l) = (FA l)
litToProp (NE (l)) = (Neg (FA l))

-- Función que dada una cláusula y la transforma en fórmula proposicional
clausulaToProp :: Clausula -> Prop
clausulaToProp (Cl (LI l)) = (FA l)
clausulaToProp (Cl (NE (l))) = Neg(FA l)
clausulaToProp (Ope (c1) Dis (c2)) = (Op (clausulaToProp(c1)) Disy (clausulaToProp(c2)))

-- Función que dada una cláusula regresa su lista de literales
listaLiterales :: Clausula -> [Literal]
listaLiterales (Cl (LI l)) = [(LI l)]
listaLiterales (Cl (NE l)) = [(NE l)]
listaLiterales (Ope (c1) Dis (c2)) = listaLiterales(c1)++listaLiterales(c2)

-- Función que dada una lista de literales devuelve la cláusula que representa su disyunción
disyLista :: [Literal] -> Clausula
disyLista [(LI l)] = Cl(LI l)
disyLista [(NE l)] = Cl(NE l)
disyLista (x:xs) = Ope (Cl(x)) Dis (disyLista(xs)) 

{-
Ejemplos utiles para la practica
--litToProp (LI (Var R))
--litToProp (NE (Var S))
--clausulaToProp (Ope (Cl(LI (Var P))) Dis (Cl(NE (Var Q))))
--clausulaToProp (Ope (Ope (Cl(NE (Var I))) Dis (Cl(LI (Var G)))) Dis (Cl(NE (Var H))))
--listaLiterales (Ope (Ope (Cl(NE (Var M))) Dis (Cl(LI (Var G)))) Dis (Cl(NE (Var H))))
--disyLista [(NE(Var K)),(LI(Cte V)),(NE(Cte F)),(LI (Var J))]
-}