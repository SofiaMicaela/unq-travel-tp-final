package reportes;

import catalogo.ItemCatalogo;

public class FilaMasReservados {

    private ItemCatalogo item;
    private int cantidadVendida;
    private double precioPromedioCobrado;

    public FilaMasReservados(ItemCatalogo item, int cantidadVendida,
                             double precioPromedioCobrado) {
        this.item = item;
        this.cantidadVendida = cantidadVendida;
        this.precioPromedioCobrado = precioPromedioCobrado;
    }

    public ItemCatalogo getItem() {
        return this.item;
    }

    public int getCantidadVendida() {
        return this.cantidadVendida;
    }

    public double getPrecioPromedioCobrado() {
        return this.precioPromedioCobrado;
    }
}