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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
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
	public DisplayUI(final String userName) throws SQLException {
		
		setVisible(true);
		setTitle("SEARCH RESULTS");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, -22, 933, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 921, 73);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblSearchResults = new JLabel("SEARCH RESULTS");
		lblSearchResults.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblSearchResults.setBounds(380, 22, 189, 25);
		panel.add(lblSearchResults);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 91, 921, 383);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery
				("select a.name, p.title from paper p, author a where "
						+ "p.paperKey=a.paperKey limit 10");
		
		JTable table = new JTable(buildTableModel(rs));
		
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
		panel_2.setBounds(6, 491, 921, 60);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnSavedAuthors = new JButton("Saved Authors");
		btnSavedAuthors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAuthors = ButtonEditor.sendEmail;
				for (String s : saveAuthors) {
					System.out.println("Values "+ s);
				}
				
				// Save author returns a result set containing saved authors and their details
				// this will be sent to Saved Authors UI
				ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
				searchDisplay.saveAuthor(saveAuthors);
				
				try {
					dispose();
					SavedAuthorsUI saved = new SavedAuthorsUI(userName);
					saved.setLocationRelativeTo(null);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSavedAuthors.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnSavedAuthors.setBounds(398, 6, 149, 29);
		panel_2.add(btnSavedAuthors);
	}

	private TableModel buildTableModel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		ResultSetMetaData metaData = rs.getMetaData();
		
		Vector<String> columnNames = new Vector<String>();
		
	    int columnCount = metaData.getColumnCount();
	    
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }
	    columnNames.add("Save");
	    
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));     
	        }
	        vector.add("save");
	        data.add(vector);
	    }
	    
	    return new DefaultTableModel(data, columnNames);
		
	}
}
