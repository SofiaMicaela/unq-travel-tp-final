package reportes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import catalogo.ItemCatalogo;

class ExportadorTextoPlanoTest {

    @Test
    void exportaElTituloYLosDatosDeLasFilas() {
        ItemCatalogo hotel = mock(ItemCatalogo.class);
        when(hotel.getNombre()).thenReturn("Hotel");

        FilaMasReservados fila =
            new FilaMasReservados(hotel, 3, 12000);

        ReporteMasReservados reporte = mock(ReporteMasReservados.class);
        when(reporte.getFilas()).thenReturn(List.of(fila));

        ExportadorTextoPlano exportador = new ExportadorTextoPlano();

        String resultado = exportador.visitar(reporte);

        String esperado = "Servicios más reservados\n"
            + "Hotel | Cantidad vendida: 3"
            + " | Precio promedio: 12000.0\n";

        assertEquals(esperado, resultado);
    }
}