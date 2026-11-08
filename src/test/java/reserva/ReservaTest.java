package reserva;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import traslados.*;

class ReservaTest {
	
	Reserva reserva;
	MetodoTraslado recepcion;

	
	@BeforeEach
	public void setUp() {
		
		Direccion destino = new Direccion("Unq", 123, "Bernal");
		reserva = new Reserva(2, destino);
		recepcion = new Recepcion(2);
	}
	@Test 
	public void permiteElegirUnTrasladoDisponible() throws TrasladoNoDisponibleExcepion {
		reserva.elegirTraslado(recepcion);
		
		assertEquals(0, reserva.costoTraslado());
		
	}
	@Test
	void rechazaUnTrasladoNoDisponible() {
		MetodoTraslado traslado = mock(MetodoTraslado.class);
		
		when(traslado.estaDisponible()).thenReturn(false);
		
		assertThrows(TrasladoNoDisponibleExcepion.class, () -> reserva.elegirTraslado(traslado));
	}
	@Test 
	void devuelveElCostoInformadoPorElTraslado() throws TrasladoNoDisponibleExcepion{
		
		MetodoTraslado traslado = mock(MetodoTraslado.class);
		
		when(traslado.estaDisponible()).thenReturn(true);
		when(traslado.costoTraslado(reserva)).thenReturn(500f);
		
		reserva.elegirTraslado(traslado);
		float costo = reserva.costoTraslado();
		
		assertEquals(500f, costo);
	}
	@Test 
	void noPermiteConsultarElCostoSinElegirTraslado() {
		
		assertThrows(IllegalStateException.class, () -> reserva.costoTraslado());
	}
	@Test
	void conservaElTrasladoCuandoSeIntentaCambiarPorUnoNoDisponible() throws TrasladoNoDisponibleExcepion {
		MetodoTraslado traslado1 = mock(MetodoTraslado.class);
		MetodoTraslado traslado2 = mock(MetodoTraslado.class);
		
		when(traslado1.estaDisponible()).thenReturn(true);
		when(traslado2.estaDisponible()).thenReturn(false);
		
		reserva.elegirTraslado(traslado1);
		
		assertThrows(TrasladoNoDisponibleExcepion.class, () -> reserva.elegirTraslado(traslado2));
		assertEquals(traslado1, reserva.getTraslado());
		
	}
		
	
}
