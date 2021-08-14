module ARBOLESP2 where

-- Gramática para representar a los árboles binarios
data AB a = Hoja a
          | Mkt (AB a) (AB a) deriving (Show, Eq)

-- Función que regresa el número de hojas del árbol.
nh :: AB a -> Int
nh (Hoja(a)) = 1
nh (Mkt (t1)(t2)) = nh (t1) + nh (t2)

-- Función que regresa el número de nodos internos del árbol.
nni :: AB a -> Int
nni (Hoja(a)) = 0
nni (Mkt (t1)(t2)) = 1 + nni (t1) + nni(t2)

-- Función que determina si un elemento está contenido en el árbol.
elemA :: Eq a => AB a -> a -> Bool
elemA (Hoja(a)) x = if(a == x)
                    then True
                    else False
elemA (Mkt (t1)(t2)) x = (elemA t1 x) || (elemA t2 x)

-- Función que toma un árbol y regresa una lista con los elementos en la forma
-- inorder.
inorderA :: AB a -> [a]
-- inorderA (Hoja(a)) = [a]
-- Creo que es recorrido postorder
inorderA (Mkt (t1)(t2)) = inorderA t1 ++ inorderA t2

-- Función que toma un elemento y lo agrega al árbol.
agregaHoja :: AB a -> a -> AB a
agregaHoja (Hoja(a)) x = (Mkt (Hoja(a))(Hoja(x)))
agregaHoja (Mkt (t1)(t2)) x = (Mkt (t1)(agregaHoja (t2) x ))

-- Función que dado un árbol y una función, aplica la misma a cada elemento del
-- árbol.
mapA :: AB a -> (a -> b) -> AB b
mapA (Hoja (a)) f = (Hoja (f(a)))
mapA (Mkt (t1) (t2)) f = (Mkt (mapA(t1) f) (mapA(t2) f))

-- Función que regresa la profundidad del árbol.
profundidad :: AB a -> Int
profundidad (Hoja(a)) = 1
profundidad (Mkt(t1)(t2)) = 1 + max (profundidad (t1)) (profundidad(t2))