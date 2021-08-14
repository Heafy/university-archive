#Clase para modelar objetos de tipo Videojuego
class Videojuego:

	#Constructor de la clase Videojuego
	def __init__(self, nombre, genero, precio, desarrollador):
		self.nombre = nombre
		self.genero = genero
		self.precio = precio
		self.desarrollador = desarrollador	

	#Metodos get de la clase Videojuegio
	def getNombre(self):
		return self.nombre

	def getGenero(self):
		return self.genero

	def getPrecio(self):
		return self.precio

	def setPrecio(self, precio):
		self.precio = precio

	def getDesarrollador(self):
		return self.desarrollador

	# Funcion para imprimir objetos de tipo Videojuego
	def toString(self):
		return "Nombre:%s\nGenero:%s\nPrecio:%s\nDesarrollador:%s\n" % \
				(self.nombre, self.genero, str(self.precio), self.desarrollador)

	#Regresa el objeto de tipo Videojuego de mayor precio
	def elMasCaro(self):
		precios = {a.getNombre(): a.getPrecio(), \
		b.getNombre(): b.getPrecio(), \
		c.getNombre(): c.getPrecio(), \
		d.getNombre(): d.getPrecio(), \
		e.getNombre(): e.getPrecio(), \
		f.getNombre(): f.getPrecio(), \
		g.getNombre(): g.getPrecio(), \
		h.getNombre(): h.getPrecio(), \
		i.getNombre(): i.getPrecio(), \
		j.getNombre(): j.getPrecio()}
		masCaro = max(precios, key=precios.get)
		masCaroPrecio = precios[masCaro]
		return "El Videojuego mas caro es: " \
				+ masCaro + \
				" con un precio de: " \
				+ str(masCaroPrecio) + " pesos"

	#Rebaja el costo de un Videojuego al 15%
	def rebajaVideojuego(self):
		precio = self.getPrecio()
		precio = precio - (precio * .15)
		self.setPrecio(precio)

#Se crean 10 objetos tipo Videojuego de la siguiente manera:
#h = Videojuego("Nombre", "Genero", 999.99, "Desarrollador")
a = Videojuego("Tom Clancys The Division", "Accion", 599.00, "Ubisoft")
b = Videojuego("Far Cry Primal", "Mundo Abierto", 599.00, "Ubisoft")
c = Videojuego("Dark Souls III", "Accion", 735.98, "From Software, Inc.")
d = Videojuego("Counter Srike: Global Offensive", "FPS", 149.99, "Valve")
e = Videojuego("Fallout 4", "Mundo Abierto", 999.00, "Bethesda Game Studios")
f = Videojuego("Grand Theft Auto V", "Mundo Abierto", 799.00, "Rockstar North")
g = Videojuego("Rocket League", "Deportes", 179.99, "Psyonix")
h = Videojuego("ARK: Survival Evolved", "Supervivencia", 269.99, "Studio Wildcard")
i = Videojuego("Street Fighter V", "Lucha", 1099.00, "Capcom")
j = Videojuego("Payday 2", "FPS", 179.99, "OVERKILL - a Starbreeze Studio")

#Codigo ejecutable, no es pedido para la practica, pero comprueba que los metodos funcionan
print (h.toString())
print ("Y el juego con descuento: \n")
h.rebajaVideojuego()
print (h.toString())
print (a.elMasCaro())