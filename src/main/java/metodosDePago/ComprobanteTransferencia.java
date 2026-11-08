package metodosDePago;

import java.time.LocalDate;

public class ComprobanteTransferencia {
	
	private String numeroOperacion;
	private String cbuCvu;
	private float monto;
	private LocalDate fechaEjecucion;
	
	public String getNumeroOperacion() {
		return numeroOperacion;
	}

	public String getCbuCvu() {
		return cbuCvu;
	}

	public float getMonto() {
		return monto;
	}

	public LocalDate getFechaEjecucion() {
		return fechaEjecucion;
	}

	public ComprobanteTransferencia(String numeroOp, String cbucvu, float monto, LocalDate fechaEjecucion) {
		this.numeroOperacion = numeroOp;
		this.cbuCvu = cbucvu;
		this.monto = monto;
		this.fechaEjecucion = fechaEjecucion;
		
	}

}
