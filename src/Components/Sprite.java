package components;

import java.awt.image.BufferedImage;

import display.Drawer;
import loaders.ImageLoader;
import framework.Component;
import framework.GameObject;
import framework.Vector;

public class Sprite extends Component {

	private static final String ImgFilePath = "Resources/Images/";
	private static final String TYPE = "Sprite";
	private BufferedImage image;
	private int offsetX, offsetY; //offsets from GameObject origin
	private int w,h;
	
	public Sprite(String filename, int width, int height){
		offsetX=0;
		offsetY=0;
		w=width;
		h=height;
		image = ImageLoader.load(filename);
	}
	
	public Sprite(String filename, int width, int height, int offsetX, int offsetY){
		this.offsetX=offsetX;
		this.offsetY=offsetY;
		image = ImageLoader.load(filename);
	}

	
	public int getHeight(){
		return w;
	}
	
	public int getWidth(){
		return h;
	}

	@Override
	public void update(GameObject obj) {}

	@Override
	public void draw(Drawer g, Vector objPos) {
		g.drawImage(image, 	objPos.intX()+offsetX, 
							objPos.intY()+offsetY, 
							w,h);
	}


	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public void interruptThreads() {}
}
