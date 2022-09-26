
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import DaoFactory.DaoFactory;
import IDao.ClienteDao;
import IDao.FacturaDao;
import IDao.FacturaProductoDao;
import IDao.ProductoDao;
import ScvReader.ReaderCsvClientes;
import ScvReader.ReaderCsvFacturas;
import ScvReader.ReaderCsvFacturasProductos;
import ScvReader.ReaderCsvProductos;
import entities.Cliente;
import entities.Factura;
import entities.FacturaProducto;
import entities.Producto;

public class Main {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		
		
		//crear generador de daos
		DaoFactory mySqlDaoFactory = DaoFactory.getDao("MySql");
		
		
		// crear cada dao
		ClienteDao clienteDao = mySqlDaoFactory.getClienteDao();
		ProductoDao productoDao = mySqlDaoFactory.getProductoDao();
		FacturaDao facturaDao = mySqlDaoFactory.getFacturaDao();
		FacturaProductoDao facturaProductoDao = mySqlDaoFactory.getFacturaProductoDao();
		
		
		//crear tablas
		System.out.println("Creando tablas:");
		System.out.println("Clientes:");
		System.out.println(clienteDao.createTable());
		System.out.println("Productos:");
		System.out.println(productoDao.createTable());
		System.out.println("Facturas:");
		System.out.println(facturaDao.createTable());
		System.out.println("FacturasProductos:");
		System.out.println(facturaProductoDao.createTable());
		System.out.println("-------------------------------------------------------------------------");
		
		
		//instanciar lectores de csv
		ReaderCsvClientes readerCsvClientes = new ReaderCsvClientes();
		ReaderCsvProductos readerCsvProductos = new ReaderCsvProductos();
		ReaderCsvFacturas readerCsvFacturas = new ReaderCsvFacturas();
		ReaderCsvFacturasProductos readerCsvFacturasProductos = new ReaderCsvFacturasProductos();
		
		
		//obtener datos de csv
		System.out.println("Leyendo datos de csv:");
		ArrayList<Cliente> clientes = readerCsvClientes.getClientes();
		//System.out.println(clientes);
		ArrayList<Producto> productos = readerCsvProductos.getProductos();
		//System.out.println(productos);
		ArrayList<Factura> facturas = readerCsvFacturas.getFacturas();
		//System.out.println(facturas);
		ArrayList<FacturaProducto> facturasProductos = readerCsvFacturasProductos.getFacturasProductos();
		//System.out.println(facturasProductos);
		System.out.println("Fin lectura de csv:");
		System.out.println("-------------------------------------------------------------------------");
		
		
		//insertar datos en las tablas
		System.out.println("insertando datos en las tablas:");
		System.out.println("Clientes:");
		System.out.println(clienteDao.insertAllData(clientes));
		System.out.println("Productos:");
		System.out.println(productoDao.insertAllData(productos));
		System.out.println("Facturas:");
		System.out.println(facturaDao.insertAllData(facturas));
		System.out.println("FacturasProductos:");
		System.out.println(facturaProductoDao.insertAllData(facturasProductos));
		System.out.println("-------------------------------------------------------------------------");
		
		
		//producto de mayor recaudacion
		System.out.println("Producto con mayor recaudacion:");
		System.out.println(productoDao.mayorRecaudacion());
		System.out.println("-------------------------------------------------------------------------");
		
		
		//Listado de clientes segun facturacion
		System.out.println("Listado de clientes segun facturacion:");
		System.out.println(clienteDao.listadoClientesFacturacion());
	}
}
