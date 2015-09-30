package Scripts;

import Display.Drawer;
import framework.Component;
import framework.Game;
import framework.GameObject;
import framework.Vector;

public class ExampleInput extends Component {

	@Override
	public String getType() {
		return "ExampleInput";
	}

	@Override
	public void init(GameObject obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameObject obj) {
		//print out true of false
		System.out.println("Button W down: " + Game.getInput().isKeyDown('W'));
		
		//move GameObject if button is pressed
		if(Game.getInput().isKeyDown('S')){
			obj.moveBy(new Vector(10,10));
		}
	}

	@Override
	public void draw(Drawer g, GameObject obj) {
		// TODO Auto-generated method stub

	}

}
