package ScvReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entities.FacturaProducto;
import entities.Producto;

public class ReaderCsvFacturasProductos extends ReaderCsv{
	public ArrayList<FacturaProducto> getFacturasProductos() throws FileNotFoundException, IOException{
		CSVParser parser = this.getParser("facturas-productos");
		
		ArrayList<FacturaProducto> facturasProductos = new ArrayList<FacturaProducto>();
		for(CSVRecord row: parser) {
			FacturaProducto FacturaProducto = new FacturaProducto();
			FacturaProducto.setIdFactura(Integer.valueOf(row.get("idFactura")));
			FacturaProducto.setIdProducto(Integer.valueOf(row.get("idProducto")));
			FacturaProducto.setCantidad(Integer.valueOf(row.get("cantidad")));
			facturasProductos.add(FacturaProducto);
		}
		return facturasProductos;
	}
}
