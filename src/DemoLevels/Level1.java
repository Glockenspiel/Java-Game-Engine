package DemoLevels;

import Scripts.ExampleInput;
import Scripts.ExampleScript;
import Components.Animation;
import Components.Sprite;
import Components.SpriteSheet;
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
		SpriteSheet s = new SpriteSheet("testSheet.png", 32,32);
		p.addComponent(new Animation(s, 32,32,-32,-32, 30)); //add counting animation
		p.moveBy(new Vector(100,0)); //move player to a suitable starting position
		addObj(p);
	}

	@Override
	public String getName() {
		return "example level";
	}

}
