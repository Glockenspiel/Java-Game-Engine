package Scripts;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import framework.Component;
import framework.GameObject;
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
	public void init(GameObject obj) { }
	
	@Override
	public void update(GameObject obj) {
		//System.out.println("Updating Component:" + getType() + " in " + getGameObjectTag() + " num:" + num);
	}

	@Override
	public void draw(Drawer g, GameObject obj) {

	}



	@Override
	public String getType() {
		return TYPE;
	}

}
