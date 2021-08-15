# Programa para calcular la potencia de un numero dado su base y su exponente
# @author Jorge Martinez Flores
#
.data
    base: 		.asciiz "Base: "
    exponente:	.asciiz "Exponente: "
    res1:		.asciiz " elevado a "
    res2:		.asciiz " es: "
    aux:		.word 	1

.text
main:
	
	lw 		$t5, aux				# Auxliar para comparar
	lw 		$t3, aux 				# Auxiliar para multiplicar

	la 		$a0, base 				# Solicita la entrada del numero base
	li 		$v0, 4 					# Servicio 4: Print string
	syscall

	li		$v0, 5					# Servicio 5: Read integer
	syscall

	move 	$t0, $v0				# Guarda la base en t0
	move 	$a0, $t0				# Prepara la base

	la 		$a0, exponente 			# Solicita la entrada del exponente
	li 		$v0, 4					# Servicio 4: Print string
	syscall

	li		$v0, 5					# Servicio 5: Read integer
	syscall

	move 	$t1, $v0				# Guarda el exponente en t1
	move	$t6, $v0				# Auxiliar para imprimir
	move 	$a0, $t1				# Prepara el exponente

	jal 	potencia					# Llamada a la funcion 

	move	$t3, $v0				# Guarda el resultado en t3

	move	$a0, $t3				
	li		$v0, 1					# Imprime la potencia
	syscall

	li		$v0, 10					# Servicio 10: Termina ejecucion
	syscall

potencia:
	beqz 	$t1, uno		# Si exponente = 0 va a 'uno'
	beq 	$t1, $t5,res	# Si exponente = 1 va a 'res'

	sub 	$sp,$sp,4   	# El stack del frame
	sw 		$ra,0($sp)

	sub 	$t1, $t1, 1		# exponente -1
	mul		$t3, $t3, $t0	# base * exponente
	jal 	potencia			# potencia(base, exponente-1) 

	lw 		$ra,0($sp)   	# Restablece la direccion de retorno
	add 	$sp,$sp,4

	sub 	$sp,$sp,4   	# Actualiza la direccion de retorno
	sw 		$v0,0($sp)

	lw 		$ra,0($sp)   	# Restablece la direcciond de retorno
	add 	$sp,$sp,4		# Hacemos pop al stack

	lw 		$s7,0($sp)   	# Pop que regresa el valor del stack
	add 	$sp,$sp,4

uno:
	jal 	print
res:
	# Como falta una multiplicacion aqui la agregamos
	# No afecta cuando el exponente = 1 directamente
	sub 	$t1, $t1, 1		# exponente -1
	mul		$t3, $t3, $t0	# base * exponente
	jal 	print

print:
	move	$a0, $t0		# Prepara el numero a imprimir
	li		$v0, 1			# Print: base
	syscall

	la 		$a0, res1		# Prepara la cadena 
	li 		$v0, 4			# Print: "elevado a"
	syscall

	move	$a0, $t6		# Prepara el numero a imprimir
	li		$v0, 1			# Print: exponente
	syscall

	la 		$a0, res2		# Prepara la cadena a imprimir
	li 		$v0, 4			# Print: es
	syscall

	move 	$a0, $t3		# Prepara el numero a imprmir
	li		$v0, 1			# Print: potencia(base, exponente)
	syscall

	li		$v0, 10
	syscall



