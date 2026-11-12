package busqueda;

import catalogo.Categoria;
import catalogo.ItemCatalogo;

public class CriterioPorCategoria implements CriterioBusqueda{
	private Categoria categoria;
	
	public CriterioPorCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public boolean cumple(ItemCatalogo item) {
		return item.esCategoria(this.categoria);
	}
}
