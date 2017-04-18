package animedatamining

/**
 * Created by frang on 4/18/2017.
 */
class MiExcepcion extends RuntimeException {
    public static final int ERROR_SERVIDOR = 1000
    public static final int ERROR_VALIDACION = 2000
    public static final int ERROR_CONEXION = 3000
    public static final int ERROR_SESION = 4000
    public static final int ERROR_BASE_DATOS = 5000
    public static final int ERROR_VISTA = 6000
    public static final int ERROR_CONTABILIDAD = 7000

    Integer codigo;
    String mensaje;

    public MiExcepcion() {
        super()
    }

    /**
     * @param mensaje
     */
    public MiExcepcion(String men) {
        super(men)
        this.mensaje = men;
    }

    /**
     * @param codigo
     * @param mensaje
     */
    public MiExcepcion(int cod, String men) {
        super(men);
        println("instanciando MiExcepción....");
        this.codigo = cod;
        this.mensaje = men;
    }
}
