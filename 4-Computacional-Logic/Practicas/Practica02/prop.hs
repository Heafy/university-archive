module PROPP2 where
import Data.List

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
   show (Neg p) = "¬ (" ++ show p ++ ")"
   show (Op p o q) = "(" ++ show p ++ show o ++ show q ++ ")"

-- Función que realiza la sustitución simultánea dada una lista con las 
-- sustituciones.
sustSimult :: Prop -> [(VarP, Prop)] -> Prop
sustSimult (FA (Cte x)) l  = (FA (Cte x))
sustSimult (FA (Var x)) l = seaVar x l
sustSimult (Neg (x)) l = (Neg (sustSimult x l))
sustSimult (Op a Conj b) l = (Op (sustSimult a l) Conj (sustSimult b l))
sustSimult (Op a Disy b) l = (Op (sustSimult a l) Disy (sustSimult b l))
sustSimult (Op a Impl b) l = (Op (sustSimult a l) Impl (sustSimult b l))
sustSimult (Op a Syss b) l = (Op (sustSimult a l) Syss (sustSimult b l))

-- Función que regresa el valor de interpretación aplicada a una función en los
-- estados recibidos como parámetros.
interpreta :: Prop -> [Estado] -> Booleano
interpreta (FA (Cte x)) l  = x
interpreta (FA (Var x)) l = cambiaEstado x l
interpreta (Neg (x)) l = negacion(interpreta x l)
interpreta (Op a Conj b) l = conjuncion (interpreta a l) (interpreta b l)
interpreta (Op a Disy b) l = disyuncion (interpreta a l) (interpreta b l)
interpreta (Op a Impl b) l = implicacion (interpreta a l) (interpreta b l)
interpreta (Op a Syss b) l = equivalencia (interpreta a l) (interpreta b l)

-- Función que dada una fórmula, elimina: dobles negaciones, disyunciones o 
-- conjunciones de la misma variable y disyunciones con constantes.
simplifica :: Prop -> Prop
simplifica (FA a) = (FA a)
simplifica (Neg (Neg (x))) = simplifica x
simplifica (Neg (x)) = Neg(simplifica x)
simplifica (Op p o q)  
  | ((o == Disy) && q == (Neg(p))) = FA (Cte V)
  | ((o == Conj) && q == (Neg(p))) = FA (Cte F)
  | ((o == Disy) && ((p == FA (Cte V))||(q ==  FA (Cte V)))) = FA (Cte V)
  | ((o == Conj) && ((p == FA (Cte F))||(q ==  FA (Cte F)))) = FA (Cte F)
  | (p == q) = simplifica p
  | ((o == Disy) && (p ==  FA (Cte F))) = simplifica q
  | ((o == Disy) && (q ==  FA (Cte F))) = simplifica p
  | ((o == Conj) && (p ==  FA (Cte V))) = simplifica q
  | ((o == Conj) && (q ==  FA (Cte V))) = simplifica p
simplifica (Op (FA a) o (FA b)) = (Op (FA a) o (FA b))
simplifica (Op p o q)  = (Op (simplifica p) o (simplifica q))
  
-- Función que regresa la forma normal negativa de una expresión
formaNN :: Prop -> Prop
formaNN f = (aplicaNeg((quitaImpl(f))))

-- Función que regresa la forma normal conjuntiva de una expresión
formaNC :: Prop -> Prop
formaNC f =  fNC(formaNN f)


-- Función que verifica si una fórmula es tautología
esTautologia :: Prop -> Booleano
esTautologia f 
 | ((modelos f) == (estados f) ) = V
 |otherwise = F

-- Función que decide si una fórmula es satisfacible
esSatisfacible :: Prop -> Booleano
esSatisfacible f 
 | ((modelos f) /= [] ) = V
 |otherwise = F

-- Función que obtiene las cláusulas de una fórmula
clausulas :: Prop -> [Prop]
clausulas p = clau(formaNC p) 


--Funcion auxiliar que hace el trabajo de clausulas
clau :: Prop -> [Prop]
clau  (Op a Conj b) = clau a ++ clau b
clau p = [p]  

--Funcion auxiliar para quitar impilcaciones
quitaImpl :: Prop -> Prop 
quitaImpl (FA a) = (FA a) 
quitaImpl (Neg (FA a)) = (Neg (FA a)) 
quitaImpl (Neg (Neg (x))) = (quitaImpl (x))
quitaImpl (Neg x) = (Neg (quitaImpl(x)))
quitaImpl (Op p Impl q) = (Op (quitaImpl(Neg(p))) Disy (quitaImpl(q)))
quitaImpl (Op p Syss q) = (Op ((Op (quitaImpl(Neg(p))) Disy (quitaImpl(q))))  Conj 
                              ((Op (quitaImpl(Neg(q))) Disy (quitaImpl(p)))))
quitaImpl (Op p o q) = (Op (quitaImpl(p)) o (quitaImpl(p))) 

