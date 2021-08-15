import matplotlib.pyplot as pl
import numpy as np
from itertools import product, combinations
from matplotlib.patches import FancyArrowPatch
from mpl_toolkits.mplot3d import proj3d
from mpl_toolkits.mplot3d import Axes3D

'''
Programa para poder graficar vectores y poder obtener sus operadores con
sus respectivas transformaciones lineales a traves de 
transformaciones lineales en R3
@author: Jorge Martinez
'''
fig = pl.figure("Operadores y Transformaciones Lineales en R3")
ax = fig.gca(projection='3d')
ax.set_aspect("equal")

#Presentacion
print "PROGRAMA PARA REALIZAR TRANSFORMACIONES LINEALES EN R3"
print "Version 1.1"
print "Jorge Martinez"

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

#Pedimos saber que operador va a usar
print "\nIntroduce el operador que deasque se ejecute:\n" \
                + "1.Reflexion\n" \
                + "2.Proyeccion ortogonal\n" \
                + "3.Rotacion\n" \
                + "4.Dilatacion o contraccion"
operador = input("")

# Iteramos los diferentes casos dentro de un if-else (a falta de un switch-case)
# Operador de Reflexion
if(operador == 1):
    numVectores = input("Introduce cuantos vectores quieres reflejar: ")
    while(numVectores > 0):
        print "Vectores restantes: " + str(numVectores)
        # Se realiza un cast sobre la coordenada y se guarda en dos variables float
        coord = raw_input("Introduce las coordenadas del vector: ")
        coord = coord.replace("(","")
        coord = coord.replace(")","")
        # Se realiza un cast sobre la coordenada y se guarda en dos variables float
        coordAux = coord.split(",")
        x1 = float(coordAux[0]) 
        y1 = float(coordAux[1])
        z1 = float(coordAux[2])

        # Se crean el vector introducido por el usuario
        original = Vector3D([0,x1],[0,y1],[0,z1], mutation_scale=20, lw=1, arrowstyle="-|>", color="red")
        ax.add_artist(original)
        # Reflexion respecto al plano xy
        xy = Vector3D([0,x1],[0,y1],[0,-z1], mutation_scale=20, lw=1, arrowstyle="-|>", color="green")
        ax.add_artist(xy)
        # Reflexion respecto al plano xz
        xz = Vector3D([0,x1],[0,-y1],[0,z1], mutation_scale=20, lw=1, arrowstyle="-|>", color="blue")
        ax.add_artist(xz)
        # Reflexion respecto al plano yz
        yz = Vector3D([0,-x1],[0,y1],[0,z1], mutation_scale=20, lw=1, arrowstyle="-|>", color="purple")
        ax.add_artist(yz)
        numVectores = numVectores-1
    pl.legend([original, xy, xz, yz], ['Original', 'Respecto al eje xy', 'Respecto al eje xz', 'Respecto al eje yz'])

#Operador de Proyeccion Ortogonal
elif(operador == 2):
    numVectores = input("Introduce cuantos vectores quieres reflejar: ")
    while(numVectores > 0):
        print "Vectores restantes: " + str(numVectores)
        # Se realiza un cast sobre la coordenada y se guarda en dos variables float
        coord = raw_input("Introduce las coordenadas del vector: ")
        coord = coord.replace("(","")
        coord = coord.replace(")","")
        # Se realiza un cast sobre la coordenada y se guarda en dos variables float
        coordAux = coord.split(",")
        x1 = float(coordAux[0]) 
        y1 = float(coordAux[1])
        z1 = float(coordAux[2])

        # Se crean el vector introducido por el usuario
        original = Vector3D([0,x1],[0,y1],[0,z1], mutation_scale=20, lw=1, arrowstyle="-|>", color="red")
        ax.add_artist(original)
        # Proyeccion ortogonal respecto al plano xy
        xy = Vector3D([0,x1],[0,y1],[0,0], mutation_scale=20, lw=1, arrowstyle="-|>", color="green")
        ax.add_artist(xy)
        # Proyeccion ortogonal respecto al plano xz
        xz = Vector3D([0,x1],[0,0],[0,z1], mutation_scale=20, lw=1, arrowstyle="-|>", color="blue")
        ax.add_artist(xz)
        # Reflexion respecto al plano yz
        yz = Vector3D([0,0],[0,y1],[0,z1], mutation_scale=20, lw=1, arrowstyle="-|>", color="purple")
        ax.add_artist(yz)
        numVectores = numVectores-1
    pl.legend([original, xy, xz, yz], ['Original', 'Respecto al plano xz', 'Respecto al plano yz', 'Respecto al plano yz'])

