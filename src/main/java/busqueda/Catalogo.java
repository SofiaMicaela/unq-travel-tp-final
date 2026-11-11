package busqueda;

import java.util.ArrayList;

import catalogo.ItemCatalogo;

public class Catalogo {
	private ArrayList<ItemCatalogo> items;
	
	public ArrayList<ItemCatalogo> buscar(CriterioBusqueda criterio){
		ArrayList<ItemCatalogo> items = new ArrayList<>();
		for (ItemCatalogo item : this.items) {
			if (criterio.cumple(item)) {
				items.add(item);
			}
		}
		return items;
	}
}
