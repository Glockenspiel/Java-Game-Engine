package services;

import javax.swing.JOptionPane;

public class SwingPrint implements Print {

	//concrete logging
	@Override
	public void log(String msg) {
		System.out.println(msg);
	}

}
