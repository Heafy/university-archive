import socket
import random

HOST = "127.0.0.1"
PORT = 5000
counter = 0

#creamos un socket
mySocket = socket.socket( socket.AF_INET, socket.SOCK_STREAM )
#Enlace
mySocket.bind( ( HOST, PORT ) )
file = open("server.txt","a")


while True:
	print "Esperando conexion"

	mySocket.listen( 1 )
	#Esperamos conexion
	connection, address = mySocket.accept()
	counter += 1
	print "Conexion", counter, "recibida de :", address[ 0 ]
	connection.send( "SERVIDOR>> Conexion exitosa\n" + "---------|\n")
	clientMessage = connection.recv( 1024 )


	while clientMessage != "FINALIZAR":
		if not clientMessage:
			break
		print "Mensaje del cliente: " + str(clientMessage)

		serverMessage = "Mensaje guardado"

		file.write(str(clientMessage)+"\n")

		#serverMessage = raw_input( "SERVIDOR>>> " )
		connection.send( "SERVIDOR>>> " + serverMessage )
		clientMessage = connection.recv( 1024 )
	#Cerramos conexion
	print "Conexion terminada"
	file.close()
	file = open("server.txt","a")
	connection.close()
