package Components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import framework.Component;
import framework.GameObject;
import Display.GraphicDrawer;

public class ExampleComponent extends Component {

	private int num=1;
	private BufferedImage image;
	private static final String TYPE = "Example";
	
	public ExampleComponent(){
		loadImage();
	}
	
	
	//example of changing value in component
	public void setNum(int num){
		this.num=num;
	}
	
	
	@Override
	public void init(GameObject obj) { }
	
	@Override
	public void update(GameObject obj) {
		System.out.println("Updating Component:" + getType() + " in " + getGameObjectTag() + " num:" + num);
	}

	@Override
	public void draw(GraphicDrawer g, GameObject obj) {
		//example drawing
		//g.drawImage(image, 0, 0, 64, 64);
		
	}
	
	private void loadImage(){
		try {
			image = javax.imageio.ImageIO.read(new File("../box.png"));
		} catch (IOException ex) {
			System.out.println("here failed");
		}
	}


	@Override
	public String getType() {
		return TYPE;
	}

}
