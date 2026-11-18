package reportes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExportadoresExposicionOperadorTest {
	private ReporteExposicionOperador reporte;

    @BeforeEach
    void setUp() {
        reporte = mock(ReporteExposicionOperador.class);
        when(reporte.getFilas()).thenReturn(
            List.of(new FilaExposicionOperador("Aerolineas", 3, 75.0))
        );
    }

    @Test
    void testExportarCSV() {
        String esperado = "Operador,Cantidad de servicios,Porcentaje del catalogo\r\n"
                        + "\"Aerolineas\",3,75.0\r\n";
        assertEquals(esperado, new ExportadorCSv().visitar(reporte));
    }

    @Test
    void testExportarTextoPlano() {
        String esperado = "Exposición por operador\n"
                        + "Aerolineas | Cantidad de servicios: 3 | Porcentaje del catálogo: 75.0%\n";
        assertEquals(esperado, new ExportadorTextoPlano().visitar(reporte));
    }

    @Test
    void testExportarHTML() {
        String html = new ExportadorHTML().visitar(reporte);
        assertTrue(html.contains("<td>Aerolineas</td><td>3</td><td>75.0%</td>"));
    }
}
