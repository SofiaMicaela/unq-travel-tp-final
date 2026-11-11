package busqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.ServicioIndividual;

public class CriterioBusquedaSimpleTest {
	private ServicioIndividual vueloBrasil;
	
	@BeforeEach
	public void setUp() {
		vueloBrasil = new ServicioIndividual("A-0001", "Vuelo Buenos Aires - Brasil", "Ida y vuelta", "Aereolineas Argentinas", Categoria.ClaseEjecutiva, 1000000, 0);
	}
	
	
	@Test
	public void busquedaPorNombreEsCaseInsensitive() {
		assertTrue(new CriterioPorNombre("vuelo BueNOS AIRES - brasil").cumple(vueloBrasil));
	}
	
	@Test
	public void siNoTienenMismoNombreFalla() {
		assertFalse(new CriterioPorNombre("vuelo BueNOS AIRES - uruguay").cumple(vueloBrasil));
	}
	
	@Test
	public void sePuedeBuscarPorPrecioMaximo() {
		assertTrue(new CriterioPorPrecioMaximo(1000001).cumple(vueloBrasil));
		assertTrue(new CriterioPorPrecioMaximo(1000000).cumple(vueloBrasil));
		assertFalse(new CriterioPorPrecioMaximo(999999).cumple(vueloBrasil));
	}
	
	@Test
	public void sePuedeBuscarPorCategoria() {
		assertTrue(new CriterioPorCategoria(Categoria.ClaseEjecutiva).cumple(vueloBrasil));
		assertFalse(new CriterioPorCategoria(Categoria.PrimeraClase).cumple(vueloBrasil));
	}
	
}
