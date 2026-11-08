package metodosDePago;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TarjetaDeCreditoTest {
	
	private Tarjeta tarjeta;
	private ApiTarjeta api;
	private LocalDate vencimiento;
	
	@BeforeEach 
	void setUp() {
		api = mock(ApiTarjeta.class);
		vencimiento = LocalDate.of(2026,11,27);
		
		tarjeta = new Tarjeta(api,1000, "12345", "789", vencimiento);
	}
	
	
	@Test
	void procesaElPagoYGeneraElVoucher() {
		when(api.validarTarjeta("12345","789", vencimiento)).thenReturn(true);
		when(api.preautorizarFondos("12345",1000)).thenReturn(true);
		when(api.ejecutarTransaccion("12345",1000)).thenReturn("t123");
		
		tarjeta.procesarPago();
		
		verify(api).validarTarjeta("12345","789",vencimiento); 
		verify(api).preautorizarFondos("12345",1000);
		verify(api).ejecutarTransaccion("12345",1000);
		
		assertEquals("t123", tarjeta.getCodigoTransaccion());
		assertNotNull(tarjeta.getVoucher());
		assertEquals("t123", tarjeta.getVoucher().getCodigoTransaccion());
		assertEquals(1000, tarjeta.getVoucher().getMonto());
	}
	@Test 
	void rechazaElPagpSoLosDAtosSonInvalidos() {
		when(api.validarTarjeta("12345", "789", vencimiento)).thenReturn(false);
		
		assertThrows(PagoRechazadoEcxeption.class, () ->tarjeta.procesarPago());
		
		verify(api, never()).preautorizarFondos(anyString(), anyFloat());
		verify(api, never()).ejecutarTransaccion(anyString(), anyFloat());
		
		assertNull(tarjeta.getCodigoTransaccion());
		assertNull(tarjeta.getVoucher());
	}
	@Test 
	void rechazaElPagoSiFallaLaPreautorizacion() {
		when(api.validarTarjeta("12345", "789", vencimiento)).thenReturn(true);
		
		when(api.preautorizarFondos("12345",1000)).thenReturn(false);
		
		assertThrows(PagoRechazadoEcxeption.class, () -> tarjeta.procesarPago());
		
		verify(api, never()).ejecutarTransaccion(anyString(), anyFloat());
		
		assertNull(tarjeta.getCodigoTransaccion());
		assertNull(tarjeta.getVoucher());
	}

}
