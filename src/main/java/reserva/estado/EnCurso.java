package reserva.estado;

import reserva.Reserva;

public class EnCurso extends EstadoBase {
	@Override
    public void finalizar(Reserva reserva) {
        reserva.setEstado(new Finalizada());
    }

    @Override
    public void cancelar(Reserva reserva) {
        reserva.setEstado(new Cancelada());
    }
}
