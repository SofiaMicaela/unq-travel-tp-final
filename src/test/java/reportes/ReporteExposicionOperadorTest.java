package reportes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.PaqueteTuristico;
import catalogo.ServicioIndividual;

public class ReporteExposicionOperadorTest {
	private ReporteExposicionOperador reporte;
    private ServicioIndividual s1, s2, s3;

    @BeforeEach
    void setUp() {
        reporte = new ReporteExposicionOperador();
        s1 = mock(ServicioIndividual.class);
        s2 = mock(ServicioIndividual.class);
        s3 = mock(ServicioIndividual.class);
        when(s1.getOperador()).thenReturn("Aerolineas");
        when(s2.getOperador()).thenReturn("Aerolineas");
        when(s3.getOperador()).thenReturn("Despegar");
    }

    @Test
    void testGenerarConServiciosYPaquetes() {
        PaqueteTuristico paquete = mock(PaqueteTuristico.class);
        when(paquete.getItems()).thenReturn(List.of(s1, s2));

        reporte.generar(List.of(s3, paquete, s1));
        List<FilaExposicionOperador> filas = reporte.getFilas();

        assertEquals(2, filas.size());
        assertEquals("Aerolineas", filas.get(0).getOperador());
        assertEquals(75.0, filas.get(0).getPorcentajeCatalogo(), 0.01);
        assertEquals("Despegar", filas.get(1).getOperador());
        assertEquals(25.0, filas.get(1).getPorcentajeCatalogo(), 0.01);
    }

    @Test
    void testCatalogoVacioONulo() {
        reporte.generar(List.of());
        assertTrue(reporte.getFilas().isEmpty());

        assertThrows(IllegalArgumentException.class, () -> reporte.generar(null));
    }

    @Test
    void testAceptarVisitante() {
        VisitanteReporte visitante = mock(VisitanteReporte.class);
        when(visitante.visitar(reporte)).thenReturn("ok");

        assertEquals("ok", reporte.aceptar(visitante));
        verify(visitante).visitar(reporte);
    }
}
