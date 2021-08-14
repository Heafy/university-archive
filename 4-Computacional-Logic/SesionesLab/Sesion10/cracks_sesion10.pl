/*
 * Sesion 10
 * Equipo: Cracks
 */

% Hechos de triangulos
triangulo(a, lados(4,4,5)).
triangulo(b, lados(3,25,26)).

% Define P como el perimetro del triangulo
perimetro(T,P):- triangulo(T, lados(X,Y,Z)), P is X+Y+Z.

% Define A como el area de un triangulo usando la Formula de Heron
area(T, A):- triangulo(T, lados(X,Y,Z)), A is sqrt(((X+Y+Z)/2)*(((X+Y+Z)/2)-X)*(((X+Y+Z)/2)-Y)*(((X+Y+Z)/2)-Z)).