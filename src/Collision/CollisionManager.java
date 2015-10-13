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
		
		ArrayList<CollisionShape> objA;// = new ArrayList<CollisionShape>();
		ArrayList<CollisionShape> objB;// = ArrayList<CollisionShape>();
		
		for(int i=0; i<objs.size()-1; i++){
			//collision components in each object
				objA = objs.get(i).getCollisionShapes();
			for(int j=i+1; j<objs.size(); j++){
				objB = objs.get(j).getCollisionShapes();
				if(objA.size()>0 && objB.size()>0){
					//todo
					//check if they overlap
					if(hasCollisions(objA, objB));
						//trigger event and pass tag of triggered collision;
					//if overlap trigger event
				}
			}
		}
	}


	private boolean hasCollisions(ArrayList<CollisionShape> objA,
			ArrayList<CollisionShape> objB) {
		
		for(CollisionShape a : objA){
			for(CollisionShape b : objB){
				if(a instanceof CollisionBox && b instanceof CollisionBox){ //box and box
					CollisionBox boxA = (CollisionBox)a;
					CollisionBox boxB = (CollisionBox)b;
					boolean hasCollision = checkOverlap(boxA, boxB);
					return hasCollision;
				}
				//todo: box and cirlce, circle and circle
			}
		}
		return false;
	}

	private boolean checkOverlap(CollisionBox boxA, CollisionBox boxB) {
		//use min and max values to check overlap
		
		return false;
	}


	//resolve collisions
	@Override
	public void resolve(ArrayList<GameObject> objs) {
		
	}
	
	
}
