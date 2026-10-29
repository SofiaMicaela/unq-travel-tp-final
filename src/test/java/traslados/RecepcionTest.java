package traslados;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import reserva.Reserva;

class RecepcionTest {
	

	@Test
	void suCostoSiempreEsCero() {
		Reserva reserva = mock(Reserva.class);
		MetodoTraslado traslado = new Recepcion(1);
		
		assertEquals(0, traslado.costoTraslado(reserva));
	}
	@Test
	void estaDisponibleSiHayGuias() {
		MetodoTraslado traslado = new Recepcion(1);
		
		assertTrue(traslado.estaDisponible());
	}
	
	@Test 
	void siNohayGuiasNoEstaDisponible() {
		MetodoTraslado traslado = new Recepcion(0);
		
		assertFalse(traslado.estaDisponible());
	}

}
