import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class Expire {
	
	private JTable tableExpire;
	public Expire(){
		
	}
	
	public JScrollPane designTableExpire(){
		
		BackendController bk = new BackendController();
		String [][] listSong = null;
		String [] songId;
		String [] songTitle ;
		String [] columnTitle = new String []{"Nº","Title","Expire"};
	
		try {
			listSong=bk.listSong("1");
			//songTitle=getSong;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		songId=listSong[0]; 
		songTitle = listSong[1];
		
		tableExpire = new JTable(new JTableModel(songId , songTitle, columnTitle, "Renew")); 
		tableExpire.setFillsViewportHeight(true);	
		tableExpire.setRowHeight(30); // set all rows height
		tableExpire.getColumnModel().getColumn(1).setPreferredWidth(300);//set column 1 width
		TableCellRenderer buttonRendererExpire = new JTableButtonRenderer();
		tableExpire.getColumn("Expire").setCellRenderer(buttonRendererExpire);
		tableExpire.addMouseListener(new JTableButtonMouseListener(tableExpire));
		JScrollPane scrollExpire = new JScrollPane(tableExpire, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		return scrollExpire;
	}


}
