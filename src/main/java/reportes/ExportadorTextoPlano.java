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
    @Override
    public String visitar(ReporteExposicionOperador reporte) {
        String texto = "Exposición por operador\n";

        for (FilaExposicionOperador fila : reporte.getFilas()) {
            texto += fila.getOperador()
                + " | Cantidad de servicios: " + fila.getCantidadServicios()
                + " | Porcentaje del catálogo: " + fila.getPorcentajeCatalogo() + "%"
                + "\n";
        }

        return texto;
    }
    @Override
    public String visitar(ReporteOcupacion reporte) {
        String texto = "Ocupación y cupo disponible\n";

        for (FilaOcupacion fila : reporte.getFilas()) {
            texto += fila.getCategoria()
                + " | Totales: " + fila.getCuposTotales()
                + " | Reservados: " + fila.getCuposReservados()
                + " | Libres: " + fila.getCuposLibres()
                + "\n";
        }

        return texto;
    }
}