package busqueda;

import catalogo.ItemCatalogo;

public class CriterioPorPrecioMaximo implements CriterioBusqueda{
	private double precioBase;
	
	public CriterioPorPrecioMaximo(double precioBase) {
		this.precioBase = precioBase;
	}
	
	@Override
	public boolean cumple(ItemCatalogo item) {
		return item.getPrecioBase() <= this.precioBase;
	}
}
