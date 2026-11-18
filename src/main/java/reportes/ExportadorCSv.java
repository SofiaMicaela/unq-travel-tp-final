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
    @Override
    public String visitar(ReporteExposicionOperador reporte) {
        String csv = "Operador,Cantidad de servicios,Porcentaje del catalogo\r\n";

        for (FilaExposicionOperador fila : reporte.getFilas()) {
            csv += escapar(fila.getOperador())
                + "," + fila.getCantidadServicios()
                + "," + fila.getPorcentajeCatalogo()
                + "\r\n";
        }

        return csv;
    }
    
    @Override
    public String visitar(ReporteOcupacion reporte) {
        String csv = "Categoria,Cupos totales,Cupos reservados,Cupos libres\r\n";

        for (FilaOcupacion fila : reporte.getFilas()) {
            csv += fila.getCategoria().name()
                + "," + fila.getCuposTotales()
                + "," + fila.getCuposReservados()
                + "," + fila.getCuposLibres()
                + "\r\n";
        }

        return csv;
    }
    
}