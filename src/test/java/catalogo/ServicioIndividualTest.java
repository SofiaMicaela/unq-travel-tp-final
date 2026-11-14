package catalogo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServicioIndividualTest {
	private ServicioIndividual servicio;

    @BeforeEach
    void setUp() {
        servicio = new ServicioIndividual(
            "V001", 
            "Vuelo Ezeiza-Miami", 
            "Vuelo directo comercial", 
            "Aerolíneas", 
            Categoria.PrimeraClase, 
            1000.0, 
            10.0 
        );
    }

    @Test
    void testGettersBasicos() {
        assertEquals("V001", servicio.getCodigo());
        assertEquals("Vuelo Ezeiza-Miami", servicio.getNombre());
        assertEquals("Vuelo directo comercial", servicio.getDescripcion());
        assertEquals("Aerolíneas", servicio.getOperador());
        assertEquals(Categoria.PrimeraClase, servicio.getCategoria());
        assertEquals(1000.0, servicio.getPrecioBase());
        assertEquals(10.0, servicio.getDescuentoPromocional());
    }

    @Test
    void testPrecioFinalConDescuentoPromocional() {
        assertEquals(900.0, servicio.getPrecioFinal(), 0.01);
    }

    @Test
    void testEsCategoria() {
        assertTrue(servicio.esCategoria(Categoria.PrimeraClase));
        assertFalse(servicio.esCategoria(Categoria.ClaseEconomica));
    }

    @Test
    void testAtributosDinamicosYValidacionDeListosParaReservar() {
        servicio.registrarAtributo("asiento", true);//Obligatorio       
        servicio.registrarAtributo("comidaEspecial", false); //Opcional

        assertFalse(servicio.estaListoParaReservar());

        servicio.configurarAtributo("asiento", "12A");
        assertTrue(servicio.estaListoParaReservar());

        servicio.configurarAtributo("comidaEspecial", "Vegetariana");
        assertTrue(servicio.estaListoParaReservar());
    }

    @Test
    void testConfigurarAtributoInexistenteLanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            servicio.configurarAtributo("atributoInvalido", "valor");
        });
        
        assertEquals("El atributo atributoInvalido no existe en este servicio.", exception.getMessage());
    }
}
