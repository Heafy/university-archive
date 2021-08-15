# Modulos de Python
import socket
import sys

# Recibe de la línea de comandos un argumento,
# la direccion IP del servidor o nombre de dominio.
def proccessArguments():
    args = sys.argv
    #print("[1] Host: " + args[1])
    #print("[2] HTTP Method: " + args[2])
    #print("[3] URL: " + args[3])
    #print("[4] User agent: " + args[4])
    #print("[5] Encoding: " + args[5])
    #print("[6] Connection: " + args[6])
    return args

# Construye la peticion HTTP a partir de los argumentos recibidos
def constructHTTPRequest(arguments):
    CRLF = "\r\n"
    HTTP_method = arguments[2] + " " + arguments[3] + " HTTP/1.1"
    host = "Host: " + arguments[1]
    user_Agent = "User Agent: " + "test user"
    encoding = "Content-Encoding: " + arguments[5]
    connection = "Connection: " + arguments[6]
    HTTP_request = HTTP_method + CRLF + host + CRLF + user_Agent + CRLF + encoding + CRLF + connection + CRLF + CRLF
    #print(HTTP_request)
    return HTTP_request

def TCPconnection(host_server, HTTP_request):

    # Crea un socket de TCP
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    # Conexion del cliente al servidor dado,
    # en el puerto 80 para HTTP
    s.connect((host_server, 80))

    # Envia la peticion HTTP al servidor
    s.send(HTTP_request.encode("utf-8"))

    # Mientras reciba informacion del servidor, la guardara
    # en HTTP_response, e imprimirla en pantalla
    while True:
        HTTP_response = s.recv(1024).decode("utf-8")
        if HTTP_response == "": break
        print(HTTP_response)

    # Una vez que la recepcion de informacion ha terminado
    # se cierra la conexion con el servidor
    s.close()

    print("\n\nConexion con el servidor finalizada\n")

arguments       = proccessArguments()
HTTP_request    = constructHTTPRequest(arguments)
TCPconnection(arguments[1], HTTP_request)