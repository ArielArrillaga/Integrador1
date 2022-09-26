package IDao;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.FacturaProducto;
import entities.Response;

public interface FacturaProductoDao extends Dao {
	public Response insertAllData(ArrayList<FacturaProducto> facturasProductos) throws SQLException;

}
