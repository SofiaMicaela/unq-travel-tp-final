package traslados;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import reserva.Direccion;
import reserva.Reserva;

class TrasladoEstandarTest {
	
	private PlataformaTransporte plataforma;
	private MetodoTraslado traslado;
	private Reserva reserva;
	private Direccion direccion;
	
	@BeforeEach 
	void setUp() {
		//double 
		plataforma = mock(PlataformaTransporte.class);
		//SUT
		traslado = new TrasladoEstandar(plataforma);
		
		direccion = new Direccion("Unq", 123, "Bernal");
		reserva = new Reserva(2, direccion);
	}
	@Test
	void devuelveElCostoEstimadoPorLaPlataforma() {
		
		when(plataforma.estimarTraslado(2, direccion)).thenReturn(1500f);
		
		float costo = traslado.costoTraslado(reserva);
		
		assertEquals(1500f, costo);
		verify(plataforma).estimarTraslado(2, direccion);
	}
	@Test 
	void siempreEstaDisponible() {
		assertTrue(traslado.estaDisponible());
	}

}
