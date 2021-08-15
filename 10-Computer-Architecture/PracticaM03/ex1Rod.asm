.data

	cad: 	.asciiz "Pablo Escobar"

.text
main:

	la 	$t0, cad		# Guardamos la direccion de la cadena str
	li 	$t1, 0		# Guardamos el 0 en el registro t1, para no usar lw con etiqueta

	loop:
		lb 		$t2, 0($t0)		# Guarda el byte en el registro 
		beqz 	$t2, end		# Si t2 == 0 va a end
		add 	$t0, $t0, 1		# Incrementa +1 la direccion
		add 	$t1, $t1, 1		# Incremente el contador
		j loop				# Regresa al ciclo

	end:
		li 		$v0, 1
		move 	$a0,$t1
		syscall
		jr $ra