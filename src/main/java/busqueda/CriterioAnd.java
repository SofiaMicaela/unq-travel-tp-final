package busqueda;

import java.util.ArrayList;

import catalogo.ItemCatalogo;

public class CriterioAnd implements CriterioBusqueda{
	private ArrayList<CriterioBusqueda> criterios = new ArrayList<>();
	
	public CriterioAnd(ArrayList<CriterioBusqueda> criterios) {
		this.criterios = criterios;
	}
	
	@Override
	public boolean cumple(ItemCatalogo item) {
		for (CriterioBusqueda criterio: criterios) {
			if (!criterio.cumple(item)){
				return false;
			}
		}
		return true;
	}
}
