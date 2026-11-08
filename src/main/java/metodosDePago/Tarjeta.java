package metodosDePago;

import java.time.LocalDate;

import reserva.Reserva;

public class Tarjeta extends ProcesadorPago {
	
	private ApiTarjeta api;
	private String numeroTarjeta;
	private String cvv;
	private LocalDate vencimiento;
	private Voucher voucher;

	public Tarjeta(ApiTarjeta api, String numeroTarjeta, String cvv, LocalDate vencimiento) {
		this.api = api;
		this.numeroTarjeta =numeroTarjeta;
		this.cvv = cvv;
		this.vencimiento = vencimiento;
	}

	public Voucher getVoucher() {
		return this.voucher;
	}
	
	@Override 
	protected void validarDatos(Reserva reserva) {
		boolean valido = this.api.validarTarjeta(this.numeroTarjeta, this.cvv, this.vencimiento);
		
		if(!valido) {
			throw new PagoRechazadoEcxeption("Los datos de la tarjeta son inválidos.");
		}
	}
	@Override 
	protected void reservarFondos(Reserva reserva) {
		boolean valido = this.api.preautorizarFondos(this.numeroTarjeta, reserva.costoTotal());
		
		if(!valido) {
			throw new PagoRechazadoEcxeption("El banco rechazó la preautorización.");
		}
	}
	@Override
	protected String ejecutarTransaccion(Reserva reserva) {
		return this.api.ejecutarTransaccion(this.numeroTarjeta, reserva.costoTotal());
	}
	@Override 
	protected void notificarResultado(String codigo, Reserva reserva) {
		super.notificarResultado(codigo, reserva);
		this.voucher = new Voucher(codigo, reserva.costoTotal());
	}

}
