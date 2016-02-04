import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

import javax.swing.JMenuBar;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.List;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableModel;

public class Main extends JFrame  {

	private JPanel contentPane;
	
	private JTable tableExpire;

	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		BackendController bc = new BackendController();
		bc.login("nethphan@gmail.com", "123456");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1000, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
				
		//contentPane.add(table, BorderLayout.WEST);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane}));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.NORTH);
		
		Buy b = new Buy();	
		Expire e = new Expire();
       
		tabbedPane.addTab("Buying", null, b.designTableBuy(), null);
		tabbedPane.addTab("Expire", null, e.designTableExpire(), null);

		
		
//		if(tabbedPane.getSelectedIndex()==0){
//			tabbedPane.addTab("Buying", null, b.designTableBuy(), null);
//
//		}
//		else if(tabbedPane.getSelectedIndex()==1){
//			tabbedPane.addTab("Expire", null, e.designTableExpire(), null);
//		}
//		else{
//			tabbedPane.addTab("Buying", null, b.designTableBuy(), null);
//			tabbedPane.addTab("Expire", null, e.designTableExpire(), null);
//
//		}
		
		
//		tableExpire = new JTable(new JTableModel()); 
//		tableExpire.setFillsViewportHeight(true);	
//		tableExpire.setRowHeight(30); // set all rows height
//		tableExpire.getColumnModel().getColumn(1).setPreferredWidth(500);//set column 1 width
//		TableCellRenderer buttonRendererExpire = new JTableButtonRenderer();
//		tableExpire.getColumn("Expire").setCellRenderer(buttonRendererExpire);
//		//table.getColumn("Button2").setCellRenderer(buttonRenderer);
//		tableExpire.addMouseListener(new JTableButtonMouseListener(tableExpire));
//		JScrollPane scrollExpire = new JScrollPane(tableExpire, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		tabbedPane.addTab("Expire", null, scrollExpire, null);
		
		
	}

}




