-- Sesion 2 de laboratorio
-- Cracks:
-- Estrada Gómez César Derian
-- García Hernández Aidé Itzel
-- Martínez Flores Jorge Yael
-- Rivera González Damian

module Crack_sesion2 where

-- Funcion recursiva que dado un numero entero regresa una suma de sus digitos
sumaDigitos :: Int -> Int
sumaDigitos 0 = 0
sumaDigitos n = mod n 10 + sumaDigitos (div n 10)

-- Funcion recursiva que dada dos listas regresa su concatenacion
concatena :: [a] -> [a] -> [a]
concatena [] [] = []
concatena [] ys = ys
concatena (x:xs) (ys) = x:(concatena xs ys) 

-- Funcion que dada una lista de numeros regresa una lista cuyos elementos
-- son la suma de sus digitos de cada numero de la original 
aplicaSumaDigitos :: [Int] -> [Int]
aplicaSumaDigitos x = map sumaDigitos x

-- Funcion que dada una lista de numeros regresa una lista cuyos elementos
-- son los multiplos de 5 de la original. 
multiplos5 :: [Int] -> [Int]
multiplos5 (xs) = filter(\x -> mod x 5 == 0) xs

-- Funcion que dada una lista de booleanos, aplica la disyuncion encadenada
-- a todos los elementos
disyuncion :: [Bool] -> Bool
disyuncion (x:xs) = foldr (||) (x) (xs)

-- Funcion que dada una lista de booleanos, aplica la conjuncion encadenada
-- a todos los elementos
conjuncion :: [Bool] -> Bool
conjuncion (x:xs) = foldr (&&) (x) xs

-- Lista de rangos
lista1 :: [Int]
lista1 = [0,13..65]

-- Lista por comprension
lista2 :: [Int]
lista2 = [x | x<-[0..9]]