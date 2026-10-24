package reserva;

import static org.junit.jupiter.api.Assertions.*;

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
	// faltan test de cuando rechaza un traslado no disponible

}
