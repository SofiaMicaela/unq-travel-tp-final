package reserva;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class DireccionTest {
	
	@Test
	public void devuelveLosDatosYLaDireccionComoTexto() {
	    Direccion direccion = new Direccion("Mitre", 123, "Quilmes");
	    
	    assertEquals("Mitre", direccion.getCalle());
	    assertEquals(123, direccion.getAltura());
	    assertEquals("Quilmes", direccion.getLocalidad());
	    assertEquals("Mitre123Quilmes", direccion.asString());
	}

}
