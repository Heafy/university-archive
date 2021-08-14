module Graficas where
import Data.List
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

-- Función que dada una gráfica regresa una lista con los vértices que la 
-- conforman (Enteros).
vertices :: Grafica -> [Vertice]
vertices [] = []
vertices ((v,a):xs) = [v] ++ vertices (xs)

-- Función que dada una gráfica determina si es conexa o no.
esConexa :: Grafica -> Bool
esConexa [(v,[])] = True
esConexa g
   | marcaVertices g (vertices g) == [] = True
   | otherwise = checaVertices g (marcaVertices g (vertices g))

-- Auxilar. Dada una gŕafica y su lista de vértices (lv).
-- Elimina los vértices adyacentes de cada vértice de la lista de 'lv'
marcaVertices :: Grafica -> [Vertice] -> [Vertice]
marcaVertices [] [] = []
marcaVertices [v] [] = []
marcaVertices [v] lv = elimina (snd v) lv
marcaVertices (x:xs) lv = marcaVertices xs (elimina (snd x) lv)

--Auxiliar. Dada una gráfica y una lista de vértices. Verifica que cada 
--vértice de la lista en la gráfica tenga al menos una adyacencia.
checaVertices :: Grafica -> [Vertice] -> Bool
checaVertices [] [] = True
checaVertices [] [n] = False
checaVertices [v] [] = True
checaVertices [v] (y:ys)
   | (elem (fst v) (y:ys)) && ((snd v)/= [] )= True
   | otherwise = False
checaVertices (x:xs) [n]
   | elem n (vertices (x:xs)) && snd(dameVertice n (x:xs))/=[] = True
   |otherwise = False
checaVertices (x:xs) (y:ys) = checaVertices (x:xs) [y] && checaVertices (x:xs) ys


--Auxiliar. Dado un vértice y un gráfica.
--Devuelve la tupla del vértice con su lista de adyacencia.
dameVertice :: Vertice -> Grafica -> Adyacencia
dameVertice v [] = error "El vértice no se encuentra"
dameVertice v [u]
   | v == (fst u) = u
   |otherwise = error "El vertice no se encuentra"
dameVertice v (x:xs)
   | elem v (vertices (x:xs)) = if v == fst x then x else dameVertice v xs


-- Auxiliar. Dadas dos listas l1,l2 nos devuelve si la lista l1 esta contenida en l2.
estaContenido :: [Vertice] -> [Vertice] -> Bool
estaContenido [] [] = True
estaContenido [x] l = elem x l
estaContenido (x:xs) l = (estaContenido [x] l) && (estaContenido xs l)
 
-- Auxiliar. Dadas dos listas l1,l2 elimina todos los elementos de la lista l1 de l2.
elimina :: [Vertice] -> [Vertice] -> [Vertice]
elimina [] [] = []
elimina [] l = l
elimina [x] (y:ys) = filter (\v -> v/= x) (y:ys)
elimina (x:xs) (y:ys)
   | estaContenido (x:ys) (y:ys) = elimina xs (quitaVertice x (y:ys))
   | otherwise = elimina (enComun (x:xs) (y:ys)) (y:ys)
   
-- Auxiliar.Dadas dos lista de vértices.
-- Devuevle una lista con los elementos que tienen en común.
enComun :: [Vertice] -> [Vertice] -> [Vertice]
enComun [] [] = []
enComun [x] l
   | elem x l = [x]
   | otherwise = []
enComun (x:xs) l = enComun [x] l ++ enComun xs l--(quitaVertice x l)
   
-- Auxilar.Función que dada una gráfica regresa una lista con 
-- los vértices que están en como adyacentes a cada vértice.
listaAdyacencia :: Grafica -> [Vertice]
listaAdyacencia [] = []
listaAdyacencia [(u,l)] = l
listaAdyacencia (x:xs) = listaAdyacencia[x] ++listaAdyacencia(xs)

-- Función que dada una gráfica determina si es completa o no.
esCompleta :: Grafica -> Bool
esCompleta [a] = True
esCompleta (x:xs)= adyacencia (vertices(x:xs)) (x:xs)

-- Auxiliar. Función dada una lista de vértices y una gráfica.
-- regresa si todas las adyacencias entre los vértices estan en la gráfica
adyacencia :: [Vertice] -> Grafica ->Bool
adyacencia [] g = True 
adyacencia [x] g = elem (x,quitaVertice x (vertices g)) g
adyacencia (x:xs) (g)
   | (adyacencia [x] g) = True && adyacencia (xs) g
   | otherwise = False   

-- Auxiliar. Función que dado un vértice y una lista de vértices.
-- regresa un lista de vértices sin el vértice como parámetro
quitaVertice :: Vertice -> [Vertice] -> [Vertice]
quitaVertice v [] = []
quitaVertice v (x:xs)
   | (v == last(x:xs)) = quitaVerticeI (length(x:xs)) (x:xs) 
   | (elem v (x:xs)) = quitaVerticeI ((x:xs)!!(v -1)) (x:xs)
   | otherwise = (x:xs)

