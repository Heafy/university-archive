'''
Programa para poder graficar vectores y poder obtener sus operadores de 
proyeccion ortogonal a traves de transformaciones lineales
'''
import pylab as pl

pl.figure("Operadores de Proyeccion Ortogonal")

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

#Dibujamos los ejes x,y en la grafica
pl.axhline(0, color='gray')
pl.axvline(0,color='gray')

# Dibuja el vector a partir de las coordenadas recibidas
ax = pl.axes()
# TODO Arreglar medida de la cabeza
original = ax.arrow(x0, y0, (x1-x0), (y1-y0), head_width=0.3, head_length=0.3, color='red')
# Proyeccion Ortogonal respecto al eje x
axisx = ax.arrow(x0, y0, (x1-x0), (y1-y1), head_width=0.3, head_length=0.3, color = 'green', label="Respecto al eje x")
# Proyeccion ortogonal respecto al eje y
axisy = ax.arrow(x0, y0, (x1-x1), (y1-y0), head_width=0.3, head_length=0.3, color='blue', label="Respecto al eje y")

# Creamos las labels para mostrar su informacion
pl.legend([original, axisx, axisy], ['Original', 'Sobre el eje x', 'Sobre el eje y'])
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
