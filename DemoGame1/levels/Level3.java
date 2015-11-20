package levels;

import levelloading.LevelConstructor;
import framework.Level;

public class Level3 extends Level {

	@Override
	public void init() {
		LevelConstructor.construct("level1.xml");
	}
}
