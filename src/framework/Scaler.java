package framework;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Scaler {
	
	//scales a buffered image to the dimensions given
	public static BufferedImage scaleTo(int width, int height, BufferedImage image){
		int originalWidth = image.getWidth();
		int originalHeight = image.getHeight();
		float scaleX = width/originalWidth;
		float scaleY = height/originalHeight;
		
		BufferedImage after = new BufferedImage(originalWidth, originalHeight, BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(scaleX, scaleY);
		AffineTransformOp scaleOp = 
		   new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		after = scaleOp.filter(image, after);
		return after;
	}
}
