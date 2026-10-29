package traslados;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import reserva.Reserva;

class TrasladoVIPTest {
	

	@Test
	void cobraElPorcentajeMasElCargoBase() {
		TrasladoVIP traslado = new TrasladoVIP(10,1000);
		
		assertEquals(1100f, traslado.calcularCosto(1000f));
	}
	@Test
	void cobraSoloElPorcentajeSiNoHayCargoBase() {
		TrasladoVIP traslado = new TrasladoVIP(10,0);
		
		assertEquals(100f, traslado.calcularCosto(1000f), 0.001f);
	}
	@Test 
	void cobraSoloElCargoBaseSiNoHayPorcentajeFijo() {
		TrasladoVIP traslado = new TrasladoVIP(0,1000);
		
		assertEquals(1000, traslado.calcularCosto(1000f), 0.001f);
	}
	@Test 
	void estaDisponible() {
		TrasladoVIP traslado = new TrasladoVIP(0,1000);
		
		assertTrue(traslado.estaDisponible());
	}
	@Test 
	void calcularElCostoAPartirdelCostoDeLaReserva() {
		Reserva reserva = mock(Reserva.class);
		MetodoTraslado traslado = new TrasladoVIP(10,1000f);
		
		when(reserva.costoSinTraslado()).thenReturn(2000f);
		
		float costo = traslado.costoTraslado(reserva);
		
		assertEquals(1200f, costo);
	}

}
