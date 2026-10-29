package traslados;

import reserva.Reserva;

public class TrasladoVIP implements MetodoTraslado {
	
	private int porcentajeFijo;
	private float cargoBase;

	public TrasladoVIP(int porcentajeFijo, float cargoBase) {
		this.cargoBase= cargoBase;
		this.porcentajeFijo = porcentajeFijo;
	}
	
	public float calcularCosto(float precio) {
		return precio * this.porcentajeFijo / 100 + this.cargoBase;
	}

	@Override
	public float costoTraslado(Reserva reserva) {
		return this.calcularCosto(reserva.costoSinTraslado());
	}

	@Override
	public boolean estaDisponible() {
		return true;
	}
	
	

}
