module Cracks_sesion6 where

-- Sinónimo para representar a los vértices. Para fines prácticos, supondremos
-- que la gráfica almacena datos de tipo entero. De esta forma, un vértice es
-- simplemente un Int.
type Vertice = Int
 
-- Sinónimo para representar las adyacencias de un vértice. Una adyacencia es 
-- una tupla formada por un vértice y la lista de vértices con los que se 
-- conecta el primero.
type Adyacencia = (Vertice, [Vertice])

-- Sinónimo para representar a las gráficas. Siguiendo la idea anterior. Una
-- gráfica es vista como una lsita de adyacencias.
type Grafica = [Adyacencia]

-- Función que dice si un vértice se encuentra en la gráfica
perteneceVertice :: Vertice -> Grafica -> Bool
perteneceVertice x [] = False
perteneceVertice x (y:ys) = if ( x == encontrarVertice (y) ) then True
    else perteneceVertice x ys

-- Función auxiliar para sacar un vértice de una adyacencia
encontrarVertice :: Adyacencia -> Vertice
encontrarVertice (n,_) = n

-- Función que regresa la lista de vértices a los que apunta un vértice
vecinosSalientes :: Grafica -> Vertice -> [Vertice]
vecinosSalientes [] _ = []
vecinosSalientes (x:xs) v = if (v == encontrarVertice (x)) then dameVecinos (x)
    else vecinosSalientes xs v

-- Función que regresa una lista de vecinos que se encuentran en una adyacencia
dameVecinos :: Adyacencia -> [Vertice]
dameVecinos (_,l) = l

-- Función que regresa la lista de vértices que apuntan a un vértice
vecinosEntrantes :: Grafica -> Vertice -> [Vertice]
vecinosEntrantes [] _ = []
vecinosEntrantes (x:xs) v = if (elem v (dameVecinos(x) ) ) then [encontrarVertice (x)] ++ vecinosEntrantes xs v
    else vecinosEntrantes xs v

ingrado :: Grafica -> Vertice -> Int
ingrado [] x = 0
ingrado [(x,l)] y
    | elem y l = 1
    | otherwise = 0
ingrado (x:xs) y = ingrado [x] y + ingrado (xs) y 
--ingrado x y = error "Funcion no implementada"

exgrado :: Grafica -> Vertice -> Int
exgrado [] x = 0
exgrado [(x,l)] y 
    | x == y = length l
    | otherwise = 0
exgrado (x:xs) y
    | fst(x) == y = exgrado [x] y
    | otherwise = exgrado (xs) y

-- Función que regresa el número de vértices en una gráfica
numVertices :: Grafica -> Int
numVertices [] = 0
numVertices (x:xs) = cuentaVertice (x) + numVertices (xs)

-- Función auxiliar que regresa la cantidad de vértices en una adyacencia
cuentaVertice :: Adyacencia -> Int
cuentaVertice (n, _) = 1

-- Función que regresa el número de aristas en una gráfica
numAristas :: Grafica -> Int
numAristas [] = 0
numAristas [(x,l)] = length l;
numAristas (x:xs) = numAristas [x] + numAristas (xs)
