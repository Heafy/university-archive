.data

x:  .word 0x1E   # a
w:  .word 0x00   # Residuo = r
y:  .word 0x05   # b
z:  .word 0x00   # Cociente = q

.text
main:

lw $t0, x # Guardamos el valor de a en a0
lw $t1, y # Guardamos el valor de b en a1


loop:
    beq $t1, $0, exit # Ciclo que termina cuando t1 = 0
    div $t0, $t1
    move $t0, $t1
    mfhi $t1
    j loop

exit:
  move $v0, $t0
  jr $ra