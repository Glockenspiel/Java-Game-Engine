package loaders;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import framework.Game;

public class ImageLoader {
	
	private static String filePath = "Resources/Images/";
	private static String errorImage = "error.png";
	private static String[] validFormats = new String[]{"JPEG", "PNG", "BMP", "GIF"};
	
	//loads an image from file that are of format JPEG, PNG, BMP, GIF
	public static BufferedImage load(String filename){
		//check extension is valid
		validFormat(filename);
		
		BufferedImage image;
			try {
				image = javax.imageio.ImageIO.read(new File(filePath+filename));
			} catch (IOException ex) {
				Game.print("Failed to load image: " + ex.getMessage(), "error");
				
				//return  Error image instead
				//and don't let loading become an infinite loop trying to load errorImage
				if(filename!=errorImage)
					return load(errorImage);
				else return null;
			}
		
		//if loaded successfully
		return image;
		}

	//returns true if valid format
	private static boolean validFormat(String filename){
		String format =  Format.getFormat(filename);
		
		for(int i=0; i<validFormats.length; i++)
			if(validFormats[i] .equalsIgnoreCase(format))
				return true;
		
		return false;
	}
}
