package metodosDePago;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import reserva.Reserva;

class BilleteraTest {

	private ApiBilletera api;
	private  Billetera billetera;
	private Reserva reserva;
	
	@BeforeEach
	void setUp() {
		api = mock(ApiBilletera.class);
		billetera = new Billetera(api, "cuenta123");
		reserva = mock(Reserva.class);
		when(reserva.costoTotal()).thenReturn(1500f);
	}
	
	@Test
	void procesaElPagoRegistraElCodigoYEnviaElPush() {
		when(api.tieneSaldoSuficiente("cuenta123", 1500)).thenReturn(true);
		
		when(api.bloquearSaldo("cuenta123", 1500)).thenReturn(true);
		
		when(api.acreditarPago("cuenta123", 1500)).thenReturn("b123");
		
		billetera.procesarPago(reserva);
		
		verify(api).tieneSaldoSuficiente("cuenta123", 1500);
		verify(api).bloquearSaldo("cuenta123", 1500);
		verify(api).acreditarPago("cuenta123",1500);
		verify(api).enviarNotificacionPush("cuenta123", "b123");
		
		assertEquals("b123", billetera.getCodigoTransaccion());
		}
	@Test 
	void rechazaElPagoSiNoHaySaldoSuficiente() {
		when(api.tieneSaldoSuficiente("cuenta123", 1500)).thenReturn(false);
		
		assertThrows(PagoRechazadoEcxeption.class, () -> billetera.procesarPago(reserva));
		
		verify(api, never()).bloquearSaldo(anyString(), anyFloat());
		
		verify(api, never()).acreditarPago(anyString(), anyFloat());
		
		verify(api, never()).enviarNotificacionPush(anyString(), anyString());
		
		assertNull(billetera.getCodigoTransaccion());
		
	}
	@Test 
	void rechazaElPagoSiFallaElBloqueoDelSaldo() {
		when(api.tieneSaldoSuficiente("cuenta123", 1500)).thenReturn(true);
		
		when(api.bloquearSaldo("cuenta123", 1500)).thenReturn(false);
		
		assertThrows(PagoRechazadoEcxeption.class, () -> billetera.procesarPago(reserva));
		
		verify(api, never()).acreditarPago(anyString(), anyFloat());
		verify(api, never()).enviarNotificacionPush(anyString(), anyString());
		
		assertNull(billetera.getCodigoTransaccion());
		
	}
	
}
