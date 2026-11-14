package traslados;

public class TrasladoNoDisponibleExcepcion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TrasladoNoDisponibleExcepcion() {
        super("El traslado elegido no está disponible.");
    }

}
