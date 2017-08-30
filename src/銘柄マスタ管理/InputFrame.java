/**
 * 
 */
package 銘柄マスタ管理;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

/**
 * @author misskabu
 *
 */
public class InputFrame extends JFrame {
	/**
	 * 入力画面のWindow
	 */
	private static final long serialVersionUID = 4465815641628134486L;
	public InputPanelMain panelMain;
	
	/**
	 * @param panel 表を内包するパネルのポインタ
	 */
	InputFrame(MastaViewPanel panel){
		this.panelMain = new InputPanelMain();
		InputPanelBottom panelBottom = new InputPanelBottom(this.panelMain,panel);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container contentpain = this.getContentPane();
		contentpain.add("North",panelMain);
		contentpain.add("South",panelBottom);
		this.setTitle("InputFrame");
		this.setBounds(800,100,370,150);
		this.setVisible(true);
	}
}
