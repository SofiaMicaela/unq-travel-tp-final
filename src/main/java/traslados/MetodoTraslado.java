package traslados;

import reserva.Reserva;

public interface MetodoTraslado {

	float costoTraslado(Reserva reserva);
	boolean estaDisponible();
	
}
