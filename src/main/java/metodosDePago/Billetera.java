package metodosDePago;

import reserva.Reserva;

public class Billetera extends ProcesadorPago {
	
	private ApiBilletera api;
	private String idCuenta;

	public Billetera(ApiBilletera api, String idCuenta) {
		this.api =api;
		this.idCuenta=idCuenta;
	}
	@Override
	protected void validarDatos(Reserva reserva) {
		boolean saldoSuficiente =
		this.api.tieneSaldoSuficiente(this.idCuenta,  reserva.costoTotal());
	
		if (!saldoSuficiente) {
			throw new PagoRechazadoEcxeption("La cuenta no tiene saldo suficiente.");
		}
	}
	@Override 
	protected void reservarFondos(Reserva reserva) {
		boolean saldoBloqueado = 
		this.api.bloquearSaldo(this.idCuenta, reserva.costoTotal());
		
		if (!saldoBloqueado) {
			throw new PagoRechazadoEcxeption("No se pudo bloquear el saldo de la cuenta.");
		}
	}
	@Override
	protected String ejecutarTransaccion(Reserva reserva) {
		 return this.api.acreditarPago(this.idCuenta, reserva.costoTotal());
	}
	@Override
	protected void notificarResultado(String codigo,Reserva reserva){
		super.notificarResultado(codigo, reserva);
		this.api.enviarNotificacionPush(this.idCuenta,  codigo);
		
	}

}
