package reserva.estado;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.Cupo;
import catalogo.ServicioIndividual;
import reserva.Direccion;
import reserva.Reserva;

public class EstadoExceptionsTest {
	private Reserva reserva;
	private ServicioIndividual vuelo;
	
	@BeforeEach
	void setUp() {
		reserva = new Reserva(5, mock(Direccion.class));
		//vuelo = new ServicioIndividual("A-0001", "Vuelo Buenos Aires - Brasil", "Ida y vuelta", "Aereolineas Argentinas", Categoria.ClaseEjecutiva, 1000000, 0, mock(Cupo.class));
	}
	
	@Test
	void estadosInvalidosSolicitudPendiente() {
		assertThrows(OperacionInvalidaEstadoExcepcion.class, () -> reserva.iniciarCheckIn());
		assertThrows(OperacionInvalidaEstadoExcepcion.class, () -> reserva.iniciarEjecucion());
		assertThrows(OperacionInvalidaEstadoExcepcion.class, () -> reserva.finalizar());
	}
	
	
}
