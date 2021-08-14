module Cracks_Sesion4 where

import Data.List

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

-- Funcion que toma dos booleanos y regresa su conjuncion
conjuncion:: Booleano -> Booleano -> Booleano
conjuncion V V = V
conjuncion V F = F
conjuncion F V = F
conjuncion F F = F

-- Funcion que toma dos booleanos y regresa su implicacion
implicacion:: Booleano -> Booleano -> Booleano
implicacion V V = V
implicacion V F = F
implicacion F _ = V

-- Funcion que toma dos booleanos y regresa su equivalencia
equivalencia:: Booleano -> Booleano -> Booleano
equivalencia V V = V
equivalencia V F = F
equivalencia F V = F
equivalencia F F = V

-- Funcion que toma una formula proposicional y 
-- regresa su numero de conectivos
con:: Prop -> Int 
con (FA x) = 0
con (Neg(x)) = con x
con (Op (p1) Conj (p2))= 1 + con p1 + con p2
con (Op (p1) Disy (p2))= 1 + con p1 + con p2
con (Op (p1) Impl (p2))= 1 + con p1 + con p2
con (Op (p1) Syss (p2))= 1 + con p1 + con p2

-- Funcion que toma una formula proposicional y regresa
-- una lista con las variables que figuran en ella
vars:: Prop -> [Prop]
vars (FA (Cte _)) = []
vars (FA (Var x)) = [(FA (Var x))]
vars (Neg (x)) = vars x
vars (Op a Conj b) = nub(vars a ++ vars b)
vars (Op a Disy b) = nub(vars a ++ vars b)
vars (Op a Impl b) = nub(vars a ++ vars b)
vars (Op a Syss b) = nub(vars a ++ vars b)

-- Funcion que toma una formula proposicional y regresa el numero
-- de presencias de formulas atómicas en ella.
atom:: Prop -> Int
atom (FA x) = 1
atom (Neg(x)) = atom (x)
atom (Op a Conj b) = atom(a) + atom(b)
atom (Op a Disy b) = atom(a) + atom(b)
atom (Op a Impl b) = atom(a) + atom(b)
atom (Op a Syss b) = atom(a) + atom(b)