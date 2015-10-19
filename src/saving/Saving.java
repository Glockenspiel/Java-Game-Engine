package saving;

public class Saving implements SavingI {
	
	private Originator originator;
    private CareTaker careTaker;
	
	public Saving(){
		originator = new Originator();
		careTaker = new CareTaker();
	}

	@Override
	public void saveState(GameState state) {
		originator.setState(state);
		careTaker.add(originator.saveStateToMemento());
	}

	@Override
	public GameState getLastState() {
		originator.getStateFromMemento(careTaker.getLatest());
		return originator.getState();
	}

}
