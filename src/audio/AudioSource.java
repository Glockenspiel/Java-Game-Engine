package audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

import loaders.Format;
import misc.MathG;
import framework.Component;
import framework.Game;
import framework.SetObjID;

public class AudioSource extends Component implements SetObjID {

	private int objID;
	private static final String PATH="Resources/Sounds/";
	private AudioInputStream stream;
	private File sound;
	private boolean loop=false, isPaused=false, volumeChanged=false;
	private int volume=93;
	
	public AudioSource(String fileName, boolean playOnStart){
		sound = new File(PATH + fileName);
		if(playOnStart)
			playThread.start();
	}
	
	//disposes of audio when GameObject is deleted
	@Override
	public void dispose(){
		if(isPlaying()){
			if(loop==false)
				Game.print("GameObect deleted before AudioSource completed - " + sound.getName(), "warning");
			playThread.interrupt();
		}
	}
	
	//plays the audio
	public void play(){
		if(isPlaying()==false)
			playThread.start();
		else if(isPaused==true)
				isPaused=false;
	}

	//stop the audio 
	public void stop(){
		if(isPlaying()){
			playThread.interrupt();
		}
	}
	
	//set a new sound, if the audio source is play it will resume with the new sound.
	public void setSound(String fileName){
		boolean wasPlaying=isPlaying();
		
		if(wasPlaying)
			playThread.interrupt();
		
		sound = new File(PATH+fileName);
		
		if(wasPlaying)
			playThread.start();
	}
	
	@Override
	public void setGameObjectID(int id) {
		objID=id;
	}
	
	private Thread playThread = new Thread(){
		public void run(){
			try
			{
				if(Format.match(sound, "wav")){
					//create audio stream
					try {
			            stream = AudioSystem.getAudioInputStream(sound);
			        } catch (Exception e){
			        	Game.print("Audio file not found: " + sound.getName(), "error");
			            return;
			        }
			
					
					//get the format
			        AudioFormat audioFormat = stream.getFormat();
			
			        //open source data
			        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
			        SourceDataLine sourceLine = null;
					try {
			            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
			            sourceLine.open(audioFormat);
			        } catch (Exception e) {
			            e.printStackTrace();
			            return;
			        }
					//start source data
			        sourceLine.start();
			        FloatControl volumeControl = (FloatControl) sourceLine.getControl(FloatControl.Type.MASTER_GAIN);
			        volumeControl.setValue(volumePercentToValue());
			
			        int nBytesRead = 0;
			        byte[] abData = new byte[sourceLine.getBufferSize()];
			        int playCount=0;
			        while(loop || (loop==false && playCount==0)){
			        		playCount++;
				        while (nBytesRead != -1) {
				        	if(isPaused==false){
				        		
				        		
				        		if(volumeChanged){
				    		        volumeControl.setValue(volumePercentToValue());
				        		}
					            try {
					                nBytesRead = stream.read(abData, 0, abData.length);
					            } catch (IOException e) {e.printStackTrace(); }
					            if (nBytesRead >= 0) {
					                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
					            }
				        	}
				        }
			        }
			
			        //drain queued data and close
			        sourceLine.drain(); 
			        sourceLine.close();
				}
				else{
					Game.print("Sound file must be of format .wav - "  + sound.getName(), "error");
				}
		        //broadcast event saying the audio is completed
				Game.getGameObjectById(objID).notifyEventListeners("Audio_complete", 0);
				
			}
			catch (Exception ex) { ex.printStackTrace(); }
		}
		
		//closes the audio stream before it calls interrupt
		@Override
		public void interrupt(){
			try {
				if(stream!=null)
					stream.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			super.interrupt();
		}
	};
	
	//returns true if the audio has started playing, (paused audio returns true)
	public boolean isPlaying(){
		return playThread.isAlive();
	}
	
	//audio will loop if set to true
	public void setLooping(boolean loop){
		this.loop = loop;
	}
	
	//pauses the audio
	public void pause(){
		isPaused=true;
	}
	
	//resumes paused audio
	public void resume(){
		isPaused=false;
	}
	
	//returns the current state as a string
	public String getState(){
		boolean playing=isPlaying();
		if(playing && !isPaused)
			return "playing";
		else if(playing && isPaused)
			return "paused";
		
		return "stopped";
	}
	
	//sets the volume percentage
	public void setVolume(int volume){
		this.volume=MathG.clamp(volume, 100, 0);
		volumeChanged=true;
	}
	
	//converts the volume percentage to the range 6 to -80
	private float volumePercentToValue(){
		return (((float)volume/100) * 86)-80;
	}
}
