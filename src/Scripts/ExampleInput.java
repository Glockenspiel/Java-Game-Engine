package Scripts;

import Display.Drawer;
import framework.Component;
import framework.Game;
import framework.GameObject;
import framework.Vector;

public class ExampleInput extends Component {

	private float XSpeed=4, YSpeed=4;
	
	@Override
	public String getType() {
		return "ExampleInput";
	}


	@Override
	public void update(GameObject obj) {
		//inverting direction
		/*
		 *  //move left and right example
		if(direction<0 && obj.getPosition().getX()<10) //moving right and x less than 10
			direction *= -1;
		else if(direction>0 && obj.getPosition().getX()>200) //moving left and x > 200
			direction *=-1;
		
		obj.moveBy(new Vector(XSpeed*direction, YSpeed));
*/
		
		//move player with input
		Vector direction = Game.getInput().getDirectionInput();
		Vector movement = new Vector(direction.getX()*XSpeed, direction.getY()*YSpeed);
		obj.moveBy(movement);
	}

	@Override
	public void draw(Drawer g, Vector objPos) {}

}
