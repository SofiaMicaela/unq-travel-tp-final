package metodosDePago;

import java.time.LocalDate;

public class Transferencia extends ProcesadorPago {
	
	private ApiTransferencia api;
	private String cbuCvu;
	private String alias; 
	private LocalDate fechaEjecucion;
	private ComprobanteTransferencia comprobante;

	public Transferencia(ApiTransferencia api, float monto, String cbucvu, String alias, LocalDate fechaEjecucion) {
		super(monto);
		this.alias = alias;
		this.api  =api;
		this.cbuCvu =cbucvu;
		this.fechaEjecucion = fechaEjecucion;
	}

	@Override
	protected void validarDatos() {
		boolean valida =
		this.api.validarCuenta(this.cbuCvu, this.alias);
		
		if (!valida) {
			throw new PagoRechazadoEcxeption("Los datos de la cuenta son inválidos.");
		}
	}
	@Override 
	protected void reservarFondos() {
		//No aplica para transferencia
	}
	@Override 
	protected void notificarResultado( String codigo) {
		super.notificarResultado(codigo);
		
		this.comprobante = new ComprobanteTransferencia ( codigo, this.cbuCvu, this.getMonto(), this.fechaEjecucion);
	}
	public ComprobanteTransferencia getComprobante() {
			return this.comprobante;
	}

	@Override
	protected String ejecutarTransaccion() {
		return this.api.transferir(this.cbuCvu, this.alias, this.getMonto(), this.fechaEjecucion);
	}

}
