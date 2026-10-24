package reserva;

import traslados.MetodoTraslado;
import traslados.TrasladoNoDisponibleExcepion;

public class Reserva {

	private int cantidadPasajeros;
	private Direccion destino;
	private MetodoTraslado metodoTraslado;

	public Reserva(int cantPasajeros, Direccion destino) {
		this.cantidadPasajeros = cantPasajeros;
		this.destino = destino;
	}

	// RETORNA 0 HASTA QUE IMPLEMENTEMOS LOS SERVICIOS TURISTICOS 
	public float costoSinTraslado() {
		return 0;   //PENDIENTE A IMPLEMENTAR
	}

	public int getCantidadPasajeros() {
		return this.cantidadPasajeros;
	}

	public Direccion getDireccion() {
		return this.destino;
	}
	public void elegirTraslado(MetodoTraslado unTraslado) throws TrasladoNoDisponibleExcepion {
		if (!unTraslado.estaDisponible()) {
			throw new TrasladoNoDisponibleExcepion();
			}
		this.metodoTraslado = unTraslado;
	}
	public float costoTraslado() {
		if(this.metodoTraslado == null) {
			throw new IllegalStateException("Todavia no se eligio un traslado.");
		}
		return this.metodoTraslado.costoTraslado(this);
	}
	
	

}
