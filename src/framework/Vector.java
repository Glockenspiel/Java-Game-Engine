package framework;

public class Vector { //for storing coordinates or directions
	
	private float x,y;
	
	public Vector(float x, float y){
		this.x=x;
		this.y=y;
	}
	
	public Vector(int x, int y){
		this.x=(float)x;
		this.y=(float)y;
	}
	
	//getter values as rounded integers used for screen coordinates
	public int intX(){
		return Math.round(x);
	}
	
	public int intY(){
		return Math.round(y);
	}
	
	
	//true values used for computation
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	//appends vector
	public void moveBy(Vector amount){
		x += amount.getX();
		y += amount.getY();
	}
	
	
	//sets vector
	public void moveTo(Vector position){
		x = position.getX();
		y = position.getY();
	}
}
