package 銘柄マスタ管理;

import java.awt.Container;

import javax.swing.JFrame;

public class MastaViewFrame extends JFrame {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MastaViewPanel panel; 
	MastaViewFrame(){		
		this.panel = new MastaViewPanel();
		this.setTitle("MastaViewFrame");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container contentpain = this.getContentPane();
		this.setBounds(200,100,450,350);
		contentpain.add(panel);
		this.setVisible(true);
	}
	public MastaViewPanel getPanel() {
		return this.panel;
	}
}
