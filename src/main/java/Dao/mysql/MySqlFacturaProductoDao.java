package Dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import DaoFactory.MySqlDaoFactory;
import IDao.FacturaProductoDao;
import entities.FacturaProducto;
import entities.Response;

public class MySqlFacturaProductoDao implements FacturaProductoDao{
	
	public Response createTable() throws SQLException {
		Response response = new Response();
		try {
			Connection con = MySqlDaoFactory.getConnection();
			con.prepareStatement("CREATE TABLE facturasProductos (idFactura integer NOT NULL , idProducto integer NOT NULL," +
	                " cantidad integer NOT NULL, PRIMARY KEY (idFactura, idProducto), FOREIGN KEY (idFactura) REFERENCES facturas (idFactura)," +
	                " FOREIGN KEY (idProducto) REFERENCES productos (id))").execute();
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
	
	public Response insertAllData(ArrayList<FacturaProducto> facturasProductos) throws SQLException {
		Connection con = MySqlDaoFactory.getConnection();
		Response response = new Response();
		
		PreparedStatement ps = con.prepareStatement("INSERT INTO facturasProductos (idFactura, idProducto, cantidad) VALUES (?,?,?)");

		try {	
			for (FacturaProducto facturaProducto : facturasProductos) {
				ps.setInt(1,facturaProducto.getIdFactura());
				ps.setInt(2,facturaProducto.getIdProducto());
				ps.setInt(3,facturaProducto.getCantidad());
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
				con.prepareStatement("DELETE FROM facturasProductos ").execute();
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