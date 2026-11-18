package reportes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import catalogo.ItemCatalogo;

class ExportadorHTMLTest {

    @Test
    void exportaLaTablaYEscapaLosCaracteresEspeciales() {
        ItemCatalogo hotel = mock(ItemCatalogo.class);
        when(hotel.getNombre()).thenReturn("Hotel & <Sol>");

        FilaMasReservados fila =
            new FilaMasReservados(hotel, 3, 12000);

        ReporteMasReservados reporte = mock(ReporteMasReservados.class);
        when(reporte.getFilas()).thenReturn(List.of(fila));

        ExportadorHTML exportador = new ExportadorHTML();

        String resultado = exportador.visitar(reporte);

        String esperado = "<!DOCTYPE html>\n"
            + "<html><head><meta charset=\"UTF-8\">"
            + "<title>Servicios más reservados</title>"
            + "</head><body>\n"
            + "<h1>Servicios más reservados</h1>\n"
            + "<table>\n"
            + "<tr><th>Item</th><th>Cantidad vendida</th>"
            + "<th>Precio promedio</th></tr>\n"
            + "<tr><td>Hotel &amp; &lt;Sol&gt;</td>"
            + "<td>3</td><td>12000.0</td></tr>\n"
            + "</table>\n</body></html>";

        assertEquals(esperado, resultado);
    }
}