--Funcion auxiliar para aplicar negaciones  
aplicaNeg :: Prop -> Prop
aplicaNeg (FA a) = (FA a) 
aplicaNeg (Neg (FA a)) = (Neg (FA a)) 
aplicaNeg (Neg(Op (p) Conj (q))) = (Op (aplicaNeg(Neg(p))) Disy (aplicaNeg(Neg(q))))
aplicaNeg (Neg(Op (p) Disy (q))) = (Op (aplicaNeg(Neg(p))) Conj (aplicaNeg(Neg(q))))
aplicaNeg (Neg (Neg (x))) = (aplicaNeg(x))
aplicaNeg f = f 
--Funcion auxiliar para buscar la variable que quiero sustituir 
seaVar :: VarP -> [(VarP, Prop)] -> Prop
seaVar _ [] = error"No se encontró la variable en el ambiente"
seaVar v (x:xs) 
 | v == fst(x) = snd(x)
 | otherwise = seaVar v (xs)

--Funcion auxiliar que busca la variable y me regresa el valor que tiene
cambiaEstado :: VarP -> [Estado] -> Booleano
cambiaEstado _ [] = error"No se encontró la variable en el ambiente"
cambiaEstado v (x:xs) 
 | v == fst(x) = snd(x)
 | otherwise = cambiaEstado v (xs)

--Funcion que regresa la negacion
negacion :: Booleano -> Booleano
negacion V = F
negacion F = V

-- Funcion que toma dos booleanos y regresa su conjuncion
conjuncion:: Booleano -> Booleano -> Booleano
conjuncion V V = V
conjuncion _ _ = F

-- Funcion que toma dos booleanos y regresa su conjuncion
disyuncion:: Booleano -> Booleano -> Booleano
disyuncion F F = F
disyuncion _ _ = V

-- Funcion que toma dos booleanos y regresa su implicacion
implicacion:: Booleano -> Booleano -> Booleano
implicacion V F = F
implicacion _ _ = V

-- Funcion que toma dos booleanos y regresa su equivalencia
equivalencia:: Booleano -> Booleano -> Booleano
equivalencia a b
 | b == a = V
 | otherwise = F 

--Funcion auxiliar para aplicar leyes de distribucion
distri :: Prop-> Prop -> Prop
distri (Op a Conj b) c =(Op (Op (a) Disy (c))  Conj   (Op (b) Disy (c)) )
distri a (Op b Conj c) = (Op (Op (b) Disy (a))  Conj   (Op (c) Disy (a)) )
distri a b = (Op a Disy b )

--Funcion auxiliar para formas normales negativas
fNC ::Prop -> Prop 
fNC f =  case f of
  Op p1 Conj p2 -> Op (fNC p1) Conj (fNC p2)
  Op p1 Disy p2 ->distri (fNC p1) (fNC p2) 
  _ -> f

-- Funcion que toma una formula proposicional 
--y regresa una lista con las variables que figuran en ella
vars:: Prop -> [VarP]
vars (FA (Cte _)) = []
vars (FA (Var x)) = [ x]
vars (Neg (x)) = vars x
vars (Op a Conj b) = nub(vars a ++ vars b)
vars (Op a Disy b) = nub(vars a ++ vars b)
vars (Op a Impl b) = nub(vars a ++ vars b)
vars (Op a Syss b) = nub(vars a ++ vars b)

--Funcion auxiliar para estados de un proposicion
--regresa una lista con 2^n elementos donde n es 
--el numero de variables en la proposicion
estados ::Prop -> [[Estado]]
estados f =  auxEstados(vars f) 

--Funcion que regresa los estados de una variable
esta2 :: VarP -> [Estado]
esta2 v = [(v,V),(v,F)]

--Funcion que dado una lista de estados y una varible 
--se encarga de generar dos nuevas lista una
--originales conbinados (P,V) y otra que contenga a la original
--concatenada con (P,F) 
conbinaConP :: VarP->[Estado]->[[Estado]]
conbinaConP p e = [((esta2 p)!!0):e,((esta2 p)!! 1):e]

--Funcion que dada una lista de listas de estados
--nos regresa las originales conbinadas con la variable P
auxEsta2 :: VarP -> [[Estado]]-> [[Estado]]
auxEsta2 p [] = []
auxEsta2 p (x:xs) = (conbinaConP p x)++(auxEsta2 p xs )  
 
--Funcion que dada una lista de variables nos da los 2^n posibles estados
--donde n es la logitud de su primer argumento
tablaVerdad :: [VarP] -> [[Estado]]->[[Estado]]
tablaVerdad []  l = l
tablaVerdad (y:ys) l =  tablaVerdad ys holi 
 where holi =  auxEsta2 y l

auxEstados :: [VarP] -> [[Estado]]
auxEstados (x:xs) = tablaVerdad (xs) ([[(x,V)],[(x,F)]]) 


modelos:: Prop ->[[Estado]]
modelos f = [i | i <- (estados f),(interpreta f i) == V]









