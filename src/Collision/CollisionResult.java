package Collision;

public class CollisionResult {
	
	private CollisionShape shapeA, shapeB;
	
	public CollisionResult(CollisionShape a, CollisionShape b){
		shapeA=a;
		shapeB=b;
	}
	
	public CollisionShape getShapeA(){
		return shapeA;
	}
	
	public CollisionShape getShapeB(){
		return shapeB;
	}
	
}
