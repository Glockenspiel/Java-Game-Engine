package saving;

public class Originator {
	   private GameStateI state;
	   
	   public void setState(GameStateI state){
		  this.state=state;
	   }

	   public GameStateI getState(){
	      return state;
	   }

	   public Memento saveStateToMemento(){
	      return new Memento(state);
	   }

	   public void getStateFromMemento(Memento Memento){
	      state = Memento.getState();
	   }
}
