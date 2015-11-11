package filtering;

public class DebugFilter implements Filter {
	
	//debugging filter
	@Override
	public String filterMsg(String msg){
		return "Debugging: " + msg;
	}
}
