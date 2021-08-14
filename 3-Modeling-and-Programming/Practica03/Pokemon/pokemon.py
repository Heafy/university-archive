#Clase padre para crear Pokemones
class Pokemon:

	#Constructor de la clase padre
	def __init__(self, nombre, energia, tipo, ataques):
		self.nombre = nombre
		self.energia = energia
		self.tipo = tipo
		self.ataques = ataques

	# Funciones get / set de la clase Pokemon
	def getNombre(self):
		return self.nombre

	def setNombre(self, nombre):
		self.nombre = nombre

	def getEnergia(self):
		return self.energia

	def setEnergia(self, energia):
		self.energia = energia

	def getAtaques(self):
		return self.ataques

	def setAtaques(self, ataques):
		self.ataques = ataques

	def getTipoPokemon(self):
		return self.tipo

	def printAtaques(self):
		for k, v in self.ataques.iteritems():
			print k, v

#Clase para los Pokemon tipo Agua, heredan de la clase Pokemon
class PokemonAgua(Pokemon):

	def __init__(self, nombre, energia, tipo, ataques):
		Pokemon.__init__(self, nombre, energia, tipo, ataques)

	def ataque(self, valorAtaque, Pokemon):
		intensidadAtaque = valorAtaque
		if(Pokemon.getTipoPokemon == "Fuego"):
			intensidadAtaque += 10
		return intensidadAtaque

#Clase para los Pokemon tipo Fuego, heredan de la clase Pokemon
class PokemonFuego(Pokemon):
	def __init__(self, nombre, energia, tipo, ataques):
		Pokemon.__init__(self, nombre, energia, tipo, ataques)

	def ataque(self, valorAtaque, Pokemon):
		intensidadAtaque = valorAtaque
		if(Pokemon.getTipoPokemon == "Planta"):
			intensidadAtaque += 10
		return intensidadAtaque

#Clase para los Pokemon tipo Planta, heredan de la clase Pokemon
class PokemonPlanta(Pokemon):
	def __init__(self, nombre, energia, tipo, ataques):
		Pokemon.__init__(self, nombre, energia, tipo, ataques)

	#Las plantas no son fuertes contra los pokemon tipo agua, fuego o electrico
	#Error de diseno
	def ataque(self, valorAtaque, Pokemon):
		intensidadAtaque = valorAtaque
		return intensidadAtaque

#Clase para los Pokemon tipo Electrico, heredan de la clase Pokemon
class PokemonElectrico(Pokemon):
	def __init__(self, nombre, energia, tipo, ataques):
		Pokemon.__init__(self, nombre, energia, tipo, ataques)

	def ataque(self, valorAtaque, Pokemon):
		intensidadAtaque = valorAtaque
		if(Pokemon.getTipoPokemon == "Agua"):
			intensidadAtaque += 10
		return intensidadAtaque

"""Los diccionarios que contienen las habilidades de los Pokemon que vamos a crear
Es mas facil crear diccionarios que nunca se van a  usar, que meterlos dentro de los if's
y ocupar mas lineas de las necesarias
Ademas, hace que todo se vea mas limpio"""

#Tipo Agua
ataquesSquirtle = {"Pistola de Agua": 15, "Acua Cola": 10, "Latigo": 15, "Hidrobomba": 25}
ataquesPoliwarth = { "Rayo Burbuja": 20, "Doble Bofeton": 15, "Golpe Dinamico": 10, "Llave Giro": 15}
#Tipo Fuego
ataquesCyndaquil = {"Lanzallamas": 15, "Rueda de Fuego": 15, "Ataque Rapido": 10, "Nitrocarga": 20}
ataquesTorchic = {"Lanzallamas": 15, "Picotazo": 10, "Cuchillada": 10, "Pirotecnia": 25}
#Tipo Agua
ataquesTreecko = {"Chirrido": 15, "Gigadrenado": 20, "Energibola": 15, "Absorber": 10}
ataquesChikorita = {"Dulce Aroma": 10, "Hoja Afilada": 15, "Aromaterapia": 15, "Rayo Solar": 20}
#Tipo Electrico
ataquesPikachu = {"Ataque Rapido": 10, "Latigo": 15, "Chispa": 15, "Impactrueno": 20}
ataquesElekid = {"Ataque Rapido": 10, "Chispazo": 15, "Golpe Trueno": 20, "Impactrueno": 20}

player1 = raw_input("Jugador 1, escribe el nombre del Pokemon que deseas elegir:\n Pokemon Tipo Agua:\n -Squirtle\n -Poliwarth\n Pokemon Tipo Fuego:\n -Cyndaquil\n -Torchic\n Pokemon Tipo Planta:\n -Treecko\n -Chikorita\n Pokemon Tipo Electrico:\n -Pikachu\n -Elekid\n").lower()

