import pylab as pl
import numpy as np

'''
Programa para poder graficar vectores y poder obtener sus operadores con
sus respectivas transformaciones lineales a traves de 
transformaciones lineales en R2
@author: Jorge Martinez
'''

pl.figure("Operadores y Transformaciones Lineales en R2")
#Dibujamos los ejes x,y en la grafica
pl.axhline(0, color='gray')
pl.axvline(0,color='gray')

#Presentacion
print "PROGRAMA PARA REALIZAR TRANSFORMACIONES LINEALES EN R2"
print "Version 1.1"
print "Jorge Martinez"

	
#Pedimos saber que operador va a usar
print "\nIntroduce el operador que deasque se ejecute:\n" \
				+ "1.Reflexion\n" \
				+ "2.Proyeccion ortogonal\n" \
				+ "3.Rotacion\n" \
				+ "4.Dilatacion o contraccion"
operador = input("")
ax = pl.axes()

# Iteramos los diferentes casos dentro de un if-else (a falta de un switch-case)
# pl.legend() coloca las etiquetas de diferentes casos
# Operador de Reflexion
if(operador ==1):
	numVectores = input("Introduce cuantos vectores quieres reflejar: ")
	while(numVectores > 0):
		print "Vectores restantes: " + str(numVectores)
		# Pedimos las coordenadas del vector en su posicion final en forma de cadena
		coord = raw_input("Introduce las coordenadas del vector: ")
		coord = coord.replace("(","")
		coord = coord.replace(")","")
		# Se realiza un cast sobre la coordenada y se guarda en dos variables float
		coordAux = coord.split(",")
		x1 = float(coordAux[0]) 
		y1 = float(coordAux[1])
		# Dibuja el vector original a partir de las coordenadas recibidas
		original = ax.arrow(0, 0, x1, y1, head_width=0.3, head_length=0.3, color='red')
		# Reflexion respecto al eje x
		axisx = ax.arrow(0, 0, x1, y1*(-1), head_width=0.3, head_length=0.3, color = 'green', label="Respecto al eje x")
		# Reflexion respecto al eje y
		axisy = ax.arrow(0, 0, x1*(-1), y1, head_width=0.3, head_length=0.3, color='blue', label="Respecto al eje y")
		# Reflexion respecto al eje y = x
		axisyx = ax.arrow(0, 0, y1, x1, head_width=0.3, head_length=0.3, color='purple', label="Respecto al eje y=x")
		numVectores = numVectores-1
	pl.legend([original, axisx, axisy, axisyx], ['Original', 'Respecto al eje x', 'Respecto al eje y', 'Respecto al eje y = x'])

# Operador de Proyeccion ortogonal
elif(operador == 2):
	numVectores = input("Introduce cuantos vectores quieres proyectar: ")
	while(numVectores > 0):
		print "Vectores restantes: " + str(numVectores)
		# Pedimos las coordenadas del vector en su posicion final en forma de cadena
		coord = raw_input("Introduce las coordenadas del vector: ")
		coord = coord.replace("(","")
		coord = coord.replace(")","")
		# Se realiza un cast sobre la coordenada y se guarda en dos variables float
		coordAux = coord.split(",")
		x1 = float(coordAux[0]) 
		y1 = float(coordAux[1])
		# Dibuja el vector original a partir de las coordenadas recibidas
		original = ax.arrow(0, 0, x1, y1, head_width=0.3, head_length=0.3, color='red')
		# Proyeccion Ortogonal respecto al eje x
		axisx = ax.arrow(0, 0, x1, y1-y1, head_width=0.3, head_length=0.3, color = 'green', label="Respecto al eje x")
		# Proyeccion ortogonal respecto al eje y
		axisy = ax.arrow(0, 0, x1-x1, y1, head_width=0.3, head_length=0.3, color='blue', label="Respecto al eje y")
		numVectores = numVectores-1
	pl.legend([original, axisx, axisy], ['Original', 'Sobre el eje x', 'Sobre el eje y'])

# Operador de rotacion 
elif(operador == 3):
	numVectores = input("Introduce cuantos vectores quieres rotar: ")
	while(numVectores > 0):
		print "Vectores restantes: " + str(numVectores)
		# Pedimos las coordenadas del vector en su posicion final en forma de cadena
		coord = raw_input("Introduce las coordenadas del vector: ")
		coord = coord.replace("(","")
		coord = coord.replace(")","")
		# Se realiza un cast sobre la coordenada y se guarda en dos variables float
		coordAux = coord.split(",")
		x1 = float(coordAux[0]) 
		y1 = float(coordAux[1])
		#Obtenemos el factor de dilatacion / contraccion
		factor = input("Introduce el factor de rotacion del vector: ")
		factor = float(factor)
		# Convertimos de radianes a grados
		x = np.radians(factor)
		y = np.radians(factor)
		x = np.cos(x) 
		y = np.sin(y)
		# Se efectuan los operadores
		x2 = (x*x1)+(-y*y1)
		y2 = (y*x1)+(x*y1)
		# Dibuja el vector original a partir de las coordenadas recibidas
		original = ax.arrow(0, 0, x1, y1, head_width=0.3, head_length=0.3, color='red')
		# Rotacion del vector
		rotacion = ax.arrow(0, 0, x2, y2, head_width=0.3, head_length=0.3, color='green')
		numVectores = numVectores-1
	pl.legend([original, rotacion], ['Original', 'Rotacion'])

#Operador de Dilatacion-Contraccion
elif(operador == 4):
	numVectores = input("Introduce cuantos vectores quieres rotar: ")
	while(numVectores > 0):
		print "Vectores restantes: " + str(numVectores)
		# Pedimos las coordenadas del vector en su posicion final en forma de cadena
		coord = raw_input("Introduce las coordenadas del vector: ")
		coord = coord.replace("(","")
		coord = coord.replace(")","")
		# Se realiza un cast sobre la coordenada y se guarda en dos variables float
		coordAux = coord.split(",")
		x1 = float(coordAux[0]) 
		y1 = float(coordAux[1])
		#Obtenemos el factor de dilatacion / contraccion
		factor = input("Introduce el factor de dilatacion o contaccion del vector: ")
		factor = float(factor)
		# Dibuja el vector original a partir de las coordenadas recibidas
		original = ax.arrow(0, 0, x1, y1, head_width=0.3, head_length=0.3, color='red')
		# El vector con su dilatacion o contraccion
		factorV = ax.arrow(0, 0, x1*factor, y1*factor, head_width=0.3, head_length=0.3, color='green')
		numVectores = numVectores-1
	pl.legend([original, factorV], ['Original', 'Factor de dilatacion/contraccion'])

ax.set_xlabel('Eje x')
ax.set_ylabel('Eje y')

#Obtenemos el valor mayor introducido
if(x1 < 0):
	x1 = x1*(-1)

if(y1 < 0):
	y1 = y1*(-1)

if(x1 > y1):
	maxValue = x1
else:
	maxValue = y1

# Establecemos limites un poco mayores a las coordenadas dadas
pl.xlim(maxValue*(-1)-4, maxValue+6)
pl.ylim(maxValue*(-1)-4, maxValue+6)
# Se dibuja la grafica
pl.show()
	