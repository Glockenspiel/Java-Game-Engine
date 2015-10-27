package levels;

import scripts.ToggleLevel;
import tiles.TileMap;
import demo.CoinDispenser;
import demo.Player;
import framework.Game;
import framework.GameObject;
import framework.Level;
import framework.Vector;
import demo.EnemySpawner;

public class Level2 extends Level {

	private final String levelName="level2";
	
	@Override
	public void init() {
		GameObject enemySpawner = new EnemySpawner("Enemy spawner", 1000);
		addObj(enemySpawner);
		
		if(Game.objExistsWithTag(Player.getDefaultTag())==false){
			GameObject player = new Player();
			addObj(player);
		}
		
		
		GameObject map = new GameObject("map");
		map.add(new TileMap("level2.xml", 64,64));
		map.moveBy(new Vector(-250,-250));
		addObj(map);
		
		
		addObj(new CoinDispenser(500,0));
		
		GameObject nextLevel = new GameObject("move level");
		nextLevel.add(new ToggleLevel(new Level1()));
		addObj(nextLevel);
	}

	@Override
	public String getName() {
		return "level2";
	}

}
