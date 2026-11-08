package catalogo.atributos;


public class TipoNumerico implements TipoDato{
    @Override
	public boolean esValido(Object valor) {
        return valor instanceof Number;
    }
}
