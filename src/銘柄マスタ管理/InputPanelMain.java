package 銘柄マスタ管理;

import java.awt.GridLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InputPanelMain extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3889990886986688230L;

	private JFormattedTextField [] textbox;
	InputPanelMain(){
		this.makeLabel("証券コード","名前","市場コード");
	}
	private void makeLabel(String... columnName){
		JLabel label[] = new JLabel[columnName.length];
		this.textbox=new JFormattedTextField[columnName.length];
		this.setLayout(new GridLayout(columnName.length,2));
		for(int i=0;i<columnName.length;i++){
			label[i]=new JLabel(columnName[i]);
			textbox[i]=new JFormattedTextField();
			this.add(label[i]);
			this.add(textbox[i]);
		}
			
	}
	/** 他クラスからテキストボックスの値を変更するメソッド
	 * @param i　どのテキストボックスかを示す配列番号　0:証券コード 1:名前 2:市場
	 * @param text　入力する値
	 */
	public void setText(int i,String text){
		textbox[i].setText(text);
	};
	/**　他クラスからテキストボックスの値を取り出すメソッド
	 * @param i　どのテキストボックスかを示す配列番号　0:証券コード 1:名前 2:市場
	 * @return　取り出したテキスト
	 */
	public String getText(int i){
		return textbox[i].getText();
	}
}
