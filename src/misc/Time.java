package misc;

public class Time {
	//values for time
	
	//returns current system time
	public static long getTime(){
		return System.currentTimeMillis();
	}
	
	//desired frames per second
	private static final float FPS= 60;
	
	//milliseconds for a frame to complete
	public static final float FRAME_TIME=1000/FPS;
	
	/* Ratio of 1 second to the number of frames in 1 second
	 * then multiply that value by 10 to get a nicer value to work with
	 * 
	 * This allows objects to change by time rather than by frame
	 * this prevents different representations at different frame rates
	 * e.g. Move an object by 10 pixels every 10 milliseconds rather than 10 pixels every frame 
	 */
	public static final float deltaTime = 10 * 1/FPS;
}
