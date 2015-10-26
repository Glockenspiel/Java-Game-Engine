package framework;

public class Time {
	//constant values for time
	
	public static long getTime(){
		return System.currentTimeMillis();
	}
	
	private static final float FPS= 60;
	public static final float FRAME_TIME=1000/FPS;
	public static final float deltaTime = 10 * 1/FPS;
}
