package levels;

import components.SpriteSheet;
import demo.Player;
import scripts.ToggleLevel;
import tiles.TileMap;
import framework.Game;
import framework.GameObject;
import framework.Level;
import framework.Vector;

public class Level1 extends Level{

	private final String levelName = "example level1";
	
	@Override
	public void init() {

		
		GameObject map = new GameObject("map");
		SpriteSheet mapSheet = new SpriteSheet("testSheet2.png", 32,32);
		
		//create tile map component
		TileMap tilemap = new TileMap(mapSheet, "level1.xml", new Vector(32,32));
		map.add(tilemap);
		addObj(map);
		
		GameObject player = new Player("Player", new Vector(50, Game.getWindow().getHeight()/2));
		addObj(player);
		
		GameObject nextLevel = new GameObject("move level");
		nextLevel.add(new ToggleLevel(new Level2()));
		addObj(nextLevel);
	}

	@Override
	public String getName() {
		return levelName;
	}

}
