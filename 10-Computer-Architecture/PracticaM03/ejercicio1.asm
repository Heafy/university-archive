# Programa que recibe una cadena y da la longitud de esta
# @autor: Jorge Martinez FLores
#

.data
    original:   .asciiz "Cadena recibida = "        # Cadena para mostrar
    str:        .asciiz "Organizacion y Arquitectura de Computadoras"                      # Cadena a la que se obtendra su longitud
    newline:    .asciiz "\n"                        #  Salto de linea
    strAux:    .asciiz "Longitud de la cadena = "  # Cadena para mostrar

.text
main:
    la      $a0, original       # Guarda la direccion de la cadena original
    li      $v0, 4
    syscall

    la      $a0, str            # Guarda la direccion de la Cadena inicial
    syscall

    la      $a0, newline        # Guarda la direccion de newline para hacer un salto de linea
    syscall
    
     la      $a0, strAux        # Cadena para poder mostrarla en la longitud
    syscall

    strLen:                     # Obteniendo la longitud de la cadena
        lb      $t0, str($t2)   # Carga los bits almacenados
        add     $t2, $t2, 1
        bnez    $t0, strLen     # Si t0 != vuelve al ciclo
        li      $v0, 11         # Load inmediate del valor al registro

    sub $t2, $t2, 1     # Por alguna razon siempre sale long+1, arregla ese error    
    li      $v0, 1
    move    $a0,$t2
    syscall
    jr $ra
