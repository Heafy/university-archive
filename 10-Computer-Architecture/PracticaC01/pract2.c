#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<stdbool.h>

/* Programa para trabajar con listas ligadas en C
 * Una lista es un nodo con una referencia al siguiente nodo.
 * @author Jorge Martinez Flores*/

/* Define la estructura nodo para la lista recursivamente
 * Un nodo es un int elemento que almacena el dato y una referencia
 * alsiguiente nodo.*/
struct nodo
{
    int elemento;
    struct nodo *siguiente;
};

/* Iniciamos la cabeza de la lista como vacia.*/
struct nodo *cabeza = NULL;
/* Iniciamos la cabeza de la lista como vacia.*/
struct nodo *rabo = NULL;

/* Funcion que nos indica si una lista es vacia. */
bool esNula(void)
{
    // Si la cabeza de la nula es vacia no tiene demas elementos
	if(cabeza == NULL)
		return true;
	else
		return false;
}

/* Funcion que crea una lista a partir del elemento que recibe
 * como parámetro.
 * Sirve para insertar el primer elemento la mayoŕia de las veces.*/
struct nodo* creaLista(int elemento)
{
    struct nodo *ptr = (struct nodo*)malloc(sizeof(struct nodo));
    if(ptr == NULL)
    {
        printf("Algo salio terriblemente mal");
        return NULL;
    }
    ptr->elemento = elemento;
    ptr->siguiente = NULL;

    cabeza = rabo = ptr;
    return ptr;
}

/* Funcion que inserta el elemento pasado como parametro al
 * principio de la lista.*/
struct nodo* insertaPrin(int elemento)
{
	if(esNula())
		return (creaLista(elemento));

	struct nodo *ptr = (struct nodo*)malloc(sizeof(struct nodo));
	if(ptr == NULL)
	{
		printf("Algo salio terriblemente mal.");
		return NULL;
	}
	ptr->elemento = elemento;
	ptr->siguiente = cabeza;
	cabeza = ptr;
	return ptr;
}


/* Funcion que inserta el elemento pasado como parametro al
 * final de la lista.*/
struct nodo* insertaFin(int elemento)
{
	if(esNula())
		return (creaLista(elemento));

	struct nodo *ptr = (struct nodo*)malloc(sizeof(struct nodo));
	if(ptr == NULL)
	{
		printf("Algo salio terriblemente mal.");
		return NULL;
	}
	ptr->elemento = elemento;
	rabo->siguiente = ptr;
	rabo = ptr;
	return ptr;

}

int longitud(void)
{
    int contador = 0;
    struct nodo *tmp = cabeza;
    while(tmp != NULL)
    {
        contador++;
    }
    return contador;
}

/* Funcion que elimina el primer elemento de la lista. */
void eliminaPrin(void)
{
    cabeza = cabeza->siguiente;
    //free(cabeza);   
}

/* Funcion que elimina el ultimo elemento de la lista. */
void eliminaFin(void)
{
    struct nodo *tmp = cabeza;
    // Recorre hasta encontrar el penultimo nodo de la lista
    for(tmp; tmp->siguiente != rabo; tmp = tmp->siguiente)
    {}
    rabo->siguiente=tmp;
    rabo= rabo->siguiente;
    rabo->siguiente = NULL;
}

/* Funcion que inserta un elemento que se pasa como parámetro en el indice
 * que se le pasa tambien como parametro. De no existir se inserta al final
 * de la lista.
 * Se da por entendido que el indice empieza desde 0.*/
void insertaInd(int elemento, int indice)
{
	struct nodo *temp, *izq, *der;
	der = cabeza;
	for(int i = -1; i<indice; i++)
	{
		izq = der;
		der = der->siguiente;
	}
	temp = (struct nodo*)malloc(sizeof(struct nodo));
	temp->elemento = elemento;
	izq->siguiente = temp;
	izq = temp;
	izq->siguiente = der;

}
/* Funcion que elimina el elemento que esta en el indice que se le pasa como 
 * parametro. De no existir no borra nada.
 * Se da por entendido que el indice empieza desde 0.*/
void eliminaInd(int indice)
{
	struct nodo *temp = cabeza;
	for(indice=indice-1; indice!=0; indice--)
	{
		temp = temp->siguiente; 
	}
	temp->siguiente = temp->siguiente->siguiente;	
}

/* Funcion que elimina el elemento pasado como parametro, si existe en la lista.*/
bool eliminaElem(int elemento){
	struct nodo *temp = cabeza;
	int i = 0;
	while(temp != NULL)
	{
		if(temp->elemento == elemento)
		{
            // Si encuentra el elemento lo elimina conforme al indice
			eliminaInd(i);
			return true;
		}
		temp = temp->siguiente;
		i++;
	}
    // Si recorrio toda la lista y no encuentra el elemento no borra nada
	return false;
}

/* Funcion que busca si existe el elemento de la lista y regresa
 * el indice de la primera aparicion. Si no existe regresa -1.*/
int buscaElem(int elemento)
{
    struct nodo *ptr = cabeza;
    int indice = 0;
    while(ptr != NULL)
    {
        if(ptr-> elemento == elemento)
        {
            return indice;
        }
        ptr = ptr->siguiente;
        indice++;
    }
    return -1;
}

