def reader(reader):
	line = reader.readLine()
	reader.readLine()

	try:
		elections= int(line)
	except ValueError:
		elections = 0

	return elections

# Una eleccion del Voto Australiano
class Election:

	# Constructor
	def __init__(self):
		self.boletas = []
		self.candidatos = []
		self.ganadores = []

	# Regresa el nombre del ganador, un nombre por linea
	def __str__(self):
		salida = ''

		for ganador in self.ganadores:
			salida += str(ganador) + '\n'

		return  salida

	# Creamos este metodo por si acaso
	def leer(self, reader):
		self.leerCandidatos(reader)
		self.leerBoletas(reader)

	def leerCandidatos(self):
		line = reader.readLine()

		try:
			candidatos = int(line)
		except ValueError:
			candidatos = 0

		while(candidatos > 0):
			line = reader.readLine()
			line = line.strip() #Remueve '\n'
			candidato = Canditato(line)
			self.candidatos.append(candidato)

			candidatos -= 1

	# Lee las votaciones
	def leerBoletas(self, reader):
		while(True):
			line = reader.readLine()
			boleta = Boleta(line)

			if line == ' \n' or line == '':
				break

			self.boletas.append(boleta)

	# Determina el ganador de las votaciones
	def votaciones(self):
		#Asigna boletas para el candidato de primera eleccion
		for boleta in self.boletas:
			voto = boleta.getVoto()
			candidato = self.candidatos[voto -1]
			candidato.agrega(boleta)

		while(Tue):
			# Busca un ganador por la mitad de votos
			mayoria = len(self.boletas) / 2

			for candidato in self.candidatos:
				if candidato.numVotos() > mayoria:
					self.ganadores = [candidato]
					return self.ganadores

			# Busca empates
			votos = []
			for candidato in self.candidatos:
				numVotos = candidato.numVotos()

				if numVotos != 0:
					votos.append(numVotos)
			if len(votos) > 0 and votos.count(votos[0]) == len(votos):
				# Existe un empate
				for candidato in self.candidatos:
					if candidato.numVotos != 0:
						self.ganadores.append(candidato)
				return self.ganadores

			# Encuentra el menor numero de votos
			menosVotos = -1
			for candidato in self.candidatos:
				numVotos = candidato.numVotos()

				# Para controlar el ciclo internamente
				if numVotos <= 0:
					continue

				if menosVotos == -1:
					menosVotos = numVotos
				else:
					menosVotos = min(menosVotos, numVotos)

			# Elimina todos los candidatos con el menor numero de votos
			boletas = []
			for candidato in self.candidatos:
				if menosVotos == candidato.numVotos():
					boletas.extend(candidato.getBoletas())
					candidato.elimina()

			# Redistribuye los votos de los candidatos eliminados
			for boleta in boletas:
				while(True):
					voto = boleta.getVoto()
					candidato = self.candidatos[voto - 1]

					if(candidato.numVotos() != 0):
						candidato.agrega(boleta)
						break

# Un candidato. Tiene un nobre y una boleta 
class Candidato:

	def __init__(self,name):
		# Solo para verificar que sea una cadena
		assert type(name) is str

		self.name = name
		self.boletas = []

	def __str__(self):
		return self.name

	def agrega(self, boleta):
		# Asocia la boleta con el candidato
		assert type(boleta) is type(Boleta(''))

		self.boletas.append(boleta)

	def getBoletas(self):
		return self.boletas

	def numVotos(self):
		return len(self.boletas)

	# Elimina las boletas del candidato
	def elimina(self):
		self.boletas = []

# Una boleta en una eleccion. Contiene una lista con los enteros representando los candidatos
class Boleta:
	def __init__(self, line):
		assert type(line) is str

		self.pointer = 0
		self.votos = []

		for voto in line.split():
			voto = int(voto)
			self.votos.append(voto)

	def __str__(self):
		return str(self.votos)

	def getVoto(self):
		pointer = self.pointer
		self.pointer += 1
		return self.votos[pointer]






