package audio;

import framework.Component;

public class AudioSource extends Component {

	private String filename;
	private AudioState currentState = null;
	
	public AudioSource(String filename, boolean startOnConstruction){
		this.filename=filename;
		
		if(startOnConstruction)
			setState(new PlayingState(this));
		else
			setState(new StoppedState());
	}
	
	public void setState(AudioState state){
		currentState = state;
		state.doAction();
	}
	
	public String getFilename(){
		return filename;
	}
	
	public AudioState getState(){
		return currentState;
	}
	
	@Override
	public void dispose(){
		currentState.endAction();
	}
}
