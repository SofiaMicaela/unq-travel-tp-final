package reportes;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import catalogo.ItemCatalogo;
import reserva.DetalleVenta;
import reserva.Reserva;

public class ReporteMasReservados implements Reporte {

    private List<FilaMasReservados> filas = new ArrayList<>();

    public List<FilaMasReservados> getFilas() {
        return List.copyOf(this.filas);
    }

    @Override
    public String aceptar(VisitanteReporte visitante) {
        return visitante.visitar(this);
    }
    public void generar(List<Reserva> reservas,
            LocalDate desde, LocalDate hasta) {

    		// verifica que las fechas no esten invertidas
    		if (desde.isAfter(hasta)) {
    			throw new IllegalArgumentException(
    					"La fecha desde debe ser anterior o igual a hasta."
    					);
    		}
    		//cuantas cantidades se vendieron item-cantidadVendida
    		Map<ItemCatalogo, Integer> cantidades = new HashMap<>();
    		// cuanto se cobro en todal item-montoTotalVendido
    		Map<ItemCatalogo, Double> importes = new HashMap<>();

    		for (Reserva reserva : reservas) {
    			// verifica el periodo
    			if (this.estaEnPeriodo(reserva, desde, hasta)) { 
    				//recorre cada detalle (item valorCobrado) de la reserva 
    				for (DetalleVenta detalle : reserva.getDetallesVenta()) {
    					ItemCatalogo item = detalle.getItem();
    					//actualizamos la cantidad vendida
    					cantidades.put(
    							item, cantidades.getOrDefault(item, 0) + 1 //busca cuantas ventas se lleva de ESE item
    					);
    					//sumamos el importe cobrado de ese item
    					importes.put(
    							item,
    							importes.getOrDefault(item, 0.0)
    							+ detalle.getImporteCobrado()
    					);
    				}
    			}
    		}

    			this.filas = this.crearFilas(cantidades, importes);
    }
    
    
	private boolean estaEnPeriodo(Reserva reserva,
						LocalDate desde, LocalDate hasta) {
		LocalDate fecha = reserva.getFechaConfirmacion();

		return fecha != null
				&& !fecha.isBefore(desde)
				&& !fecha.isAfter(hasta);
	}
	//devueve una lista de FilaMasReservados (item-catidadVendida-promedioprecio)
    private List<FilaMasReservados> crearFilas(
            Map<ItemCatalogo, Integer> cantidades,
            Map<ItemCatalogo, Double> importes) {

        List<FilaMasReservados> resultado = new ArrayList<>();
        //recorre los item registrados en el mapa 
        for (ItemCatalogo item : cantidades.keySet()) {
            int cantidad = cantidades.get(item);
            double promedio = importes.get(item) / cantidad; // divide lo acumulado por la cantidad

            resultado.add(
                new FilaMasReservados(item, cantidad, promedio)
            );
        }

        resultado.sort((fila1, fila2) ->
        	Integer.compare(
        			fila2.getCantidadVendida(),
        			fila1.getCantidadVendida()
        			)
    );

        return resultado;
    }
}