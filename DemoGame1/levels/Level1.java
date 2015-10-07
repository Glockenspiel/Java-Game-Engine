package levels;


import components.Animation;
import components.Sprite;
import components.SpriteSheet;
import demo.Player;
import scripts.PlayerInput;
import scripts.cameraFollow;
import tiles.TileMap;
import framework.Game;
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
		
		GameObject player = new Player("Player", new Vector(50, Game.getWindow().getHeight()/2));
		addObj(player);
	}

	@Override
	public String getName() {
		return levelName;
	}

}
