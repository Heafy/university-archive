module Cracks_Sesion3 where

-- NAT ------------------------------------------------------------------------

-- Definicion del tipo Nat
-- Un Nat es el Cero o es un Sucesor de un Nat
data Nat = Cero | S Nat 
instance Show Nat where
    show n = show $ toInt n

-- Funcion que recibe un Nat y le da 
-- una representacion en un entero
toInt :: Nat -> Int
toInt Cero = 0
toInt (S n) = 1 + toInt n

-- Función suma que toma dos números naturales
-- y regresa su suma
suma::Nat->Nat->Nat
suma Cero n = n
suma n Cero = n
suma (S m) n = S (suma n m)

-- Función producto que toma dos números naturales
-- y regresa su producto
producto::Nat->Nat->Nat
producto Cero n = Cero
producto (S n) m =  suma (producto n m) m

-- Función potencia que toma dos números naturales
-- y regresa su potencia.
potencia:: Nat -> Nat -> Nat
potencia n Cero = S Cero
potencia n (S Cero) = n
potencia n (S k) = producto n (potencia n k) 

-- LISTAS ---------------------------------------------------------------------

-- Definicion del tipo Lista
-- Una lista vacia es una lista
-- Una elemento concatenado con una lista es una lista
data Lista a = Vacia | Cons a (Lista a)
instance Show a => Show (Lista a) where
    show l = imprime l

-- Funcion para imprimir las listas
imprime :: Show a => Lista a-> String
imprime Vacia = "[]"
imprime l = imprimeAux "[" l

imprimeAux :: Show a => String -> Lista a -> String
imprimeAux s Vacia = s ++ "]"
imprimeAux s (Cons x xs)
    | vacia xs = imprimeAux (s ++ (show x)) xs
    | otherwise = imprimeAux (s ++ (show x) ++ ",") xs

vacia :: Lista a -> Bool
vacia Vacia = True
vacia _ = False    
  
-- Funcion concatena que recibe dos listas y regresa
-- la concatenacion de ambas listas  
concatena :: Lista a -> Lista a -> Lista a
concatena Vacia s = s
concatena s Vacia = s
concatena (Cons x xs) s = Cons x (concatena xs s)

-- Funcion reversa que toma una Lista y regresa
-- la reversa de esa lista
reversa :: Lista a -> Lista a
reversa Vacia = Vacia
reversa (Cons x xs) = concatena (reversa (xs)) (Cons x Vacia)

-- Funcion mapea que toma una Lista y una funcion y regresa
-- una lista cuyos elementos son el resultado de aplicar 
-- la funcion a cada elemento de la original
mapea :: Lista a -> (a -> b) -> Lista b
mapea Vacia f = Vacia
mapea (Cons x xs) f =  Cons (f x) (mapea xs f)
 

-- FIGURAS --------------------------------------------------------------------

data Figura = Triangulo Double Double Double | Cuadrado Double | Circulo Double deriving Show

area :: Figura -> Double
area fig = case fig of
           Triangulo l1 l2 l3 -> (l1*l2)/2
           Cuadrado l -> l*l
           Circulo d -> pi*(d/2)*(d/2)

perimetro :: Figura -> Double
perimetro fig = case fig of
           Triangulo l1 l2 l3 -> l1+l2+l3
           Cuadrado l -> l*4
           Circulo d -> 2*pi*(d/2)
