package levels;

import collision.CollisionBox;
import misc.Timer;
import misc.Vector;
import components.RigidBody;
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
		
		Timer timer =new Timer();
		timer.setMicrosecs();
		timer.start();
		for(int i=0; i<1000; i++){
			GameObject obj = new GameObject("stressTest");
			obj.moveBy(new Vector(i*5, 0));
			RigidBody body = new RigidBody(20);
			body.setGravity(0);
			obj.add(body);
			obj.add(new CollisionBox(0,0,4,4));
			addObj(obj);
		}
		timer.stopAndPrint("stress test creation time: ");
		
		GameObject nextLevel = new GameObject("move level");
		nextLevel.add(new ToggleLevel(new Level2()));
		addObj(nextLevel);
	}

	@Override
	public String getName() {
		return levelName;
	}

}
