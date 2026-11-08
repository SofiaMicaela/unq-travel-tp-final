package metodosDePago;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransferenciaTest {
	
	private ApiTransferencia api;
	private Transferencia transferencia;
	private LocalDate fecha; 
	
	@BeforeEach
	void setUp() {
		api = mock(ApiTransferencia.class);
		fecha = LocalDate.now();
		
		transferencia = new Transferencia( api, 2000,  "12345", "viaje", fecha);
	}
	@Test
	void procesaLaTransferenciaYGeneraElComprobante() {
		when(api.validarCuenta("12345", "viaje")).thenReturn(true);
		
		when(api.transferir("12345", "viaje", 2000, fecha)).thenReturn("t123");
		
		transferencia.procesarPago();
		
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
		
		assertThrows(PagoRechazadoEcxeption.class,() -> transferencia.procesarPago());
		
		verify(api, never()).transferir(anyString(), anyString(), anyFloat(), any(LocalDate.class));
		
		assertNull(transferencia.getCodigoTransaccion());
		assertNull(transferencia.getComprobante());
	}
	@Test 
	void procesarUnaTransferenciaProgramada() {
		LocalDate fechaProgramada = fecha.plusDays(7);
		
		transferencia = new Transferencia ( api, 2000, "12345", "viaje", fechaProgramada);
		
		when(api.validarCuenta("12345","viaje")).thenReturn(true);
		when(api.transferir("12345","viaje",2000, fechaProgramada)).thenReturn("t123");
		
		transferencia.procesarPago();
		
		verify(api).transferir("12345","viaje", 2000, fechaProgramada );
		
		assertEquals("t123", transferencia.getCodigoTransaccion());
		assertNotNull(transferencia.getComprobante());
		assertEquals(fechaProgramada, transferencia.getComprobante().getFechaEjecucion());
		
	}
}
