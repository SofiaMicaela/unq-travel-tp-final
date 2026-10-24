package traslados;

import reserva.Reserva;

public class TrasladoEstandar implements MetodoTraslado {
	
	private PlataformaTransporte plataformaTransporte;
	
	public TrasladoEstandar(PlataformaTransporte plataformaTransporte) {
		this.plataformaTransporte =plataformaTransporte;
	}
	@Override
	public float costoTraslado( Reserva reserva) {
		return plataformaTransporte.estimarTraslado(reserva.getCantidadPasajeros(), reserva.getDireccion());
		
	}
	@Override
	public boolean estaDisponible() {
		return true;
	}
}
