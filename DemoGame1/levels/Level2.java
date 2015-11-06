package levels;

import misc.Vector;
import scripts.ToggleLevel;
import tiles.TileMap;
import demo.CoinDispenser;
import demo.Player;
import framework.Game;
import framework.GameObject;
import framework.Level;
import demo.EnemySpawner;

public class Level2 extends Level {

	private final String levelName="level2";
	
	//initialise level
	@Override
	public void init() {
		//spawner of enemys
		GameObject enemySpawner = new EnemySpawner("Enemy spawner", 1000);
		addObj(enemySpawner);
		
		//add player if it doesn't exist already
		if(Game.objExistsWithTag(Player.getDefaultTag())==false){
			GameObject player = new Player();
			addObj(player);
		}
		
		//create the tile map object
		GameObject map = new GameObject("map");
		map.add(new TileMap("level2.xml", 64,64));
		map.moveBy(new Vector(-250,-250));
		addObj(map);
		
		//create and add a coin dispenser
		addObj(new CoinDispenser(500,0));
		
		//add a script to toggle to next level
		GameObject nextLevel = new GameObject("move level");
		nextLevel.add(new ToggleLevel(new Level1()));
		addObj(nextLevel);
	}

	@Override
	public String getName() {
		return "level2";
	}

}
