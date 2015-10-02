package Components;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import Display.Drawer;
import Loaders.ImageLoader;
import framework.Component;
import framework.GameObject;
import framework.Vector;

public class Sprite extends Component {

	private static final String ImgFilePath = "Resources/Images/";
	private static final String TYPE = "Sprite";
	private BufferedImage image;
	private int w,h; //width and height
	private int offsetX, offsetY; //offsets from GameObject origin
	
	public Sprite(String filename, int width, int height){
		w=width;
		h=height;
		offsetX=0;
		offsetY=0;
		image = ImageLoader.load(filename);
	}
	
	public Sprite(String filename, int width, int height, int offsetX, int offsetY){
		w=width;
		h=height;
		this.offsetX=offsetX;
		this.offsetY=offsetY;
		image = ImageLoader.load(filename);
	}

	@Override
	public void update(GameObject obj) {}

	@Override
	public void draw(Drawer g, Vector objPos) {
		g.drawImage(image, 	objPos.intX()+offsetX, 
							objPos.intY()+offsetY, 
							w, h);
	}


	@Override
	public String getType() {
		return TYPE;
	}

}
