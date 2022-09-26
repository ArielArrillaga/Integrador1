package IDao;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.Factura;
import entities.Response;

public interface FacturaDao extends Dao {
	public Response insertAllData(ArrayList<Factura> facturas) throws SQLException;
}