#Operador de Rotacion
elif(operador == 3):
    numVectores = input("Introduce cuantos vectores quieres reflejar: ")
    while(numVectores > 0):
        print "Vectores restantes: " + str(numVectores)
        # Se realiza un cast sobre la coordenada y se guarda en dos variables float
        coord = raw_input("Introduce las coordenadas del vector: ")
        coord = coord.replace("(","")
        coord = coord.replace(")","")
        # Se realiza un cast sobre la coordenada y se guarda en dos variables float
        coordAux = coord.split(",")
        x1 = float(coordAux[0]) 
        y1 = float(coordAux[1])
        z1 = float(coordAux[2])
        #Obtenemos el factor de dilatacion / contraccion
        factor = input("Introduce el factor de rotacion del vector: ")
        factor = float(factor)
    
        #Obtenemos las nuevas coordenadas del vector a partir de su transformacion lineal
        xAux = np.radians(factor)
        yAux = np.radians(factor)
        zAux = np.radians(factor)
        costx = np.cos(xAux)*x1
        sintx = np.sin(xAux)*x1
        costy = np.cos(yAux)*y1
        sinty = np.sin(yAux)*y1
        costz = np.cos(zAux)*z1
        sintz = np.cos(zAux)*z1

        # Se crean el vector introducido por el usuario
        original = Vector3D([0,x1],[0,y1],[0,z1], mutation_scale=20, lw=1, arrowstyle="-|>", color="red")
        ax.add_artist(original)
        # Rotacion alrededor del eje x
        b = Vector3D([0,x1],[0,costy-(sintz)],[0,sinty+costz], mutation_scale=20, lw=1, arrowstyle="-|>", color="green")
        ax.add_artist(b)
        # Rotacion alrededor del eje y 
        c = Vector3D([0,costx+sintx],[0,y1],[0,sintx+costz], mutation_scale=20, lw=1, arrowstyle="-|>", color="blue")
        ax.add_artist(c)
        # Rotacion alrededor del eje z
        d = Vector3D([0, costx-sinty],[0,sintx+costy],[0,z1], mutation_scale=20, lw=1, arrowstyle="-|>", color="purple")
        ax.add_artist(d)
        numVectores = numVectores-1
    pl.legend([original, b, c, d], ['Original', 'Alrededor del eje x', 'Alrededor del eje y', 'Alrededor del eje z'])

# Operador de Dilatacion-Contraccion
elif(operador == 4):
    numVectores = input("Introduce cuantos vectores quieres reflejar: ")
    while(numVectores > 0):
        print "Vectores restantes: " + str(numVectores)
        # Se realiza un cast sobre la coordenada y se guarda en dos variables float
        coord = raw_input("Introduce las coordenadas del vector: ")
        coord = coord.replace("(","")
        coord = coord.replace(")","")
        # Se realiza un cast sobre la coordenada y se guarda en dos variables float
        coordAux = coord.split(",")
        x1 = float(coordAux[0]) 
        y1 = float(coordAux[1])
        z1 = float(coordAux[2])
        #Obtenemos el factor de dilatacion / contraccion
        factor = input("Introduce el factor de dilatacion o contaccion del vector: ")
        factor = float(factor)

        # Se crean el vector introducido por el usuario
        original = Vector3D([0,x1],[0,y1],[0,z1], mutation_scale=20, lw=1, arrowstyle="-|>", color="red")
        ax.add_artist(original)
        #El vector con su dilatacion-contraccion correspondiente
        b = Vector3D([0,x1*factor],[0,y1*factor],[0,z1*factor], mutation_scale=20, lw=1, arrowstyle="-|>", color="green")
        ax.add_artist(b)
        numVectores = numVectores-1
    pl.legend([original, b], ['Original', 'Factor de dilatacion/contraccion'])

#Obtenemos el valor mayor introducido
if(x1 < 0):
    x1 = x1*(-1)

if(y1 < 0):
    y1 = y1*(-1)

if(z1 < 0):
    z1 = z1*(-1)

maxValue = max([x1,y1,z1])

# Creamos los ejes x,y,z a partir de los objetos Vectores que creamos
# Usamos el valor maximo como auxiliar
x = Vector3D([maxValue*(-1)-6,maxValue+6],[0,0],[0,0], mutation_scale=20, lw=1, arrowstyle="-", color="black")
y = Vector3D([0,0],[maxValue*(-1)-6,maxValue+6],[0,0], mutation_scale=20, lw=1, arrowstyle="-", color="black")
z = Vector3D([0,0],[0,0],[maxValue*(-1)-6,maxValue+6], mutation_scale=20, lw=1, arrowstyle="-", color="black")
ax.add_artist(x)
ax.add_artist(y)
ax.add_artist(z)
# Las etiquetas para poder diferencias los ejes
ax.set_xlabel('Eje x')
ax.set_ylabel('Eje y')
ax.set_zlabel('Eje z')

# Los limites sobre los cuales se dibuja la grafica
ax.set_xlim([maxValue*(-1)-6,maxValue+6])
ax.set_ylim([maxValue*(-1)-6,maxValue+6])
ax.set_zlim([maxValue*(-1)-6,maxValue+6])
# Mostramos la grafica
pl.show()