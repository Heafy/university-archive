Integrantes del equipo:
- Martínez Flores Jorge Yael
- Sanchez Morales Rodrigo Alejandro

1. ¿Por qué no utilizamos para el load_average simplemente el número de threads 
de la ready_list?
Porque si ese fuera el caso no se estaría tomando en cuenta el load_avg anterior,
es decir no se tendría en cuenta que está haciendo el sistema un segundo antes
de que se calculara

2. ¿Por qué razón al utilzar el módulo del punto flotante en modo kernel vuelve
mas lento al kernel?
Porque no está diseñado para soportar operaciones en punto flotante, debido a 
que si los hubiera los registros de punto flotante no se guardan ni se restauran
en las llamadas a sistema.

3. Para calcular el recent_cpu es posible utilizar un arreglo de n elementos, 
donde en cada casilla del arreglo se guarda el tiempo utilizado por el thread en 
ese instante ¿Por qué no utilzamos la anterior estrategia?
Porque estarías almacenando el tiempo utilizado en cada instante, cuando solo es 
necesario guardar el recent_cpu para el tick que está corriendo, ademáß un arreglo
es de tipo dinámico, se tendría que crear espacios en memoria para al macenar el 
arreglo suponiendo que tengamos un límite para el recent_cpu con arreglos.