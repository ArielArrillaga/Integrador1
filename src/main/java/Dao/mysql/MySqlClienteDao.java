package Dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DaoFactory.MySqlDaoFactory;
import IDao.ClienteDao;
import entities.Cliente;
import entities.ClienteFacturacion;
import entities.ClienteFacturacionResponse;
import entities.Response;

public class MySqlClienteDao implements ClienteDao{
	
	public Response createTable() throws SQLException {
		Response response = new Response();
		try {
			Connection con = MySqlDaoFactory.getConnection();
			con.prepareStatement("CREATE TABLE cliente (id INT PRIMARY KEY, nombre VARCHAR(50) NOT NULL, email VARCHAR(150) NOT NULL)").execute();
			con.commit();
			con.close();
			response.setMsj("Tabla creada con exito");
			return response;
		}
		catch (SQLException e) {
			response.setMsj("La tabla no se pudo crear, revise si ya la creo previamente");
			return response;
		}
	}
	
	public Response insertAllData(ArrayList<Cliente> clientes) throws SQLException {
		Connection con = MySqlDaoFactory.getConnection();
		Response response = new Response();
		
		PreparedStatement ps = con.prepareStatement("INSERT INTO cliente (id, nombre, email) VALUES (?,?,?)");

		try {	
			for (Cliente cliente : clientes) {
				ps.setInt(1,cliente.getId());
				ps.setString(2,cliente.getNombre());
				ps.setString(3,cliente.getEmail());
	            ps.addBatch();
	            ps.executeBatch();
	            con.commit();
			}
			response.setMsj("datos insertados con exito");
			return response;
        } catch (SQLException e) {
        	response.setMsj("algo salio mal, almenos un registro contiene un error o tiene un id repetido");
			return response;
        }
		
	}
	
	public Response deleteAll() throws SQLException {
		Connection con = MySqlDaoFactory.getConnection();
		Response response = new Response();
		
			try {
				con.prepareStatement("DELETE FROM cliente ").execute();
				con.commit();
				con.close();
				response.setMsj("datos borrados con exito");
				return response;
	        } catch (SQLException e) {
	        	response.setMsj("algo salio mal, no se pudo vaciar la tabla");
				return response;
	        }

	}
	
/*	"SELECT c.id, c.name, c.email, SUM(bp.quantity*p.value) as Facturacion " +
    "FROM Client c JOIN Bill b ON c.id = b.idClient JOIN BillProduct bp ON b.idBill = bp.idBill " +
    "JOIN Product p ON p.idProduct = bp.idProduct " +
    "GROUP BY c.id, c.name, c.email " +
    "ORDER BY Facturacion DESC;\n"*/
    
@Override
public ClienteFacturacionResponse listadoClientesFacturacion() throws SQLException {
	ClienteFacturacionResponse response = new ClienteFacturacionResponse();
	ArrayList<ClienteFacturacion> clientes = new ArrayList<ClienteFacturacion>();
	
	try {
		Connection con = MySqlDaoFactory.getConnection();
		  PreparedStatement ps = con.prepareStatement("SELECT c.id, c.nombre, c.email, SUM(fp.cantidad*p.valor) as Facturacion " +
				    "FROM cliente c JOIN facturas f ON c.id = f.idCliente JOIN facturasProductos fp ON f.idFactura = fp.idFactura " +
				    "JOIN productos p ON p.id = fp.idProducto " +
				    "GROUP BY c.id, c.nombre, c.email " +
				    "ORDER BY Facturacion DESC;");
		ResultSet rs = ps.executeQuery();	  	
		
	  while (rs.next()) {
		  ClienteFacturacion cliente = new ClienteFacturacion();
		  cliente.setId(rs.getInt(1));
		  cliente.setNombre(rs.getString(2));
		  cliente.setEmail(rs.getString(3));
		  cliente.setFacturacion(rs.getInt(4));
		  clientes.add(cliente);

       } 
		 
		con.commit();
		con.close();	
		response.setMensaje("Clientes recuperados con exito");
		response.setClientesFacturacion(clientes);
		return response;
	}
	catch (SQLException e) {
		response.setMensaje("algo salio mal, no se pudo obtener el listado de clientes");
		return response;
	}
}
}
