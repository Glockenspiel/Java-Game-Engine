package levels;

import misc.Vector;
import components.SpriteSheet;
import demo.Player;
import scripts.ToggleLevel;
import tiles.TileMap;
import framework.Game;
import framework.GameObject;
import framework.Level;

public class Level1 extends Level{

	private final String levelName = "example level1";
	
	//initialise level
	@Override
	public void init() {

		
		GameObject map = new GameObject("map");
		
		//create tile map component
		TileMap tilemap = new TileMap("level1.xml", 32,32);
		map.add(tilemap);
		addObj(map);
		
		
		if(Game.objExistsWithTag(Player.getDefaultTag())==false){
			GameObject player = new Player();
			addObj(player);
		}
		
		GameObject nextLevel = new GameObject("move level");
		nextLevel.add(new ToggleLevel(new Level2()));
		addObj(nextLevel);
	}

	@Override
	public String getName() {
		return levelName;
	}

}
