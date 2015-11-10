package audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

public class PlayingState implements AudioState {

	private AudioSource audioSrc;
	private static final String PATH="Resources/Sounds/";
	private AudioInputStream stream;
	
	private File sound;
	private boolean muted = false; // This should explain itself
	private float volume = 100.0f; // This is the volume that goes from 0 to 100
	private float pan = 0.0f; // The balance between the speakers 0 is both sides and it goes from -1 to 1
	
	private double seconds = 0.0d; // The amount of seconds to wait before the sound starts playing
	
	private boolean looped_forever = false; // It will keep looping forever if this is true
	
	private int loop_times = 0; // Set the amount of extra times you want the sound to loop (you don't need to have looped_forever set to true)
	private int loops_done = 0; // When the program is running this is counting the times the sound has looped so it knows when to stop
	
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
			//play audio here
			System.out.println(sound.getName());
			//thread here
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
					
					// Set Volume
					FloatControl volume_control = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
					volume_control.setValue((float) (Math.log(volume / 100.0f) / Math.log(10.0f) * 20.0f));
					
					// Mute
					BooleanControl mute_control = (BooleanControl) line.getControl(BooleanControl.Type.MUTE);
					mute_control.setValue(muted);
					
					FloatControl pan_control = (FloatControl) line.getControl(FloatControl.Type.PAN);
					pan_control.setValue(pan);
					
					long last_update = System.currentTimeMillis();
					double since_last_update = (System.currentTimeMillis() - last_update) / 1000.0d;
					
					// Wait the amount of seconds set before continuing
					while (since_last_update < seconds)
					{
						since_last_update = (System.currentTimeMillis() - last_update) / 1000.0d;
					}
					
					//System.out.println("Playing!");
					
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
					} catch(IOException ex){ System.out.println("catched");}
					
					line.drain();
					line.stop();
					
					if (looped_forever)
					{
						new Thread(playThread).start();
					}
					else if (loops_done < loop_times)
					{
						loops_done++;
						new Thread(playThread).start();
					}
				}
				
				endAction();

			}
			catch (Exception ex) { ex.printStackTrace(); }
			//end of thread
			
			
		}
		
		@Override
		public void interrupt(){
			super.interrupt();
			System.out.println("interupt");
			try {
				if(stream!=null)
					stream.close();
				System.out.println("closing");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};
}
