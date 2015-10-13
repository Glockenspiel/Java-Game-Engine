package components;

import display.Drawer;
import framework.Component;
import framework.Game;
import framework.GameObject;
import framework.MathG;
import framework.Vector;

public class RigidBody extends Component {
	
	private static final String TYPE="Rigidbody";
	private float gravity=1;
	private int mass;
	private float drag=1; //from 0-100
	private Vector velocity=new Vector(0,0);
	
	public RigidBody(int mass){
		this.mass=mass;
	}
	
	public void setGravity(float gravity){
		if(gravity<0) 
			gravity = 0;
		this.gravity=gravity;
	}
	
	public int getMass(){
		return mass;
	}
	
	public void setMass(int mass){
		if(mass<0)
			mass=0;
		this.mass=mass;
	}
	
	public float getGravity(){
		return gravity;
	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	public void addForce(Vector force){
		float x = force.getX() * massRatio();
		float y = force.getY() * massRatio();
		
		
		velocity = Vector.add(velocity, new Vector(x,y));
	}
	
	public Vector getVelocity(){
		return velocity;
	}
	
	public float getVelocityDistance(){
		return (float)MathG.distance(0, 0, velocity.getX(), velocity.getY());
	}

	@Override
	public void update(GameObject obj) {
		//apply gravity
		addForce(new Vector(0,gravity));
		
		//apply drag 
		velocity = new Vector(velocity.getX()*((100-drag)/100),velocity.getY()*((100-drag)/100));
		
		//move GameObject by velocity
		obj.moveBy(velocity);
	}

	@Override
	public void draw(Drawer g, Vector objPos) {}
	
	private float massRatio(){
		return 100/(float)mass;
	}

	public void setDrag(float drag) {
		this.drag = MathG.clamp(drag, 0, 100);
	}
	
	public float getDrag(){
		return drag;
	}

}
