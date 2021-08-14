package icc;

/**
 * Interface para escuchas de bases de datos.
 */
@FunctionalInterface
public interface EscuchaBaseDeDatos<T extends Registro> {

    /**
     * Nos dice si hubo algún cambion en la base de datos.
     * @param accion el tipo de cambio: {@link BaseDeDatos#BASE_LIMPIADA},
     *               {@link BaseDeDatos#REGISTRO_AGREGADO} o {@link
     *               BaseDeDatos#REGISTRO_ELIMINADO}.
     * @param registro el registro afectado, o <code>null</code> si la base de
     *                 datos fue limpiada.
     */
    public void baseDeDatosModificada(int accion, T registro);
}
