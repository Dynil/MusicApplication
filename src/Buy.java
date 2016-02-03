import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class Buy {
	private JTable tableBuy;
	
	public Buy(){
		
	}
	public JScrollPane designTableBuy(){
			
		BackendController bk = new BackendController();
		
		String [][] listSong = null;
		String [] songId;
		String [] songTitle ;
		String [] columnTitle = new String []{"Nº","Title","Buy"};
	
		
		try {
			
			listSong=bk.listSong("1");
			
			//songTitle=getSong;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		songId=listSong[0]; 
		songTitle = listSong[1];
		
		
		tableBuy = new JTable(new JTableModel(songId , songTitle, columnTitle, "Buy")); 
		tableBuy.setFillsViewportHeight(true);	
		tableBuy.setRowHeight(30); // set all rows height
		tableBuy.getColumnModel().getColumn(1).setPreferredWidth(1000);//set column 1 width
		TableCellRenderer buttonRendererBuy = new JTableButtonRenderer();
		tableBuy.getColumn("Buy").setCellRenderer(buttonRendererBuy);
		tableBuy.addMouseListener(new JTableButtonMouseListener(tableBuy));
		JScrollPane scrollBuy = new JScrollPane(tableBuy, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		return scrollBuy;
	}
}
