package entities;

public class Producto {
	private String nombre;
	private Float valor;
	private int id;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", valor=" + valor + ", id=" + id + "]";
	}
	
	
}
