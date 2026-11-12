package reserva.estado;

import catalogo.ItemCatalogo;
import metodosDePago.ProcesadorPago;
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
    	reserva.procesarPago();
    	reserva.setEstado(new Confirmada());
    }

    @Override
    public void cancelar(Reserva reserva) {
        reserva.setEstado(new Cancelada());
    }
    
    public String nombre() {
        return "Solicitud pendiente";
    }

    @Override 
    public void procesarPago(Reserva reserva, ProcesadorPago procesador) {
    	if (procesador == null) {
			throw new IllegalStateException( "Todavia no se eligio un metodo de pago");
		}
    	procesador.procesarPago(reserva);
    	reserva.setEstado(new Confirmada());
	

    }
}
