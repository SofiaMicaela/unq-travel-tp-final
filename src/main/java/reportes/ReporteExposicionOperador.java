package reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import catalogo.ItemCatalogo;
import catalogo.*;

public class ReporteExposicionOperador implements Reporte{
	private List<FilaExposicionOperador> filas = new ArrayList<>();

    public List<FilaExposicionOperador> getFilas() {
        return List.copyOf(this.filas);
    }

    @Override
    public String aceptar(VisitanteReporte visitante) {
        return visitante.visitar(this);
    }

    public void generar(List<ItemCatalogo> catalogo) {
        if (catalogo == null) {
            throw new IllegalArgumentException("El catálogo no puede ser nulo.");
        }

        List<ServicioIndividual> servicios = this.obtenerServicios(catalogo);
        int totalServicios = servicios.size();

        if (totalServicios == 0) {
            this.filas = new ArrayList<>();
            return;
        }

        Map<String, Integer> cantidadesPorOperador = new HashMap<>();
        for (ServicioIndividual servicio : servicios) {
            String operador = servicio.getOperador();
            cantidadesPorOperador.put(
                operador,
                cantidadesPorOperador.getOrDefault(operador, 0) + 1
            );
        }

        this.filas = this.crearFilas(cantidadesPorOperador, totalServicios);
    }

    private List<ServicioIndividual> obtenerServicios(List<ItemCatalogo> items) {
        List<ServicioIndividual> resultado = new ArrayList<>();
        for (ItemCatalogo item : items) {
            if (item instanceof ServicioIndividual) {
                resultado.add((ServicioIndividual) item);
            } else if (item instanceof PaqueteTuristico) {
                resultado.addAll(this.obtenerServicios(((PaqueteTuristico) item).getItems()));
            }
        }
        return resultado;
    }

    private List<FilaExposicionOperador> crearFilas(
            Map<String, Integer> cantidadesPorOperador,
            int totalServicios) {

        List<FilaExposicionOperador> resultado = new ArrayList<>();

        for (String operador : cantidadesPorOperador.keySet()) {
            int cantidad = cantidadesPorOperador.get(operador);
            double porcentaje = (cantidad * 100.0) / totalServicios;

            resultado.add(
                new FilaExposicionOperador(operador, cantidad, porcentaje)
            );
        }

        resultado.sort((fila1, fila2) ->
            Integer.compare(
                fila2.getCantidadServicios(),
                fila1.getCantidadServicios()
            )
        );

        return resultado;
    }
}

