package reserva;

public class Direccion {
	
	private String calle;
	private int altura;
	private String localidad;


	public String getCalle() {
		return calle;
	}


	public int getAltura() {
		return altura;
	}


	public String getLocalidad() {
		return localidad;
	}


	public Direccion(String calle, int altura, String localidad) {
		this.altura = altura;
		this.calle = calle;
		this.localidad = localidad;
	}

}
