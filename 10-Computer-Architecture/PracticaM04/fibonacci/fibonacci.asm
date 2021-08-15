# Programa para calcular cualquier numero de la sucesion de Fibonacci
# @author Jorge Martinez FLores
#
.data
	entrada: 	.asciiz "Introduce un numero: "
	res1: 		.asciiz "Fibonacci de "
	res2: 		.asciiz " = "

.text
main:

	la 		$a0,entrada		# Solicita la entrada de un numero al usuario  
	li 		$v0,4			# Servicio 4: Print string
	syscall

	li 		$v0,5    		# Servicio 5: Read integer
	syscall

	move	$t2,$v0    		# Guarda el entero en $t2

	# Prepara la funcion para obtener el fibonacci
	move 	$a0,$t2

	move 	$v0,$t2
	jal 	fibonacci     	# fibonacci(entrada)
	move 	$t3,$v0    		# res1 en  $t3

	# Preparamos los mensajes de salida
	la 		$a0,res1   		# Prepara la cadena a imprimir
	li 		$v0,4			# Print: "Fibonacci de "
	syscall

	move 	$a0,$t2    		# Prepara el entero introducido para imprimirlo
	li 		$v0,1			# Print: entrada
	syscall

	la 		$a0,res2  		# Prepara la cadena a imprimir
	li 		$v0,4			# Print: " = "
	syscall

	move 	$a0,$t3    		# Prepara el resultado a imprimir
	li 		$v0,1			# Print: resultado de Fibonacci
	syscall

	li 		$v0,10			# Servicio 10: Termina ejecucion
	syscall

# Funcion para calcular el numero de Fibonacci
fibonacci:
	beqz	$a0,zero   		#Si entrada == 0, va a 'zero'
	beq 	$a0,1,uno   	#Si entrada == 1, va a 'uno'

	# fibonacci(entrada-1)
	sub 	$sp,$sp,4   	# El stack del frame
	sw 		$ra,0($sp)

	sub 	$a0,$a0,1   	# entrada - 1 
	jal 	fibonacci     	# fibonacci(entrada-1)
	add 	$a0,$a0,1

	lw 		$ra,0($sp)   	# Restablece la direccion de retorno
	add 	$sp,$sp,4

	sub 	$sp,$sp,4   	# Actualiza la direccion de retorno
	sw 		$v0,0($sp)
	
	# fibonacci(entrada-2)
	sub 	$sp,$sp,4   	# Guarda la direccion de retorno
	sw 		$ra,0($sp)

	sub 	$a0,$a0,2   	# entrada - 2
	jal 	fibonacci     	# fibonacci(entrada-2)
	add 	$a0,$a0,2

	lw 		$ra,0($sp)   	# Restablece la direcciond de retorno
	add 	$sp,$sp,4		# Hacemos pop al stack

	lw 		$s7,0($sp)   	# Pop que regresa el valor del stack
	add 	$sp,$sp,4

	add 	$v0,$v0,$s7 	# fibonacci(entrada-2)+fibonacci(entrada-1)
	jr 		$ra 

zero:
	li 		$v0,0			# Resultado = 0
	jr 		$ra
uno:
	li 		$v0,1			# Resultado = 1
	jr 		$ra

