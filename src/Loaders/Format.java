package loaders;

import java.io.File;

public class Format {
	
	//return extension for a file name
	public static String getFormat(String filename){
		String [] elements = filename.split("\\.");
		
		//no extension return false
		if(elements.length==0) 
			return "";
		
 		String format = elements[elements.length-1]; //get last element
 		return format;
	}

	public static boolean match(File file, String format) {
		return getFormat(file.getName()).equalsIgnoreCase(format);
	}
}
