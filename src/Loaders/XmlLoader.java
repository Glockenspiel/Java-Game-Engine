package loaders;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import framework.Game;


public class XmlLoader {
	
	//loads an .xml file into a NodeList and displays error message and returns null if failed to load
	public static NodeList load(String filename, String rootName){
		File xmlFile = new File(filename);
		
		if(!xmlFile.exists()){
			Game.print("File was not found: " + filename+ " \nMake sure you have to correct path", "error");
			return null;
		}
		
		if(Format.getFormat(filename).equalsIgnoreCase("xml")==false){
			Game.print("Extension is not .xml for file: " + filename, "error");
			return null;
		}
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document doc = null;
		try {
			doc = dBuilder.parse(xmlFile);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		
		NodeList nList = doc.getElementsByTagName(rootName);
		return nList;
	}
	
	public static void writeStringtoXML(String content, String filename) throws IOException {
	        java.io.FileWriter fw = new java.io.FileWriter(filename);
	        fw.write(content);
	        fw.close();
	    }
	
}
