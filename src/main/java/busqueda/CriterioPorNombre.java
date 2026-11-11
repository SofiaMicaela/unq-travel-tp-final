package busqueda;

import catalogo.ItemCatalogo;

public class CriterioPorNombre implements CriterioBusqueda{
	private String nombre;
	
	public CriterioPorNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public boolean cumple(ItemCatalogo item) {
		return item.getNombre().equalsIgnoreCase(nombre);
	}
}
