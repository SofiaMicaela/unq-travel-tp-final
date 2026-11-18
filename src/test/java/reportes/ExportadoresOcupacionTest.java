package reportes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;

public class ExportadoresOcupacionTest {
	private ReporteOcupacion reporteMock;

    @BeforeEach
    void setUp() {
        reporteMock = mock(ReporteOcupacion.class);
        List<FilaOcupacion> filas = List.of(
            new FilaOcupacion(Categoria.PrimeraClase, 10, 3)
        );
        when(reporteMock.getFilas()).thenReturn(filas);
    }

    @Test
    void testExportarCSV() {
        String esperado = "Categoria,Cupos totales,Cupos reservados,Cupos libres\r\n"
                        + "PrimeraClase,10,3,7\r\n";
        assertEquals(esperado, new ExportadorCSv().visitar(reporteMock));
    }

    @Test
    void testExportarTextoPlano() {
        String esperado = "Ocupación y cupo disponible\n"
                        + "PrimeraClase | Totales: 10 | Reservados: 3 | Libres: 7\n";
        assertEquals(esperado, new ExportadorTextoPlano().visitar(reporteMock));
    }

    @Test
    void testExportarHTML() {
        String html = new ExportadorHTML().visitar(reporteMock);
        assertTrue(html.contains("<td>PrimeraClase</td><td>10</td><td>3</td><td>7</td>"));
    }
}
