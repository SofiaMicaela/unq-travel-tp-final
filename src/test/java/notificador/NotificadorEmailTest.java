package notificador;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import metodosDePago.ProcesadorPago;
import reserva.Direccion;
import reserva.Reserva;

public class NotificadorEmailTest {
	private MailSender mailSender;
	private Reserva reserva;
	
	@BeforeEach
	public void setUp() {
		mailSender = mock(MailSender.class);
		reserva = new Reserva(10, mock(Direccion.class));
		ProcesadorPago procesador = mock(ProcesadorPago.class);
	    reserva.elegirMetodoPago(procesador);
		reserva.suscribir(new NotificadorEmail(mailSender));
	}
	
	@Test
	public void seEnviaMailEnEstadoNotificable() {
		reserva.confirmar();
		verify(mailSender).enviarMail(reserva.getDireccion().asString(), 
									 "Cambio de estado de la reserva",
				   					 "Tu reserva pasó de estar Solicitud pendiente a Confirmada.",
				   				     null);
	}
	
	@Test
	public void noSeEnviaMailEnEstadoNoNotificable() {
		reserva.confirmar();
		reserva.iniciarCheckIn();
		verify(mailSender, times(1)).enviarMail(reserva.getDireccion().asString(), 
				 "Cambio de estado de la reserva",
					 "Tu reserva pasó de estar Solicitud pendiente a Confirmada.",
				     null);
	}
}
