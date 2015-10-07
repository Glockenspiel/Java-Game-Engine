package debugging;

import javax.swing.JOptionPane;

public class SwingPrint implements Print {
	
	@Override
	public void dialog(String msg, String title, int type) {
		JOptionPane.showMessageDialog(null, title , msg, type);
	}

	@Override
	public void log(String msg) {
		System.out.println(msg);
	}

}
