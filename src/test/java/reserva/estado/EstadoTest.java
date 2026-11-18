package reserva.estado;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.Cupo;
import catalogo.ItemCatalogo;
import catalogo.ServicioIndividual;
import metodosDePago.ProcesadorPago;
import reserva.Direccion;
import reserva.Reserva;

public class EstadoTest {
	private Reserva reserva;
    private ServicioIndividual vuelo;
    private ProcesadorPago procesador;

    @BeforeEach
    void setUp() {
    	reserva = new Reserva(5, mock(Direccion.class));
		procesador = mock(ProcesadorPago.class);
		reserva.elegirMetodoPago(procesador);
		vuelo = new ServicioIndividual("A-0001", "Vuelo Buenos Aires - Brasil", "Ida y vuelta", "Aereolineas Argentinas", Categoria.ClaseEjecutiva, 1000000, 0, mock(Cupo.class));
		
		reserva.agregarServicio(vuelo);
    }

    @Test
    void testSolicitudPendientePermiteAgregarServicio() {
    	List<ItemCatalogo> listaSimulada = new ArrayList<>();
    	listaSimulada.add(vuelo);
    	assertEquals(listaSimulada, reserva.getServiciosAux());

        assertTrue(listaSimulada.contains(vuelo));
    }
    
    @Test
	void sePuedenQuitarItemsEnBorrador() {
		reserva.quitarServicio(vuelo);
		assertEquals(0, reserva.getServiciosAux().size());
	}
    
    @Test
    void noSePuedenQuitarItemsEnEstadoDistintoABorrador() {
    	reserva.confirmar();
		assertThrows(OperacionInvalidaEstadoExcepcion.class, () -> reserva.quitarServicio(vuelo));
    }

    @Test
    void testSolicitudPendientePermiteConfirmarYCambiaEstado() {
    	SolicitudPendiente estado = new SolicitudPendiente();

        estado.procesarPago(reserva, procesador);

        verify(procesador).procesarPago(reserva);
        //verify(reserva).registrarVenta();
        //verify(reserva).setEstado(any(Confirmada.class)); 
    }
    
    @Test
    void cicloDeVidaDeReservaFuncionaCorrectamente() {
    	assertEquals("Solicitud pendiente", reserva.getEstado().nombre());
    	
    	reserva.confirmar();
    	assertEquals("Confirmada", reserva.getEstado().nombre());
    	
    	reserva.iniciarCheckIn();
    	assertEquals("En checkIn", reserva.getEstado().nombre());
    	
    	reserva.iniciarEjecucion();
    	assertEquals("En curso", reserva.getEstado().nombre());
    	
    	reserva.finalizar();
    	assertEquals("Finalizada", reserva.getEstado().nombre());
    }
    
    @Test
    void estadosNotificables() {
    	reserva.confirmar();
    	assertTrue(reserva.getEstado().esNotificable());
    	
    	reserva.iniciarCheckIn();
    	assertFalse(reserva.getEstado().esNotificable());
    	
    	reserva.iniciarEjecucion();
    	assertTrue(reserva.getEstado().esNotificable());
    	
    	reserva.finalizar();
    	assertTrue(reserva.getEstado().esNotificable());
    }
    
    //-- Sección cancelado
    @Test
    void reservaPendienteEsCancelable() {
    	reserva.cancelar();
    	assertTrue(reserva.getEstado().estaCancelada());
    	assertEquals("Cancelada", reserva.getEstado().nombre());
    }
    
    @Test
    void reservaConfirmadaEsCancelable() {
    	reserva.confirmar();
    	reserva.cancelar();
    	assertTrue(reserva.getEstado().estaCancelada());
    }
    
    @Test
    void reservaEnCheckInEsCancelable() {
    	reserva.confirmar();
    	reserva.iniciarCheckIn();
    	reserva.cancelar();
    	assertTrue(reserva.getEstado().estaCancelada());
    }
    
    @Test
    void reservaEnCursoEsCancelable() {
    	reserva.confirmar();
    	reserva.iniciarCheckIn();
    	reserva.iniciarEjecucion();
    	reserva.cancelar();
    	assertTrue(reserva.getEstado().estaCancelada());
    }
    
    @Test
    void estadoCanceladoNoTieneOperacionesDisponibles() {
    	reserva.cancelar();
    	assertThrows(OperacionInvalidaEstadoExcepcion.class, () -> reserva.confirmar());
    	assertThrows(OperacionInvalidaEstadoExcepcion.class, () -> reserva.iniciarCheckIn());
    	assertThrows(OperacionInvalidaEstadoExcepcion.class, () -> reserva.iniciarEjecucion());
    	assertThrows(OperacionInvalidaEstadoExcepcion.class, () -> reserva.finalizar());
    	assertThrows(OperacionInvalidaEstadoExcepcion.class, () -> reserva.cancelar());
    }
}
