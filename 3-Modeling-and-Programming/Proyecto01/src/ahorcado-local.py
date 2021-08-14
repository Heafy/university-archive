import random

# El arreglo que contiene todas las frutas que se pueden seleccionar
fruits = ["MANGO", "SANDIA", "GUAYABA", "COCO", "TORONJA", "GUANABANA", "HIGO", \
        "TAMARINDO", "MANZANA", "MELON", "PLATANO", "CIRUELA", "LIMON", "CEREZA", \
        "UVA", "PAPAYA", "KIWI", "NARANJA", "MANDARINA", "PERA", "FRESA"]

# Se escoje una fruta al azar de este arreglo que sera la fruta  adivinar
fruit = fruits[random.randrange(len(fruits))]

# La fruta seleccionada, pero en un arreglo
fruitArr = []

# El arreglo que contendra en primera instancia los espacios vacios
# Despues se ira sustituyendo por los caracteres que introduzca el usuario
word = []

#Funcion para generar espacios en base a la cadena que recibe
def fillArrays(cad):
        for i in range(len(cad)):
                word.append("_")
                fruitArr.append(fruit[i])

# Funcion que revisa si todavia quedan espacios en el arreglo que recibe
# arr - el arreglo que recibe
# Return False - si encuentra al menos un espacio en el arreglo
# Return True - si ya no hay espacios en el arreglo
def checkSpaces(arr):
        for i in range(len(arr)):
                if(arr[i] == "_"):
                        return False
        return True

# Funcion que intercambia los elementos de frutaArr por los elementos de espacio
# La variable b es un auxiliar para saber si un elemento se altera
# arr1 - Arreglo de fruta
# arr2 - Arreglo con espacios
# car - Cadena con el caracter introducido
# Return b - la cadena con 1 o 0
def swap(arr1, arr2, car):
        b = ""
        for i in range(len(arr1)):
                if(car == arr1[i]):
                        arr2[i] = arr1[i]
                        b += "1"
                else:
                        b += "0"
        return b

# Funcion que se encarga de dibujar el muneco del Ahorcado
# Actuara sobre el numero (oportunidades) y regresara la cadena correspondiente
# A falta de un switch-case, anidaremos con bloques if-else
# num - Numero de intentos que le quedan, para saber que resultado mostrar
def drawHangman(num):
        start = "---------|\n"
        head = "         O\n"
        body1 = "         |\n"
        body2 = "        ~|\n"
        body3 = "        ~|~\n"
        feet1 = "        /\n"
        feet2 = "        / \\"
        if(num == 6):
                return start
        elif(num == 5):
                return start + head
        elif(num == 4):
                return start + head + body1 
        elif(num == 3):
                return start + head + body2
        elif(num == 2):
                return start + head + body3
        elif(num == 1):
                return start + head + body3 + feet1
        elif(num == 0):
                return start + head + body3 + feet2

# Funcion para imprimir el arreglo en un formato bonito
def toString(arr):
        a = ""
        for i in arr:
                a += i + " "
        return a

# Funcion principal del programa
# Modela todo el juego
def letsPlay():
        fillArrays(fruit)
        opportunity = 6
        while(opportunity > 0):
                # Imprimimos la fruta (solo para pruebas)
                print fruit
                # Muestra las oportunidades
                print "Tienes " + str(opportunity) + " oportunidad(es).\n"
                # Pedimos la entrada al usuario
                char = raw_input("Introduce un caracter: ")
                char = char.upper()
                aux = swap(fruitArr, word, char)
                # Usamos el auxiliar para reducir las oportunidades
                if "1" not in aux:
                        opportunity -= 1
                # Mostramos el progreso del juego junto con el dibujo
                print drawHangman(opportunity)
                print toString(word)
                # Revisa el estado del juego, en caso de que gane o pierda
                if(checkSpaces(word) == True):
                        print "Felicidades, has ganado."
                        break
                if(opportunity == 0):
                        print "Perdiste"
                        break

# Llamamos la funcion principal
letsPlay()
