package busqueda;

import java.util.ArrayList;

import catalogo.ItemCatalogo;

public class CriterioOr implements CriterioBusqueda{
	private ArrayList<CriterioBusqueda> criterios;
	
	public CriterioOr(ArrayList<CriterioBusqueda> criterios) {
		this.criterios = criterios;
	}
	
	@Override
	public boolean cumple(ItemCatalogo item) {
		for (CriterioBusqueda criterio: criterios) {
			if (criterio.cumple(item)){
				return true;
			}
		}
		return false;
	}
}
