package catalogo.atributos;

public class DefinicionAtributo {
	private String nombre;
    private boolean obligatorio;
    private TipoDato tipo;

    public DefinicionAtributo(String nombre, boolean obligatorio, TipoDato tipo) {
        this.nombre = nombre;
        this.obligatorio = obligatorio;
        this.tipo = tipo;
    }

    public String getNombre() { return nombre; }
    
    public boolean esObligatorio() { return obligatorio; }
    
    public boolean validarTipo(Object valor) {
        return this.tipo.esValido(valor);
    }
}
