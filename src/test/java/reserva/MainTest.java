package reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.Cupo;
import catalogo.ItemCatalogo;
import catalogo.PaqueteTuristico;
import catalogo.ServicioIndividual;
import metodosDePago.ApiTarjeta;
import metodosDePago.Tarjeta;
import notificador.ObservadorReserva;
import reportes.ExportadorTextoPlano;
import reportes.ReporteMasReservados;
import reportes.ReporteOcupacion;
import reserva.estado.Finalizada;
import reserva.estado.SolicitudPendiente;
import traslados.TrasladoNoDisponibleExcepcion;
import traslados.TrasladoVIP;

class MainTest {

	@Test
    void simulacionReservaHastaReporte() throws TrasladoNoDisponibleExcepcion {
        ServicioIndividual vuelo = new ServicioIndividual(
                "VLO-01", "Vuelo a Bariloche", "Ida y vuelta", "Aerolíneas",
                Categoria.PrimeraClase, 10000.0, 0.0, new Cupo(10));

        ServicioIndividual salon = new ServicioIndividual(
                "SLN-01", "Acceso Salón VIP", "Sala de embarque", "Aerolíneas",
                Categoria.ClaseEjecutiva, 5000.0, 0.0, new Cupo(5));

        PaqueteTuristico packVip = new PaqueteTuristico("Pack VIP", "Vuelo + Salón", 10.0);
        packVip.agregarItem(vuelo);
        packVip.agregarItem(salon);

        Reserva reserva = new Reserva(2, mock(Direccion.class));
        ObservadorReserva observador = mock(ObservadorReserva.class);
        reserva.suscribir(observador);

        reserva.agregarServicio(packVip);
        reserva.elegirTraslado(new TrasladoVIP(10, 500.0f)); // $1.350 + $500 = $1.850
        assertEquals(15350.0f, reserva.costoTotal(), 0.01f);

        ApiTarjeta apiTarjeta = mock(ApiTarjeta.class);
        when(apiTarjeta.validarTarjeta(anyString(), anyString(), any())).thenReturn(true);
        when(apiTarjeta.preautorizarFondos(anyString(), anyFloat())).thenReturn(true);
        when(apiTarjeta.ejecutarTransaccion(anyString(), anyFloat())).thenReturn("TX-001");

        reserva.elegirMetodoPago(new Tarjeta(apiTarjeta, "4509123412341234", "123", LocalDate.of(2028, 12, 31)));
        reserva.confirmar(); 
        reserva.iniciarCheckIn();
        reserva.iniciarEjecucion();
        reserva.finalizar();

        assertInstanceOf(Finalizada.class, reserva.getEstado());
        verify(observador, times(4)).update(eq(reserva), any(), any());

        LocalDate hoy = LocalDate.now();
        List<Reserva> reservas = List.of(reserva);
        List<ItemCatalogo> catalogo = List.of(vuelo, salon);
        ExportadorTextoPlano exportador = new ExportadorTextoPlano();

        ReporteMasReservados masReservados = new ReporteMasReservados();
        masReservados.generar(reservas, hoy.minusDays(1), hoy.plusDays(1));
        String salidaMasReservados = masReservados.aceptar(exportador);

        assertTrue(salidaMasReservados.contains("Pack VIP | Cantidad vendida: 1 | Precio promedio: 13500.0"));

        ReporteOcupacion ocupacion = new ReporteOcupacion();
        ocupacion.generar(catalogo, reservas, hoy.minusDays(1), hoy.plusDays(1));
        String salidaOcupacion = ocupacion.aceptar(exportador);

        assertTrue(salidaOcupacion.contains("PrimeraClase | Totales: 10 | Reservados: 2 | Libres: 8"));
        assertTrue(salidaOcupacion.contains("ClaseEjecutiva | Totales: 5 | Reservados: 2 | Libres: 3"));
    }
}
