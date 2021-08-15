#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>

struct node{
    int data;
    struct node *next;
};

struct node *cabeza = NULL;
struct node *rabo = NULL;

bool esNula(void)
{
	if(cabeza == NULL)
		return true;
	else
		return false;
}

struct node* creaLista(int data){
    struct node *aux = (struct node*)malloc(sizeof(struct node));
    aux->data = data;
    aux->next = NULL;
    cabeza = rabo = aux;
    return aux;
}

struct node* insertaPrin(int data){
	if(esNula())
		return (creaLista(data));

	struct node *aux = (struct node*)malloc(sizeof(struct node));
	if(aux == NULL){
		printf("Algo salio terriblemente mal.");
		return NULL;
	}
	aux->data = data;
	aux->next = cabeza;
	cabeza = aux;
	return aux;
}

struct node* insertaFin(int data){
	if(esNula())
		return (creaLista(data));

	struct node *aux = (struct node*)malloc(sizeof(struct node));
	if(aux == NULL)
	{
		printf("Algo salio terriblemente mal.");
		return NULL;
	}
	aux->data = data;
	rabo->next = aux;
	rabo = aux;
	return aux;
}

/* Funcion que elimina el primer data de la lista. */
void eliminaPrin(void)
{
    cabeza = cabeza->next;
    //free(cabeza);   
}

/* Funcion que elimina el ultimo data de la lista. */
void eliminaFin(void)
{
    struct node *aux = cabeza;
    // Recorre hasta encontrar el penultimo node de la lista
    for(aux; aux->next != rabo; aux = aux->next)
    {}
    rabo->next=aux;
    rabo= rabo->next;
    rabo->next = NULL;
}

/* Funcion que inserta un data que se pasa como parámetro en el indice
 * que se le pasa tambien como parametro. De no existir se inserta al final
 * de la lista.
 * Se da por entendido que el indice empieza desde 0.*/
void insertaInd(int data, int indice)
{
	struct node *temp, *izq, *der;
	der = cabeza;
	for(int i = -1; i<indice; i++)
	{
		izq = der;
		der = der->next;
	}
	temp = (struct node*)malloc(sizeof(struct node));
	temp->data = data;
	izq->next = temp;
	izq = temp;
	izq->next = der;

}
/* Funcion que elimina el data que esta en el indice que se le pasa como 
 * parametro. De no existir no borra nada.
 * Se da por entendido que el indice empieza desde 0.*/
void eliminaInd(int indice)
{
	struct node *aux = cabeza;
	for(indice=indice-1; indice!=0; indice--)
	{
		aux = aux->next; 
	}
	aux->next = aux->next->next;	
}

/* Funcion que elimina el data pasado como parametro, si existe en la lista.*/
bool eliminaElem(int data){
	struct node *aux = cabeza;
	int i = 0;
	while(aux != NULL)
	{
		if(aux->data == data)
		{
            // Si encuentra el data lo elimina conforme al indice
			eliminaInd(i);
			return true;
		}
		aux = aux->next;
		i++;
	}
    // Si recorrio toda la lista y no encuentra el data no borra nada
	return false;
}

/* Funcion que busca si existe el data de la lista y regresa
 * el indice de la primera aparicion. Si no existe regresa -1.*/
int buscaElem(int data)
{
    struct node *aux = cabeza;
    int indice = 0;
    while(aux != NULL)
    {
        if(aux-> data == data)
        {
            return indice;
        }
        aux = aux->next;
        indice++;
    }
    return -1;
}

/* Funcion que hace una copia la de lista que recibe.*/
struct node* copia(struct node* original)
{
    if(original == NULL)
        return NULL;
    struct node *aux = (struct node *)malloc(sizeof(struct node));
    aux->data=original->data;
    aux->next=copia(original->next);
    return aux;
} 

/* Funcion que imprime el contenido de la lista. */
void imprime()
{
    struct node *aux = cabeza;
    if(esNula())
    {
        printf("[]");
        return;
    }


    printf("[");
    while(aux != rabo)
    {
    	//Imprime hasta el penultimo data
        printf("%d, ",aux->data);
        aux = aux->next;
    }
    // Imprime el ultimo data
    printf("%d]\n", aux->data);
}


int main()
{
    // 1. Crea una lista con 10 datas a discrecion
	printf("Crea una lista con 10 datas a discrecion\n");
    for(int i=0; i<=10; i++)
    {
        insertaFin(i);
    }
    imprime();
    printf("\n");

    // 2. Duplicar la lista
    struct node *laCopia = copia(cabeza);
    printf("Duplica la lista\n");
    imprime();
    printf("\n");

    // 3. Elimina los dos primeros datas de la primer lista
    printf("Elimina los dos primeros datas de la primer lista\n");
    eliminaPrin();
    eliminaPrin();
    imprime();
    printf("\n");

    // 4. Eliminar los dos ultimos datas de la segunda lista
    cabeza = rabo = NULL;
    for(int i=0; i<=10; i++)
    {
        insertaFin(i);
    }
    printf("Eliminar los dos ultimos datas de la segunda lista\n");
    eliminaFin();
    eliminaFin();
    imprime();
    printf("\n");

    // 5. Concatenar las dos listas
    //printf("Concatena las dos listas");   FALTA IMPLEMENTACIÓN
    return 0;
    
}