package metodosDePago;

import java.time.LocalDate;

import reserva.Reserva;

public class Transferencia extends ProcesadorPago {
	
	private ApiTransferencia api;
	private String cbuCvu;
	private String alias; 
	private LocalDate fechaEjecucion;
	private ComprobanteTransferencia comprobante;

	public Transferencia(ApiTransferencia api, String cbucvu, String alias, LocalDate fechaEjecucion) {
		this.alias = alias;
		this.api  =api;
		this.cbuCvu =cbucvu;
		this.fechaEjecucion = fechaEjecucion;
	}

	@Override
	protected void validarDatos(Reserva reserva) {
		boolean valida =
		this.api.validarCuenta(this.cbuCvu, this.alias);
		
		if (!valida) {
			throw new PagoRechazadoEcxeption("Los datos de la cuenta son inválidos.");
		}
	}
	@Override 
	protected void reservarFondos(Reserva reserva) {
		//No aplica para transferencia
	}
	@Override 
	protected void notificarResultado( String codigo, Reserva reserva) {
		super.notificarResultado(codigo, reserva);
		
		this.comprobante = new ComprobanteTransferencia ( codigo, this.cbuCvu, reserva.costoTotal(), this.fechaEjecucion);
	}
	public ComprobanteTransferencia getComprobante() {
			return this.comprobante;
	}

	@Override
	protected String ejecutarTransaccion(Reserva reserva) {
		return this.api.transferir(this.cbuCvu, this.alias, reserva.costoTotal(), this.fechaEjecucion);
	}

}
