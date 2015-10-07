package levels;

import scripts.ToggleLevel;
import demo.Player;
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
		
		GameObject player = new Player("Player", new Vector(50, 128));
		addObj(player);
		
		GameObject nextLevel = new GameObject("move level");
		nextLevel.add(new ToggleLevel(new Level1()));
		addObj(nextLevel);
	}

	@Override
	public String getName() {
		return "level2";
	}

}
