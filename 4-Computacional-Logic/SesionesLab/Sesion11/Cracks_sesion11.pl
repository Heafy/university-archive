/**
 * Sesion 11 del laboratorio de Logica Computacional
 * Equipo: Cracks
 */

% Regla que determina si una lista esta formada por ceros y unos
binario([]).
binario([X|XS]):- ((X==1);(X==0)), binario(XS).

% Regla que representa la suma de elementos de una lista
suma([], 0).
suma([X|XS], Y):- suma(XS, Z), Y is X+Z.

% Regla que representa el valor maximo de una lista
maximo([],N):- N>0.
maximo([X|XS],N):- (N>=X),maximo(XS,N).

% Hechos del lenguaje Efe
ofo(a, afa).
ofo(e, efe).
ofo(i, ifi).
ofo(o, ofo).
ofo(u, ufu).
ofo(X, X). 

% Regla que representa la conversion de una cadena en otra en el lenguaje
% Efe. Las cadenas se representan mediante listas.
transforma([],[]).
transforma([A|B],[C|D]):- ofo(A,C), transforma(B, D).