package reportes;

public class FilaExposicionOperador {
	private String operador;
    private int cantidadServicios;
    private double porcentajeCatalogo;

    public FilaExposicionOperador(String operador, int cantidadServicios,
                                  double porcentajeCatalogo) {
        this.operador = operador;
        this.cantidadServicios = cantidadServicios;
        this.porcentajeCatalogo = porcentajeCatalogo;
    }

    public String getOperador() {
        return this.operador;
    }

    public int getCantidadServicios() {
        return this.cantidadServicios;
    }

    public double getPorcentajeCatalogo() {
        return this.porcentajeCatalogo;
    }


}
