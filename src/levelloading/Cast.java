package levelloading;

public class Cast {
	
	public static char toChar(String val){
		return val.charAt(0);
	}
	
	public static byte toByte(String val){
		return Byte.parseByte(val);
	}
	
	public static short toShort(String val){
		return Short.parseShort(val);
	}
	
	public static int toInt(String val){
		return Integer.parseInt(val);
	}
	
	public static long toLong(String val){
		return Long.parseLong(val);
	}
	
	public static float toFloat(String val){
		return Float.parseFloat(val);
	}
	
	public static double toDouble(String val){
		return Double.parseDouble(val);
	}
	
	public static boolean toBoolean(String val){
		return Boolean.parseBoolean(val);
	}
}
