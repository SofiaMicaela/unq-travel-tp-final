package catalogo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CupoTest {
	private Cupo cupo;
	
	
	@BeforeEach
	void setUp() {
		cupo = new Cupo(2);
	}
	
	@Test
	void sePuedeReservarSiHayCuposDisponibles() {
		cupo.reservar();
		cupo.reservar();
		assertThrows(IllegalArgumentException.class, () -> cupo.reservar());
	}
	
	@Test
	void gettersBasicosFuncionanCorrectamente() {
		assertEquals(2,cupo.getCapacidadTotal());
		assertEquals(0,cupo.getCuposReservados());
		cupo.reservar();
		assertEquals(1,cupo.getCuposReservados());
	}
}
