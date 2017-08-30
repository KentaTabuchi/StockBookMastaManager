package 銘柄マスタ管理;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class MastaViewPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTable table;
	MastaViewPanel(){
		DAOFacade facade = new DAOFacade();
		table = new JTable(facade.getAllBookMastaModel());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		this.setTableColumnWidth(70,300);
		this.setLayout(new BorderLayout());
		JScrollPane scrollpane = new JScrollPane(table);
		this.add(scrollpane);
		}
private void setTableColumnWidth(int...widthSize){
	  DefaultTableColumnModel columnModel = (DefaultTableColumnModel)table.getColumnModel();
	  TableColumn column = null;
	  
	  for(int i=0;i<widthSize.length;i++){
	      column = columnModel.getColumn(i);
	      column.setPreferredWidth(widthSize[i]);

	  }
}
}
