package Scripts;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import framework.Component;
import framework.Game;
import framework.GameObject;
import framework.Vector;
import Display.Drawer;

public class ExampleScript extends Component {

	private int num=1;
	private BufferedImage image;
	private static final String TYPE = "Example";
	
	public ExampleScript(){
	}
	
	
	//example of changing value in component
	public void setNum(int num){
		this.num=num;
	}
	
	
	@Override
	public void update(GameObject obj) {
	}

	@Override
	public void draw(Drawer g, Vector objsPos) {}



	@Override
	public String getType() {
		return TYPE;
	}

}
