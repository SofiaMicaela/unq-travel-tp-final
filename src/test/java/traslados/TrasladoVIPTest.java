package traslados;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TrasladoVIPTest {
	

	@Test
	void cobraElPorcentajeMasElCargoBase() {
		TrasladoVIP traslado = new TrasladoVIP(10,1000);
		
		assertEquals(1100f, traslado.calcularCosto(1000f));
	}
	@Test
	void cobraSoloElPorcentajeSiNoHayCargoBase() {
		TrasladoVIP traslado = new TrasladoVIP(10,0);
		
		assertEquals(100f, traslado.calcularCosto(1000f));
	}
	@Test 
	void cobraSoloElCargoBaseSiNoHayPorcentajeFijo() {
		TrasladoVIP traslado = new TrasladoVIP(0,1000);
		
		assertEquals(1000, traslado.calcularCosto(1000f));
	}
	@Test 
	void estaDisponible() {
		TrasladoVIP traslado = new TrasladoVIP(0,1000);
		
		assertTrue(traslado.estaDisponible());
	}
	
	// faltan test de calcularTraslado sobre un reserva 

}
