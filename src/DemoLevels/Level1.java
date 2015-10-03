package DemoLevels;

import Scripts.ExampleInput;
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
		TileMap tilemap = new TileMap(mapSheet, "example.csv");
		tilemap.addTile(new AnimatedTile(mapSheet, 100,100, 300,150,30,false));
		tilemap.addTile(new BasicTile(mapSheet.getFrame(0, 0), 100,100,20,20,false));
		
		map.add(tilemap);
		addObj(map);
		
	
		
		GameObject p = new GameObject("Player");
		p.add(new Sprite("box.png", 64,64)); //add component
		p.add(new ExampleInput());//adding a script
		SpriteSheet s = new SpriteSheet("testSheet.png", 32,32);
		p.add(new Animation(s, 32,32,-32,-32, 10)); //add counting animation
		p.moveBy(new Vector(100,0)); //move player to a suitable starting position
		addObj(p);

	}

	@Override
	public String getName() {
		return "example level";
	}

}
