package reserva.estado;

public class Cancelada extends EstadoBase{
	@Override
    public String nombre() {
    	return "Cancelado";
    }
	
	@Override
	public boolean estaCancelada() {
		return true;
	}
}
