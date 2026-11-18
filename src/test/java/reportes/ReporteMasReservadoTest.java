package reportes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.ItemCatalogo;
import reserva.DetalleVenta;
import reserva.Reserva;

class ReporteMasReservadosTest {

    private ReporteMasReservados reporte;
    private LocalDate desde;
    private LocalDate hasta;
    private ItemCatalogo hotel;
    private ItemCatalogo excursion;

    @BeforeEach
    void setUp() {
        reporte = new ReporteMasReservados();
        desde = LocalDate.of(2026, 11, 1);
        hasta = LocalDate.of(2026, 11, 30);
        hotel = mock(ItemCatalogo.class);
        excursion = mock(ItemCatalogo.class);
    }

    @Test
    void agrupaVentasCalculaPromedioYOrdenaPorCantidad() {
        Reserva primera = mock(Reserva.class);
        Reserva segunda = mock(Reserva.class);

        when(primera.getFechaConfirmacion()).thenReturn(desde);
        when(segunda.getFechaConfirmacion()).thenReturn(hasta);
        when(primera.getDetallesVenta()).thenReturn(List.of(new DetalleVenta(excursion, 4000),new DetalleVenta(hotel, 10000)));
        when(segunda.getDetallesVenta()).thenReturn(List.of(new DetalleVenta(hotel, 12000)));

        reporte.generar(List.of(primera, segunda), desde, hasta);

        List<FilaMasReservados> filas = reporte.getFilas();

        assertEquals(2, filas.size());
        assertEquals(hotel, filas.get(0).getItem());
        assertEquals(2, filas.get(0).getCantidadVendida());
        assertEquals(11000, filas.get(0).getPrecioPromedioCobrado(), 0.01);
        assertEquals(excursion, filas.get(1).getItem());
        assertEquals(1, filas.get(1).getCantidadVendida());
        assertEquals(4000, filas.get(1).getPrecioPromedioCobrado(), 0.01);
    }

    @Test
    void descartaReservasFueraDelPeriodoOSinConfirmar() {
        Reserva anterior = mock(Reserva.class);
        Reserva posterior = mock(Reserva.class);

        when(anterior.getFechaConfirmacion()).thenReturn(desde.minusDays(1));
        when(posterior.getFechaConfirmacion()).thenReturn(hasta.plusDays(1));

        List<DetalleVenta> detalles = List.of(new DetalleVenta(hotel, 10000));

        when(anterior.getDetallesVenta()).thenReturn(detalles);
        when(posterior.getDetallesVenta()).thenReturn(detalles);

        reporte.generar(List.of(anterior, posterior), desde, hasta);

        assertTrue(reporte.getFilas().isEmpty());
    }

    @Test
    void rechazaUnPeriodoConFechasInvertidas() {
        assertThrows(IllegalArgumentException.class, () -> reporte.generar(List.of(), hasta, desde));
    }
}