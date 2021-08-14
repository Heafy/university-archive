module Practica1 where

-- Función que encuentra la derivada f'(v) de la ecuación f(x) = ax²+bx+c. El 
-- primer argumento de la función corresponderá al valor de a, el segundo al 
-- valor de b, el tercero al valor de c y el cuarto al valor de v que es el 
-- valor de evaluación.
deriva :: Int -> Int -> Int -> Int -> Int
deriva a b c v = 2*a*v + b

-- Función para calcular el área de un cilindro dada la altura y el diámetro
-- como primer y segundo parámetro respectivamente.
areaCilindro :: Float -> Float -> Float
areaCilindro d h = 2*pi*(r)*(r+h)
    where r = (d/2)    

-- Función para calcular el volumen de un cilindro dada la altura y el diámetro
-- como primer y segundo parámetro respectivamente.
volumenCilindro :: Float -> Float -> Float
volumenCilindro d h = pi*(r^2)*h
    where r = (d/2)

-- Función que recibe tres parámetros, el primero indica la operación que se va
-- a realizar con los otros dos parámetros, las posibles operaciones son:
--
-- 's' = devuelve el segundo parámetro
-- 't' = devuelve el tercer parámetro
-- 'a' = suma
-- 'r' = resta
-- 'p' = multiplicación
-- 'd' = división entera
-- 'e' = potencia (el segundo parámetro elevado al tercero)
aplicaOperacion :: Char -> Int -> Int -> Int
aplicaOperacion op l r
    | op == 's' = l
    | op == 't' = r
    | op == 'a' = l +r
    | op == 'r' = l -r
    | op == 'p' = l*r
    | op == 'd' = div l r
    | op == 'e' = l^r
    | otherwise = error "Error: Caracter invalido"

-- Función recursiva que calcula una aproximación con un número entero a la raíz
-- cuadrada.
raizEntera :: Int -> Int
raizEntera 0 = 0
raizEntera 1 = 1
raizEntera n = raizAux n where 
    raizAux  i 
        | i*i > n   = raizAux (i-1) 
        | i*i <= n  = i

-- Función recursiva que devuelve la suma de los primeros n números naturales.
sumaNat :: Int -> Int
sumaNat 0 = 0
sumaNat 1 =  1
sumaNat n = n + sumaNat(n-1)

-- Función recursiva que devuelve la longitud de un número entero.
longitud :: Int -> Int
longitud 0 = 0  
longitud n = 1 + longitud(div n 10)

-- Función que regresa una lista con los n primeros números de tribonacci 
-- iniciando con 0, 0, 1.
tribonaccies :: Int -> [Int]
tribonaccies 0 = [0]
tribonaccies x = map tribonacciesAux [1..x]

-- Funcion auxiliar que regresa el numero n de tribonacci
tribonacciesAux :: Int -> Int
tribonacciesAux 1 = 0
tribonacciesAux 2 = 0
tribonacciesAux 3 = 1
tribonacciesAux x = (tribonacciesAux (x-1) + tribonacciesAux (x-2) + tribonacciesAux (x-3))

-- Función que dada una lista elimina los elementos duplicados adyacentes de una
-- lista dejando únicamente una aparición de cada elemento. La implementación de
-- esta función usa foldr.
elimDup :: Eq a => [a] -> [a]
elimDup [] = []
elimDup [x] = [x]
elimDup (x:xs)= if (x == head xs) 
                then elimDup(xs) 
                else x:elimDup(xs)

-- Función que dada una función de comparación y una lista como parámetros,
-- devuelve el elemento maximal de la lista para esa función de comparación. La
-- implementación de esta función usa foldl.
maximal :: (a -> a -> a) -> [a] -> a
maximal f (x:xs) = foldl f x xs

-- Función que regresa la reversa de una lista.
reversa :: [a] -> [a]
reversa [] = []
reversa (l:ls) = reversa ls ++ [l]

-- Función que devuelve una lista con los elementos que cumplen con el predicado
-- recibido como parámetro
filtra :: (a -> Bool) -> [a] -> [a]
filtra p [] = []
filtra p (x:xs)
    | p x = x:filtra p xs
    | otherwise = filtra p xs

-- Función que toma una lista como parámetro y regresa otra lista con los 
-- elementos que aparecen una única vez en la original.
unicaVez :: Eq a => [a] -> [a]
unicaVez [] = []
unicaVez (x:xs)= if (elem x xs)
                 then unicaVez(elimina x (x:xs))
                 else x:unicaVez xs

-- Función que elimina en una lista el elemento recibidio como parámetro
elimina :: Eq a=> a -> [a] -> [a]
elimina n [] = []
elimina n (x:xs) =  filter (\x -> n/=x) (x:xs)

-- Función que recibe una lista y regresa una lista de pares (k, x), donde k es
-- el número de apariciones consecutivas de x en la lista recibida.
apariciones :: Eq a => [a] -> [(Int, a)]
apariciones [] = []
apariciones [x] = [(1,x)]
apariciones (x:xs)
    | x/=head xs = (1,x):apariciones(xs)
    | otherwise =(n +1,x): apariciones(drop n xs )
    where n = fst(head(apariciones(xs)))

-- Función que dada una lista de la forma [a0,a1,a2, ... , am,an,ao,ap] devuelve
-- una lista de pares cuyos elementos son (a0,ap) (a1,ao) (a2 an). Se debe 
-- asegurar que la lista recibida siemre sea de longitud par.
empareja :: Eq a=>[a] -> [(a,a)]
empareja [] = []
empareja (x:xs) = if (mod (length(x:xs)) 2 == 0) 
                    then [(x, (last xs) )] ++ empareja (init xs)
                    else error "Error: La longitud de la lista es impar"

-- AGREGA AQUÍ LA DEFINICIÓN DE LAS LISTAS POR COMPRENSIÓN

lista1 :: [Float]
lista1 =  [(1/2*(-2+2^x))| x<-[1..7]]

-- La lista recibe un número como parámetro un Int 'n'.
-- Devuelve una lista con las coordenadas posibles hasta 'n'.
lista2 :: Int -> [(Int, Int)]
lista2 n = [(x,x+1) | x <- [3,7..n]]
