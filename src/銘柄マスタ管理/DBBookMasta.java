/**
 * 
 */
package 銘柄マスタ管理;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author misskabu
 *　This is the class for connect and write/read records to "株式マスタ" table;
 */
public class DBBookMasta {
	final String H2DRIVER = "org.h2.Driver";
	final String H2URL = "jdbc:h2:~/JAR/trade";
	private DefaultTableModel tableModel;
	private HashMap<String,String> inputedTexts;
	public DBBookMasta() {
		super();
		setInputedTexts(new HashMap<>());
		
	}
	/**
	 * 株式マスタの内容を取得してこのクラスのtableModelにセットする。
	 * 市場カラムは別テーブルからリレーションシップしている。
	 */
	public void getAllBookMasta(){		
			try (Connection con = DriverManager.getConnection(H2URL);
				PreparedStatement ps = con.prepareStatement(
						"SELECT 証券コード,名前,市場 FROM 株式マスタ LEFT JOIN 市場マスタ ON 株式マスタ.市場コード = 市場マスタ.市場コード");){
				System.out.println("DBAllRecordからデータベース接続しました");	
				ResultSet rs = ps.executeQuery();
				System.out.println("レコード取得成功");
				String[] columnNames = {"証券コード","名前","市場"};
				String[] record = new String[9];	
				if(tableModel==null){
					tableModel = new DefaultTableModel(columnNames,0);
				}
				tableModel.setRowCount(0);
				try {
					while(rs.next()){
						record[0] = rs.getString("証券コード");
					    record[1] = rs.getString("名前");
					    record[2] = rs.getString("市場");
						tableModel.addRow(record);
					}
					System.out.println("tableModelへの書き込みに成功");
				} catch (SQLException e) {
					//e.printStackTrace();
					System.out.println("トータルモデルのSQLに失敗");
				}
				}
			catch (Exception e) {
				e.printStackTrace();
				}
	}
	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}
	public void addRecord() {
		
		try (Connection con= DriverManager.getConnection(H2URL);
			PreparedStatement ps = con.prepareStatement
			("INSERT INTO 株式マスタ (証券コード,名前,市場コード) VALUES(?,?,?)")){
			System.out.println("addrecordからデータベースに接続しました");
			System.out.println(getInputedTexts().get("証券コード"));
			System.out.println(getInputedTexts().get("名前"));
			System.out.println(getInputedTexts().get("市場"));
			try {
				ps.setInt(1,Integer.valueOf(getInputedTexts().get("証券コード")));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.setString(2,((getInputedTexts().get("名前"))));
			} catch (NumberFormatException | SQLException e) {
				System.out.println("名前の取得エラー");
			} catch(IllegalArgumentException e){
				System.out.println("入力された型が違うようです");	
			}
			try {
				ps.setInt(3,Integer.valueOf((getInputedTexts().get("市場"))));
			} catch (NumberFormatException | SQLException e) {
				System.out.println("市場コードの取得エラー");
			} catch(IllegalArgumentException e){
				System.out.println("入力された型が違うようです");	
			}
			int result = ps.executeUpdate();
				if(result!=0){
					System.out.println(result + "件のレコード書き込み成功しました");
					JOptionPane.showMessageDialog(null, result+"件のレコード書き込み成功しました");
				}
				else{
					System.out.println("書き込みに失敗しました");
					JOptionPane.showMessageDialog(null, result+"書き込みに失敗しました");
				}
			} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			}
	}
	public HashMap<String,String> getInputedTexts() {
		return inputedTexts;
	}
	public void setInputedTexts(HashMap<String,String> inputedTexts) {
		this.inputedTexts = inputedTexts;
	}
	/**
	 * 証券コードの欄に入力された証券コードのレコードを削除する
	 */
	public void deleteRecord() {
	
		try (Connection con = DriverManager.getConnection(H2URL);
			PreparedStatement ps = con.prepareStatement("DELETE FROM 株式マスタ WHERE 証券コード = ?");){
			ps.setInt(1,Integer.valueOf(this.inputedTexts.get("証券コード")));
			int result = ps.executeUpdate();
					if(result!=0){
						System.out.println(result + "件のレコード削除に成功しました");
						JOptionPane.showMessageDialog(null,result + "件のレコード削除に成功しました");
					}
					else{
						System.out.println("削除に失敗しました");
					}
			} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			}
	}
	public void alterRecord() {
		
		try (Connection con = DriverManager.getConnection(H2URL);
			PreparedStatement ps = con.prepareStatement("UPDATE 株式マスタ SET 名前 = ?,市場コード = ? WHERE 証券コード = ?");){	
			ps.setString(1,((inputedTexts.get("名前"))));
			ps.setInt(2,Integer.valueOf(inputedTexts.get("市場コード")));
			ps.setInt(3, Integer.valueOf(inputedTexts.get("証券コード")));
			int result = ps.executeUpdate();
					if(result!=0){System.out.println(result + "件のレコード更新に成功しました");
						JOptionPane.showMessageDialog(null,result + "件のレコード更新に成功しました");}
					else{System.out.println("更新に失敗しました");
						JOptionPane.showMessageDialog(null, "更新に失敗しました");}
			} catch (Exception e) {
			e.printStackTrace();
			}
	}

}
