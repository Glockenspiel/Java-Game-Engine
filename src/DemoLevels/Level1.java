package DemoLevels;


import Scripts.PlayerInput;
import Tiles.AnimatedTile;
import Tiles.BasicTile;
import Tiles.TileMap;
import Components.Animation;
import Components.Sprite;
import Components.SpriteSheet;
import framework.GameObject;
import framework.Level;
import framework.Vector;

public class Level1 extends Level{

	@Override
	public void init() {

		
		GameObject map = new GameObject("map");
		SpriteSheet mapSheet = new SpriteSheet("testSheet2.png", 32,32);
		
		//create tile map component
		TileMap tilemap = new TileMap(mapSheet, "level1.xml", new Vector(32,32));
		//tilemap.addTile(new AnimatedTile(mapSheet, 100,100, 300,150,30,false));
		//tilemap.addTile(new BasicTile(mapSheet.getFrame(0, 0), 100,100,20,20,false));
		
		map.add(tilemap);
		addObj(map);
		
	
		
		GameObject p = new GameObject("Player");
		
		Vector playerStartPosition = new Vector(100,10);
		
		//dimensions to display images
		Vector playerSize = new Vector(64,64);
		Vector rocketSize = new Vector(32,32);
		
		//offset position of rocket flames
		Vector rocketOffset = new Vector(-rocketSize.intX(),-rocketSize.intY()/2+playerSize.intY()/2);
		
		Sprite triangle = new Sprite("triangle.png", playerSize.intX(), playerSize.intY());
		p.add(triangle); //add component
		
		
		//rocket flame animation
		SpriteSheet s = new SpriteSheet("rocket.png", 32,32);
		p.add(new Animation(s, rocketSize.intX(), rocketSize.intY(), rocketOffset,3));
		
		p.moveBy(playerStartPosition); //move player to a suitable starting position
		p.add(new PlayerInput(playerSize));//adding a script for input
		addObj(p);

	}

	@Override
	public String getName() {
		return "example level";
	}

}
