package levels;

import components.Sprite;
import Collision.CollisionBox;
import scripts.ToggleLevel;
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
		//addObj(enemySpawner);
		
		if(Game.objExistsWithTag(Player.getDefaultTag())==false){
			GameObject player = new Player(new Vector(50, 128));
			addObj(player);
		}
		
		GameObject testCollision = new GameObject("test");
		testCollision.add(new CollisionBox(0,0,128,128,false));
		testCollision.add(new Sprite("box.png",128,128));
		testCollision.moveTo(new Vector(200,200));
		addObj(testCollision);
		
		GameObject nextLevel = new GameObject("move level");
		nextLevel.add(new ToggleLevel(new Level1()));
		addObj(nextLevel);
	}

	@Override
	public String getName() {
		return "level2";
	}

}
