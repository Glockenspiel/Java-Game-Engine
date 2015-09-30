package Components;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import Display.GraphicDrawer;
import framework.Component;
import framework.GameObject;

public class Sprite extends Component {

	private static final String ImgFilePath = "Resources/Images/";
	private static final String TYPE = "Sprite";
	private BufferedImage image;
	private int w,h;
	
	public Sprite(String filename, int width, int height){
		w=width;
		h=height;
		
		loadImage(ImgFilePath + filename);
	}
	
	@Override
	public void init(GameObject obj) {}

	@Override
	public void update(GameObject obj) {}

	@Override
	public void draw(GraphicDrawer g, GameObject obj) {
		g.drawImage(image, obj.getPosition().intX(), obj.getPosition().intY(), w, h);
	}
	
	//loading images sould be done in sperate design pattern with 
	private void loadImage(String filename){
		try {
			image = javax.imageio.ImageIO.read(new File(filename));
		} catch (IOException ex) {
			System.out.println("Failed to load image: " + ex.getMessage());
		}
	}

	@Override
	public String getType() {
		return TYPE;
	}

}
