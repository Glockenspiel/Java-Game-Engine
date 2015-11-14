package levels;

import demo.Player;
import framework.Game;
import framework.Level;

public class Level3 extends Level {

	@Override
	public void init() {
		if(Game.objExistsWithTag("player")==false){
			addObj(new Player());
		}
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
