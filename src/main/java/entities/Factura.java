package entities;

public class Factura {
	private int idCliente;
	private int idFactura;
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	@Override
	public String toString() {
		return "Facturas [idCliente=" + idCliente + ", idFactura=" + idFactura + "]";
	}
	
	
}
