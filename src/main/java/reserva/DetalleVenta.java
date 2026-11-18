package reserva;

import catalogo.ItemCatalogo;

public class DetalleVenta {

    private final ItemCatalogo item;
    private final double importeCobrado;

    public DetalleVenta(ItemCatalogo item, double importeCobrado) {
        this.item = item;
        this.importeCobrado = importeCobrado;
    }

    public ItemCatalogo getItem() {
        return this.item;
    }

    public double getImporteCobrado() {
        return this.importeCobrado;
    }
}