package ScvReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entities.Factura;


public class ReaderCsvFacturas extends ReaderCsv{
	public ArrayList<Factura> getFacturas() throws FileNotFoundException, IOException{
		CSVParser parser = this.getParser("facturas");
		
		ArrayList<Factura> facturas = new ArrayList<Factura>();
		for(CSVRecord row: parser) {
			Factura factura = new Factura();
			factura.setIdFactura(Integer.valueOf(row.get("idFactura")));
			factura.setIdCliente(Integer.valueOf(row.get("idCliente")));
			facturas.add(factura);
		}
		return facturas;
	}
}
