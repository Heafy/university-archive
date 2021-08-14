/* EJERCICIO 1 */

/* Es necesario el desorden de los ejercicios para evitar que los 
 * hechos con la misma aridad esten ordenados y evitemos una
 * advertencia de Prolog. Lo hace para imponer mejores practicas
 * */
.([]).								
.(.(a), .(b), []).					% Inciso a
.(.([]),.([]), []).					% Inciso d
.(.(a), .(.(b), .(c)), []).			% Inciso c
.(.(a), .(.(b), .(c)), .(d),[]).	% Inciso b

/* EJERCICIO 2 */

% Relaciona listas con su ultimo elemento
ultimo([X], X).
ultimo([_|XS], R):- ultimo(XS, R).

% Relaciona listas con su longitud
longitud([], 0).
longitud([_|XS], R):- longitud(XS, R1), R is R1+1.

% Recorre los elementos de una lista un lugar a la izquierda
recorrei([], []).
recorrei([X|XS], R):- append(XS, [X], R).

% Recorre los elementos de una lista un lugar a la derecha
recorred([], []).
recorred(R, [X|XS]):- append(XS, [X], R).

/* EJERCICIO 3 */

% Transiciones del automata
% delta: Q x Σ -> Q
delta(1, b, 2).
delta(2, a, 2).
delta(2, a, 3).
delta(3, b, 2).

% Funcion auxiliar
% Mueve las transiciones del estado en el que esta a el
% estado que lleva consumir cada caracter de la lista
% Recibe dos estados y una lista
leeDelta(Q, [], Q).
leeDelta(Q0, [X|XS], Qf):- delta(Q0, X, Q1), leeDelta(Q1, XS, Qf).

% Regla que indica si una cadena es aceptada por el automata
acepta(L):- leeDelta(1, L, 3).

/* EJERCICIO 4 */

% Funcion auxiliar
% Predicado que es cierto si la lista R es la reversa de la lista L
reversa(X, Y):- reversa(X, [], Y, Y).
reversa([], Y, Y, []).
reversa([X|XS], W, Y, [_|Z]):- reversa(XS, [X|W], Y, Z).

% Indica si una cadena representada por listas es palindroma
palindromo(L):- reversa(L,L).