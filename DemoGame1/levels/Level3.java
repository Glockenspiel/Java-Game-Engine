package levels;

import scripts.StressScript;
import demo.Player;
import framework.Game;
import framework.GameObject;
import framework.Level;

public class Level3 extends Level {

	@Override
	public void init() {
		if(Game.objExistsWithTag("player")==false){
			addObj(new Player());
		}
		
		
		int count=100;
		for(int i =0; i<count; i++){
			GameObject o  = new GameObject("stress");
			o.add(new StressScript());
			addObj(o);
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
