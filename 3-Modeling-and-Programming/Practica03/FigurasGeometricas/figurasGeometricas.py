import math

#Clase para modelar figuras geometricas
class Figura:

	#Constructor de la clase Figura
	def __init__(self, radio, altura):
		self.radio = radio
		self. altura = altura

	#Funciones get de la clase Figura
	#Los set no son necesarios
	def getRadio(self):
		return self.radio

	def getAltura(self):
		return self.altura

#Clase para modelar un cilindro, hereda de la clase Figura
class Cilindro(Figura):
	
	#Constructor de la clase Cilindro
	def __init__(self, radio, altura):
		Figura.__init__(self, radio, altura)

	#Funcion para calcular el volumen de un cilindro
	# V = (pi)*(r^2)*(h)
	def calculaVolumen(self):
		volumen = (math.pi * math.pow(self.getRadio(), 2)) * self.getAltura()
		print "El volumen del cilindro es: " + str(volumen)


#Clase para modelar una esfera, hereda de la clase Figura
class Esfera(Figura):

	#Constructor de la clase Esfera
	def __init__(self, radio, altura):
		Figura.__init__(self, radio, altura)

	#Funcion para calcular el volumen de una esfera
	#Por razones que desconozco cuando intento usar 4/3 me dice que es = 1
	#Pero 4/3 = 1.3333333333
	#Asi que para facilitar las cosas mejor usare su valor decimal
	# V = (4/3)*(pi)*(r^3)
	def calculaVolumen(self):
		volumen = (1.333333) * (math.pi * math.pow(self.getRadio(), 3))
		print "El volumen de la esfera es: " + str(volumen)


#Clase para modelar un cono, hereda de la clase Figura
class Cono(Figura):

	#Constructor de la clase Cono 
	def __init__(self, radio, altura):
		Figura.__init__(self, radio, altura)

	#Funcion para calcular el volumen de un cono
	# V= ((pi)*r^2*(h))/3
	def calculaVolumen(self):
		volumen = (math.pi * math.pow(self.getRadio(), 2) * self.getAltura()) / 3
		print "El volumen del cono es: " + str(volumen)

figura = raw_input(" Introduce el nombre de la figura de la que deseas saber el volumen:\n 1.Cilindro \n 2.Esfera \n 3.Cono \n").lower()

# Cilindro
if(figura == "cilindro" or figura == "1"):
	radio = input("Introduce el radio del cilindro: ")
	altura = input("Introduce la altura del cilindro: ")
	cilindro = Cilindro(radio, altura)
	print (cilindro.calculaVolumen())
# Esfera
elif(figura == "esfera"  or figura == "2"):
	radio = input("Introduce el radio de la esfera: ")
	esfera = Esfera(radio, 0)
	print (esfera.calculaVolumen())
# Cono
elif(figura == "cono"  or figura == "3"):
	radio = input("Introduce el radio del cono: ")
	altura = input("Introduce la altura del cono: ")
	cono = Cono(radio, altura)
	print (cono.calculaVolumen())
else:
	print "Figura incorrecta, intenta de nuevo."
