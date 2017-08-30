package 銘柄マスタ管理;

import java.awt.Button;
import java.awt.FlowLayout;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.TableModel;

public class InputPanelBottom extends JPanel {
	/**
	 * 
	 */
	private final String[] columnName={"証券コード","名前","市場"};
	private static final long serialVersionUID = 1L;
	private InputPanelMain panelMain;
	private MastaViewPanel mastaPanel;
	private Button addButton;
	private Button deleteButton;
	private Button alterButton;
	private Button loadButton;
	/**
	 *テキストボックスから吸い上げたテキストのリスト。<String 検索キーワード,String テキスト>
	 */
	private HashMap<String,String> inputedTexts = new HashMap<>();
	/**
	 * 市場コードの対応マップ。　<String 検索する市場名,Integer市場コード>
	 */
	private HashMap<String,Integer> marketCode = new HashMap<>();
	InputPanelBottom(InputPanelMain panelMain,MastaViewPanel panel){
		this.panelMain=panelMain;
		this.mastaPanel=panel;
		this.setMarketCode();
		addButton = new Button("追加");
		deleteButton = new Button("削除");
		alterButton = new Button("更新");
		loadButton = new Button("読込");
		addButton.addActionListener(event->{
			System.out.println("追加ボタンが押されました");
			this.textboxToMap(this.columnName);
			DAOFacade facade = new DAOFacade();
			facade.setInputedTexts(this.inputedTexts);
			facade.addRecord();
			this.mastaPanel.table.setModel(facade.getAllBookMastaModel());
			
		});
		deleteButton.addActionListener(e->{
			System.out.println("削除ボタンが押されました");
			this.textboxToMap(this.columnName);
			DAOFacade facade = new DAOFacade();
			facade.setInputedTexts(this.inputedTexts);
			facade.deleteRecord();
			this.mastaPanel.table.setModel(facade.getAllBookMastaModel());
		});
		alterButton.addActionListener(e->{
			this.textboxToMap(this.columnName);
			DAOFacade facade = new DAOFacade();
			facade.setInputedTexts(this.inputedTexts);
			facade.alterRecord();
			this.mastaPanel.table.setModel(facade.getAllBookMastaModel());
		});
		loadButton.addActionListener(event->{
			int row = panel.table.getSelectedRow();
			TableModel model = panel.table.getModel();

				this.panelMain.setText(0, model.getValueAt(row, 0).toString());
				this.panelMain.setText(1, model.getValueAt(row, 1).toString());
				try{this.panelMain.setText(2, marketTextToCode(model.getValueAt(row, 2).toString()));}
				catch(NullPointerException exception){this.panelMain.setText(2,"");}
				
		});

		this.setLayout(new FlowLayout());
		this.add(addButton);
		this.add(deleteButton);	
		this.add(alterButton);
		this.add(loadButton);
	}
	
	/**
	 * @param key
	 * テキストボックスに入力されたテキストをハッシュマップに吸い上げてFacadeクラスへ渡す準備をする
	 */
	private void textboxToMap(String...key){
		boolean isNull=false;
		
		for(int i=0;i<key.length;i++){
			if(this.panelMain.getText(i).equals("")){
				isNull=true;
			}
			else{
				this.inputedTexts.put(key[i], this.panelMain.getText(i));
			}
		}
		if(isNull){
			JOptionPane.showMessageDialog(this,"テキストに未入力があります");
		}	
	}
	/** 市場名を市場コードに変換するコンバーター
	 * 読み込みボタンを押した時に表のテキストを変換するために使う
	 * @param text　市場名
	 * @return　市場コード
	 */
	private String marketTextToCode(String text) {
		return String.valueOf(marketCode.get(text));
	}
	/**
	 * 市場コード対応マップの作成。定数リストの代わりで動的に変更しないので引数はない。
	 */
	private void setMarketCode() {
		this.marketCode.put("東証1部", 1);
		this.marketCode.put("東証2部", 2);
		this.marketCode.put("東証JQS", 3);
		this.marketCode.put("マザーズ", 4);
	}


}
