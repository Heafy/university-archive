# Funcion principal para escalonar la matriz
# param M la matriz a escalonar
def escalona(M):
    # Si la matriz es vacia no hace nada
    if not M: 
        return
    pivote = 0
    filas = len(M)
    columnas = len(M[0])
    for r in range(filas):
        if pivote >= columnas:
            return
        i = r
        while M[i][pivote] == 0:
            i += 1
            if i == filas:
                i = r
                pivote += 1
                if columnas == pivote:
                    return
        # Intercambia las filas
        M[i],M[r] = M[r],M[i]
        # Si M[r, pivote] no es 0 divida la fila r por M[r, pivote]
        lv = M[r][pivote]
        # Convierte en float
        # Evita impresiciones aritmeticas
        M[r] = [ mrx / float(lv) for mrx in M[r]]
        for i in range(filas):
            if i != r:
                lv = M[i][pivote]
                # Resta M[i, pivote] multpiplicado por la fila r 
                # desde la fila i
                M[i] = [ iv - lv*rv for rv,iv in zip(M[r],M[i])]
        pivote += 1
 
 
A = [
   [ 1, 2, -1, -4],
   [ 2, 3, -1, -11],
   [-2, 0, -3, 22],
   ]
 
escalona(A)
 
# Imprime la matriz de forma bonita
print('\n'.join([' '.join(['{:4}'.format(item) for item in row]) 
      for row in A]))