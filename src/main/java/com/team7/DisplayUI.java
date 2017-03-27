package com.team7;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DisplayUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Set<String> saveAuthors = new HashSet<String>();

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DisplayUI(List<String> authors, final String userName) throws SQLException {
		
		setVisible(true);
		setTitle("SEARCH RESULTS");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, -22, 933, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 909, 73);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblSearchResults = new JLabel("SEARCH RESULTS");
		lblSearchResults.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblSearchResults.setBounds(380, 22, 189, 25);
		panel.add(lblSearchResults);
		
		JButton btnLogout = new JButton("LogOut");
		btnLogout.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnLogout.setBounds(746, 0, 117, 34);
		panel.add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose();
				 LoginUI log = new LoginUI();
				 log.setVisible(true);
				 log.setSize(950,600);
				 log.setLocationRelativeTo(null);
			}
		});
		
		JButton btnSearch = new JButton("Search UI");
		btnSearch.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnSearch.setBounds(625, 0, 117, 34);
		panel.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SearchUI search = new SearchUI(userName);
				search.setSize(950, 600);
				search.setLocationRelativeTo(null);				
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 91, 909, 373);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		JTable table = new JTable(buildTableModel(authors));
		
		JTableHeader header = table.getTableHeader();
		header.setDefaultRenderer(new HeaderRenderer(table));
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		
		
		table.getColumn("Save").setCellRenderer(new JTableButtonRenderer());
		table.getColumn("Save").setCellEditor(
				new ButtonEditor(new JCheckBox()));
		
		table.setPreferredScrollableViewportSize(new Dimension(550, 350));
		JScrollPane scroll = new JScrollPane(table);
//		getContentPane().add(scroll, FlowLayout.CENTER);
		setVisible(true);
		panel_1.add(scroll);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 477, 921, 55);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnSavedAuthors = new JButton("Saved Authors");
		btnSavedAuthors.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnSavedAuthors.setBounds(385, 0, 149, 34);
		panel_2.add(btnSavedAuthors);
		btnSavedAuthors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// List of authors who have been saved by the user
				saveAuthors = ButtonEditor.sendEmail;
				
				ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
				ResultSet result = null;
				try {
					// candidate details will give all the details of the authors present in 
					// the saved list
					result = searchDisplay.candidateDetails(saveAuthors);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				try {
					dispose();
					SavedAuthorsUI saved = new SavedAuthorsUI(result, userName);
					saved.setLocationRelativeTo(null);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}


	private TableModel buildTableModel(List<String> authors) throws SQLException {
		// TODO Auto-generated method stub
//		ResultSetMetaData metaData = rs.getMetaData();
		
		Vector<String> columnNames = new Vector<String>();
		
//	    int columnCount = metaData.getColumnCount();
//	   
		columnNames.add("Author Name");
	    columnNames.add("Save");
	    
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    
	    
	    for (String author: authors) {
	    	Vector<Object> vector = new Vector<Object>();
	    	
	    	for (int columnIndex= 1; columnIndex <=1; columnIndex++) {
	    		vector.add(author);
	    	}
	    	vector.addElement("save");
	    	data.addElement(vector);
	    }
	    
//	    for (String author: authors) {
//	    	vector.add(author);
//	    	data.addElement(vector);
//	    }	       
//	    for (int columnIndex = 1; columnIndex <= 2; columnIndex++) {
//	    	for (String author: authors) {
//		    	vector.add(author);
//		    }
//	    } 
//	    vector.add("save");
//	    data.add(vector);
	   
 
	    
	    return new DefaultTableModel(data, columnNames);
		
	}
}
