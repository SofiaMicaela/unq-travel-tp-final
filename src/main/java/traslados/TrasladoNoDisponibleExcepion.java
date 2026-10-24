package traslados;

public class TrasladoNoDisponibleExcepion extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TrasladoNoDisponibleExcepion() {
        super("El traslado elegido no está disponible.");
    }

}
