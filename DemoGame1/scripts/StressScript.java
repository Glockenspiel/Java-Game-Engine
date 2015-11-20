package scripts;

import framework.Game;
import framework.GameObject;
import framework.Script;

public class StressScript implements Script {

	@Override
	public void execute(GameObject obj) {
		int sum=0;
		for(int i=0; i<10; i++){
			if(Game.getGameObjectByTag("hello")!=null)
				sum++;
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
