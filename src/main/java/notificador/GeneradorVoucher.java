package notificador;

import reserva.Reserva;
import reserva.estado.EstadoBase;

import java.util.ArrayList;

import metodosDePago.Voucher;

public class GeneradorVoucher implements ObservadorReserva{
	private ArrayList<Voucher> vouchers = new ArrayList<>();

	
	@Override
	public void update(Reserva reserva, EstadoBase antiguo, EstadoBase nuevo) {
		if (!nuevo.estaFinalizada()) {
			return;
		}
		float costoReserva = reserva.costoSinTraslado() + reserva.costoTraslado();
		this.vouchers.add(new Voucher("Factura N°" + ("" + vouchers.size()), costoReserva));
	}
	
	public ArrayList<Voucher> getVouchers() {
		return this.vouchers;
	}
}
