package saving;

import java.util.ArrayList;

import framework.GameObject;
import framework.GameObjectStateI;
import framework.Level;

public class Originator {
	   private GameState state;

	   public void setState(ArrayList<GameObject> objs, Level currentLevel){
	      state = new GameState(objs, currentLevel);
	   }
	   
	   public void setState(GameState state){
		  this.state=state;
	   }

	   public GameState getState(){
	      return state;
	   }

	   public Memento saveStateToMemento(){
	      return new Memento(state);
	   }

	   public void getStateFromMemento(Memento Memento){
	      state = Memento.getState();
	   }
}
