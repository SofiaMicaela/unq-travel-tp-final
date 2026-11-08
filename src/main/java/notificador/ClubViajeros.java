package notificador;

import reserva.Reserva;
import reserva.estado.EstadoBase;

public class ClubViajeros implements ObservadorReserva{
private MailSender mailSender;
	
	public ClubViajeros(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public double montoDescuento() {
		return 5;
	}
	
	@Override
	public void update(Reserva reserva, EstadoBase antiguo, EstadoBase nuevo) {
		if(!nuevo.estaCancelada()) {
			return;
		}
		this.mailSender.enviarMail(reserva.getDireccion().asString(),
								   "Nuevo cupón disponible",
								   "Con la cancelación de tu reserva obtuviste un cupón del " + ("" + this.montoDescuento()) + "% de descuento para tu próxima reserva!", 
								   null);
	}
}
