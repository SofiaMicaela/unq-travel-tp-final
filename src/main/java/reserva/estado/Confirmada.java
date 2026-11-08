package reserva.estado;

import reserva.Reserva;

public class Confirmada extends EstadoBase{
	@Override
    public void iniciarCheckIn(Reserva reserva) {
        reserva.setEstado(new EnCheckIn());
    }

    @Override
    public void cancelar(Reserva reserva) {
        reserva.setEstado(new Cancelada());
    }
}
