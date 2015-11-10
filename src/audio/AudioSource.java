package audio;

import framework.Component;

public class AudioSource extends Component {

	private String filename;
	private AudioState currentState;
	
	public AudioSource(String filename, boolean startOnConstruction){
		this.filename=filename;
		
		if(startOnConstruction)
			setState(new PlayingState(this));
		else
			setState(new StoppedState());
	}
	
	public void setState(AudioState state){
		if(currentState!=null)
			currentState.endAction();
		
		currentState = state;
		state.doAction();
	}
	
	public AudioState getState(){
		return currentState;
	}
}
