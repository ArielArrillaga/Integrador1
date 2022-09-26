package entities;

public class ClienteFacturacion extends Cliente{

	private int facturacion;
	
	public int getFacturacion() {
		return facturacion;
	}
	public void setFacturacion(int facturacion) {
		this.facturacion = facturacion;
	}
	@Override
	public String toString() {
		return "ClienteFacturacion [nombre=" + this.getNombre() + ", email=" + this.getEmail() + ", id=" + this.getId() + ", facturacion="
				+ facturacion + "]\n";
	}
	
	
}
