package catalogo;

import java.util.ArrayList;

public interface ItemCatalogo {
	String getNombre();
    String getDescripcion();
    double getPrecioBase();
    double getPrecioFinal();
	boolean esCategoria(Categoria categoria);
}
