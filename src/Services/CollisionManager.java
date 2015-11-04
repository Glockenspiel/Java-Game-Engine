package services;

import java.util.ArrayList;

import collision.CollisionBox;
import collision.CollisionResult;
import collision.CollisionShape;
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
		ArrayList<CollisionResult> results = new ArrayList<CollisionResult>();
		
		for(int i=0; i<objs.size()-1; i++){
			//collision components in each object
				objA = objs.get(i).getCollisionShapes();
			for(int j=i+1; j<objs.size(); j++){
				objB = objs.get(j).getCollisionShapes();
				//don't check any overlaps if one of the 2 GameObject has no collision shapes
				if(objA.size()>0 && objB.size()>0){
					//check if they overlap
					results.clear();
					results = hasCollisions(objA, objB);
					if(results.size()>0){
						//if the collision shapes which overlapped 
						//notify both objects of the overlaps to invoke onTrigger() to listeners
						if(collResShapeAHasTrigger(results))
							objs.get(i).collisionOverlap(objs.get(j).getTag(), objs.get(j).getID());
						
						if(collResShapeBHasTrigger(results))
							objs.get(j).collisionOverlap(objs.get(i).getTag(), objs.get(i).getID());
					}
				}
			}
		}
	}


	//returns an ArrayList of results from the ArrayList of CollisionShapes in object A and B
	private ArrayList<CollisionResult> hasCollisions(ArrayList<CollisionShape> objA,
			ArrayList<CollisionShape> objB) {
		
		
		ArrayList<CollisionResult> collisionResults  = new ArrayList<CollisionResult>();
		for(CollisionShape a : objA){
			for(CollisionShape b : objB){
				if(a instanceof CollisionBox && b instanceof CollisionBox){ //box and box
					CollisionBox boxA = (CollisionBox)a;
					CollisionBox boxB = (CollisionBox)b;
					boolean hasCollision = checkOverlap(boxA, boxB);
					if(hasCollision)
						collisionResults.add(new CollisionResult(boxA, boxB));
				}
				//todo: box and cirlce, circle and circle
			}
		}
		
		return collisionResults;
	}

	//checks if there is an overlap between 2 collision boxes
	private boolean checkOverlap(CollisionBox a, CollisionBox b) {
		//use min and max values to check overlap
		
		/* example of Case1:
		 * +-------+
		 * |       |
		 * |   B   |
		 * |     +-+-----+
		 * +-----+ +     |
		 *       |   A   |
		 * 	   	 |       |
		 *  	 +-------+	
		 * 
		 */
		
		//case 1
		if(a.minX()<=b.maxX() && a.minY()<b.maxY()  //B bottom right corner intersects A
				&& a.maxX()>b.minX() && a.maxY()>b.minY()) 
			return true;
		
		//case 2
		else if(a.maxX()>=b.minX() && a.minY()<b.maxY() //B bottom left intersects A
				&& a.minX()<b.maxX() && a.maxY()>b.minY())
			return true;
		
		//case 3
		else if(a.maxX()>=b.minX() && a.maxY()>b.minY() //B top left intersects A
				&& a.minX()<b.maxX() && a.minY()<b.maxY())
			return true;
		
		//case 4
		else if(a.minX()<=b.maxX() && a.maxY()>b.minY()  //B top right intersects A
				&& a.maxX()>b.minX() && a.minY()<b.maxY())
			return true;
		
		return false;
	}


	//resolve collisions
	@Override
	public void resolve(ArrayList<GameObject> objs) {
		if(objs.size()<2) return;
		
		for(int i=0; i<objs.size()-1; i++){
			for(int j=i+1; j<objs.size(); j++){
				
			}
		}
	}
	
	//returns true if any CollisionResult shape A returns true for isTrigger()
	private boolean collResShapeAHasTrigger(ArrayList<CollisionResult> results) {
		for(CollisionResult r : results)
			if(r.getShapeA().getIsTrigger())
				return true;
		return false;
	}
	
	//returns true if any CollisionResult shape B returns true for isTrigger()
	private boolean collResShapeBHasTrigger(ArrayList<CollisionResult> results) {
		for(CollisionResult r : results)
			if(r.getShapeB().getIsTrigger())
				return true;
		return false;
	}
}
