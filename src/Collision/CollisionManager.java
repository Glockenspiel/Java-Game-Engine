package Collision;

import java.util.ArrayList;

import framework.Component;
import framework.Game;
import framework.GameObject;

public class CollisionManager implements CollisionManagerI {

	//detect collision and trigger events
	@Override
	public void detect(ArrayList<GameObject> objs) {
		if(objs.size()<2) return; //return if only 1 object
		
		ArrayList<Component> objA = new ArrayList<Component>();
		ArrayList<Component> objB = new ArrayList<Component>();
		
		
		for(int i=0; i<objs.size()-1; i++){
			//collision components in each object
			objs.get(i).getAllComponentsByType("CollisionBox", objA);
			for(int j=i+1; j<objs.size(); j++){
				objs.get(j).getAllComponentsByType("CollisionBox", objB);
				
				//both have collisionBoxes
				if(objA.size()>0 && objB.size()>0){
					//todo
					//check if they overlap
					//if overlap trigger event
				}
			}
		}
	}

	//resolve collisions
	@Override
	public void resolve(ArrayList<GameObject> objs) {
		
	}
	
	
}