-- Auxiliar. Función que dado un índice y una lista de vértices regresa 
-- una lista de vértices sin el vértice que tenía la posición del índice.
quitaVerticeI :: Int -> [Vertice] -> [Vertice]
quitaVerticeI n [] = []
quitaVerticeI n (x:xs)
   | (n == 1) = tail(x:xs)
   | (n == length(x:xs)) = init (x:xs)
   | otherwise = (take(n-1) (x:xs))++(drop (n) (x:xs))

-- Fución que dada una gráfica determina si contiene un camino hamiltoniano.
caminoHamiltoniano :: Grafica -> Bool
caminoHamiltoniano g = caminoHamiltonianoAux  (permutations (vertices g))  (listaDeAristas g)



--Funcion auxiliar que dado una lista de Vertices(todos los posibles caminos) y el conjunto de las aristas de una grafica
--nos dice si existe un caminoHamiltoniano
caminoHamiltonianoAux::[[Vertice]]->[(Vertice,Vertice)]->Bool
caminoHamiltonianoAux [] _ = False
caminoHamiltonianoAux (x:xs) l = if (existeCamino x l) == True then True 
                                else caminoHamiltonianoAux xs l 

--Funcion auxiliar que recibe una lista de vertices y nos dice si podemos formar un camino
--i.e. la grafica tiene todas las aritas necesarias para formar el camino formado por la sucesion de
--vertices de la lista que toma como primer parametro 
existeCamino::[Vertice]->[(Vertice,Vertice)]->Bool
existeCamino [] _ = True
existeCamino [x] l = True
existeCamino (x:xs:xss) l = if elem (x,xs) l then  existeCamino (xs:xss) l
                           else False

--Funcion auxiliar que nos regresa las aristas de una grafica
listaDeAristas::Grafica->[(Vertice,Vertice)]
listaDeAristas [] = []
listaDeAristas ((u,v):xs) = zip (replicate (length v) u) v ++ listaDeAristas xs
-- Función que dada una gráfica y un entero k, determina si contiene un clan de
-- tamaño k.
clan :: Grafica -> Int -> Bool
clan g n = tieneClan (conjuntosDek n g)

--Funcion auxiliar que dada una lista de graficas nos dice si alguna es completa
tieneClan::[Grafica]->Bool
tieneClan [] = False
tieneClan (x:xs) = if(esCompleta x) then True 
                    else tieneClan xs


--Funcion auxiliar que dada una grafica nos regresa las posibles subgraficas de tamaño k 
conjuntosDek::Int->Grafica->[Grafica]
conjuntosDek k g = [x| x<- subsequences g , (length x) == k]

{-
Ejemplos:
vertices [(1,[3]),(2,[1]),(3,[5]),(4,[2]),(5,[7]),(6,[4]),(7,[6]),(8,[7])]
vertices[(1,[3]),(2,[1]),(3,[5]),(4,[2]),(5,[7]),(6,[4]),(7,[6])]

esConexa [(1,[3]),(2,[1]),(3,[5]),(4,[2]),(5,[7]),(6,[4]),(7,[6]),(8,[7])]
esConexa [(1,[3]),(2,[1]),(3,[5]),(4,[2]),(5,[7]),(6,[4]),(7,[6]),(8,[])]

esCompleta [(1,[2,3,4]),(2,[1,3,4]),(3,[1,2,4]),(4,[1,2,3])]
esCompleta [(1,[2,3,4]),(2,[1,3,4]),(3,[1,2,4]),(4,[])]
En caminos hamiltonianos se toman en cuenta caminos dirigidos i.e [(1,[2]),(2,[3]),(3,[])] tiene un 
camino pero [(1,[]),(2,[3,1]),(3,[])] no a pesar que ambas se podrian ver como trayectoria de logitud 2 
caminoHamiltoniano [(1,[2,3]),(2,[1,4]),(3,[1,4]),(4,[2,3,5,6]),(5,[4,7]),(6,[4,7]),(7,[5,6,8]),(8,[7])]
caminoHamiltoniano [(1,[3]),(2,[1]),(3,[5]),(4,[2]),(5,[7]),(6,[4]),(7,[6]),(8,[7])]
caminoHamiltoniano [(1,[3]),(2,[1]),(3,[5]),(4,[2]),(5,[7]),(6,[4]),(7,[6]),(8,[])]

clan [(1,[2,3,4]),(2,[1,3,4]),(3,[1,2,4]),(4,[1,2,3])] 4
clan [(1,[2,3]),(2,[1,3]),(3,[1,2]),(4,[])] 3
clan [(1,[2,3]),(2,[1,3]),(3,[1,2]),(4,[])] 4
-}
