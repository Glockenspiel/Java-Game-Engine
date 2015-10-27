package saving;

import framework.Game;

public class LoadingState implements LoadingStateI {

	@Override
	public void loadLatestState(GameStateI state) {
		Game.changeLevel(state.getCurrentLevel());
	}

}
