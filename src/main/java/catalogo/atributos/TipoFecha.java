package catalogo.atributos;

import java.util.Date;

public class TipoFecha implements TipoDato{
    @Override
	public boolean esValido(Object valor) {
        return valor instanceof Date;
    }
}
