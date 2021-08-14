module Sesion1 where

-- Funcion que aumenta en dos a un numero entero
f :: Int -> Int
f x = x + 2

-- Funcion que calcula el cuadrado de un numero
cuadrado :: Int -> Int
cuadrado x = x * x  

-- Funcion que regresa el minimo entre dos numeros
minimo :: Int -> Int -> Int
minimo x y = if x <= y 
                then x 
                else y

-- Funcion que determina si un numero es par 
esPar :: Int -> Bool
esPar x = if mod x 2 == 0 
            then True 
            else False

-- Funcion que da el nombre del mes en base al numero introducido 
-- Util para probar las guardias
numeroMes :: Int -> String
numeroMes n 
    | n == 1 = "Enero"
    | n == 2 = "Febrero"
    | n == 3 = "Marzo"
    | n == 4 = "Abril"
    | n == 5 = "Mayo"
    | n == 6 = "Junio"
    | n == 7 = "Julio"
    | n == 8 = "Agosto"
    | n == 9 = "Septiembre"
    | n == 10 = "Octubre"
    | n == 11 = "Noviembre"
    | n == 12 = "Diciembre"
    | otherwise = "Numero invalido" 

areaHeron :: Float -> Float -> Float -> Float
areaHeron a b c = 
    let s = (a+b+c)/2 in 
        sqrt(s*(s-a)*(s-b)*(s-c))

areaHeron2 :: Float -> Float ->Float -> Float
areaHeron2 a b c  = sqrt(s*(s-a)*(s-b)*(s-c))
    where s = (a+b+c)/2  