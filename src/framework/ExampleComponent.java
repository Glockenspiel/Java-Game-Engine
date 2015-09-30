package framework;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Display.GraphicDrawer;

public class ExampleComponent extends Component {

	private int num=1;
	private BufferedImage image;
	
	public ExampleComponent(){
		setType("Example");
		loadImage();
	}
	
	
	//example of changing value in component
	public void setNum(int num){
		this.num=num;
	}
	
	
	@Override
	public void init() { }
	
	@Override
	public void update() {
		System.out.println("Updating Component:" + getType() + " in " + getGameObjectTag() + " num:" + num);
	}

	@Override
	public void draw(GraphicDrawer g) {
		//example drawing
		//g.drawImage(image, 0,0,64,64, null);
		g.drawImage(image, 0, 0, 64, 64);
		
	}
	
	private void loadImage(){
		try {
			image = javax.imageio.ImageIO.read(new File("../box.png"));
		} catch (IOException ex) {
			System.out.println("here failed");
		}
	}

}
