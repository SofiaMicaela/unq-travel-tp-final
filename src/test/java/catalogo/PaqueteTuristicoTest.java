package catalogo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaqueteTuristicoTest {
	private PaqueteTuristico paquete;
    private ItemCatalogo mockItem1;
    private ItemCatalogo mockItem2;

    @BeforeEach
    void setUp() {
        paquete = new PaqueteTuristico("Paquete Cancun", 20.0);
        
        mockItem1 = mock(ItemCatalogo.class);
        mockItem2 = mock(ItemCatalogo.class);
    }

    @Test
    void testAgregarItemsYCalcularPreciosConMocks() {
        when(mockItem1.getPrecioFinal()).thenReturn(400.0);
        when(mockItem2.getPrecioFinal()).thenReturn(600.0);

        paquete.agregarItem(mockItem1);
        paquete.agregarItem(mockItem2);

        assertEquals(1000.0, paquete.getPrecioBase(), 0.01);

        assertEquals(800.0, paquete.getPrecioFinal(), 0.01);
        assertEquals(20.0, paquete.getDescuentoPorcentaje());
        assertEquals(2, paquete.getItems().size());
    }

    @Test
    void testEsCategoriaConMocks() {
        paquete.agregarItem(mockItem1);
        paquete.agregarItem(mockItem2);

        when(mockItem1.esCategoria(Categoria.ClaseEconomica)).thenReturn(false);
        when(mockItem2.esCategoria(Categoria.ClaseEconomica)).thenReturn(false);
        
        assertFalse(paquete.esCategoria(Categoria.ClaseEconomica));

        when(mockItem2.esCategoria(Categoria.ClaseEjecutiva)).thenReturn(true);
       
        assertTrue(paquete.esCategoria(Categoria.ClaseEjecutiva));
    }
}
