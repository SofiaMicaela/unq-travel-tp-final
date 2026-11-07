package catalogo;
import catalogo.atributos.*;
import java.util.HashMap;
import java.util.Map;

public class ServicioIndividual implements ItemCatalogo{
	private String codigo;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String operador;
    private double precioBase;
    private double descuentoPromocional;
    private Map<DefinicionAtributo, Object> atributosDinamicos = new HashMap<>();
    
    public ServicioIndividual(String codigo, String nombre, String descripcion, 
            								String operador, String categoria, 
            						double precioBase, double descuentoPromocional) {
    		this.codigo = codigo;
    		this.nombre = nombre;
    		this.descripcion = descripcion;
    		this.operador = operador;
    		this.categoria = categoria;
    		this.precioBase = precioBase;
    		this.descuentoPromocional = descuentoPromocional;
}
    @Override
	public String getNombre() {
		return nombre;
	}
	@Override
	public String getDescripcion() {
		return descripcion;
	}
	@Override
	public double getPrecioBase() {
		return precioBase;
	}
	@Override
	public double getPrecioFinal() {
        return precioBase * (1 - (descuentoPromocional / 100));
    }
	
	public void validar() {
        for (Map.Entry<DefinicionAtributo, Object> entry : atributosDinamicos.entrySet()) {
            DefinicionAtributo def = entry.getKey();
            Object valor = entry.getValue();

            if (def.esObligatorio() && valor == null) {
                throw new IllegalStateException("Falta valor para el atributo obligatorio: " + def.getNombre());
            }

            if (valor != null && !def.validarTipo(valor)) {
                throw new IllegalArgumentException("Tipo de dato inválido para el atributo: " + def.getNombre());
            }
        }
	}
}
