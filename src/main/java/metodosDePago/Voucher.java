package metodosDePago;

public class Voucher {
	
	private String codigoTransaccion;
	private float monto;
	
	public Voucher(String codigoTransaccion, float monto) {
		this.codigoTransaccion = codigoTransaccion;
		this.monto = monto;
	}

	public String getCodigoTransaccion() {
		return codigoTransaccion;
	}

	public float getMonto() {
		return monto;
	}

}
