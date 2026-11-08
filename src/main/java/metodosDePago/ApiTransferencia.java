package metodosDePago;

import java.time.LocalDate;

public interface ApiTransferencia {

	boolean validarCuenta(String cbuCvu, String alias);

	String transferir(String cbuCvu, String alias, float monto, LocalDate fechaEjecucion);

}
