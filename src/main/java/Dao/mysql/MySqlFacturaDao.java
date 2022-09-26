package Dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import DaoFactory.MySqlDaoFactory;
import IDao.FacturaDao;
import entities.Factura;
import entities.Response;

public class MySqlFacturaDao implements FacturaDao{
	
	public Response createTable() throws SQLException {
		Response response = new Response();
		try {
			Connection con = MySqlDaoFactory.getConnection();
			con.prepareStatement("CREATE TABLE facturas (idFactura INT PRIMARY KEY, idCliente INT NOT NULL, FOREIGN KEY (idCliente) REFERENCES cliente (id))").execute();
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
	
	public Response insertAllData(ArrayList<Factura> facturas) throws SQLException {
		Connection con = MySqlDaoFactory.getConnection();
		Response response = new Response();
		
		PreparedStatement ps = con.prepareStatement("INSERT INTO facturas (idFactura, idCliente) VALUES (?,?)");

		try {	
			for (Factura factura : facturas) {
				ps.setInt(1,factura.getIdFactura());
				ps.setInt(2,factura.getIdCliente());
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
				con.prepareStatement("DELETE FROM facturas ").execute();
				con.commit();
				con.close();
				response.setMsj("datos borrados con exito");
				return response;
	        } catch (SQLException e) {
	        	response.setMsj("algo salio mal, no se pudo vaciar la tabla");
				return response;
	        }

	}
}
