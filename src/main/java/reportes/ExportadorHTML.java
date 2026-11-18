package reportes;

public class ExportadorHTML implements VisitanteReporte {

    @Override
    public String visitar(ReporteMasReservados reporte) {
        String html = "<!DOCTYPE html>\n"
            + "<html><head><meta charset=\"UTF-8\">"
            + "<title>Servicios más reservados</title>"
            + "</head><body>\n"
            + "<h1>Servicios más reservados</h1>\n"
            + "<table>\n"
            + "<tr><th>Item</th><th>Cantidad vendida</th>"
            + "<th>Precio promedio</th></tr>\n";

        for (FilaMasReservados fila : reporte.getFilas()) {
            html += "<tr>"
                + "<td>" + escapar(fila.getItem().getNombre()) + "</td>"
                + "<td>" + fila.getCantidadVendida() + "</td>"
                + "<td>" + fila.getPrecioPromedioCobrado() + "</td>"
                + "</tr>\n";
        }

        return html + "</table>\n</body></html>";
    }

    private String escapar(String texto) {
        return texto.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;");
    }
}