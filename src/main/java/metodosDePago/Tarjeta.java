package metodosDePago;

import java.time.LocalDate;

public class Tarjeta extends ProcesadorPago {
	
	private ApiTarjeta api;
	private String numeroTarjeta;
	private String cvv;
	private LocalDate vencimiento;
	private Voucher voucher;

	public Tarjeta(ApiTarjeta api, float monto, String numeroTarjeta, String cvv, LocalDate vencimiento) {
		super(monto);
		this.api = api;
		this.numeroTarjeta =numeroTarjeta;
		this.cvv = cvv;
		this.vencimiento = vencimiento;
	}

	public Voucher getVoucher() {
		return this.voucher;
	}
	
	@Override 
	protected void validarDatos() {
		boolean valido = this.api.validarTarjeta(this.numeroTarjeta, this.cvv, this.vencimiento);
		
		if(!valido) {
			throw new PagoRechazadoEcxeption("Los datos de la tarjeta son inválidos.");
		}
	}
	@Override 
	protected void reservarFondos() {
		boolean valido = this.api.preautorizarFondos(this.numeroTarjeta, this.getMonto());
		
		if(!valido) {
			throw new PagoRechazadoEcxeption("El banco rechazó la preautorización.");
		}
	}
	@Override
	protected String ejecutarTransaccion() {
		return this.api.ejecutarTransaccion(this.numeroTarjeta, this.getMonto());
	}
	@Override 
	protected void notificarResultado(String codigo) {
		super.notificarResultado(codigo);
		this.voucher = new Voucher(codigo, this.getMonto());
	}

}
