package Dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DaoFactory.MySqlDaoFactory;
import IDao.ProductoDao;
import entities.Producto;
import entities.ProductoResponse;
import entities.Response;

public class MySqlProductoDao implements ProductoDao{
	
	public Response createTable() throws SQLException {
		Response response = new Response();
		try {
			Connection con = MySqlDaoFactory.getConnection();
			con.prepareStatement("CREATE TABLE productos (id INT PRIMARY KEY, nombre VARCHAR(50) NOT NULL, valor FLOAT NOT NULL)").execute();
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
	
	public Response insertAllData(ArrayList<Producto> productos) throws SQLException {
		Connection con = MySqlDaoFactory.getConnection();
		Response response = new Response();
		
		PreparedStatement ps = con.prepareStatement("INSERT INTO productos (id, nombre, valor) VALUES (?,?,?)");

		try {	
			for (Producto producto : productos) {
				ps.setInt(1,producto.getId());
				ps.setString(2,producto.getNombre());
				ps.setFloat(3,producto.getValor());
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
				con.prepareStatement("DELETE FROM productos ").execute();
				con.commit();
				con.close();
				response.setMsj("datos borrados con exito");
				return response;
	        } catch (SQLException e) {
	        	response.setMsj("algo salio mal, no se pudo vaciar la tabla");
				return response;
	        }

	}

	@Override
	public ProductoResponse mayorRecaudacion() throws SQLException {
		Producto producto = new Producto();
		ProductoResponse response = new ProductoResponse();
		
		try {
			Connection con = MySqlDaoFactory.getConnection();
			  ResultSet rs = con.prepareStatement("SELECT p.id, p.nombre, p.valor, SUM(fp.cantidad*p.valor) as recaudacion "
					+ "FROM facturasProductos fp join productos p on fp.idProducto = p.id "
					+ "group by p.id, p.nombre, p.valor "
					+ "order by recaudacion desc limit 1")
					.executeQuery();
			
		  while (rs.next()) {
			  producto.setId(rs.getInt(1));
			  producto.setNombre(rs.getString(2));
			  producto.setValor(rs.getFloat(3));
           } 
			 
			con.commit();
			con.close();	
			response.setMensaje("Producto recuperado con exito");
			response.setProducto(producto);
			return response;
		}
		catch (SQLException e) {
			response.setMensaje("algo salio mal, no se pudo obtener el producto");
			return response;
		}
	}
}