#Codigo para asignar un Pokemon al primer jugador
#Asigna a Squirtle
while(True):
	if(player1 == "squirtle"):
		pokemon1 = PokemonAgua("Squirtle", 100, "Agua", ataquesSquirtle)
		break
	#Asigna a Poliwarth
	elif(player1 == "poliwarth"):
		pokemon1 = PokemonAgua("Poliwarth", 100, "Agua", ataquesPoliwarth)
		break
	#Asigna a Cyndaquil
	elif(player1 == "cyndaquil"):
		pokemon1 = PokemonFuego("Cyndaquil", 100, "Fuego", ataquesCyndaquil)
		break
	#Asigna a Torchic
	elif(player1 == "torchic"):
		pokemon1 = PokemonFuego("Torchic", 100, "Fuego", ataquesTorchic)
		break
	#Asigna a Treecko
	elif(player1 == "treecko"):
		pokemon1 = PokemonPlanta("Treecko", 100, "Planta", ataquesTreecko)
		break
	#Asigna a Chikorita
	elif(player1 == "chikorita"):
		pokemon1 = PokemonPlanta("Chikorita", 100, "Planta", ataquesChikorita)
		break
	#Asigna a Pikachu
	elif(player1 == "pikachu"):
		pokemon1 = PokemonElectrico("Pikachu", 100, "Electrico", ataquesPikachu)
		break
	#Asigna a Elekid
	elif(player1 == "elekid"):
		pokemon1 = PokemonElectrico("Elekid", 100, "Electrico", ataquesElekid)
		break
	else:
		print "Entrada no valida, intentalo de nuevo"
		player1 = raw_input("Jugador 1, escribe el nombre del Pokemon que deseas elegir:\n Pokemon Tipo Agua:\n -Squirtle\n -Poliwarth\n Pokemon Tipo Fuego:\n -Cyndaquil\n -Torchic\n Pokemon Tipo Planta:\n -Treecko\n -Chikorita\n Pokemon Tipo Electrico:\n -Pikachu\n -Elekid\n").lower()
		print "\n"

print "El Pokemon seleccionado por el Jugador 1 es: " + pokemon1.getNombre() + "\n"

player2 = raw_input("Jugador 2, escribe el nombre del Pokemon que deseas elegir:\n Pokemon Tipo Agua:\n -Squirtle\n -Poliwarth\n Pokemon Tipo Fuego:\n -Cyndaquil\n -Torchic\n Pokemon Tipo Planta:\n -Treecko\n -Chikorita\n Pokemon Tipo Electrico:\n -Pikachu\n -Elekid\n").lower()

while(True):
	if(player2 == "squirtle"):
		pokemon2 = PokemonAgua("Squirtle", 100, "Agua", ataquesSquirtle)
		break
	#Asigna a Poliwarth
	elif(player2 == "poliwarth"):
		pokemon2 = PokemonAgua("Poliwarth", 100, "Agua", ataquesPoliwarth)
		break
	#Asigna a Cyndaquil
	elif(player2 == "cyndaquil"):
		pokemon2 = PokemonFuego("Cyndaquil", 100, "Fuego", ataquesCyndaquil)
		break
	#Asigna a Torchic
	elif(player2 == "torchic"):
		pokemon2 = PokemonFuego("Torchic", 100, "Fuego", ataquesTorchic)
		break
	#Asigna a Treecko
	elif(player2 == "treecko"):
		pokemon2 = PokemonPlanta("Treecko", 100, "Planta", ataquesTreecko)
		break
	#Asigna a Chikorita
	elif(player2 == "chikorita"):
		pokemon2 = PokemonPlanta("Chikorita", 100, "Planta", ataquesChikorita)
		break
	#Asigna a Pikachu
	elif(player2 == "pikachu"):
		pokemon2 = PokemonElectrico("Pikachu", 100, "Electrico", ataquesPikachu)
		break
	#Asigna a Elekid
	elif(player2 == "elekid"):
		pokemon2 = PokemonElectrico("Elekid", 100, "Electrico", ataquesElekid)
		break
	else:
		print "Entrada no valida, intentalo de nuevo\n"
		player2 = raw_input("Jugador 2, escribe el nombre del Pokemon que deseas elegir:\n Pokemon Tipo Agua:\n -Squirtle\n -Poliwarth\n Pokemon Tipo Fuego:\n -Cyndaquil\n -Torchic\n Pokemon Tipo Planta:\n -Treecko\n -Chikorita\n Pokemon Tipo Electrico:\n -Pikachu\n -Elekid\n").lower()
		print "\n"

print "El Pokemon seleccionado por el Jugador 2 es: " + pokemon2.getNombre() + "\n"
print "Se enfrentaran en una batalla Pokemon: " + pokemon1.getNombre() + " y " + pokemon2.getNombre() + "\n"

while(pokemon1.getEnergia() != 0 or pokemon2.getEnergia() != 0):
	print "La energia actual de " + pokemon1.getNombre() + " (Jugador 1) es: " + str(pokemon1.getEnergia()) + "\n"\
	"La energia actual de " + pokemon2.getNombre() + " (Jugador 2) es: " + str(pokemon2.getEnergia()) + "\n"
	
	print pokemon1.getNombre() + " selecciona un ataque: "
	atPokemon1 = raw_input(pokemon1.printAtaques())
