package framework;

public class MathG {
	
	//generates a random number between the minimum and maximum values
	public static int randomNumber(int min, int max){
		if(min==max) return min;
		else if(min>max){
			int temp=min;
			min=max;
			max=temp;
		}
		
		int randomNum = (int)(Math.random() * max) + min;
		return randomNum;
	}
	
	//clamp value to minimum and maximum with integers
	public static int clamp(int value, int max, int min){
		return Math.min(max, Math.max(min, value));
	}
	
	//clamp value to minimum and maximum with floats
	public static float clamp(float val, float max, float min) {
		if(val>=max) return max;
		else if(val<=min) return min;
		return val;
	}
		
	//lerp is a simple smoothing algorithm
	public static int lerp(int a, int b, float smoothness){
		//linear interpolation based on the following formula
		//value1 + (value2-value1) * smoothingAmount
		float result = a + (a-b)*(1/(float)smoothness); 
		return Math.round(result);
	}
		
	//distance from point a to b
	public static double distance(float x1, float y1, float x2, float y2){
		return Math.hypot(x1-x2, y1-y2);
	}
		
}
