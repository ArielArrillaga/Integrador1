package entities;

import java.util.ArrayList;

public class ClienteFacturacionResponse {
	private String mensaje;
	private ArrayList<ClienteFacturacion> clientesFacturacion;
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public ArrayList<ClienteFacturacion> getClientesFacturacion() {
		return clientesFacturacion;
	}
	public void setClientesFacturacion(ArrayList<ClienteFacturacion> clientesFacturacion) {
		this.clientesFacturacion = clientesFacturacion;
	}
	@Override
	public String toString() {
		return "ClienteFacturacionRespose [mensaje=" + mensaje + ", clientesFacturacion=" + clientesFacturacion + "]";
	}
	
}
