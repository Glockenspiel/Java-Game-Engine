package scripts;

import misc.Vector;
import components.RigidBody;
import framework.Component;
import framework.GameObject;
import framework.Script;

public class MoveScript implements Script {
	
	private int id;
	private Vector moveSpeed = new Vector(-10,0);
	private float maxSpeed;
	
	//adds forces to a rigid body each frame until it reaches maximum speed
	public MoveScript(int id, float maxSpeed){
		this.id=id;
		this.maxSpeed=maxSpeed;
	}

	//moves the rigid body
	@Override
	public void execute(GameObject obj) {
		Component c = obj.getComponentByType(RigidBody.class);
		if(c instanceof RigidBody){
			RigidBody body =((RigidBody) c);
			if(body.getVelocityDistance()<maxSpeed)
				body.addForce(moveSpeed.getDeltaVector());
		}
	}

	@Override
	public void construct(String[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getSaveArgs() {
		// TODO Auto-generated method stub
		return null;
	}
}
