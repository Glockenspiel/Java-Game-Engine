package demo;

import levelloading.Cast;
import misc.Time;
import misc.Vector;
import framework.Game;
import framework.GameObject;

public class CoinDispenser extends GameObject {
	
	private long lastSpawn=0;
	private long delay = 1400;
	
	public CoinDispenser(){}

	public CoinDispenser(int x, int y){
		super("CoinDispenser");
		super.moveTo(new Vector(x,y));
		
	}
	
	//Dispenses coin at each interval at a random heights
	@Override
	public void update(){
		super.update();
		if(Time.getTime()-delay>lastSpawn){
			lastSpawn=Time.getTime();
			spawn();
		}
	}

	//spawn a coin
	private void spawn() {
		Coin coin = new Coin("coin");
		coin.moveBy(super.getPosition());
		Game.addGameObject(coin);
	}

	@Override
	public void construct(String[] args){
		super.construct(args);
		super.moveTo(new Vector(Cast.toFloat(args[1]), Cast.toFloat(args[2])));
	}
}
