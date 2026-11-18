package reportes;

import catalogo.Categoria;

public class FilaOcupacion {
	private Categoria categoria;
    private int cuposTotales;
    private int cuposReservados;
    private int cuposLibres;

    public FilaOcupacion(Categoria categoria, int cuposTotales, int cuposReservados) {
        this.categoria = categoria;
        this.cuposTotales = cuposTotales;
        this.cuposReservados = cuposReservados;
        this.cuposLibres = Math.max(0, cuposTotales - cuposReservados);
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public int getCuposTotales() {
        return this.cuposTotales;
    }

    public int getCuposReservados() {
        return this.cuposReservados;
    }

    public int getCuposLibres() {
        return this.cuposLibres;
    }
}
