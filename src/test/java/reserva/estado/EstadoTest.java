package reserva.estado;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.ItemCatalogo;
import reserva.Reserva;

public class EstadoTest {
	private Reserva mockReserva;
    private ItemCatalogo mockServicio;

    @BeforeEach
    void setUp() {
        mockReserva = mock(Reserva.class);
        mockServicio = mock(ItemCatalogo.class);
    }

    @Test
    void testSolicitudPendientePermiteAgregarServicio() {
        SolicitudPendiente estado = new SolicitudPendiente();
        List<ItemCatalogo> listaSimulada = new ArrayList<>();
        when(mockReserva.getServiciosAux()).thenReturn(listaSimulada);

        estado.agregarServicio(mockReserva, mockServicio);

        assertTrue(listaSimulada.contains(mockServicio));
    }

    @Test
    void testSolicitudPendientePermiteConfirmarYCambiaEstado() {
        SolicitudPendiente estado = new SolicitudPendiente();

        estado.confirmar(mockReserva);

        verify(mockReserva).procesarPago(); 
        verify(mockReserva).setEstado(any(Confirmada.class));
    }

    @Test
    void testConfirmadaLanzaExcepcionAlIntentarAgregarServicio() {
        Confirmada estado = new Confirmada();

        OperacionInvalidaEstadoExcepcion excepcion = assertThrows(OperacionInvalidaEstadoExcepcion.class, () -> {
            estado.agregarServicio(mockReserva, mockServicio);
        });
        
        assertEquals("No se pueden agregar servicios estando en estado: Confirmada", excepcion.getMessage());    }

    @Test
    void testConfirmadaPermiteIniciarCheckInYCambiaEstado() {
        Confirmada estado = new Confirmada();

        estado.iniciarCheckIn(mockReserva);

        verify(mockReserva).setEstado(any(EnCheckIn.class));
    }

    @Test
    void testCanceladaLanzaExcepcionAlIntentarCualquierAccion() {
        Cancelada estado = new Cancelada();

        assertThrows(OperacionInvalidaEstadoExcepcion.class, () -> {
            estado.confirmar(mockReserva);
        });

        assertThrows(OperacionInvalidaEstadoExcepcion.class, () -> {
            estado.cancelar(mockReserva);
        });
    }
}
