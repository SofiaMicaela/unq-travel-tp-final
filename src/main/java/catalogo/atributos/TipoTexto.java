package catalogo.atributos;


public class TipoTexto implements TipoDato{
    @Override
	public boolean esValido(Object valor) {
        return valor instanceof String;
    }
}
