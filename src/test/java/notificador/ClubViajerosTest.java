package notificador;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import reserva.Direccion;
import reserva.Reserva;

public class ClubViajerosTest {
	private MailSender mailSender;
	private Reserva reserva;
	
	@BeforeEach
	public void setUp() {
		mailSender = mock(MailSender.class);
		reserva = new Reserva(10, mock(Direccion.class));
		reserva.suscribir(new ClubViajeros(mailSender));
	}
	
	@Test
	public void seEnviaMailEnEstadoCancelado() {
		reserva.cancelar();
		verify(mailSender).enviarMail(reserva.getDireccion().asString(), 
									 "Nuevo cupón disponible",
									 "Con la cancelación de tu reserva obtuviste un cupón del 5.0% de descuento para tu próxima reserva!", // ToDo: No es dinámico con respecto a la implementación, quizá haya que cambiarlo
				   				     null);
	}
	
	@Test
	public void noSeEnviaMailEnEstadoNoCancelado() {
		reserva.confirmar();
		verify(mailSender, never()).enviarMail(reserva.getDireccion().asString(), 
				 "Cambio de estado de la reserva",
					 "Tu reserva pasó de estar Solicitud pendiente a Confirmado.",
				     null);
	}
}
