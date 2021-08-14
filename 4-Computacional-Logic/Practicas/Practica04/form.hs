module PrimerOrden where

-- Sinónimo para representar los nombres de variables y funciones.
type Nombre = String

-- Sinónimo para representar sustituciones.
type Sust = [(Nombre,Termino)]

type Ecuacion = (Termino, Termino)

-- Gramática para representar términos.
data Termino = V Nombre
             | F Nombre [Termino] deriving (Eq)

-- Gramática para representar fórmulas de la Lógica de Primer Orden.
data FORM = TrueF
             | FalseF
             | Pr Nombre [Termino]
             | Eq Termino Termino
             | Neg FORM
             | Conj FORM FORM
             | Disy FORM FORM
             | Impl FORM FORM
             | Equi FORM FORM
             | PT Nombre FORM
             | EX Nombre FORM deriving(Eq)

instance Show Termino where
    show (V a) = show a
    show (F n a) = show n ++ "(" ++ show a ++ ")"

instance Show FORM where
    show TrueF = "⊤"
    show FalseF = "⊥"
    show (Pr n l) =  show n ++ "(" ++ show l ++")"
    show (Eq t1 t2) = show t1 ++ "=" ++ show t2
    show (Neg f) = "¬(" ++ show f ++ ")"
    show (Conj p q) = show p ++ " ∧ " ++ show q
    show (Disy p q) = show p ++ " ∨ " ++ show q
    show (Impl p q) = show p ++ " => " ++ show q
    show (Equi p q) = show p ++ " <=> " ++ show q
    show (PT n f) = "∀" ++ show n ++ "(" ++ show f ++ ")"
    show (EX n f) = "∃" ++ show n ++ "(" ++ show f ++ ")"

-- Función que verifica si dos fórmulas son alpha-equivalentes.
vAlfaEq :: FORM -> FORM -> Bool
vAlfaEq f1 f2
    | fv(f1) == fv(f2) = sonDiferentes (bv(f1)) (bv(f2))

-- Funcion auxiliar. Dadas dos listas de nombres de variables nos dice si son diferentes.
sonDiferentes :: [Nombre] -> [Nombre] -> Bool
sonDiferentes [] [] = False
sonDiferentes [x] l
    | elem x l = False
    | otherwise = True
sonDiferentes (x:xs) l = sonDiferentes [x] l && sonDiferentes xs l

-- Devuelve las variables libres 
fv:: FORM -> [Nombre]
fv v = case v of
       TrueF -> []
       FalseF -> []
       Pr _ ts -> concat [ varT t | t<-ts]
       Eq t1 t2 -> varT t1 ++ varT t2
       Neg f -> fv f
       Conj f1 f2 -> fv f1 ++ fv f2
       Disy f1 f2 -> fv f1 ++ fv f2
       Impl f1 f2 -> fv f1 ++ fv f2
       Equi f1 f2 -> fv f1 ++ fv f2
       PT x f -> filter (x/=) $ fv f
       EX x f -> filter (x/=) $ fv f

-- Devuelve las variables ligadas
bv:: FORM -> [Nombre]
bv v = case v of
       TrueF -> []
       FalseF -> []
       Pr _ ts -> []
       Eq t1 t2 -> []
       Neg f -> bv f
       Conj f1 f2 -> bv f1 ++ bv f2
       Disy f1 f2 -> bv f1 ++ bv f2
       Impl f1 f2 -> bv f1 ++ bv f2
       Equi f1 f2 -> bv f1 ++ bv f2
       PT x f -> x:(bv f)
       EX x f -> x:(bv f)

-- Devuelve la lista con todos los nombres de variables que figuran en t
varT:: Termino -> [Nombre]
varT (V n) = [n]
varT (F n []) = []
varT (F n ls) = varTAux ls

-- Funcion auxiliar para varT
varTAux:: [Termino] -> [Nombre]
varTAux [] = []
varTAux ((V n):xs) = [n]
varTAux ((F n []:xs)) = []
varTAux ((F n ls):xs) = varTAux ls ++ varTAux xs

-- Función que renombra las variables ligadas de una fórmula de manera que las
-- listas de variables libres y ligadas que sean ajenas. Estos es un caso 
-- particular de la siguiente función.
renVL :: FORM -> FORM
renVL TrueF  = TrueF
renVL FalseF  = FalseF
renVL (Pr p ts) = (Pr p ts)
renVL (Eq t1 t2) = (Eq t1 t2)
renVL (Neg t)  = Neg (renVL t)
renVL (Conj t1 t2) = Conj (renVL t1) (renVL t2)
renVL (Disy t1 t2) = Disy (renVL t1) (renVL t2)
renVL (Impl t1 t2) = Impl (renVL t1) (renVL t2)
renVL (Equi t1 t2) = Equi (renVL t1) (renVL t2)
renVL (PT n f) = (PT (n++"0") (renVL(apsubsF f [(n,V (n++"0"))]))  )
renVL (EX n f) = (EX (n++"0") (renVL(apsubsF f [(n,V (n++"0"))]))  )