/* Funcion que hace una copia la de lista que recibe.*/
struct nodo* copia(struct nodo* original)
{
    if(original == NULL)
        return NULL;
    struct nodo *temp = (struct nodo *)malloc(sizeof(struct nodo));
    temp->elemento=original->elemento;
    temp->siguiente=copia(original->siguiente);
    return temp;
} 

/* Funcion que imprime el contenido de la lista. */
void imprime()
{
    struct nodo *ptr = cabeza;
    if(esNula())
    {
        printf("[]");
        return;
    }


    printf("[");
    while(ptr != rabo)
    {
    	//Imprime hasta el penultimo elemento
        printf("%d, ",ptr->elemento);
        ptr = ptr->siguiente;
    }
    // Imprime el ultimo elemento
    printf("%d]\n", ptr->elemento);
}

int main(void)
{

	// Cadenas auxiliares
	char agregado[] = "Elemento agregado correctamente\n";
	char eliminado[] = "Elemento eliminado correctamente\n";
	char error[] = "Opcion incorrecta, intenta de nuevo\n";
	
	printf("Menu de la practica 2\n\n");
	int num;
	while(num != 7)
	{
		printf("Introduzca el numero de la opcion que desea: \n");
		printf("1. Agregar un elemento.\n");
		printf("2. Borrar un elemento\n");
		printf("3. Mostrar la lista\n");
		printf("4. Vaciar la lista\n");
		/*
		printf("5. Agregar una lista manualmente\n");
		printf("6. Cargar la lista a un archivo\n");
		printf("7. Cargar una lista de un archivo\n");
		printf("8. Salir del programa\n");
		*/
		scanf("%d", &num);
		//printf(num = %d\n", num);

		int opcion;
		int opcion1;

		switch(num)
		{
			// Agregar un elemento
			case 1:
				printf("\nIntroduce en que orden deseas que se agregue\n");
				printf("1. Agregar al inicio\n");
				printf("2. Agregar al final\n");
				printf("3. Agregar dado un indice\n");
				scanf("%d", &opcion);

				switch(opcion)
				{
					// Agregar al inicio
					case 1:
						printf("Introduce el numero que se va a agregar al inicio de la lista:");
						scanf("%d", &opcion1);
						insertaPrin(opcion1);
						printf("%s\n", agregado);
						break;

					// Agregar al final
					case 2: 
						printf("Introduce el numero que se va a agregar al final de la lista:");
						scanf("%d", &opcion1);
						insertaFin(opcion1);
						printf("%s\n", agregado);
						break;

					// Agregar dado un indice
					case 3:
						if(esNula())
						{
							printf("La lista esta vacia, intenta agregar de otra manera\n");
							break;
						}
						printf("Introduce el indice de la lista donde deseas agregar el elemento:");
						int iterador;
						scanf("%d", &iterador);

						printf("Introduce el elemento a agregar\n");
						scanf("%d", &opcion1);

						insertaInd(opcion1, iterador);
						printf("%s\n", agregado);
						break;

					// Caso por defecto
					default:
						printf("%s\n", error);	
				}
				break;

			// Borrar un elemento
			case 2:
				printf("\nIntroduce el numero del elemento que deseas eliminar\n");
				printf("1. Eliminar el primer elemento\n");
				printf("2. Eliminar el ultimo elemento\n");
				printf("3. Agregar dado un indice\n");
				scanf("%d", &opcion);
				switch(opcion)
				{
					// Eliminar el primer elemento
					case 1:
						eliminaPrin();
						printf("%s\n", eliminado);
						break;

					// Eliminar el ultimo elemento
					case 2:
						eliminaFin();
						printf("%s\n", eliminado);
						break;

					// Agregar dado un indice
					case 3:
						printf("Introduce el indice de la lista que deseas eliminar:");
						int iterador;
						scanf("%d", &iterador);
						eliminaInd(iterador);
						printf("%s\n", eliminado);
						break;

					// Caso por defecto
					default:
						printf("%s\n", error);
				}
				break;

			// Mostrar un elemento
			case 3:
				printf("La lista actual: \n");
				imprime();
				break;

			// Vaciar la lista
			case 4:
				cabeza = NULL;
				break;

			/*
			// Agregar una lista manualmente
			case 5:
				
				// Para hacer un split de cadenas a partir de una coma
				printf("Introduce una lista en forma de cadena\n");
				printf("La lista se introducira al final de la lista\n");
				printf("Ejemplo: \n");
				printf("1, 2, 3 , 4, ...\n");
				char str[20];
				scanf("%s", str);
				//printf("Cadena introducida:%s\n", str);
				const char s[2] = ", ";
				char *token;

				//Obtiene el primer token 
				token = strtok(str, s);

				//Revisa los demas tokens. 
				while( token != NULL)
				{
					//TODO Cast de string a int para aregar a la lista
					printf("%s\n", token);
					token = strtok(NULL, s);
				}
				
				break;

			// Cargar la lista a un archivo
			case 6:
				break;

			// Cargar una lista de un arcivo
			case 7:
				break;
			*/
			// Terminar el programa
			case 8:
				// No hace nada, pero si no se va al default
				break;
			default:
				printf("Opcion incorrecta, intenta de nuevo.\n\n");
		}


	}
	
	
	return 0;
}


