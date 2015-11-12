package components;

import java.awt.image.BufferedImage;

import display.Drawer;
import loaders.ImageLoader;
import misc.Vector;
import framework.Component;

public class Sprite extends Component {

	private BufferedImage image;
	private int offsetX, offsetY; //offsets from GameObject origin
	private int w,h;
	
	//simple constructor
	public Sprite(String filename, int width, int height){
		offsetX=0;
		offsetY=0;
		w=width;
		h=height;
		image = ImageLoader.load(filename);
	}
	
	//constructor with more values
	public Sprite(String filename, int width, int height, int offsetX, int offsetY){
		w=width;
		h=height;
		this.offsetX=offsetX;
		this.offsetY=offsetY;
		image = ImageLoader.load(filename);
	}

	//returns the height
	public int getHeight(){
		return w;
	}
	
	//returns the width
	public int getWidth(){
		return h;
	}

	//draw this sprite
	@Override
	public void draw(Drawer g, Vector objPos) {
		g.drawImage(image, 	objPos.intX()+offsetX, 
							objPos.intY()+offsetY, 
							w,h);
	}
}
