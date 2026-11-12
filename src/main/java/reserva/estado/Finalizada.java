package reserva.estado;

public class Finalizada extends EstadoBase{
	@Override
    public boolean esNotificable() {
    	return true;
    }
	
	
	
	@Override
	public boolean estaFinalizada() {
		return true;
	}
}
