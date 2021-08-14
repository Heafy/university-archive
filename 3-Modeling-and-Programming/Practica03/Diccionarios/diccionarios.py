# Pedimos la cadena sobre la que vamos a trabajar
cadena = raw_input("Introduce una cadena de texto: ")

print "\nAhora contaremos las apariciones de cada caracter en: " + cadena

# Usamos lower para que todos los caracteres sean en minuscula
# Y eliminamos los espacios, aunque son caracteres no creo que sea importante contarlos
cadena = cadena.lower().replace(" ", "")

# Creamos y llenamos las apariciones de cada caracter en un diccionario
diccionario = {}
for caracter in cadena:
	if caracter in diccionario:
		diccionario[caracter] += 1
	else:
		diccionario[caracter] = 1

# Mostramos el resultado final
print diccionario
