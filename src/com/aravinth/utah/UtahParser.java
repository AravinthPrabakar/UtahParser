package com.aravinth.utah;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.xml.bind.JAXBException;


import com.sonalake.utah.Parser;
import com.sonalake.utah.config.Config;
import com.sonalake.utah.config.ConfigLoader;

public class UtahParser {
	
	public static List<Map<String, String>> parse(String fileName, String configFileName /* xml file having config */) 
			throws JAXBException, FileNotFoundException, IOException{
		List<Map<String, String>> records = new ArrayList<Map<String, String>>();
		
		try {
			FileReader fr = new FileReader(configFileName);
			Config config = new ConfigLoader().loadConfig(fr);

			/* load a file and iterate through the records */
			try (Reader in = new FileReader(fileName)) {
			  Parser parser = Parser.parse(config, in);
			  while (true) {
			    Map<String, String> record = parser.next();
			    if (Objects.isNull(record)) {
			    	break;
			    } else {
			    	if(record.isEmpty()) {
			    		continue;
			    	}
			    	records.add(record);
			    }
			  }
			}
		} catch (Exception e) {
			System.out.println("Exception while parsing file:{}"+fileName);
			e.printStackTrace();
		}
		System.out.println(records.size());
		return records;
	}
	
	public static void main(String args[]) {
		String fileName = "D:\\Temp\\config.txt";
		String config = "D:\\Temp\\config.xml";
		try {
			System.out.println(parse(fileName,config));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
