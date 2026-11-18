package reportes;

public class ExportadorCSv implements VisitanteReporte {

    @Override
    public String visitar(ReporteMasReservados reporte) {
        String csv = "Item,Cantidad vendida,Precio promedio\r\n";

        for (FilaMasReservados fila : reporte.getFilas()) {
            csv += escapar(fila.getItem().getNombre())
                + "," + fila.getCantidadVendida()
                + "," + fila.getPrecioPromedioCobrado()
                + "\r\n";
        }

        return csv;
    }

    private String escapar(String texto) {
        return "\"" + texto.replace("\"", "\"\"") + "\"";
    }
}