#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>

/* Programa para representar listas ligadas en C
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
    struct nodo *ptr = cabeza;
    while(ptr != NULL)
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
    struct nodo *ptr = cabeza;
    // Recorre hasta encontrar el penultimo nodo de la lista
    for(ptr; ptr->siguiente != rabo; ptr = ptr->siguiente)
    {}
    rabo->siguiente=ptr;
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
	struct nodo *ptr = cabeza;
	for(indice=indice-1; indice!=0; indice--)
	{
		ptr = ptr->siguiente; 
	}
	ptr->siguiente = ptr->siguiente->siguiente;	
}

/* Funcion que elimina el elemento pasado como parametro, si existe en la lista.*/
bool eliminaElem(int elemento){
	struct nodo *ptr = cabeza;
	int i = 0;
	while(ptr != NULL)
	{
		if(ptr->elemento == elemento)
		{
            // Si encuentra el elemento lo elimina conforme al indice
			eliminaInd(i);
			return true;
		}
		ptr = ptr->siguiente;
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
    struct nodo *ptr = (struct nodo *)malloc(sizeof(struct nodo));
    ptr->elemento=original->elemento;
    ptr->siguiente=copia(original->siguiente);
    return ptr;
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
    // 1. Crea una lista con 10 elementos a discrecion
	printf("Crea una lista con 10 elementos a discrecion\n");
    for(int i=0; i<=10; i++)
    {
        insertaFin(i);
    }
    imprime();
    printf("\n");

    // 2. Duplicar la lista
    struct nodo *laCopia = copia(cabeza);
    printf("Duplica la lista\n");
    imprime();
    printf("\n");

    // 3. Elimina los dos primeros elementos de la primer lista
    printf("Elimina los dos primeros elementos de la primer lista\n");
    eliminaPrin();
    eliminaPrin();
    imprime();
    printf("\n");

    // 4. Eliminar los dos ultimos elementos de la segunda lista
    cabeza = rabo = NULL;
    for(int i=0; i<=10; i++)
    {
        insertaFin(i);
    }
    printf("Eliminar los dos ultimos elementos de la segunda lista\n");
    eliminaFin();
    eliminaFin();
    imprime();
    printf("\n");

    // 5. Concatenar las dos listas
    //printf("Concatena las dos listas");   FALTA IMPLEMENTACIÓN
    return 0;
    
}