package catalogo;

public class Atributo {
	private final String nombre;
    private Object valor;
    private final boolean obligatorio;


    public Atributo(String nombre, boolean obligatorio) {
        this.nombre = nombre;
        this.obligatorio = obligatorio;
        this.valor = null; 
    }

 //Sobrecarga de metodos, atributos que ya vienen con un valor
    public Atributo(String nombre, Object valor, boolean obligatorio) {
        this.nombre = nombre;
        this.valor = valor;
        this.obligatorio = obligatorio;
    }

    public String getNombre() {
        return nombre;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public boolean esObligatorio() {
        return obligatorio;
    }

    public boolean tieneValor() {
        return this.valor != null;
    }
}
