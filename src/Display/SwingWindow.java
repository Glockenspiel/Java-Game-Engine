package Display;

import javax.swing.JFrame;

public class SwingWindow implements Window{
	private JFrame f;
	
	public SwingWindow(){
		f = new JFrame();
		DrawPanel panel = new DrawPanel();
		f.add(panel);
		f.setBounds(100,50,500,300);
		f.setVisible(true);
	}


	@Override
	public void drawScene() {
		f.repaint();
	}
}
