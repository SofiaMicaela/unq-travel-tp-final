package notificador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import metodosDePago.ProcesadorPago;
import reserva.Direccion;
import reserva.Reserva;
import traslados.MetodoTraslado;
import traslados.Recepcion;
import traslados.TrasladoNoDisponibleExcepcion;
import traslados.TrasladoVIP;

public class GeneradorVoucherTest {
	private GeneradorVoucher generador;
	private Reserva reserva;

	@BeforeEach
	public void setUp() {
		Recepcion traslado = new Recepcion(4);
		reserva = new Reserva(10, mock(Direccion.class));
		reserva.elegirTraslado(traslado);
		generador = new GeneradorVoucher();
		ProcesadorPago procesador = mock(ProcesadorPago.class);
		reserva.elegirMetodoPago(procesador);
		reserva.suscribir(generador);
	}

	@Test
	public void seAgregaVoucherAlFinalizar() {
		reserva.confirmar();
		reserva.iniciarCheckIn();
		reserva.iniciarEjecucion();
		reserva.finalizar();
		assertEquals(1, generador.getVouchers().size());
	}
	
	@Test
	public void noSeAgregaVoucherSiNoFinaliza() {
		reserva.confirmar();
		reserva.iniciarCheckIn();
		reserva.iniciarEjecucion();
		assertEquals(0, generador.getVouchers().size());
	}
}
