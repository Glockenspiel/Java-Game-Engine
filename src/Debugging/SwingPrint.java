package Debugging;

public class SwingPrint implements Print {
	
	@Override
	public void dialog(String msg, int type) {
		
	}

	@Override
	public void log(String msg) {
		System.out.println(msg);
	}

}
