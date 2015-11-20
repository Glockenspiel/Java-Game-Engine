package prefabs;

import prescripts.ChangeLevel;
import levelloading.Cast;
import collision.CollisionBox;
import framework.Game;
import framework.GameObject;

public class Door extends GameObject {

	public Door(){}
	
	public Door(String tag) {
		super(tag);
		
		//Game.print(tag);
		add(new ChangeLevel(tag));
		add(new CollisionBox(0,0,60,60));
	}
	
	@Override
	public void construct(String[] args){
		super.construct(args);
		
		add(new ChangeLevel(args[6]));
		/*
		add(new CollisionBox(
				Cast.toInt(args[7]), 
				Cast.toInt(args[8]), 
				Cast.toInt(args[9]), 
				Cast.toInt(args[10]) ));
				*/
	}
	
}
