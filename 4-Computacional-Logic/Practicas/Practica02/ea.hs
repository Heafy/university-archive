module EAP2 where

import Data.Maybe

-- Gramática para representar a los números naturales
data Nat = Cero
         | Suc Nat deriving(Show, Eq)

-- Funcion que recibe un Nat y le da 
-- una representacion en un entero
toInt :: Nat -> Int
toInt Cero = 0
toInt (Suc n) = 1 + toInt n

-- Gramática para representar símbolos de variables
data Id = A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z deriving(Show,Eq)

-- Gramática para representar a las expresiones aritméticas
data EA = Var Id
        | Cte Nat
        | Sum EA EA
        | Res EA EA
        | Mul EA EA
        | Div EA EA
        | Paren EA --deriving(Show, Eq)

-- Sinónimo para representar al ambiente de evaluación
type Env = [(Id, Int)]

-- Hace parte de la familia Show al tipo Booleano.
instance Show EA where
   show exp = showEA exp

-- Función que dada una expresión aritmética, devuelve su representación como
-- cadena.
showEA :: EA -> String
showEA s = case s of--error "Función no implementada"
    Var id -> show id
    Cte nat -> show (toInt(nat))
    Sum a b -> show a ++ " + " ++ show b
    Res a b -> show a ++ " - " ++ show b
    Mul a b -> show a ++ " * " ++ show b
    Div a b -> show a ++ " / " ++ show b
    Paren a -> "(" ++ show a ++ ")"

-- Función que dada una expresión y un ambiente de evaluación, devuelve el 
-- resultado de evaluar dicha expresión como el entero que la representa.
evalua :: EA -> Env -> Int
evalua ea [] = error "No se encontro la variable en el ambiente"
evalua (Var v) env = if(isVar v env) 
                        then seaVar v env
                        else error "No se encontro la variable en el ambiente"
evalua (Cte c) env = (toInt(c))
evalua (Sum ea1 ea2) env = (evalua ea1 env) + (evalua ea2 env)
evalua (Res ea1 ea2) env = (evalua ea1 env) - (evalua ea2 env)
evalua (Mul ea1 ea2) env = (evalua ea1 env) * (evalua ea2 env)
evalua (Div ea1 ea2) env = div (evalua ea1 env) (evalua ea2 env)
evalua (Paren ea) env = evalua ea env

-- Auxiliar. Función que dada un identificador de variable 
-- y un ambiente a evaluar
-- Da como resultado el valor de la variable en el ambiente.
seaVar :: Id -> Env -> Int
seaVar id [] = 0
seaVar id (x:xs) = if( id == fst(x))
                    then snd(x) 
                    else seaVar id (xs)

--Auxiliar. Función que dada un identificador de variable 
-- y un ambiente a evaluar
-- Da como resultado si la variable está o no en el ambiente.
isVar :: Id -> Env -> Bool
isVar id [] = False
isVar id (x:xs) = if (id == fst(x))
                    then True 
                    else isVar id (xs)