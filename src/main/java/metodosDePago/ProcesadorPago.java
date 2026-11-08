package metodosDePago;

public abstract class ProcesadorPago {
	
	private float monto;
	private String codigoTransaccion;
	
	public ProcesadorPago(float monto) {
		this.monto = monto;
	}

	public final void procesarPago() {
		this.validarDatos();
		this.reservarFondos();
		String codigo = this.ejecutarTransaccion();
		this.notificarResultado(codigo);
	}
	
	protected float getMonto() {
		return this.monto;
	}
	protected abstract void validarDatos();
	protected abstract void reservarFondos();
	protected abstract String ejecutarTransaccion();
	protected void notificarResultado(String codigo) {
		this.codigoTransaccion = codigo;
	}
	public String getCodigoTransaccion() {
		return this.codigoTransaccion;
	}

}
