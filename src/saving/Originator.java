package saving;

public class Originator {
	   private GameStateI state;
	   
	   //set the state
	   public void setState(GameStateI state){
		  this.state=state;
	   }

	   //return the state
	   public GameStateI getState(){
	      return state;
	   }

	   //save the state to memento
	   public Memento saveStateToMemento(){
	      return new Memento(state);
	   }

	   //get the state from the memento
	   public void getStateFromMemento(Memento Memento){
	      state = Memento.getState();
	   }
}
