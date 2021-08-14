% REMOVER ESTA LINEA CUANDO LAS FUNCIONES ESTEN IMPLEMENTADAS
% Solo remueve advertencias para variables
:- style_check(-singleton).

/* MAPAS Y COLORACIONES */

% Dado un mapa con 6 paises arbitrarios. Escribir la regla colorea
% Que colorea el mapa usando unicamente cuatro colores de manera 
% Que dos paises adyacentes no tengan el mismo color

/* Tipos de color */
tipo(rojo).
tipo(azul).
tipo(amarillo).
tipo(verde).
/* Función que le da color a un país */
color(rojo,P1).
color(azul,P2).
color(amarillo,P3).
color(verde,P4).
%colorea(P1, P2, P3, P4, P5, P6):-write('Funcion no implementada').
colorea(P1, P2, P3, P4, P5, P6):- color(C1,P1), color(C2,P2), color(C3,P3),
                                  color(C4,P4), color(C5,P5), color(C6,P6),
                                  C1\==C2, C2\==C3, C3\==C4, C4\==C5, C5\==C6.

/* COMPUERTAS LOGICAS */

% Comportamiento de entrada y salida de las compuertas logicas AND
% Se excluyen todos los demas casos porque son falsos.
and(1, 1).

% Comportamiento de entrada y salida de las compuertas logicas OR
% Se excluyen todos los demas casos porque son falsos.
or(0, 1).
or(1, 0).
or(1, 1).

% Comportamiento de entrada y salida de las compuertas logicas NOT
% Se excluyen todos los demas casos porque son falsos.
not(0).

% Primer circuito
circuitoA(X, Y):- \+and(X, Y).

% Segundo circuito
circuitoB(V, Z, Y, X):- \+and((and(V, Z)), (and(Y, X))).

/* ARBOLES BINARIOS */

% Reresentacion de arbol binario en Prolog
bt(void).
bt(node(A, T1, T2)) :- integer(A), bt(T1), bt(T2).

% Se cumple si A es elemento de T
elem(A, bt(node(R, void, void))):- A =:= R.
elem(A, bt(node(R, T1, T2))):-A =:= R; elem(A, T1); elem(A, T2).

% Se cumple si A es mayor a todos los elementos de T
% maxtree(A,T):-write('Funcion no implementada').
maxtree(A, void).
maxtree(A, bt(node(R, void, void))):- A > R.
maxtree(A, bt(node(R, T1, T2))):- A > R, maxtree(A, T1), maxtree(A, T2).

% Se cumple si A es menor a todos los elementos de T
mintree(A, void).
mintree(A, bt(node(R, void, void))):- A < R.
mintree(A, bt(node(R, T1, T2))):- A < R, mintree(A, T1), mintree(A, T2).

% Se cumple si A esta en T
innode(A, T1, T2):- elem(A, T1, T2).
%innode(A, bt(node(R, void, void))):- A = R.
%innode(A, bt(node(R, T1, T2))):- A = R, innode(A, T1); innode(A, T2).

/* ARBOLES BINARIOS DE BUSQUEDA*/

% Representacion de arboles binarios de busqueda
bst(void).
bst(node(A, T1, T2)) :- 
	integer(A), maxtree(A, T1), mintree(A, T2),bst(T1), bst(T2).

% Se cumple si A es elemento del arbol binario de busqueda T
% Nota: Usar las propiedades del bst para buscar A, no solo usar fuerza bruta
elembst(A,bst(node(R, T1 ,T2))) :- A = R;(A < R, elembst(A,T1));(A > R, elembst(A, T2)).

% Se cumple si T2 es el resultado de insertar A en T1
insert(E, T1, T3) :- inAux(E, T1, T2), compara(T2, T3).

% Auxiliar. Agrega el elemento E a T1 devolviendolo en T2. 
inAux(E, T1, T2) :- T1 == bst(void), T2 = bst(node(E , bst(void), bst(void))).
inAux(E, bst(node(R, bst(void), bst(void))), T2) :- E < R, T2 = bst(node(R, bst(node(E, bst(void), bst(void))), bst(void))).
inAux(E, bst(node(R, bst(void), bst(void))), T2) :- E > R, T2 = bst(node(R, bst(void), bst(node(E, bst(void), bst(void))))).
inAux(E, bst(node(R, T1, T2))) :- (E < R, inAux(E, T1)); (E > R, inAux(E, T2)).

% Auxiliar que compara los dos ároles que tiene como argumento.
compara(bst(void), bst(void)) :- 0 = 0.
compara(bst(node(R1, void, void)), bst(node(R2, void, void))) :- R1 = R2.
compara(bst(node(R1, A1, A2)), bst(node(R2, B1, B2))) :- R1 = R2, compara(A1, B1), compara(A2, B2). 

% Se cumple si T2 es el resultado de eliminar A de T1
elim(_, void, void).
elim(A, node(A, void, void), void).
elim(A, node(A, node(B, void, void), void), node(B, void, void)).
elim(A, node(A, T, node(B, void, void)), node(B, T, void)).
elim(A, node(_, T1, T2), node(_, T3, T4)) :- elim(A, T1, T2); elim(A, T3, T4).

/* Ejemplos:
* *bst(node(3, bst(node(2, bst(node(1, void, void)), void)), bst(node(5, bst(node(4,void, void)), bst(node(6, void, void)))))).
* insert(1, bst(node(2,bst(void), bst(void))), bst(node(2, bst(node(1, bst(void), bst(void))), bst(void)))).
* insert(1, bst(node(2,bst(void), bst(void))), bst(node(2, bst(void), bst(node(3, bst(void), bst(void)))))).
*/
