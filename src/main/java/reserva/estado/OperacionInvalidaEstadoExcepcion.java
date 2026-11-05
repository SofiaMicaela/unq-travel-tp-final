package reserva.estado;

public class OperacionInvalidaEstadoExcepcion extends RuntimeException {
   
	public OperacionInvalidaEstadoExcepcion(String mensaje) {
        super(mensaje);
    }
}