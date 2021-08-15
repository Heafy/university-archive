import matplotlib.pyplot as pl
import numpy as np
from itertools import product, combinations
from matplotlib.patches import FancyArrowPatch
from mpl_toolkits.mplot3d import proj3d
from mpl_toolkits.mplot3d import Axes3D

fig = pl.figure("Operadores de dilatacion/contraccion")
ax = fig.gca(projection='3d')
ax.set_aspect("equal")

# Dibuja un punto
ax.scatter([0],[0],[0],color="g",s=100)

# Clase para dibujar un vector
class Vector3D(FancyArrowPatch):
	#Constructor de la clase Vector3D
    def __init__(self, xs, ys, zs, *args, **kwargs):
        FancyArrowPatch.__init__(self, (0,0), (0,0), *args, **kwargs)
        self._verts3d = xs, ys, zs

   	# Funcion para dibujar el vector
    def draw(self, renderer):
        xs3d, ys3d, zs3d = self._verts3d
        xs, ys, zs = proj3d.proj_transform(xs3d, ys3d, zs3d, renderer.M)
        self.set_positions((xs[0],ys[0]),(xs[1],ys[1]))
        FancyArrowPatch.draw(self, renderer)

# Pedimos las coordenadas del vector en su posicion final en forma de cadena
coordX = raw_input("Introduce las coordenadas iniciales del vector: ")
# Se realiza un cast sobre la coordenada y se guarda en dos variables float
coordXAux = coordX.split(",")
x0 = float(coordXAux[0]) 
y0 = float(coordXAux[1])
z0 = float(coordXAux[2])

# Pedimos las coordenadas del vector en su posicion final en forma de cadena
coordY = raw_input("Introduce las coordenadas finales del vector: ")
# Se realiza un cast sobre la coordenada y se guarda en dos variables float
coordYAux = coordY.split(",")
x1 = float(coordYAux[0]) 
y1 = float(coordYAux[1])
z1 = float(coordYAux[2])

#Obtenemos el factor de dilatacion / contraccion
factor = input("Introduce el factor de dilatacion o contaccion del vector: ")
factor = float(factor)

if(factor < 1):
    strFactor = "contraccion"
elif(factor > 1):
    strFactor = "dilatacion"

# Se crean los objetos instancia de la clase Vector3D a partir de las coordenadas
a = Vector3D([x0,x1],[y0,y1],[z0,z1], mutation_scale=20, lw=1, arrowstyle="-|>", color="k")
ax.add_artist(a)
# Sus transformaciones lineales
# Contraccion-dilatacion
b = Vector3D([x0,x1*factor],[y0,y1*factor],[z0,z1*factor], mutation_scale=20, lw=1, arrowstyle="-|>", color="k")
ax.add_artist(b)
# Tomamo*factors el arreglo de las coordenadas creadas anteriormente y lo casteamos a
# variables float para manipularlos mejor, esto nos ayudara a definir los limites
for i in range(len(coordXAux)):
    coordXAux[i] = float(coordXAux[i])
    coordYAux[i] = float(coordYAux[i])

# Obtenemos el valor maximo de ambas coordenadas
if(max(coordXAux) > max(coordYAux)):
    max = max(coordXAux)
else:
    max = max(coordYAux)

# Los limites sobre los cuales se dibuja la grafica
ax.set_xlim([(max*-1)-2,max+2])
ax.set_ylim([(max*-1)-2,max+2])
ax.set_zlim([(max*-1)-2,max+2])

# Creamos los ejes x,y,z a partir de los objetos Vectores que creamos
x = Vector3D([0,max+2],[0,0],[0,0], mutation_scale=20, lw=1, arrowstyle="-", color="k")
y = Vector3D([0,0],[0,max+2],[0,0], mutation_scale=20, lw=1, arrowstyle="-", color="k")
z = Vector3D([0,0],[0,0],[0,max+2], mutation_scale=20, lw=1, arrowstyle="-", color="k")
ax.add_artist(x)
ax.add_artist(y)
ax.add_artist(z)



# Las etiquetas para poder diferencias los ejes
ax.set_xlabel('Eje x')
ax.set_ylabel('Eje y')
ax.set_zlabel('Eje z')
# Mostramos la grafica
pl.show()