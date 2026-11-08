package notificador;

import reserva.Reserva;
import reserva.estado.EstadoBase;

/* ToDo: mal planteado. no utiliza mailSender


public class GeneradorVoucher implements ObservadorReserva{
	private MailSender mailSender;
	
	public GeneradorVoucher(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Override
	public void update(Reserva reserva, EstadoBase antiguo, EstadoBase nuevo) {
		if (!nuevo.estaFinalizada()) {
			return;
		enviarMail(reserva.getDireccion().asString(),
				   "Comprobante creado",
				   "Tu comprobante fiscal con su respectivo voucher ya se encuentran disponibles.",
				   null);
		}
	}
}
*/