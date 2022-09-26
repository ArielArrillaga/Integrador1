package IDao;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.Producto;
import entities.ProductoResponse;
import entities.Response;

public interface ProductoDao extends Dao{

	public Response insertAllData(ArrayList<Producto> productos) throws SQLException;
	public ProductoResponse mayorRecaudacion()throws SQLException;
	
}
