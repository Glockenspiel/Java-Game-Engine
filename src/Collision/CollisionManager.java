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
				//don't check any overlaps if one of the 2 GameObject has no collision shapes
				if(objA.size()>0 && objB.size()>0){
					//check if they overlap
					if(hasCollisions(objA, objB)){
						//notify both objects of the overlaps
						
						//todo: check if onCollision or onTrigger, and pass boolean to collisionOverlap()
						objs.get(i).collisionOverlap(objs.get(j).getTag());
						objs.get(j).collisionOverlap(objs.get(i).getTag());
					}
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

	private boolean checkOverlap(CollisionBox a, CollisionBox b) {
		//use min and max values to check overlap
		
		/* example of Case1:
		 * +------+
		 * |      |
		 * |   B  |
		 * |    +-+-----+
		 * +----+ +     |
		 *      |   A   |
		 * 		|       |
		 * 		+-------+	
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
		
	}
	
	
}
