package metodosDePago;

import java.time.LocalDate;


public interface ApiTarjeta {

	boolean validarTarjeta(String numeroTarjeta, String cvv, LocalDate vencimiento);
	
	boolean preautorizarFondos(String numeroTarjeta, float monto);

	String ejecutarTransaccion(String numeroTarjeta, float monto);

}
