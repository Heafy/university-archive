import socket

HOST = "127.0.0.1"
PORT = 5000

# creamos un socket
print "Intentamos conectarnos"
mySocket = socket.socket( socket.AF_INET, socket.SOCK_STREAM )
#Conexion al servidors
mySocket.connect( ( HOST, PORT ) )
print "Estamos conectados al servidor\n" + "Introduce un mensaje\n"
#Procesamos conexion
serverMessage = mySocket.recv( 1024 )

while serverMessage != "FINALIZAR":
	if not serverMessage:
		break
	print serverMessage

	clientMessage = raw_input( "CLIENTE>>> " )
	mySocket.send(clientMessage)
	serverMessage = mySocket.recv( 1024 )

#Cerramos comexion
print "Conexion terminada"
mySocket.close()