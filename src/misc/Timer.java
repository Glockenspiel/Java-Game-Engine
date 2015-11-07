package misc;

public class Timer {

	public static final int MILLI=1000000,MICRO=1000, NANO=1;
	
	private long[] times = new long[100];
	
	//used to make sure 100 times were logged when calculating the average
	private int maxTimeIndex=0; 
	
	private int timeIndex=0;
	private long start;
	private int multiplier=1;
	private String secType="ns";
	
	public Timer(){}
	
	public static long nanoTime(){
		return System.nanoTime();
	}
	
	public void start(){
		start = nanoTime();
	}
	
	public void stopAndPrint(){
		long end = nanoTime();
		System.out.println("Time:" + ((end-start)/multiplier) + secType);
	}
	
	//logs time and saves it to an array
	public void stopAndLog(){
		long end = System.nanoTime();
		times[timeIndex] = end-start;
		
		timeIndex++;
		maxTimeIndex=timeIndex;
		
		if(timeIndex>=times.length)
			timeIndex=0;
	}
	
	//calculates the average time from logs
	public long calculateAvg(){
		long total=0;
		for(int i=0; i<times.length && i < maxTimeIndex; i++)
			total+=times[i];
		if(total==0 || maxTimeIndex==0)
			return 1;
		return total/maxTimeIndex;
	}
	
	public void clearLoggedTimes(){
		times = new long[100];
		maxTimeIndex=0;
	}
	
	public void printAvg(){
		System.out.println("Avg time: " + calculateAvg()/multiplier + secType);
	}

	public void stopAndPrint(String string) {
		long end = nanoTime();
		System.out.println(string + ((end-start)/multiplier) + secType);
	}
	
	public void setNanosecs(){
		secType="ns";
		multiplier=NANO;
	}
	
	public void setMicrosecs(){
		secType="us";
		multiplier=MICRO;
	}
	
	public void setMillisecs(){
		secType="ms";
		multiplier=MILLI;
	}

	public long getCurrentTime(int type) {
		return (nanoTime()-start)/type;
	}
}
