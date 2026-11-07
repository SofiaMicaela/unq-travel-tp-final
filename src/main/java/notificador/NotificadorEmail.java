package notificador;

public class NotificadorEmail implements ObservadorReserva{
		
	private MailSender mailSender;
	/* ToDo: sacar comentario una vez agregado EstadoBase
	
	@Override
	public void update(EstadoBase antiguo, EstadoBase nuevo){
		if !(nuevo.esNotificable()){
			return
		}	
		this.estadoReserva = "Tu reserva pasó de estar " + antiguo.nombre() + "a " + nuevo.nombre() + "."
			
	}
	*/
}
