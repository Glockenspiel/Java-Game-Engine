package services;

import java.util.ArrayList;

import misc.MathG;
import collision.CollisionBox;
import collision.CollisionCircle;
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
				//box and box
				if(a instanceof CollisionBox && b instanceof CollisionBox){ //box and box
					CollisionBox boxA = (CollisionBox)a;
					CollisionBox boxB = (CollisionBox)b;
					boolean hasCollision = checkOverlap(boxA, boxB);
					if(hasCollision)
						collisionResults.add(new CollisionResult(boxA, boxB));
				}
				//circle and circle
				else if(a instanceof CollisionCircle && b instanceof CollisionCircle){
					CollisionCircle circleA = (CollisionCircle)a;
					CollisionCircle circleB = (CollisionCircle)b;
					boolean hasCollision = checkOverlap(circleA, circleB);
					if(hasCollision)
						collisionResults.add(new CollisionResult(circleA, circleB));
				}
				//box and circle
				else if((a instanceof CollisionBox && b instanceof CollisionCircle) ||
						(b instanceof CollisionBox && a instanceof CollisionCircle)){
					
					CollisionBox box = null;
					CollisionCircle circle = null;
					
					//if a is the collision box
					if(a instanceof CollisionBox){
						box = (CollisionBox)a;
						circle = (CollisionCircle)b;
					}
					//if a is the collision circle
					else{
						box = (CollisionBox)b;
						circle = (CollisionCircle)a;
					}
					
					boolean hasCollision = checkOverlap(box, circle);
					if(hasCollision)
						collisionResults.add(new CollisionResult(box, circle));
				}
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

	//returns true if there's an overlap between 2 circles 
	private boolean checkOverlap(CollisionCircle circleA,
			CollisionCircle circleB) {
		
		//get the distance from the centre of A to B
		double distance = MathG.distance(circleA.getX(), circleA.getY(), circleB.getX(), circleB.getY());
		
		//if the distance is less than the radius of A+B then there is an overlap, so return true
		if(distance < circleA.getRadius()+circleB.getRadius())
			return true;
		
		return false;
	}
	
	//returns true if these's an overlap between the box and the circle
	private boolean checkOverlap(CollisionBox box, CollisionCircle circle) {
		//source code for this function from :
		//http://stackoverflow.com/questions/401847/circle-rectangle-collision-detection-intersection
		
		float circleDistanceX, circleDistanceY;
		
		//calculate the distance from point a to b 
		//this collapses the computation into 1 quadrant
		circleDistanceX = Math.abs(circle.getX() - box.getCenterX());
	    circleDistanceY = Math.abs(circle.getY() - box.getCenterY());

	    //eliminates case where the circle is too far away from the box to overlap
	    if (	(circleDistanceX > (box.width()/2 + circle.getRadius())) || 
	    		((circleDistanceY > (box.height()/2 + circle.getRadius())))) { 
	    	return false; 
	    }
	    
	    //easy case where were the circle is close enough so there's a guaranteed overlap
	    if (circleDistanceX <= (box.width()/2)) { return true; } 
	    if (circleDistanceY <= (box.height()/2)) { return true; }
	    
	    /* hard case: the circle may intersect the corner of the 
	     * compute the distance from the centre of the circle and the corner,
	     * and then verify that the distance is not more than the radius of the circle
	     */
	    float cornerDistance_sq = MathG.sqr(circleDistanceX - box.width()/2) +
	                         MathG.sqr(circleDistanceY - box.height()/2);

	    return (cornerDistance_sq <= (MathG.sqr(circle.getRadius())));
	}
	
	//returns true if any CollisionResult shape A returns true for isTrigger()
	private boolean collResShapeAHasTrigger(ArrayList<CollisionResult> results) {
		for(CollisionResult r : results)
				return true;
		return false;
	}
	
	//returns true if any CollisionResult shape B returns true for isTrigger()
	private boolean collResShapeBHasTrigger(ArrayList<CollisionResult> results) {
		for(CollisionResult r : results)
				return true;
		return false;
	}
}
