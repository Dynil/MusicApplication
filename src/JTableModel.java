import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;

public class JTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static String[] COLUMN_NAMES ;
	private static Class<?>[] COLUMN_TYPES;
	private String [] songTitle;
	private String [] songId;
	private String buttonTitle;
	
	
	JTableModel(String[] songId, String[] songTitle, String[] columnTitle, String buttonTitle){
		
		
		COLUMN_NAMES = columnTitle;
		COLUMN_TYPES = new Class<?>[] {String.class, String.class, CustomJButton.class};
		this.buttonTitle=buttonTitle;
		
		this.songId=songId;
		this.songTitle=songTitle;
		
	}
	
	@Override public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override public int getRowCount() {
		return songTitle.length;
	}
	
	@Override public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }
	
	@Override public Class<?> getColumnClass(int columnIndex) {
		return COLUMN_TYPES[columnIndex];
	}

	@Override public Object getValueAt(final int rowIndex, final int columnIndex) {
		
	
		switch (columnIndex) {
			case 0: return (rowIndex+1);
			case 1: return songTitle[rowIndex];
			//case 2: // fall through
			case 2: final JButton button = new JButton(this.buttonTitle);
			
				if(this.buttonTitle=="Buy"){
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							
							JFrame parent = new JFrame();

						    JOptionPane optionPane = new JOptionPane();
						    JSlider slider = JSliderOnJOptionPane.getSlider(optionPane);
						    slider.setMajorTickSpacing(1);
						    slider.setPaintTicks(true);
						    slider.addChangeListener(new ChangeListener() {
								
								@Override
								public void stateChanged(ChangeEvent e) {
									// TODO Auto-generated method stub
									int value=slider.getValue();
									String price ="Price : "+value+"$";
									optionPane.setMessage(new Object[] { songTitle[rowIndex],"How many time do u wants to play it?: ", slider,price});
								}
							});
						    optionPane.setMessage(new Object[] { songTitle[rowIndex],"How many time do u wants to play it?: ", slider, "Price : 50$" });
						    optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
						    optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
						    JDialog dialog = optionPane.createDialog(parent, "Buying");
						    dialog.setVisible(true);
						     
						   
						    System.out.println("Input: " + optionPane.getInputValue());
						    
						    BackendController bc = new BackendController();
						    try {
								bc.buySong("1",songId[rowIndex],optionPane.getInputValue().toString(),"2016-02-20 00:00:00","8686");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						    
//						    String url = "http://localhost:8888/DataSecurity/test.pdf";
//						    url = "http://khsong.com/mp3/khmer/SSB/SSB24/SSB-24-07-prachan.mp3";
//						    File destination = new File("/Users/duchdynil/Desktop/Mysong/prachan.mp3");
//						    try {
//								Downloader.downloadFileFromURL(url, destination);
//							} catch (Throwable e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
						}
					});
				}
				else if (this.buttonTitle=="Renew"){
					
				}
			
						
					return button;
			default: return "Error";

		}
		
	}	
	
	
	  
}


