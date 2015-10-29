package saving;

import framework.Game;

public class LoadingState implements LoadingStateI {

	//load the state into the game
	@Override
	public void loadState(GameStateI state) {
		Game.changeLevel(state.getCurrentLevel());
	}

}
