package busqueda;

import catalogo.ItemCatalogo;

public class CriterioNot implements CriterioBusqueda{
	private CriterioBusqueda criterio;
	
	public CriterioNot(CriterioBusqueda criterio) {
		this.criterio = criterio;
	}
	
	@Override
	public boolean cumple(ItemCatalogo item) {
		return !criterio.cumple(item);
	}
}
