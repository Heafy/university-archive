.data
    n: 		.asciiz "n = "
    e:	.asciiz "e = "
    auxiliar:		.word 	1

.text
main:
	
	lw 		$s7, auxiliar	
	lw 		$s3, auxiliar 				

	la 		$a0, n 				# La entrada de un numero
	li 		$v0, 4 					# Imprime la cadena
	syscall

	li		$v0, 5					# Lee el entero
	syscall

	move 	$s1, $v0				 
	move 	$a0, $s1				

	la 		$a0, e 			# La entrada de un numero
	li 		$v0, 4					# Imprime la cadena
	syscall

	li		$v0, 5					# Lee el entero
	syscall

	move 	$s2, $v0				
	move 	$a0, $s2				

	jal 	power					# Llamada a la funcion 

power:
	beqz 	$s2, one		# e = 0
	beq 	$s2, $s7,dif	# e = 1 

	sub 	$s2, $s2, 1		
	mul		$s3, $s3, $s1	# n * e
	jal 	power			# power(n, e-1) 

one:
	li		$v0, 1
	move	$a0, $s3				
	li		$v0, 1					# Imprime la potencia
	syscall

	li		$v0, 10					
	syscall
	jr		$ra
dif:
	sub 	$s2, $s2, 1		
	mul		$s3, $s3, $s1	# n * e

	move	$a0, $s3				
	li		$v0, 1					# Imprime la potencia
	syscall

	li		$v0, 10			
	syscall
	jr		$ra
