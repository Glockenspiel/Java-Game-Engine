package saving;

public class Memento {
	
	private GameState gameState;
	
	public Memento(GameState state){
		gameState=state;
	}
	
	public GameState getState(){
		return gameState;
	}
}
