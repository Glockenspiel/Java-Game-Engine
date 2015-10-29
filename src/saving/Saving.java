package saving;

import framework.Game;

public class Saving implements SavingI {
	
	private Originator originator;
    private CareTaker careTaker;
	
	public Saving(){
		originator = new Originator();
		careTaker = new CareTaker();
	}

	//get the current game state and save it
	@Override
	public void saveState() {
		GameState state = new GameState(Game.getCurrentLevel());
		originator.setState(state);
		careTaker.add(originator.saveStateToMemento());
	}

	//returns the last saved state
	@Override
	public GameStateI getLastState() {
		originator.getStateFromMemento(careTaker.getLatest());
		return originator.getState();
	}

}
