package busqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.ServicioIndividual;

public class CriterioBusquedaAvanzado {
	private ServicioIndividual vueloBrasil;
	private CriterioBusqueda criterioPorNombre;
	private CriterioBusqueda criterioPorNombreFalse;
	private CriterioBusqueda criterioPorCategoria;
	private CriterioBusqueda criterioPorCategoriaFalse;
	private ArrayList<CriterioBusqueda> listaCriteriosTrue;
	private ArrayList<CriterioBusqueda> listaCriteriosMix;
	private ArrayList<CriterioBusqueda> listaCriteriosFalse;
	
	@BeforeEach
	public void setUp() {
		vueloBrasil = new ServicioIndividual("A-0001", "Vuelo Buenos Aires - Brasil", "Ida y vuelta", "Aereolineas Argentinas", Categoria.ClaseEjecutiva, 1000000, 0);
		listaCriteriosTrue = new ArrayList<CriterioBusqueda>();
		listaCriteriosMix = new ArrayList<CriterioBusqueda>();
		listaCriteriosFalse = new ArrayList<CriterioBusqueda>();
		
		criterioPorNombre = new CriterioPorNombre("vuelo BueNOS AIRES - brasil");
		criterioPorNombreFalse = new CriterioPorNombre("vuelo BueNOS AIRES - uruguay");
		criterioPorCategoria = new CriterioPorCategoria(Categoria.ClaseEjecutiva);
		criterioPorCategoriaFalse = new CriterioPorCategoria(Categoria.PrimeraClase);
		
		listaCriteriosTrue.add(criterioPorNombre);
		listaCriteriosTrue.add(criterioPorCategoria);
		
		listaCriteriosMix.add(criterioPorNombre);
		listaCriteriosMix.add(criterioPorCategoriaFalse);
		
		listaCriteriosFalse.add(criterioPorNombreFalse);
		listaCriteriosFalse.add(criterioPorCategoriaFalse);
	}
	
	
	@Test
	public void criterioAndFunciona() {
		assertTrue(new CriterioAnd(listaCriteriosTrue).cumple(vueloBrasil));
		assertFalse(new CriterioAnd(listaCriteriosMix).cumple(vueloBrasil));
		assertFalse(new CriterioAnd(listaCriteriosFalse).cumple(vueloBrasil));
	}
	
	@Test
	public void criterioOrFunciona() {
		assertTrue(new CriterioOr(listaCriteriosTrue).cumple(vueloBrasil));
		assertTrue(new CriterioOr(listaCriteriosMix).cumple(vueloBrasil));
		assertFalse(new CriterioOr(listaCriteriosFalse).cumple(vueloBrasil));
	}
	
	@Test
	public void criterioNotFunciona() {
		assertFalse(new CriterioNot(criterioPorNombre).cumple(vueloBrasil));
		assertTrue(new CriterioNot(criterioPorNombreFalse).cumple(vueloBrasil));
		assertFalse(new CriterioNot(criterioPorCategoria).cumple(vueloBrasil));
		assertTrue(new CriterioNot(criterioPorCategoriaFalse).cumple(vueloBrasil));
	}
}
