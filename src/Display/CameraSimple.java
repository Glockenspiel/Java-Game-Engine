package display;

import framework.Game;
import framework.Vector;

public class CameraSimple implements Camera {

	private Vector position;
	private int followId=-1;
	Vector offset;
	
	public CameraSimple(int x, int y){
		position=new Vector(x,y);
	}

	//moves the camera by an amount
	@Override
	public void moveBy(Vector amount) {
		position.moveBy(amount);
	}

	//moves the camera to a position
	@Override
	public void moveTo(Vector position) {
		position.moveTo(position);
	}

	//Gets camera's position
	@Override
	public Vector getPosition() {
		return position;
	}

	//follows a game object 
	@Override
	public void followObjectID(int id, Vector offset) {
		followId=id;
		this.offset=offset;
	}
	
	//follows a game object 
	@Override
	public void followObjectTag(String tag, Vector offset) {
		int id = Game.getGameObjectByTag(tag).getID();
		followObjectID(id, offset);
	}
	
	

	@Override
	public void update() {
		//true if camera is following something
		if(followId!=-1){ 
			Vector objPos = Game.getGameObjectById(followId).getPosition();
			Vector objCameraPos = Vector.multiply(objPos,new Vector(-1,-1));
			Vector cameraPos = Vector.add(objCameraPos, offset);
			position.moveTo(cameraPos);
		}
	}

	@Override
	public void unFollow() {
		followId=-1;
	}

	@Override
	public int getFollowId() {
		return followId;
	}
}
