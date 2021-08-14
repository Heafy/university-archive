/**
 * Sesion 9 del laboratorio de Logica Computacional
 * Equipo: Cracks
 * Integrantes:
 * Estrada Gomez Cesar Derian
 * Garcia Hernandez Aidé Itzel
 * Martinez Flores Jorge Yael
 * Rivera Gonzalez Damian
 */
 
/* Algunos tipo de Pokemon*/
tipo(agua).
tipo(fantasma).
tipo(fuego).
tipo(hada).
tipo(normal).
tipo(nulo). /* Util cuando el pokemon no tiene un segundo tipo */
tipo(planta).
tipo(psiquico).
tipo(veneno).
tipo(volador).

/* Algunos pokemon junto a sus tipos*/ 
pokemon(charizard, volador, fuego).
pokemon(chikorita, planta, nulo).
pokemon(gengar, fantasma, veneno).
pokemon(arcanine, fuego, nulo).
pokemon(gyarados, agua, volador).
pokemon(kyogre, agua, nulo).
pokemon(mewtwo, psiquico, nulo).
pokemon(teddiursa, normal, nulo).
pokemon(togepi, hada, nulo).
pokemon(togetic, hada, volador).

/* Predicado que indica cuando el pokemon tipo t1 
 * tiene un ataque fuerte contra el pokemon tipo t2 */
fuerte(agua, fuego).
fuerte(fantasma, fantasma).
fuerte(fantasma, psiquico).
fuerte(fuego, planta).
fuerte(planta, agua).
fuerte(psiquico, veneno).
fuerte(veneno, hada).
fuerte(veneno, planta).
fuerte(volador, planta).

/* Predicado que indica cuando el pokemon tipo t1 
 * tiene un ataque debil contra el pokemon tipo t2 */
debil(agua, agua).
debil(agua, planta).
debil(fuego, agua).
debil(fuego, fuego).
debil(psiquico, psiquico).
debil(hada, fuego).
debil(hada, veneno).
debil(planta, fuego).
debil(planta, planta).
debil(planta, veneno).
debil(veneno, fantasma).
debil(veneno, veneno).
debil(volador, psiquico).

/* Predicado que indica cuando el pokemon tipo t1 
 * tiene un ataque sin efecto contra el pokemon tipo t2 */
sin_efecto(fantasma, normal).
sin_efecto(normal, fantasma).

/* Predicado que se cumple cuando el ataque A es fuerte contra
 * el pokemon P */
superefectivo(A, P):-fuerte(A, X), pokemon(P,X,_).
superefectivo(A, P):-fuerte(A, X), pokemon(P,_,X).