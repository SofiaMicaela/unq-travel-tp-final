package metodosDePago;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reserva.Reserva;

class TarjetaDeCreditoTest {
	
	private Tarjeta tarjeta;
	private ApiTarjeta api;
	private LocalDate vencimiento;
	private Reserva reserva;
	
	@BeforeEach 
	void setUp() {
		api = mock(ApiTarjeta.class);
		vencimiento = LocalDate.of(2026,11,27);
		reserva = mock(Reserva.class);
		
		tarjeta = new Tarjeta(api, "12345", "789", vencimiento);
	}
	
	
	@Test
	void procesaElPagoYGeneraElVoucher() {
		
		Reserva reserva = mock(Reserva.class);
		when(reserva.costoTotal()).thenReturn(1000f);
		
		Tarjeta tarjetaParaReserva = new Tarjeta(api, "12345","789", vencimiento);
		
		when(api.validarTarjeta("12345","789", vencimiento)).thenReturn(true);
		when(api.preautorizarFondos("12345",1000)).thenReturn(true);
		when(api.ejecutarTransaccion("12345",1000)).thenReturn("t123");
		
		tarjetaParaReserva.procesarPago(reserva);
		
		verify(api).validarTarjeta("12345","789",vencimiento); 
		verify(api).preautorizarFondos("12345",1000);
		verify(api).ejecutarTransaccion("12345",1000);
		
		assertEquals("t123", tarjetaParaReserva.getCodigoTransaccion());
		assertNotNull(tarjetaParaReserva.getVoucher());
		assertEquals("t123", tarjetaParaReserva.getVoucher().getCodigoTransaccion());
		assertEquals(1000, tarjetaParaReserva.getVoucher().getMonto());
	}
	@Test 
	void rechazaElPagpSoLosDAtosSonInvalidos() {
		when(api.validarTarjeta("12345", "789", vencimiento)).thenReturn(false);
		
		assertThrows(PagoRechazadoEcxeption.class, () ->tarjeta.procesarPago(reserva));
		
		verify(api, never()).preautorizarFondos(anyString(), anyFloat());
		verify(api, never()).ejecutarTransaccion(anyString(), anyFloat());
		
		assertNull(tarjeta.getCodigoTransaccion());
		assertNull(tarjeta.getVoucher());
	}
	@Test 
	void rechazaElPagoSiFallaLaPreautorizacion() {
		when(reserva.costoTotal()).thenReturn(1000f);
		when(api.validarTarjeta("12345", "789", vencimiento)).thenReturn(true);
		
		when(api.preautorizarFondos("12345",1000)).thenReturn(false);
		
		assertThrows(PagoRechazadoEcxeption.class, () -> tarjeta.procesarPago(reserva));
		
		verify(api, never()).ejecutarTransaccion(anyString(), anyFloat());
		
		assertNull(tarjeta.getCodigoTransaccion());
		assertNull(tarjeta.getVoucher());
	}

}
