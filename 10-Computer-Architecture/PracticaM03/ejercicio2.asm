# Programa que recibe una cadena e imprime la reversa
# @autor: Jorge Martinez FLores
#

.data
    original:   .asciiz "Cadena recibida = "        # Cadena para mostrar
    str:        .asciiz "Organizacion y Arquitectura de Computadoras"                      # Cadena a la que se obtendra su longitud
    newline:    .asciiz "\n"                        #  Salto de linea
    reversa:    .asciiz "Cadena en reversa = "      # Cadena para mostrar

.text
main:
    la      $a0, original       # Guarda la direccion de la cadena original
    li      $v0, 4
    syscall

    la      $a0, str            # Guarda la direccion de la Cadena inicial
    syscall

    la      $a0, newline        # Guarda la direccion de newline para hacer un salto de linea
    syscall
    
    la      $a0, reversa        # Cadena para poder mostrarla en reversa
    syscall

    li      $t2, 0
    
    strLen:                     # Obteniendo la longitud de la cadena
        lb      $t0, str($t2)   # Carga los bits almacenados
        add     $t2, $t2, 1
        bnez    $t0, strLen     # Si t0 != vuelve al ciclo
        li      $v0, 11         # Load inmediate del valor al registro

    Loop:
        sub     $t2, $t2, 1     # Resta con la longitud de la cadena 
        la      $t0, str($t2)   # Guardando la direccion de la cadena
        lb      $a0, ($t0)
        syscall
        bnez    $t2, Loop       # Cuando $t3 != 0 ocurre Loop, puede terminar antes
    
    li      $v0, 10 
    syscall
    jr $ra