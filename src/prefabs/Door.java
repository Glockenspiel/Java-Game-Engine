package prefabs;

import levelloading.Cast;
import scripts.ChangeLevel;
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
		add(new ChangeLevel(args[1]));
		
		if(args.length==2)
			add(new CollisionBox(0,0,60,60));
		
		else{
			add(new CollisionBox(Cast.toInt(args[2]), Cast.toInt(args[3]), 
					Cast.toInt(args[4]), Cast.toInt(args[5])));
		}
		
		
	}
}
