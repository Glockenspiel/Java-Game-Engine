package audio;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;

public class PlayingState implements AudioState {

	private AudioSource audioSrc;
	private static String PATH="Resources/Audio";
	private AudioInputStream audioInputStream;
	
	public PlayingState(AudioSource src){
		audioSrc = src;
	}
	
	@Override
	public void doAction() {
		playThread.start();
	}

	@Override
	public void endAction() {
		playThread.interrupt();
		audioSrc.setState(new StoppedState());
	}

	
	private Thread playThread = new Thread(){
		public void run(){
			//play audio here
			
			
			endAction();
		}
		
		@Override
		public void interrupt(){
			super.interrupt();
			try {
				audioInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};
}
