package audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import framework.Component;
import framework.Game;
import framework.SetObjID;

public class AudioSource extends Component implements SetObjID {

	private int objID;
	private static final String PATH="Resources/Sounds/";
	private AudioInputStream stream;
	private File sound;
	private boolean loop=false;
	
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
				// Check if the audio file is a .wav file
				if (sound.getName().toLowerCase().contains(".wav"))
				{
					int playCount=0;
					while(loop || (loop==false && playCount==0)){
						playCount++;
						stream = AudioSystem.getAudioInputStream(sound);
						
						AudioFormat format = stream.getFormat();
						
						if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED)
						{
							format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
									format.getSampleRate(),
									format.getSampleSizeInBits() * 2,
									format.getChannels(),
									format.getFrameSize() * 2,
									format.getFrameRate(),
									true);
							
							stream = AudioSystem.getAudioInputStream(format, stream);
						}
						
						SourceDataLine.Info info = new DataLine.Info(
								SourceDataLine.class,
								stream.getFormat(),
								(int) (stream.getFrameLength() * format.getFrameSize()));
						
						SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
						line.open(stream.getFormat());
						line.start();
									
							
							int num_read = 0;
							byte[] buf = new byte[line.getBufferSize()];
							
							try{
								while ((num_read = stream.read(buf, 0, buf.length)) >= 0)
								{
									int offset = 0;
									
									while (offset < num_read)
									{
										offset += line.write(buf, offset, num_read - offset);
									}
								}
							} catch(IOException ex){ 
								Game.print("caught ioexception", "warning");
							}
						
						
						line.drain();
						line.stop();
					}
					
				}
				stream.close();
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
	
	public boolean isPlaying(){
		return playThread.isAlive();
	}
	
	//audio will loop if set to true
	public void setLooping(boolean loop){
		this.loop = loop;
	}
}
