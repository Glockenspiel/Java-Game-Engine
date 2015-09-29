package framework;

import java.awt.Graphics;

public class ExampleComponent extends Component {

	private int num=1;
	
	public ExampleComponent(){
		setType("Example");
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
	public void draw(Graphics g) {
		//example drawing
		g.drawLine(10, 10, 100, 100);
	}

}
