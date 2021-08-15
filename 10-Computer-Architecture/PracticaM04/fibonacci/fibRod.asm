.data
	num: 	.asciiz "Introduce un numero: "

.text
main:

	la 		$a0,num			# La entrada de un numero  
	li 		$v0,4			# Imprime la cadena
	syscall

	li 		$v0,5    		# Lee el entero
	syscall

	move	$t0,$v0    		# Guarda el entero en $t0

	# Prepara la funcion para obtener el fib
	move 	$a0,$t0
	move 	$v0,$t0
	jal 	fib     		# fib(num)
	move 	$t1,$v0    		# res1 en  $t1

	# Preparamos los mensajes de salida

	move 	$a0,$t1    		
	li 		$v0,1			# Imprime el resultado
	syscall

	li 		$v0,10			# Termina el programa
	syscall


fib:
	beqz	$a0,res0  		#Si num == 0, fib = 0
	beq 	$a0,1,res1   	#Si num == 1, fub = 1

	# fib(num-1)
	sub 	$sp,$sp,4   	# El tam del stack
	sw 		$ra,0($sp)

	sub 	$a0,$a0,1   	# num - 1 
	jal 	fib     		# fib(num-1)
	add 	$a0,$a0,1

	lw 		$ra,0($sp)   	# Restablece la direccion de retorno
	add 	$sp,$sp,4


	sub 	$sp,$sp,4   	# Actualiza la direccion de retorno
	sw 		$v0,0($sp)
	# fib(num-2)
	sub 	$sp,$sp,4   	# Guarda la direccion de retorno
	sw 		$ra,0($sp)

	sub 	$a0,$a0,2   	# num - 2
	jal 	fib     		# fib(num-2)
	add 	$a0,$a0,2

	lw 		$ra,0($sp)   	# Restablece la direcciond de retorno
	add 	$sp,$sp,4		
	#---------------
	lw 		$s7,0($sp)   
	add 	$sp,$sp,4

	add 	$v0,$v0,$s7 	# fib(num-2)+fib(num-1)
	jr 		$ra 

res0:
	li 		$v0,0			# Resultado = 0
	jr 		$ra
res1:
	li 		$v0,1			# Resultado = 1
	jr 		$ra