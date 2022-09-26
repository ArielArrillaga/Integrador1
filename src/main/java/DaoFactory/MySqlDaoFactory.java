package DaoFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Dao.mysql.MySqlClienteDao;
import Dao.mysql.MySqlFacturaDao;
import Dao.mysql.MySqlFacturaProductoDao;
import Dao.mysql.MySqlProductoDao;
import IDao.ClienteDao;
import IDao.FacturaDao;
import IDao.FacturaProductoDao;
import IDao.ProductoDao;

public class MySqlDaoFactory extends DaoFactory{

	public static final String URI = "jdbc:mysql://localhost:13306/arquitecturas";
	
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	public MySqlDaoFactory() {
		try {
			registerDriver();			
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			 e.printStackTrace();
	            System.exit(1);
		}
	}
	
	public static void registerDriver() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {

		try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
      
	}
	
	public static Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(URI,"root","Password");
		con.setAutoCommit(false);
		return con;
	}
	
	
	@Override
	public ClienteDao getClienteDao() {
		return new MySqlClienteDao();
	}

	@Override
	public ProductoDao getProductoDao() {
		return new MySqlProductoDao();
	}
	
	@Override
	public FacturaDao getFacturaDao() {
		return new MySqlFacturaDao();
	}
	
	@Override
	public FacturaProductoDao getFacturaProductoDao() {
		return new MySqlFacturaProductoDao();
	}

}
