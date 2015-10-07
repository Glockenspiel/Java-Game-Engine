package demo;

import framework.Game;
import framework.GameObject;
import framework.MathG;
import framework.Vector;

public class EnemySpawner extends GameObject {

	private long lastSpawn=0;
	private long spawnTime;
	
	//spawns an enemy waves every spawnTime
	public EnemySpawner(String tag, long spawnTime) {
		super(tag);
		this.spawnTime=spawnTime;
	}
	
	//checks if spawn this update
	@Override
	public void update(){
		long currentTime = System.currentTimeMillis();
		if(currentTime-lastSpawn>spawnTime){
			spawn();
			lastSpawn=currentTime;
		}
	}
	
	//spawn an enemy
	private void spawn(){
		//move to a position
		GameObject o = new Enemy("Enemy1");
		o.moveTo(new Vector(500, MathG.randomNumber(0, 250)));
		o.delete(8000);
		Game.addGameObject(o);
	}

}
