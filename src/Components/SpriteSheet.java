package Components;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import framework.Scaler;
import framework.Vector;
import Loaders.ImageLoader;

public class SpriteSheet {
	
	private BufferedImage[][] images;
	private BufferedImage errorImage;
	BufferedImage srcImage;
	
	public SpriteSheet(String filename, int frameWidth, int frameHeight){
		
		//todo: frame width & height dont scale the image. tip: use graphics2d
		srcImage = ImageLoader.load(filename);
		loadFrames(frameWidth, frameHeight, filename);
	}
	
	//scale all images
	public void scaleImages(int displayWidth, int displayHeight) {
		if(images[0][0]==null) return;
		
		for(int y=0; y<images.length; y++){
			for(int x=0; x<images[y].length; x++){
				images[y][x] = Scaler.scaleTo(displayWidth, displayHeight, images[y][x]);
			}
		}
	}

	//loads image file and splits into a grid of images (frames) stored in a 2D array BufferedImages
	private void loadFrames(int width, int height, String filename){
		
		// split srcImag into a grid depending on frame size
		int frameCols = srcImage.getWidth()/width;
		int frameRows = srcImage.getHeight()/height; 
		images= new BufferedImage[frameCols][frameRows];
		
		//load error image instead of returning null
		errorImage=ImageLoader.load("error.png");

		for(int y=0; y<frameRows; y++)
			for(int x=0; x<frameCols; x++){
				images[x][y]= 
						srcImage.getSubimage(x*width, y*height, 
							width, height);
			}
	}
	
	public int displayHeight(){
		if(images[0][0]==null) 
			return 0;
		return images[0][0].getHeight();
	}
	
	public int displayWidth(){
		if(images[0][0]==null) 
			return 0;
		return images[0][0].getWidth();
	}

	
	public int gridHeight(){
		if(images==null) 
			return -1;
		
		return images[0].length;
	}
	
	public int gridWidth(){
		if(images==null) 
			return -1;
		
		return images.length;
	}
	
	public BufferedImage getFrame(int x, int y){
		if(x<gridWidth() && x>=0 && y<gridHeight() && y>=0)
			return images[x][y];
		
		return errorImage;
	}
}
