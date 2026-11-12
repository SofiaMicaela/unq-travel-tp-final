package busqueda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.ItemCatalogo;
import catalogo.ServicioIndividual;

public class BusquedaCatalogoTest {
	private ServicioIndividual vueloBrasil;
	private CriterioBusqueda criterioPorNombre;
	private CriterioBusqueda criterioPorNombreFalse;
	private CriterioBusqueda criterioPorCategoria;
	private CriterioBusqueda criterioPorCategoriaFalse;
	private ArrayList<CriterioBusqueda> listaCriteriosTrue;
	private ArrayList<CriterioBusqueda> listaCriteriosMix;
	private ArrayList<CriterioBusqueda> listaCriteriosFalse;
	private Catalogo catalogo;
	private ArrayList<ItemCatalogo> listaItems;
	
	@BeforeEach
	public void setUp() {
		vueloBrasil = new ServicioIndividual("A-0001", "Vuelo Buenos Aires - Brasil", "Ida y vuelta", "Aereolineas Argentinas", Categoria.ClaseEjecutiva, 1000000, 0);
		listaItems = new ArrayList<ItemCatalogo>();
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
		
		listaItems.add(vueloBrasil);
		
		catalogo = new Catalogo(listaItems);
	}
	
	
	@Test
	public void sePuedenMezclarCriteriosDeBusqueda() {
		assertEquals(listaItems, catalogo.buscar(criterioPorNombre));
		assertEquals(Collections.emptyList(), catalogo.buscar(criterioPorNombreFalse));
	}
}
