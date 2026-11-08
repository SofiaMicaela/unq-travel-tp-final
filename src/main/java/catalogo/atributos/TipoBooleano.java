package catalogo.atributos;

public class TipoBooleano implements TipoDato {
	@Override
    public boolean esValido(Object valor) {
        return valor instanceof Boolean;
    }
}
