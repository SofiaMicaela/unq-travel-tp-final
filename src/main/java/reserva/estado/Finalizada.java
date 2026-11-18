package reserva.estado;

public class Finalizada extends EstadoBase{
	
	@Override
	public String nombre() {
		return "Finalizada";
	}
	
	@Override
    public boolean esNotificable() {
    	return true;
    }
	
	@Override
	public boolean estaFinalizada() {
		return true;
	}
}
