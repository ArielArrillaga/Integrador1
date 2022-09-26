package ScvReader;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entities.Cliente;

public class ReaderCsvClientes extends ReaderCsv{
	public ArrayList<Cliente> getClientes() throws FileNotFoundException, IOException{
		CSVParser parser = this.getParser("clientes");
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		for(CSVRecord row: parser) {
			Cliente cliente = new Cliente();
			cliente.setId(Integer.valueOf(row.get("idCliente")));
			cliente.setNombre(row.get("nombre"));
			cliente.setEmail(row.get("email"));
			clientes.add(cliente);
		}
		return clientes;
	}
}
