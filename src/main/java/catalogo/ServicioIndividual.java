package catalogo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServicioIndividual implements ItemCatalogo{
	private String codigo;
    private String nombre;
    private String descripcion;
    private Categoria categoria;
    private String operador;
    private double precioBase;
    private double descuentoPromocional;
    private Map<String, Atributo> atributos = new HashMap<>();
    
    public ServicioIndividual(String codigo, String nombre, String descripcion, 
            								String operador, Categoria categoria, 
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
	@Override
	public boolean esCategoria(Categoria categoria) {
		return this.categoria == categoria;
	}

	
	public void registrarAtributo(String nombre, boolean obligatorio) {
        this.atributos.put(nombre, new Atributo(nombre, obligatorio));
    }
	
	public void configurarAtributo(String nombre, Object valor) {
        if (this.atributos.containsKey(nombre)) {
            this.atributos.get(nombre).setValor(valor);
        } else {
            throw new IllegalArgumentException("El atributo " + nombre + " no existe en este servicio.");
        }
    }
	
	public boolean estaListoParaReservar() {
        for (Atributo atributo : atributos.values()) {
            if (atributo.esObligatorio() && !atributo.tieneValor()) {
                return false; 
            }
        }
        return true;
    }
	public String getCodigo() {
		return codigo;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public String getOperador() {
		return operador;
	}
	public double getDescuentoPromocional() {
		return descuentoPromocional;
	}
	public Map<String, Atributo> getAtributosDinamicos() {
		return atributos;
	}
}
