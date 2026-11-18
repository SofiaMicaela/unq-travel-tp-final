package reportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import catalogo.Categoria;
import catalogo.ItemCatalogo;
import catalogo.PaqueteTuristico;
import catalogo.ServicioIndividual;
import reserva.Reserva;
import reserva.estado.Cancelada;

public class ReporteOcupacion implements Reporte {
	private List<FilaOcupacion> filas = new ArrayList<>();

    public List<FilaOcupacion> getFilas() {
        return List.copyOf(this.filas);
    }

    @Override
    public String aceptar(VisitanteReporte visitante) {
        return visitante.visitar(this);
    }

    public void generar(List<ItemCatalogo> catalogo, List<Reserva> reservas, LocalDate desde, LocalDate hasta) {
        if (catalogo == null || reservas == null) {
            throw new IllegalArgumentException("El catálogo y las reservas no pueden ser nulos.");
        }
        if (desde.isAfter(hasta)) {
            throw new IllegalArgumentException("La fecha desde debe ser anterior o igual a hasta.");
        }

        Map<Categoria, Integer> totalesPorCategoria = new EnumMap<>(Categoria.class);
        Map<Categoria, Integer> reservadosPorCategoria = new EnumMap<>(Categoria.class);

        for (Categoria cat : Categoria.values()) {
            totalesPorCategoria.put(cat, 0);
            reservadosPorCategoria.put(cat, 0);
        }

        for (ServicioIndividual servicio : this.obtenerServicios(catalogo)) {
            Categoria cat = servicio.getCategoria();
            totalesPorCategoria.put(cat, totalesPorCategoria.get(cat) + servicio.getCupoTotal());
        }

        for (Reserva reserva : reservas) {
            if (this.estaEnPeriodo(reserva, desde, hasta) && !(reserva.getEstado() instanceof Cancelada)) {
                for (ServicioIndividual servicio : this.obtenerServicios(reserva.getServiciosAux())) {
                    Categoria cat = servicio.getCategoria();
                    reservadosPorCategoria.put(
                        cat,
                        reservadosPorCategoria.get(cat) + reserva.getCantidadPasajeros()
                    );
                }
            }
        }

        this.filas = new ArrayList<>();
        for (Categoria cat : Categoria.values()) {
            int totales = totalesPorCategoria.get(cat);
            int reservados = reservadosPorCategoria.get(cat);
            if (totales > 0 || reservados > 0) {
                this.filas.add(new FilaOcupacion(cat, totales, reservados));
            }
        }
    }

    private boolean estaEnPeriodo(Reserva reserva, LocalDate desde, LocalDate hasta) {
        LocalDate fecha = reserva.getFechaConfirmacion();
        return fecha != null
                && !fecha.isBefore(desde)
                && !fecha.isAfter(hasta);
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
}
