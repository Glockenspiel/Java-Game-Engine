package services;


public class ConsolePrint implements Print {

	//concrete logging
	@Override
	public void log(String msg) {
		System.out.println(msg);
	}

}
