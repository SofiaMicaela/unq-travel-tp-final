package reportes;

public class ExportadorTextoPlano implements VisitanteReporte {

    @Override
    public String visitar(ReporteMasReservados reporte) {
        String texto = "Servicios más reservados\n";

        for (FilaMasReservados fila : reporte.getFilas()) {
            texto += fila.getItem().getNombre()
                + " | Cantidad vendida: " + fila.getCantidadVendida()
                + " | Precio promedio: " + fila.getPrecioPromedioCobrado()
                + "\n";
        }

        return texto;
    }
}