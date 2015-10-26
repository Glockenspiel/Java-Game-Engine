package scripts;

import components.RigidBody;
import framework.Component;
import framework.Game;
import framework.GameObject;
import framework.Script;
import framework.Vector;

public class MoveScript implements Script {
	
	private int id;
	private Vector moveSpeed = new Vector(-10,0);
	private float maxSpeed;
	
	public MoveScript(int id, float maxSpeed){
		this.id=id;
		this.maxSpeed=maxSpeed;
	}

	@Override
	public void execute(GameObject obj) {
		Component c = obj.getComponentByType("RigidBody");
		if(c instanceof RigidBody){
			RigidBody body =((RigidBody) c);
			if(body.getVelocityDistance()<maxSpeed)
				body.addForce(moveSpeed.getDeltaVector());
		}
	}

	@Override
	public void interuptThreads() {
		// TODO Auto-generated method stub

	}

}
