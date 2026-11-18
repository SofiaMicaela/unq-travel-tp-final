package busqueda;

import catalogo.ItemCatalogo;

public class CriterioPorDisponibilidad implements CriterioBusqueda {

    @Override
    public boolean cumple(ItemCatalogo item) {
        return item.tieneCuposDisponibles();
    }
}
