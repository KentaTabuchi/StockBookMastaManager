/**
 * 
 */
package 銘柄マスタ管理;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;



/**
 * @author misskabu
 *
 */
public class DAOFacade {
	private HashMap<String,String> inputedTexts;
	public DAOFacade(){
		this.setInputedTexts(new HashMap<>());
	}
	public DefaultTableModel getAllBookMastaModel(){
		DBBookMasta dbMasta = new DBBookMasta();
		
		dbMasta.getAllBookMasta();
		return dbMasta.getTableModel();
	}

	@SuppressWarnings("unchecked")
	public void addRecord() {
		// TODO 自動生成されたメソッド・スタブ
		DBBookMasta db = new DBBookMasta();
		db.setInputedTexts((HashMap<String, String>) this.getInputedTexts().clone());
		db.addRecord();
	}


	@SuppressWarnings("unchecked")
	public void deleteRecord() {
		// TODO 自動生成されたメソッド・スタブ
		DBBookMasta db = new DBBookMasta();
		db.setInputedTexts((HashMap<String, String>) this.getInputedTexts().clone());
		db.deleteRecord();
	}

	@SuppressWarnings("unchecked")
	public void alterRecord() {
		// TODO 自動生成されたメソッド・スタブ
		DBBookMasta db = new DBBookMasta();
		db.setInputedTexts((HashMap<String,String>)this.getInputedTexts().clone());
		db.alterRecord();
	}

	public int getMaxId() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
	public HashMap<String,String> getInputedTexts() {
		return inputedTexts;
	}
	public void setInputedTexts(HashMap<String,String> inputedTexts) {
		this.inputedTexts = inputedTexts;
	}


}
