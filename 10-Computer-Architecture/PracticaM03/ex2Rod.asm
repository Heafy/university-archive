.data

	cad: 	.asciiz "Pablo Escobar"
	rev: 	.asciiz ""
	int: 	.word 0x00

.text
main:

	la 	$t0, cad		# Guardamos la direccion de la cadena str
	li 	$t2, 0		# Guardamos el 0 en el registro t2, para no usar lw con etiqueta

	la      $a0, rev       # Cadena para poder mostrarla en reversa
    syscall

	len:
		lb 		$t3, 0($t0)		# Guarda el byte en el registro 
		beqz 	$t3, finLen		# Si t2 == 0 va a end
		add 	$t0, $t0, 1		# Incrementa +1 la direccion
		add 	$t2, $t2, 1		# Incremente el contador
		j len				# Regresa al ciclo

	finLen:
		li	$v0, 11
		loopRev:
        	sub     $t2, $t2, 1     # Resta la longitud de la cadena 
        	la      $t0, cad($t2)   # Guardando la direccion de la cadena
        	lb      $a0, ($t0)
        	syscall
        	bnez    $t2, loopRev       # Cuando $t3 != 0 regresa el ciclo
	li      $v0, 10 
    syscall
	jr $ra
	  