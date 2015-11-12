package components;

import java.awt.image.BufferedImage;

import loaders.ImageLoader;

public class SpriteSheet {
	
	private BufferedImage[][] images;
	private BufferedImage errorImage;
	private BufferedImage srcImage;
	
	//constructor
	public SpriteSheet(String filename, int frameWidth, int frameHeight){
		srcImage = ImageLoader.load(filename);
		loadFrames(frameWidth, frameHeight, filename);
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

	//number of frames on the y-axis in the image
	public int gridHeight(){
		if(images==null) 
			return -1;
		
		return images[0].length;
	}
	
	//number of frames on the x-axis in the image
	public int gridWidth(){
		if(images==null) 
			return -1;
		
		return images.length;
	}
	
	//returns frame at coordinate x,y
	public BufferedImage getFrame(int x, int y){
		if(x<gridWidth() && x>=0 && y<gridHeight() && y>=0)
			return images[x][y];
		
		return errorImage;
	}
}
