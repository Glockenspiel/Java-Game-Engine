package saving;

public interface SavingI {
	public void saveState(GameState state);
	public GameState getLastState();
}
