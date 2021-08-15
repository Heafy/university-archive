Integrantes del equipo:
- Martínez Flores Jorge Yael
- Sanchez Morales Rodrigo Alejandro

1. ¿Qué es es un deadlock?
Sean t1 y t2 thread y p1 y p2 procesos, Si proceso1 tiene a recurso1 y necesita
a recurso2 para continuar. Y proceso2 tiene asignado a recurso2, y necesita a 
recurso1 para continuar. Cada proceso espera que el otro libere un recurso que 
no liberará hasta que el otro libere su recurso, lo cual no sucederá si el 
primero no libera su recurso, a eso se le conoce como un deadlock.

2. Para implementar las primitivas de sincronización los sistemas operativos 
apagan las interrupciones para simular operaciones atómicas y conseguir exclusión
mutua ¿Es posible obtener exclusión mutua sin apagar las interrupciones? Si la 
respuesta es afirmativa ¿De qué forma se implementa?
Sí. Se puede implementar con una estructura Spinlock, la cual es una primitiva 
de sincronización que brinda exclusión mutua de bajo nivel que "gira" mientras 
espera para adquirir un bloqueo.

3. ¿Por qué es mejor utilizar primitivas de sincronización en lugar de apagar y 
encender la interrupciones directamente?
Porque no estaríamos tomando en cuenta la gestión de procesos solo apagando 
interrupciones.

4. En pintos un lock es un semaphore inicializado en 1 ¿Por qué no usar 
directamente un semaphore?
Aunque no son muy comunes existen semáforos con valores mayores a 1, aunque no 
son usados frecuentemente, con el lock aseguramos que su valor siempre será 1.
