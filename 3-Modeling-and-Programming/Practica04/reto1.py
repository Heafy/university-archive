#Una funcion recursiva que hace el algoritmo 3n+1 
#Hasta que n = 1
def reto1(n):
    if(n ==1):
        return 1
    if(n%2 == 0):
        n = n/2
    else:
        n = 3*n+1
    return reto1(n)+1

# Funcuion que se encarga de calcular la maxima longitud del ciclo que recibe
# Lo regresa en una cadena con el formato que se pide
def max_reto1(i, j):
    maxReto1 = 0
    for n in xrange(i, j+1):
        maxReto1= max(reto1(n), maxReto1)
    cad =  str(i) + " " + str(j) + " " + str(maxReto1)
    return cad

#Pedimos lectura de archivo que contiene la entrada para el programa
f = open("archivo.txt", "r")
#Pedimos escritura de archivo que guardara la salida del programa
g = open("salida.txt", "w")
arr = []
for linea in f.readlines():
    valor= linea
    arr = valor.split(" ")
    #Se hace un cast para que sean enteros, en otro caso los interpreta como cadenas
    int1 = int(arr[0])
    int2 = int(arr[1])
    #Se escribe en el archivo de salida
    g.write(max_reto1(int1, int2)+"\n")
f.close()
g.close()
