package catalogo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AtributoTest {

	    @Test
	    void testAtributoNoObligatorioNaceSinValor() {
	        Atributo atributo = new Atributo("asiento", false);
	        
	        assertEquals("asiento", atributo.getNombre());
	        assertFalse(atributo.esObligatorio());
	        assertNull(atributo.getValor());
	        assertFalse(atributo.tieneValor());
	    }

	    @Test
	    void testAtributoConValorInicialEnConstructorSobrecargado() {
	        Atributo atributo = new Atributo("clase", "Ejecutiva", true);
	        
	        assertEquals("clase", atributo.getNombre());
	        assertTrue(atributo.esObligatorio());
	        assertEquals("Ejecutiva", atributo.getValor());
	        assertTrue(atributo.tieneValor());
	    }

	    @Test
	    void testSetValorActualizaCorrectamente() {
	        Atributo atributo = new Atributo("equipajeExtra", true);
	        assertFalse(atributo.tieneValor());
	        
	        atributo.setValor(23); 
	        
	        assertEquals(23, atributo.getValor());
	        assertTrue(atributo.tieneValor());
	    }
	}