-- Función que renombra la variables ligadas de una fórmula de forma que sus 
-- nombres sean ajenos a los de una lista dada.
renVLconj :: FORM -> [Nombre] -> FORM
renVLconj TrueF l = TrueF
renVLconj FalseF  l = FalseF
renVLconj (Pr p ts)l  = (Pr p ts)
renVLconj (Eq t1 t2) l = (Eq t1 t2)
renVLconj (Neg t)  l = Neg (renVLconj t l)
renVLconj (Conj t1 t2) l = Conj (renVLconj t1 l) (renVLconj t2 l)
renVLconj (Disy t1 t2) l = Disy (renVLconj t1 l) (renVLconj t2 l)
renVLconj (Impl t1 t2) l = Impl (renVLconj t1 l) (renVLconj t2 l)
renVLconj (Equi t1 t2) l = Equi (renVLconj t1 l) (renVLconj t2 l)
renVLconj (PT n f) l = if(not (elem (n++"0") l ) ) 
                        then (PT (n++"0") (renVL(apsubsF f [(n,V (n++"0"))])))
                        else renVLconj (PT (n++"0") (renVL(apsubsF f [(n,V (n++"0"))]))) l
renVLconj (EX n f) l = if(not (elem (n++"0") l ) ) 
                        then (EX (n++"0") (renVL(apsubsF f [(n,V (n++"0"))])))
                        else renVLconj (PT (n++"0") (renVL(apsubsF f [(n,V (n++"0"))]))) l

-- Funcion auxiliar
apsubsF:: FORM -> Sust -> FORM
apsubsF TrueF _ = TrueF
apsubsF FalseF _ = FalseF
apsubsF (Pr p ts) sust = Pr p [apsubsT t sust | t<-ts]
apsubsF (Eq t1 t2) sust = Eq (apsubsT t1 sust) (apsubsT t2 sust)
apsubsF (Neg t) sust = Neg (apsubsF t sust)
apsubsF (Conj t1 t2) sust = Conj (apsubsF t1 sust) (apsubsF t2 sust)
apsubsF (Disy t1 t2) sust = Disy (apsubsF t1 sust) (apsubsF t2 sust)
apsubsF (Impl t1 t2) sust = Impl (apsubsF t1 sust) (apsubsF t2 sust)
apsubsF (Equi t1 t2) sust = Equi (apsubsF t1 sust) (apsubsF t2 sust)
apsubsF (PT n f) s
    | not (elem n (nombreSust s) && elem n (termSust s) ) = PT n (apsubsF f s)
    | otherwise = (PT n f)
apsubsF (EX n f) s
    | not (elem n (nombreSust s) && elem n (termSust s) ) = EX n (apsubsF f s)
    | otherwise = (EX n f)

nombreSust :: Sust -> [Nombre]
nombreSust [] = []
nombreSust ((n,t): xs) = n:(nombreSust xs)

termSust :: Sust -> [Nombre]
termSust [] = []
termSust ((n,(V t)): xs) = t:(termSust xs)

-- Función que aplica sustitución sobre un término
apsubsT:: Termino -> Sust -> Termino
apsubsT (V n) ((x, t): xs)
    | n == x = t
    | otherwise = (V n)
apsubsT (F n []) ((x,t):xs) = (F n [])
apsubsT (F n l) s = (F n (map (\x -> apsubsT x s)l))  

-- Función que implementa la sustitución en fórmulas usano la 
-- alpha-equivalencia.
apsubF2 :: FORM -> Sust -> FORM
apsubF2 f s = apsubsF( renVLconj f ( (nombreSust s) ++ (fv f) ) ) s

-- Función que dada una sustitución, elimina de ella los pares con componentes
-- iguales correspondientes a sustituciones de la forma x:=x.
simpSus :: Sust -> Sust
simpSus [] = []
simpSus ((n,V n1):xs) = if (n==n1) 
                        then  (simpSus xs)
                        else  (n,V n1):(simpSus xs)
simpSus (x:xs) = x:(simpSus xs)

-- Función que dadas dos sustituciones devuelve su composición.
compSus :: Sust -> Sust -> Sust
compSus [] [] = []
compSus x y = union x y 

-- Funcion auxiliar para la union de listas
union:: Eq a => [a] -> [a] -> [a]
union [] b = b
union (x:xs) b
    | elem x b = union xs b
    | otherwise = x:(union xs b)

-- Función que dados dos términos devuelve una lista de sustituciones de tal
-- forma que:
-- · Si t1, t2 no son unificables la lista es vacía.
-- · Si sí lo son, la lista contiene como único elemento al unificador 
--   correspondiente.
unifica :: Termino -> Termino -> [Sust]
unifica (V x) (V y) = if (x==y) 
                        then []
                        else [ [(x, (V y))] ]
unifica (V x) (F f ts) = if (elem (V x) (ts)) 
                            then []
                            else [ [(x, (F f ts))] ]
unifica (F f ts) (V x) = if (elem (V x) (ts)) 
                            then []
                            else [ [(x, (F f ts))] ]
unifica (F f1 l1) (F f2 l2) = if ((f1 == f2) && (length l1 == length l2)) 
                                then unificaAux l1 l2
                                else []

-- Funcion auxiliar para la funcion unifica
unificaAux :: [Termino] -> [Termino] -> [Sust]
unificaAux [] _ = []
unificaAux _ [] = []
unificaAux (x:xs) (y:ys) = if (x==y) 
                            then []
                            else (unifica x y) ++ unificaAux xs ys

-- Función que implementa el caso general para unificar un conjunto (lista)
-- W = {t1,..,tn}
unificaConj :: [Termino] -> [Sust]
unificaConj [] = []
unificaConj (x:xs) = (unificaConjAux x xs) ++ (unificaConj xs)

unificaConjAux :: Termino -> [Termino] -> [Sust]
unificaConjAux _ [] = []
unificaConjAux x (y:ys) = unifica x y

--vAlfaEq (PT "x" (EX "y" (Impl (Pr "P" [V "x"]) (Pr "P" [V "y"])))) (PT "w" (EX "z" (Impl (Pr "P" [V "w"]) (Pr "P" [V "z"]))))
