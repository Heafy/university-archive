# BUG: Doble Enter no permite cerrar el ciclo de lectura de entrada
# clientMessage diferente de null

import socket
import random

HOST = "127.0.0.1"
PORT = 5000
counter = 0

#creamos un socket
mySocket = socket.socket( socket.AF_INET, socket.SOCK_STREAM )
#Enlace
mySocket.bind( ( HOST, PORT ) )

# El arreglo que contiene todas las frutas que se pueden seleccionar
fruits = ["MANGO", "SANDIA", "GUAYABA", "COCO", "TORONJA", "GUANABANA", "HIGO", \
        "TAMARINDO", "MANZANA", "MELON", "PLATANO", "CIRUELA", "LIMON", "CEREZA", \
        "UVA", "PAPAYA", "KIWI", "NARANJA", "MANDARINA", "PERA", "FRESA"]



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

r = True
while r:
	print "Esperando conexion"
	mySocket.listen( 1 )
	#Esperamos conexion
	connection, address = mySocket.accept()
	counter += 1
	print "Conexion", counter, "recibida de :", address[ 0 ]

	# Se escoje una fruta al azar de este arreglo que sera la fruta  adivinar
	fruit = raw_input("Selecciona la palabra: ")
	#	fruit = fruits[random.randrange(len(fruits))]
	fillArrays(fruit)
	opportunity = 6
	print "Palabra del juego: " + str(fruit)
	#Procesamos la conexion
	connection.send( "SERVIDOR>> Conexion exitosa\n" + "---------|\n" + toString(word) + "\nEscribe los caracteres")
	clientMessage = connection.recv( 1024 )

	while clientMessage != "FINALIZAR":
		if not clientMessage:
			break
		print "Caracter del cliente: " + str(clientMessage)
		
		clientMessage = clientMessage.upper()
		aux = swap(fruitArr, word, str(clientMessage))
		if "1" not in aux:
			opportunity -= 1
		serverMessage = "\n" + drawHangman(opportunity) + "\n" + toString(word)
		print word
		 # Revisa el estado del juego, en caso de que gane o pierda
		if(checkSpaces(word) == True):
			serverMessage = serverMessage + "\nSERVIDOR>> FELICIDADES, HAS GANADO"
			print "El jugador ha ganado."
			r = False
		if(opportunity == 0):
			serverMessage = serverMessage + "\nSERVIDOR>> LO SIENTO, PERDISTE \nLA PALABRA ERA: " + str(fruit)
			print "El jugador ha perdido."
			r = False

		#serverMessage = raw_input( "SERVIDOR>>> " )
		connection.send( "SERVIDOR>>> " + serverMessage )
		clientMessage = connection.recv( 1024 )
	#Cerramos conexion
	print "Conexion terminada"
	connection.close()