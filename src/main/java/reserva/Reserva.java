package reserva;

import java.util.ArrayList;
import notificador.ObservadorReserva;
import java.util.List;
import traslados.MetodoTraslado;
import traslados.TrasladoNoDisponibleExcepion;
import catalogo.ItemCatalogo;///
import reserva.estado.EstadoBase;///
import reserva.estado.SolicitudPendiente;

public class Reserva {

	private int cantidadPasajeros;
	private Direccion destino;
	private MetodoTraslado metodoTraslado;
	private ArrayList<ObservadorReserva> observadores = new ArrayList<>();
	private EstadoBase estado;///
    private List<ItemCatalogo> servicios;///

	public Reserva(int cantPasajeros, Direccion destino) {
		this.cantidadPasajeros = cantPasajeros;
		this.destino = destino;
		this.servicios = new ArrayList<>();///
        this.estado = new SolicitudPendiente();///
	}

	public float costoSinTraslado() {////
        float total = 0;
        for (ItemCatalogo item : this.servicios) {
            total += item.getPrecioFinal();
        }
        return total;
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
	
	//--- Notificador --------------------------
	
	public void notificar(EstadoBase antiguo, EstadoBase nuevo) {
		for (ObservadorReserva observador: observadores) {
			observador.update(this, antiguo, nuevo);
		}
	}
	
	public void suscribir(ObservadorReserva obs) {
		this.observadores.add(obs);
	}
	
	public void desuscribir(ObservadorReserva obs) {
		this.observadores.remove(obs);
	}
	
	//------------------------------------------

	public void setEstado(EstadoBase nuevoEstado) {/////
		EstadoBase estadoAntiguo = this.estado;
		this.estado = nuevoEstado;
		notificar(estadoAntiguo, estado);
    }
	
	public List<ItemCatalogo> getServiciosAux() {/////
        return this.servicios;
    }
	public void agregarServicio(ItemCatalogo servicio) {////
        this.estado.agregarServicio(this, servicio);
    }
    
    public void quitarServicio(ItemCatalogo servicio) {////
        this.estado.quitarServicio(this, servicio);
    }
    
    public void confirmar() {////
        this.estado.confirmar(this);
    }

    public void iniciarCheckIn() {////
        this.estado.iniciarCheckIn(this);
    }

    public void iniciarEjecucion() {////
        this.estado.iniciarEjecucion(this);
    }

    public void finalizar() {///
        this.estado.finalizar(this);
    }

    public void cancelar() {////
        this.estado.cancelar(this);
    }
    
}
