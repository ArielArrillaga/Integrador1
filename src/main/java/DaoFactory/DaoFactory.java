package DaoFactory;

import java.lang.reflect.InvocationTargetException;

import IDao.ClienteDao;
import IDao.FacturaDao;
import IDao.FacturaProductoDao;
import IDao.ProductoDao;

public abstract class DaoFactory {
	public abstract ClienteDao getClienteDao();
	public abstract ProductoDao getProductoDao();
	public abstract FacturaDao getFacturaDao();
	public abstract FacturaProductoDao getFacturaProductoDao();
	
	
	public static DaoFactory getDao(String dataBase) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		switch (dataBase) {
		case "MySql":
			return new MySqlDaoFactory();
		case "postgre":
			//	dao = new postgreDaoFactory();
			return null;
		case "MongoDb":
			//	dao = new MongoDbDaoFactory();
			return null;
		default:
			return null;
		}
	}
}
