package framework;

public class MathG {
	
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
}
