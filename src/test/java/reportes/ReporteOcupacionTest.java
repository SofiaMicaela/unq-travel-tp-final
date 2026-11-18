package reportes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.ServicioIndividual;
import reserva.Reserva;

public class ReporteOcupacionTest {
	private ReporteOcupacion reporte;
    private ServicioIndividual servicioMock;
    private Reserva reservaMock;
    private LocalDate desde;
    private LocalDate hasta;

    @BeforeEach
    void setUp() {
        reporte = new ReporteOcupacion();
        desde = LocalDate.of(2026, 11, 1);
        hasta = LocalDate.of(2026, 11, 30);

        servicioMock = mock(ServicioIndividual.class);
        when(servicioMock.getCategoria()).thenReturn(Categoria.PrimeraClase);
        when(servicioMock.getCupoTotal()).thenReturn(10);

        reservaMock = mock(Reserva.class);
        when(reservaMock.getFechaConfirmacion()).thenReturn(LocalDate.of(2026, 11, 15));
        when(reservaMock.getCantidadPasajeros()).thenReturn(3);
        when(reservaMock.getServiciosAux()).thenReturn(List.of(servicioMock));
        when(reservaMock.getEstado()).thenReturn(mock(reserva.estado.Confirmada.class));
    }

    @Test
    void testGenerarOcupacionCalculaTotalesYLibres() {
        reporte.generar(List.of(servicioMock), List.of(reservaMock), desde, hasta);
        List<FilaOcupacion> filas = reporte.getFilas();

        assertEquals(1, filas.size());
        assertEquals(Categoria.PrimeraClase, filas.get(0).getCategoria());
        assertEquals(10, filas.get(0).getCuposTotales());
        assertEquals(3, filas.get(0).getCuposReservados());
        assertEquals(7, filas.get(0).getCuposLibres());
    }

    @Test
    void testGenerarConFechasInvalidasOArgNulos() {
        assertThrows(IllegalArgumentException.class, () -> reporte.generar(null, List.of(), desde, hasta));
        assertThrows(IllegalArgumentException.class, () -> reporte.generar(List.of(), List.of(), hasta, desde));
    }

    @Test
    void testAceptarVisitante() {
        VisitanteReporte visitante = mock(VisitanteReporte.class);
        when(visitante.visitar(reporte)).thenReturn("ok");

        assertEquals("ok", reporte.aceptar(visitante));
        verify(visitante).visitar(reporte);
    }
}
