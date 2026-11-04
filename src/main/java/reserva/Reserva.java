package reserva;

import java.util.ArrayList;

import notificador.ObservadorReserva;
import traslados.MetodoTraslado;
import traslados.TrasladoNoDisponibleExcepion;

public class Reserva {

	private int cantidadPasajeros;
	private Direccion destino;
	private MetodoTraslado metodoTraslado;
	private ArrayList<ObservadorReserva> observadores = new ArrayList<>();

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
	
	
	//--- State --------------------------------
	
	//ToDo: puede solaparse con el state de elias. Es importante que al cambiar de estado notifique
	//      avisando su estado previo y actual.
	/*
	public void cambiarEstado(EstadoBase estado){
		EstadoBase estadoAntiguo = this.estado
		this.estado = estado
		notificar(estadoAntiguo, estado)
	} 
	*/
	//------------------------------------------
	
	//--- Notificador --------------------------
	
	//ToDo: corregir cuando se agregue STATE al proyecto
	/*
	public void notificar(EstadoBase antiguo, EstadoBase nuevo) {
		for (ObservadorReserva observador: observadores) {
			observador.update(antiguo, nuevo);
		}
		
	}
	*/
	
	public void suscribir(ObservadorReserva obs) {
		this.observadores.add(obs);
	}
	
	public void desuscribir(ObservadorReserva obs) {
		this.observadores.remove(obs);
	}
	
	//------------------------------------------
}
