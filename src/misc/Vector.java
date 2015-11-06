package misc;

public class Vector { //for storing 2D coordinates or directions
	
	private float x,y;
	
	//float constructor
	public Vector(float x, float y){
		this.x=x;
		this.y=y;
	}
	
	//int constructor
	public Vector(int x, int y){
		this.x=(float)x;
		this.y=(float)y;
	}
	
	//getter values as rounded integers used for screen coordinates
	
	//return X value as integer
	public int intX(){
		return Math.round(x);
	}
	
	//return Y value as integer
	public int intY(){
		return Math.round(y);
	}
	
	
	//true values used for computation
	//returns exact value of X
	public float getX(){
		return x;
	}
	
	//returns exact value of Y
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
	
	//returns a formated string of X and Y values
	public String toString(){
		return "("+ (int)x + "," + (int)y + ")";
	}
	
	//multiple vector a by b on each axis and return result
	public static Vector multiply(Vector a, Vector b){
		return new Vector(a.getX() * b.getX(), a.getY() * b.getY());
	}
	
	//add vector a by b on each axis and return result
	public static Vector add(Vector a, Vector b){
		return new Vector(a.getX() + b.getX(), a.getY() + b.getY());
	}
	
	//subtract vector a by b on each axis and return result
	public static Vector subtract(Vector a, Vector b){
		return new Vector(a.getX() - b.getX(), a.getY() - b.getY());
	}
	
	//multiplies the vector by Time.deltaTime
	public Vector getDeltaVector(){
		Vector dt = new Vector(x*Time.deltaTime, y*Time.deltaTime);
		return dt;
	}
}
