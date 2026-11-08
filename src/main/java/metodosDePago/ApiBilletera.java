package metodosDePago;

public interface ApiBilletera {

	
	boolean tieneSaldoSuficiente(String idCuenta, float monto);

	boolean bloquearSaldo(String idCuenta, float monto);

	String acreditarPago(String idCuenta, float monto);

	void enviarNotificacionPush(String idCuenta, String codigo);

}
