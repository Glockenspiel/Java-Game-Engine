package components;

import misc.MathG;
import misc.Time;
import misc.Vector;
import display.Drawer;
import framework.Component;
import framework.Game;
import framework.GameObject;

//physics movement component
public class RigidBody extends Component {
	
	private float gravity=1;
	private int mass;
	private float drag=1; //from 0-100
	private Vector velocity=new Vector(0,0);
	
	//constructor
	public RigidBody(int mass){
		this.mass=mass;
	}
	
	//set the gravity
	public void setGravity(float gravity){
		if(gravity<0) 
			gravity = 0;
		this.gravity=gravity;
	}
	
	//return the mass
	public int getMass(){
		return mass;
	}
	
	//set the mass
	public void setMass(int mass){
		if(mass<0)
			mass=0;
		this.mass=mass;
	}
	
	//return the gravity
	public float getGravity(){
		return gravity;
	}
	
	//add a force
	public void addForce(Vector force){
		float x = force.getX() * massRatio();
		float y = force.getY() * massRatio();
		
		
		velocity = Vector.add(velocity, new Vector(x,y));
	}
	
	//returns the current velocity
	public Vector getVelocity(){
		return velocity;
	}
	
	//returns the distance the rigid body is moving 
	public float getVelocityDistance(){
		return (float)MathG.distance(0, 0, velocity.getX(), velocity.getY());
	}

	//calculate the position change of the GameObject
	@Override
	public void update(GameObject obj) {
		//apply gravity
		addForce(new Vector(0,gravity));
		
		//apply drag 
		velocity = new Vector(velocity.getX()*((100-drag)/100),velocity.getY()*((100-drag)/100));
		
		//move GameObject by velocity
		Vector moveBy = new Vector(velocity.getX(), velocity.getY());

		//ignore small movements
		if(Math.abs(moveBy.getX())<1f)
			moveBy = new Vector(0,moveBy.getY());
		if(Math.abs(moveBy.getY())<1f)
			moveBy = new Vector(moveBy.getX(),0);
		
		moveBy = new Vector(moveBy.getX()*Time.deltaTime, moveBy.getY()*Time.deltaTime);
		obj.moveBy(moveBy);
	}
	
	//ratio of the current mass to the default mass
	private float massRatio(){
		return 100/(float)mass;
	}

	//set the drag
	public void setDrag(float drag) {
		this.drag = MathG.clamp(drag, 0, 100);
	}
	
	//returns the drag
	public float getDrag(){
		return drag;
	}
}
