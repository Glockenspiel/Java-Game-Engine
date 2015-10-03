package Loaders;

public class Format {
	
	public static String getFormat(String filename){
		String [] elements = filename.split("\\.");
		
		//no extension return false
		if(elements.length==0) 
			return "";
		
 		String format = elements[elements.length-1]; //get last element
 		return format;
	}
}
