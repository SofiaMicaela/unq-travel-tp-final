package notificador;

import reserva.Reserva;
import reserva.estado.EstadoBase;

public interface ObservadorReserva {
	public void update(Reserva reserva, EstadoBase antiguo, EstadoBase nuevo);
}
