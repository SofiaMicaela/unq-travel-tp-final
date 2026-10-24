package traslados;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import reserva.Direccion;
import reserva.Reserva;

class RecepcionTest {
	
	Direccion destino = new Direccion("calle", 123, "unLugar");
	Reserva reserva = new Reserva(2, destino);
	

	@Test
	void suCostoSiempreEsCero() {
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
