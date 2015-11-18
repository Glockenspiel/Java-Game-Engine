package framework;

import levelloading.Cast;
import misc.Vector;
import display.Drawer;

public abstract class Component extends Constructable{	

	//update is where to do the game's logic
	public void update(GameObject obj){};
	
	//draw is where draw calls should be placed and is called after update
	public void draw(Drawer g, Vector objPos){};
	
	//called on deletion
	public void dispose(){};
	
	
	/*
	 * Short hand for construction of object
	 */
	public static char toChar(String val){
		return Cast.toChar(val);
	}
	
	public static byte toByte(String val){
		return Cast.toByte(val);
	}
	
	public static short toShort(String val){
		return Cast.toShort(val);
	}
	
	public static int toInt(String val){
		return Cast.toInt(val);
	}
	
	public static long toLong(String val){
		return Cast.toLong(val);
	}
	
	public static float toFloat(String val){
		return Cast.toFloat(val);
	}
	
	public static double toDouble(String val){
		return Cast.toDouble(val);
	}
	
	public static boolean toBoolean(String val){
		return Cast.toBoolean(val);
	}
}
