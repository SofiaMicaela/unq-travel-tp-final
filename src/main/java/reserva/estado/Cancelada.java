package reserva.estado;

public class Cancelada extends EstadoBase{
	
	@Override
	public String nombre() {
		return "Cancelada";
	}
	
	@Override
	public boolean estaCancelada() {
		return true;
	}
}
