package components;

import java.awt.image.BufferedImage;

import levelloading.Cast;
import levelloading.Constructable;
import loaders.ImageLoader;

public class SpriteSheet extends Constructable{
	
	private BufferedImage[][] images;
	private BufferedImage errorImage;
	private BufferedImage srcImage;
	private int frameWidth, frameHeight;
	private String filename;
	
	public SpriteSheet(){}
	
	//constructor
	public SpriteSheet(String filename, int frameWidth, int frameHeight){
		srcImage = ImageLoader.load(filename);
		this.frameHeight=frameHeight;
		this.frameWidth=frameWidth;
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

	public int getFrameWidth() {
		return frameWidth;
	}
	
	public int getFrameHeight() {
		return frameHeight;
	}

	@Override
	public void construct(String[] args) {
		filename= args[0];
		srcImage = ImageLoader.load(filename);
		frameHeight=Cast.toInt(args[1]);
		frameWidth=Cast.toInt(args[2]);
		loadFrames(frameWidth, frameHeight, args[0]);
	}

	@Override
	public String[] getSaveArgs() {
		String [] args = new String[]{
				filename,
				String.valueOf(frameHeight),
				String.valueOf(frameWidth)
		};
		return args;
	}
}
