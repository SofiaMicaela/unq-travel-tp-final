package traslados;

import reserva.Reserva;

public class Recepcion implements MetodoTraslado {
	
	private int guiasDisponibles;

	public Recepcion(int guiasDisponibles) {
		this.guiasDisponibles = guiasDisponibles;
	}

	@Override
	public float costoTraslado(Reserva reserva) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean estaDisponible() {
		return guiasDisponibles >0;
	}

}
