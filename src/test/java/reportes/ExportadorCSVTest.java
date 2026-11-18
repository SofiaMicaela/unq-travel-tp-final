package reportes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import catalogo.ItemCatalogo;

class ExportadorCSVTest {

    @Test
    void exportaLosDatosYEscapaLasComillasDelNombre() {
        ItemCatalogo hotel = mock(ItemCatalogo.class);
        when(hotel.getNombre()).thenReturn("Hotel \"Sol\", centro");

        FilaMasReservados fila =
            new FilaMasReservados(hotel, 3, 12000);

        ReporteMasReservados reporte = mock(ReporteMasReservados.class);
        when(reporte.getFilas()).thenReturn(List.of(fila));

        ExportadorCSv exportador = new ExportadorCSv();

        String resultado = exportador.visitar(reporte);

        String esperado = "Item,Cantidad vendida,Precio promedio\r\n"
            + "\"Hotel \"\"Sol\"\", centro\",3,12000.0\r\n";

        assertEquals(esperado, resultado);
    }
}