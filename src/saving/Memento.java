package saving;

public class Memento {
	
	private GameStateI gameState;
	
	public Memento(GameStateI state){
		gameState=state;
	}
	
	public GameStateI getState(){
		return gameState;
	}
}
