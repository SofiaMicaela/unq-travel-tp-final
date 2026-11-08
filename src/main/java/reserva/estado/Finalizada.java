package reserva.estado;

public class Finalizada extends EstadoBase{
	@Override
    public boolean esNotificable() {
    	return true;
    }
	
	@Override
    public String nombre() {
    	return "Finalizado";
    }
	
	@Override
	public boolean estaFinalizada() {
		return true;
	}
}
