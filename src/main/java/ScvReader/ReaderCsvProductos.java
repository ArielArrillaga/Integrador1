package ScvReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entities.Producto;

public class ReaderCsvProductos extends ReaderCsv{
	public ArrayList<Producto> getProductos() throws FileNotFoundException, IOException{
		CSVParser parser = this.getParser("productos");
		
		ArrayList<Producto> productos = new ArrayList<Producto>();
		for(CSVRecord row: parser) {
			Producto producto = new Producto();
			producto.setId(Integer.valueOf(row.get("idProducto")));
			producto.setNombre(row.get("nombre"));
			producto.setValor(Float.valueOf(row.get("valor")));
			productos.add(producto);
		}
		return productos;
	}
	
}
