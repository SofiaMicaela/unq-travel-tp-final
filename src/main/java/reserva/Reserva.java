package reserva;

import java.util.ArrayList;
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

	public void setEstado(EstadoBase nuevoEstado) {/////
        this.estado = nuevoEstado;
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
