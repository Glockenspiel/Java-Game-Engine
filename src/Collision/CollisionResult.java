package collision;

//object to store the relationship between 2 CollisionShapes to increase the readability of code
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
