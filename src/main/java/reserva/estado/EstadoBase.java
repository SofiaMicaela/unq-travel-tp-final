package reserva.estado;

import catalogo.ItemCatalogo;
import metodosDePago.ProcesadorPago;
import reserva.Reserva;

public class EstadoBase {
	public void agregarServicio(Reserva reserva, ItemCatalogo servicio) {
        throw new OperacionInvalidaEstadoExcepcion("No se pueden agregar servicios en este estado.");
    }
    
    public void quitarServicio(Reserva reserva, ItemCatalogo servicio) {
        throw new OperacionInvalidaEstadoExcepcion("No se pueden quitar servicios en este estado.");
    }
    
    public void confirmar(Reserva reserva) {
        throw new OperacionInvalidaEstadoExcepcion("No se puede confirmar la reserva desde este estado.");
    }
    
    public void iniciarCheckIn(Reserva reserva) {
        throw new OperacionInvalidaEstadoExcepcion("No se puede iniciar el check-in desde este estado.");
    }
    
    public void iniciarEjecucion(Reserva reserva) {
        throw new OperacionInvalidaEstadoExcepcion("No se puede pasar a En Curso desde este estado.");
    }
    
    public void finalizar(Reserva reserva) {
        throw new OperacionInvalidaEstadoExcepcion("No se puede finalizar la reserva desde este estado.");
    }
    
    public void cancelar(Reserva reserva) {
        throw new OperacionInvalidaEstadoExcepcion("La reserva no puede ser cancelada desde este estado.");
    }
    public void procesarPago(Reserva reserva, ProcesadorPago procesador) {
    	throw new OperacionInvalidaEstadoExcepcion("No se puede procesar el pago en este estado.");
    }
}
