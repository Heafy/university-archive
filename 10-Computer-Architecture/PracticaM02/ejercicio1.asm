.data

x: 	.word 0x17	# Dividendo
w:	.word 0x00	# Residuo
y: 	.word 0x04	# Divisor
z:	.word 0x00	# Cociente  

.text
main:

lw $t0, x 	# Dividendo
lw $t1, y	# Divisor
lw $t2, z	# Cociente
lw $t3, x 	# Residuo

move $t5, $t0 	# Copia dividendo


loop:	
	sub $t5, $t3, $t1	# Dividendo - divisor / Valor auxiliar
	blez $t5, exit		# Si t0 == 0 Sale el ciclo
	move $t3, $t5		# Copiamos el registro, es el residuo
	addiu $t2, $t2, 1	# Sumamos un +1 al cociente 
	j loop 				# Volvemos a correr el ciclo

exit: 
sw $t2, z 	# Cociente
sw $t3, w 	# Residuo
jr $ra
	