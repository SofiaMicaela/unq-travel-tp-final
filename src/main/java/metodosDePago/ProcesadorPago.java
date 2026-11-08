package metodosDePago;

import reserva.Reserva;

public abstract class ProcesadorPago {
	
	private String codigoTransaccion;

	public final void procesarPago(Reserva reserva) {
		this.validarDatos(reserva);
		this.reservarFondos(reserva);
		String codigo = this.ejecutarTransaccion(reserva);
		this.notificarResultado(codigo,reserva);
	}
	
	protected abstract void validarDatos(Reserva reserva);
	protected abstract void reservarFondos(Reserva reserva);
	protected abstract String ejecutarTransaccion(Reserva reserva);
	protected void notificarResultado(String codigo,Reserva reserva) {
		this.codigoTransaccion = codigo;
	}
	public String getCodigoTransaccion() {
		return this.codigoTransaccion;
	}

}
