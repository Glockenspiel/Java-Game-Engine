package levels;

import prefabs.Door;
import levelloading.ObjConstructor;
import misc.Vector;
import scripts.ToggleLevel;
import tiles.TileMap;
import demo.CoinDispenser;
import demo.Player;
import framework.Game;
import framework.GameObject;
import framework.Level;
import demo.EnemySpawner;

public class Level1 extends Level {
	
	//initialise level
	@Override
	public void init() {
		
		//spawner of enemys
		GameObject enemySpawner = new EnemySpawner("Enemy spawner", 1000);
		addObj(enemySpawner);
		
		//add player if it doesn't exist already
		if(Game.objExistsWithTag(Player.getDefaultTag())==false){
			GameObject player = ObjConstructor.loadGameObject(new String[]{"player"}, Player.class.getName());
			addObj(player);
		}
		
		//create the tile map object
		//GameObject map = new GameObject("map");
		String[] mapArgs = new String[]{"map"};
		GameObject map = ObjConstructor.loadGameObject(mapArgs, GameObject.class.getName());
		// map.add(new TileMap("level1.xml", 64,64));
		//	map.moveBy(new Vector(-250,-250));
		
		String[] tilemapArgs = new String[]{"level1.xml", "64", "64"};
		ObjConstructor.loadAddableObject(tilemapArgs, TileMap.class.getName(), map);
		addObj(map);
		//create and add a coin dispenser
		addObj(new CoinDispenser(500,0));
		
		//add a script to toggle to next level
		GameObject nextLevel = ObjConstructor.loadGameObject(new String[]{"more level"}, GameObject.class.getName());
		nextLevel.add(new ToggleLevel(new Level2()));
		addObj(nextLevel);
		
		GameObject Door = ObjConstructor.loadGameObject(new String[]{"door",Level3.class.getName()}, Door.class.getName());
		addObj(Door);
		//addObj(new Door(Level2.class.getName()));
	}
}
