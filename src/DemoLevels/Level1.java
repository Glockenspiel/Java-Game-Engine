package DemoLevels;

import Scripts.ExampleInput;
import Scripts.ExampleScript;
import Components.Sprite;
import framework.GameObject;
import framework.Level;
import framework.Vector;

public class Level1 extends Level{

	@Override
	public void init() {

		addObj(new GameObject("ground"));
		addObj(new GameObject("enemy"));	
		
		GameObject p = new GameObject("Player");
		p.addComponent(new ExampleScript()); //add component
		p.addComponent(new Sprite("box.png", 64,64)); //add component
		p.addComponent(new ExampleInput());//adding a script
		p.moveBy(new Vector(100,0)); //move player to a suitable starting position
		addObj(p);
	}

}
