package catalogo;

public class ServicioIndividual implements ItemCatalogo{
	private String codigo;
    private String nombre;
    private String descripcion;
    private String categoria;
    private double precioBase;
    private double descuentoPromocional;
    
    public ServicioIndividual(String codigo, String nombre , double precioBase, double descuentoPromocional) {
    	this.nombre = nombre;
    	this.precioBase = precioBase;
    	this.descuentoPromocional = descuentoPromocional;
    }
    @Override
	public String getNombre() {
		return nombre;
	}
	@Override
	public String getDescripcion() {
		return descripcion;
	}
	@Override
	public double getPrecioBase() {
		return precioBase;
	}
	@Override
	public double getPrecioFinal() {
        return precioBase * (1 - (descuentoPromocional / 100));
    }
}
