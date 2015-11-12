package audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import framework.Game;

public class PlayingState implements AudioState {

	private AudioSource audioSrc;
	private static final String PATH="Resources/Sounds/";
	private AudioInputStream stream;
	
	private File sound;
	
	public PlayingState(AudioSource src){
		audioSrc = src;
		sound = new File(PATH + src.getFilename());
	}
	
	@Override
	public void doAction() {
		playThread.start();
	}

	@Override
	public void endAction() {
		if(playThread.isAlive())
			playThread.interrupt();
		
		audioSrc.setState(new StoppedState());
	}

	
	private Thread playThread = new Thread(){
		public void run(){
			try
			{
				// Check if the audio file is a .wav file
				if (sound.getName().toLowerCase().contains(".wav"))
				{
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
						Game.print("caught   ioexception", "warning");
					}
					
					line.drain();
					line.stop();
					
				}
				
				endAction();

			}
			catch (Exception ex) { ex.printStackTrace(); }
			//end of thread
			
			
		}
		
		@Override
		public void interrupt(){
			Game.print("GameObect deleted before AudioSource completed its play state - " + sound.getName(), "warning");
			try {
				if(stream!=null)
					stream.close();
				Game.print("closing sound -                    " + sound.getName());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			super.interrupt();
		}
	};
}
