package reserva.estado;

import reserva.Reserva;

public class EnCheckIn extends EstadoBase{
	@Override
    public void iniciarEjecucion(Reserva reserva) {
        reserva.setEstado(new EnCurso());
    }
	
	@Override
	public String nombre() {
		return "En checkIn";
	}

    @Override
    public void cancelar(Reserva reserva) {
        reserva.setEstado(new Cancelada());
    }
    

}
