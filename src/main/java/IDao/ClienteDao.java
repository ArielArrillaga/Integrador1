package IDao;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.Cliente;
import entities.ClienteFacturacionResponse;
import entities.Response;

public interface ClienteDao extends Dao{
	public Response insertAllData(ArrayList<Cliente> clientes) throws SQLException;
	public ClienteFacturacionResponse listadoClientesFacturacion() throws SQLException ;

}
