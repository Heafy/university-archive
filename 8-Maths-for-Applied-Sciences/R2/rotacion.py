'''
Programa para poder graficar vectores y poder obtener sus operadores de 
rotacion a traves de transformaciones lineales
'''
import pylab as pl
import numpy as np

pl.figure("Operadores de Rotacion")

# Pedimos las coordenadas del vector en su posicion final en forma de cadena
coordX = raw_input("Introduce las coordenadas iniciales del vector: ")
# Se realiza un cast sobre la coordenada y se guarda en dos variables float
coordXAux = coordX.split(",")
x0 = float(coordXAux[0]) 
y0 = float(coordXAux[1])

# Pedimos las coordenadas del vector en su posicion final en forma de cadena
coordY = raw_input("Introduce las coordenadas finales del vector: ")
# Se realiza un cast sobre la coordenada y se guarda en dos variables float
coordYAux = coordY.split(",")
x1 = float(coordYAux[0]) 
y1 = float(coordYAux[1])


#Obtenemos el factor de dilatacion / contraccion
factor = input("Introduce el factor de rotacion del vector: ")
factor = float(factor)
print factor

#Obtenemos las nuevas coordenadas del vector a partir de su transformacion lineal
x = np.radians(factor)
y = np.radians(factor)
x = np.cos(x) 
y = np.sin(y)

x2 = (x*x1)+(-y*y1)
y2 = (y*x1)+(x*y1)
print x2
print y2

#Dibujamos los ejes x,y en la grafica
pl.axhline(0, color='gray')
pl.axvline(0,color='gray')

# Dibuja el vector a partir de las coordenadas recibidas
ax = pl.axes()
# TODO Arreglar medida de la cabeza
original = ax.arrow(x0, y0, (x1-x0), (y1-y0), head_width=0.3, head_length=0.3, color='red')
# Rotacion del vector
rotacion = ax.arrow(x0, y0, (x2-x0), (y2-y0), head_width=0.3, head_length=0.3, color='green')

# Creamos las labels para mostrar su informacion
pl.legend([original, rotacion], ['Original', 'Rotacion'])
ax.set_xlabel('Eje x')
ax.set_ylabel('Eje y')

# Obtiene el valor maximo entre ambas coordenadas x
if(x0>x1):
	xLim = x0
else:
	xLim = x1

# Revisa que el valor sea mayor a 0
# En otro caso, lo vuelve positivo
if(xLim < 0):
	xLim = xLim*(-1)

# Obtiene el valor maximo entre ambas coordenadas y
if(y0>y1):
	yLim = y0
else:
	yLim = y1

# Revisa que el valor sea mayor a 0
# En otro caso, lo vuelve positivo
if(yLim < 0):
	yLim = yLim*(-1)

# Obtiene el valor maximo para volver mas uniforme el plano
if(xLim > yLim):
	maxLim = xLim
else:
	maxLim = yLim


# Establecemos limites un poco mayores a las coordenadas dadas
pl.xlim(maxLim*(-1)-4, maxLim+6)
pl.ylim(maxLim*(-1)-4, maxLim+6)
# Se dibuja la grafica
pl.show()
