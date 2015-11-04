package services;

import javax.swing.JOptionPane;

public class SwingPrint implements Print {
	//concrete dialog box
	@Override
	public void dialog(String msg, String title, int type) {
		JOptionPane.showMessageDialog(null, title , msg, type);
	}

	//concrete logging
	@Override
	public void log(String msg) {
		System.out.println(msg);
	}

}
