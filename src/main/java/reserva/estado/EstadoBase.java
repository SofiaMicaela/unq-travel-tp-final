package reserva.estado;

import catalogo.ItemCatalogo;
import metodosDePago.ProcesadorPago;
import reserva.Reserva;

public class EstadoBase {
	public void agregarServicio(Reserva reserva, ItemCatalogo servicio) {
        throw new OperacionInvalidaEstadoExcepcion("No se pueden agregar servicios estando en estado: "+ this.nombre());
    }
    
    public void quitarServicio(Reserva reserva, ItemCatalogo servicio) {
        throw new OperacionInvalidaEstadoExcepcion("No se pueden quitar servicios estando en estado: "+ this.nombre());
    }
    
    public void confirmar(Reserva reserva) {
        throw new OperacionInvalidaEstadoExcepcion("No se puede confirmar la reserva estando en estado: " + this.nombre());
    }
    
    public void iniciarCheckIn(Reserva reserva) {
        throw new OperacionInvalidaEstadoExcepcion("No se puede iniciar el check-in estando en estado: "+ this.nombre());
    }
    
    public void iniciarEjecucion(Reserva reserva) {
        throw new OperacionInvalidaEstadoExcepcion("No se puede pasar a En Curso estando en estado: "+ this.nombre());
    }
    
    public void finalizar(Reserva reserva) {
        throw new OperacionInvalidaEstadoExcepcion("No se puede finalizar la reserva estando en estado: "+ this.nombre());
    }
    
    public void cancelar(Reserva reserva) {
        throw new OperacionInvalidaEstadoExcepcion("La reserva no puede ser cancelada estando en estado: "+ this.nombre());
    }
    
    public boolean esNotificable() {
    	return false;
    }
    
    public boolean estaCancelada() {
    	return false;
    }
    
    public boolean estaFinalizada() {
    	return false;
    }
    
    public String nombre() {
  
        return this.getClass().getSimpleName();
    }
    
    public void procesarPago(Reserva reserva, ProcesadorPago procesador) {
    	throw new OperacionInvalidaEstadoExcepcion("No se puede procesar el pago estando en estado:  "+ this.nombre());
    }
}
