package levels;


import components.Animation;
import components.Sprite;
import components.SpriteSheet;

import scripts.PlayerInput;
import scripts.cameraFollow;
import tiles.TileMap;
import framework.GameObject;
import framework.Level;
import framework.Vector;

public class Level1 extends Level{

	private String levelName = "example level1";
	
	@Override
	public void init() {

		
		GameObject map = new GameObject("map");
		SpriteSheet mapSheet = new SpriteSheet("testSheet2.png", 32,32);
		
		//create tile map component
		TileMap tilemap = new TileMap(mapSheet, "level1.xml", new Vector(32,32));
		map.add(tilemap);
		addObj(map);
		
	
		
		GameObject p = new GameObject("Player");
		//dimensions to display images
		Vector playerSize = new Vector(64,64);
		Vector rocketSize = new Vector(32,32);
		
		Vector playerStartPosition = new Vector(50,128-playerSize.intY()/2);
		
		
		
		//offset position of rocket flames
		Vector rocketOffset = new Vector(-rocketSize.intX(),-rocketSize.intY()/2+playerSize.intY()/2);
		
		Sprite triangle = new Sprite("triangle.png", playerSize.intX(), playerSize.intY());
		p.add(triangle); //add component
		
		
		//rocket flame animation
		SpriteSheet s = new SpriteSheet("rocket.png", 32,32);
		p.add(new Animation(s, rocketSize.intX(), rocketSize.intY(), rocketOffset,3));
		
		p.moveBy(playerStartPosition); //move player to a suitable starting position
		p.add(new PlayerInput(playerSize));//adding a script for input

		p.add(new cameraFollow(p.getID(), playerStartPosition));
		addObj(p);

	}

	@Override
	public String getName() {
		return levelName;
	}

}
