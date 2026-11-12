package reserva.estado;

import reserva.Reserva;

public class EnCheckIn extends EstadoBase{
	@Override
    public void iniciarEjecucion(Reserva reserva) {
        reserva.setEstado(new EnCurso());
    }

    @Override
    public void cancelar(Reserva reserva) {
        reserva.setEstado(new Cancelada());
    }
    

}
