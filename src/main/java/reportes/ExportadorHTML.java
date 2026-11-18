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
    @Override
    public String visitar(ReporteExposicionOperador reporte) {
        String html = "<!DOCTYPE html>\n"
            + "<html><head><meta charset=\"UTF-8\">"
            + "<title>Exposición por operador</title>"
            + "</head><body>\n"
            + "<h1>Exposición por operador</h1>\n"
            + "<table>\n"
            + "<tr><th>Operador</th><th>Cantidad de servicios</th>"
            + "<th>Porcentaje del catálogo</th></tr>\n";

        for (FilaExposicionOperador fila : reporte.getFilas()) {
            html += "<tr>"
                + "<td>" + escapar(fila.getOperador()) + "</td>"
                + "<td>" + fila.getCantidadServicios() + "</td>"
                + "<td>" + fila.getPorcentajeCatalogo() + "%</td>"
                + "</tr>\n";
        }

        return html + "</table>\n</body></html>";
    }
    @Override
    public String visitar(ReporteOcupacion reporte) {
        String html = "<!DOCTYPE html>\n"
            + "<html><head><meta charset=\"UTF-8\">"
            + "<title>Ocupación y cupo disponible</title>"
            + "</head><body>\n"
            + "<h1>Ocupación y cupo disponible</h1>\n"
            + "<table>\n"
            + "<tr><th>Categoría</th><th>Cupos totales</th>"
            + "<th>Cupos reservados</th><th>Cupos libres</th></tr>\n";

        for (FilaOcupacion fila : reporte.getFilas()) {
            html += "<tr>"
                + "<td>" + fila.getCategoria().name() + "</td>"
                + "<td>" + fila.getCuposTotales() + "</td>"
                + "<td>" + fila.getCuposReservados() + "</td>"
                + "<td>" + fila.getCuposLibres() + "</td>"
                + "</tr>\n";
        }

        return html + "</table>\n</body></html>";
    }
}