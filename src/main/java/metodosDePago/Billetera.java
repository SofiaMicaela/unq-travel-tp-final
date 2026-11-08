package metodosDePago;

public class Billetera extends ProcesadorPago {
	
	private ApiBilletera api;
	private String idCuenta;

	public Billetera(ApiBilletera api, float monto, String idCuenta) {
		super(monto);
		this.api =api;
		this.idCuenta=idCuenta;
	}
	@Override
	protected void validarDatos() {
		boolean saldoSuficiente =
		this.api.tieneSaldoSuficiente(this.idCuenta,  this.getMonto());
	
		if (!saldoSuficiente) {
			throw new PagoRechazadoEcxeption("La cuenta no tiene saldo suficiente.");
		}
	}
	@Override 
	protected void reservarFondos() {
		boolean saldoBloqueado = 
		this.api.bloquearSaldo(this.idCuenta, this.getMonto());
		
		if (!saldoBloqueado) {
			throw new PagoRechazadoEcxeption("No se pudo bloquear el saldo de la cuenta.");
		}
	}
	@Override
	protected String ejecutarTransaccion() {
		 return this.api.acreditarPago(this.idCuenta, this.getMonto());
	}
	@Override
	protected void notificarResultado(String codigo){
		super.notificarResultado(codigo);
		this.api.enviarNotificacionPush(this.idCuenta,  codigo);
		
	}

}
