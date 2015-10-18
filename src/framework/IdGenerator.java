package framework;

import java.util.ArrayList;

public class IdGenerator {
	
	private static int lastID=0;
	private static boolean reachedMax=false;
	
	//generates a unique id for use of a GameObject in this Game
	public static int generateID(){
		if(lastID==Integer.MAX_VALUE-1){
			reachedMax=true;
		}
		
		if(reachedMax){
			//find all exisitng ids
			ArrayList<Integer> existingIDs = new ArrayList<Integer>();
			for(GameObject o : Game.copyOfGameObjects()){
				if(o.hasID())
					existingIDs.add(o.getID());
			}
			
			//once found a unique id, return it
			boolean flag=true;
			for(int i=0; i<4000 && flag; i++)
				if(existingIDs.contains(i)==false){
					return i;
				}
		}
		//else if hasn't reached the maximum value of int
		//get next value and return it
		lastID++;
		return lastID-1;
	}
}
