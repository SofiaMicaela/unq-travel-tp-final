package notificador;

import reserva.Reserva;
import reserva.estado.EstadoBase;

public class NotificadorEmail implements ObservadorReserva{
		
	private MailSender mailSender;
	
	public NotificadorEmail(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Override
	public void update(Reserva reserva, EstadoBase antiguo, EstadoBase nuevo){
		if (!nuevo.esNotificable()){
			return;
		}	
		this.mailSender.enviarMail(reserva.getDireccion().asString(), 
								   "Cambio de estado de la reserva",
								   "Tu reserva pasó de estar " + antiguo.nombre() + " a " + nuevo.nombre() + ".",
								   null);
	}
}
