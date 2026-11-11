package catalogo;

import java.util.ArrayList;
import java.util.List;

public class PaqueteTuristico implements ItemCatalogo{
	private String nombre;
    private String descripcion;
    private double descuentoPorcentaje;
    private List<ItemCatalogo> items = new ArrayList<>();

    public PaqueteTuristico(String nombre, double descuentoPorcentaje) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descuentoPorcentaje = descuentoPorcentaje;
    }

    public void agregarItem(ItemCatalogo item) {
        this.items.add(item);
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
    	double total = 0.0;
        for (ItemCatalogo item : this.items) {
            total += item.getPrecioFinal(); 
        }
        return total;    }

    @Override
    public double getPrecioFinal() {
        return getPrecioBase() * (1 - (descuentoPorcentaje / 100));
    }
    
    @Override
    public ArrayList<Categoria> getCategorias(){
    	ArrayList<Categoria> categorias = new ArrayList<Categoria>();
    	for (ItemCatalogo item : this.items) {
    		categorias.addAll(item.getCategorias());
    	}
    	return categorias;
    }

	public double getDescuentoPorcentaje() {
		return descuentoPorcentaje;
	}

	public List<ItemCatalogo> getItems() {
		return items;
	}
}
