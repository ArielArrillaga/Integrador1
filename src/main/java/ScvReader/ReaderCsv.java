package ScvReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class ReaderCsv {
	
	public CSVParser getParser(String nameCsv) throws FileNotFoundException, IOException {
	    String filePath = new File("").getAbsolutePath();
	    
		@SuppressWarnings("deprecation")
		CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
				FileReader(filePath+"/src/main/java/Scv/"+nameCsv+".csv"));

		return parser;
	}

}
