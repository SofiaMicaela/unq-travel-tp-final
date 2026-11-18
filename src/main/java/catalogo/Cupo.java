package catalogo;

import java.time.LocalDate;

import reserva.Direccion;

public class Cupo {
	private int capacidadTotal;
	private int cuposReservados;
	
	public Cupo(int capacidadTotal) {
		this.capacidadTotal = capacidadTotal;
		this.cuposReservados = 0;
	}
	
	public int cuposDisponibles() {
		return capacidadTotal - cuposReservados;
	}
	
	public boolean hayDisponibles() {
		return this.cuposDisponibles() > 0;
	}
	
	public void reservar() {
        if (cuposDisponibles() < 1) {
            throw new IllegalArgumentException("No hay cupos disponibles");
        }
        cuposReservados += 1;
    }

    public int getCapacidadTotal() {
        return capacidadTotal;
    }

    public int getCuposReservados() {
        return cuposReservados;
    }
}
