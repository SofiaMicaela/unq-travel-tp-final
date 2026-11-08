package reserva.estado;

import catalogo.ItemCatalogo;
import reserva.Reserva;

public class SolicitudPendiente extends EstadoBase {
	@Override
    public void agregarServicio(Reserva reserva, ItemCatalogo servicio) {
        reserva.getServiciosAux().add(servicio); 
    }

    @Override
    public void quitarServicio(Reserva reserva, ItemCatalogo servicio) {
        reserva.getServiciosAux().remove(servicio);
    }

    @Override
    public void confirmar(Reserva reserva) {
        reserva.setEstado(new Confirmada());
    }

    @Override
    public void cancelar(Reserva reserva) {
        reserva.setEstado(new Cancelada());
    }
    
    @Override
    public String nombre() {
    	return "Solicitud pendiente";
    }
}
