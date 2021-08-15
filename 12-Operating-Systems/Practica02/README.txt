Integrantes del equipo:
- Estrada Gomez Cesar Derian
- Martínez Flores Jorge Yael
- Sanchez Morales Rodrigo Alejandro
- Tadeo Guillen Diana G.

1. En el PCB de Pintos (struct thread) no hay una referencia directa al código 
del thread, ¿De qué forma sabemos en que instrucción se bloqueó el thread por 
la acción del calendarizador?
Porque el thread no estaría en la ready_list

2. ¿De qué forma podemos obtener el PCB del thread que está en ejecución?
Con thread_current();

3. ¿Qué se tiene que hacer para cambiar un thread en ejecución por otro?
Llamar a la función switch_threads (cur, next); que llama a la rutina para
cambiar los threads de ejecución, está escrita en ensamblador.

4. En Pintos, ¿Qué diferencias existen entre el thread idle y el thread main?
Se manejan principalmente en dos struct diferentes: struct thread *idle_thread; y
struct thread *initial_thread; y el thread idle se ejecuta cuando ningun otro
thread esta ejecutandose