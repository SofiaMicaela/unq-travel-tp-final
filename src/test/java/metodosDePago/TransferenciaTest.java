package metodosDePago;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import reserva.Reserva;

class TransferenciaTest {
	
	private ApiTransferencia api;
	private Transferencia transferencia;
	private LocalDate fecha; 
	private Reserva reserva;
	
	@BeforeEach
	void setUp() {
		api = mock(ApiTransferencia.class);
		fecha = LocalDate.now();
		reserva = mock(Reserva.class);
		
		transferencia = new Transferencia( api,"12345", "viaje", fecha);
	}
	@Test
	void procesaLaTransferenciaYGeneraElComprobante() {
		when(reserva.costoTotal()).thenReturn(2000f);
		when(api.validarCuenta("12345", "viaje")).thenReturn(true);
		
		when(api.transferir("12345", "viaje", 2000, fecha)).thenReturn("t123");
		
		transferencia.procesarPago(reserva);
		
		verify(api).validarCuenta("12345", "viaje");
		
		verify(api).transferir("12345", "viaje", 2000, fecha);
		
		assertEquals("t123", transferencia.getCodigoTransaccion());
		assertNotNull(transferencia.getComprobante());
		assertEquals("t123", transferencia.getComprobante().getNumeroOperacion());
		assertEquals("12345", transferencia.getComprobante().getCbuCvu());
		assertEquals( 2000, transferencia.getComprobante().getMonto());
		assertEquals(fecha, transferencia.getComprobante().getFechaEjecucion());
		
	}
	@Test 
	void rechazaElPagoSiLaCuentaNoEsValida() {
		when(api.validarCuenta("12345", "viaje")).thenReturn(false);
		
		assertThrows(PagoRechazadoEcxeption.class,() -> transferencia.procesarPago(reserva));
		
		verify(api, never()).transferir(anyString(), anyString(), anyFloat(), any(LocalDate.class));
		
		assertNull(transferencia.getCodigoTransaccion());
		assertNull(transferencia.getComprobante());
	}
	@Test 
	void procesarUnaTransferenciaProgramada() {
		when(reserva.costoTotal()).thenReturn(2000f);
		LocalDate fechaProgramada = fecha.plusDays(7);
		
		transferencia = new Transferencia ( api, "12345", "viaje", fechaProgramada);
		
		when(api.validarCuenta("12345","viaje")).thenReturn(true);
		when(api.transferir("12345","viaje",2000, fechaProgramada)).thenReturn("t123");
		
		transferencia.procesarPago(reserva);
		
		verify(api).transferir("12345","viaje", 2000, fechaProgramada );
		
		assertEquals("t123", transferencia.getCodigoTransaccion());
		assertNotNull(transferencia.getComprobante());
		assertEquals(fechaProgramada, transferencia.getComprobante().getFechaEjecucion());
		
	}
}
