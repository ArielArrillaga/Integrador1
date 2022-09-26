package entities;

public class ProductoResponse {
	private Producto producto;
	private String mensaje;
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	@Override
	public String toString() {
		return "Mensaje: "+ mensaje +"\n"+
				"Producto [nombre=" + producto.getNombre() + ", valor=" + producto.getValor() + ", id=" + producto.getId()+ "]";
	}
	
	
}
