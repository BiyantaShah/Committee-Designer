package com.team7;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class SavedAuthorsUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Set<String> sendMail = new HashSet<String>();

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public SavedAuthorsUI(ResultSet result, final String userName) throws SQLException {
		
		setVisible(true);
		setTitle("SAVED AUTHORS");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, -22, 933, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 921, 101);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblSavedAuthors = new JLabel("Save Authors");
		lblSavedAuthors.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblSavedAuthors.setBounds(345, 17, 206, 16);
		panel.add(lblSavedAuthors);
		
		JLabel lblNewLabel = new JLabel("To select authors for the committee, click \"select\" beside the row");
		lblNewLabel.setBounds(242, 68, 433, 16);
		panel.add(lblNewLabel);
		
		JButton btnSearch = new JButton("Search UI");
		btnSearch.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnSearch.setBounds(620, 0, 117, 34);
		panel.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SearchUI search = new SearchUI(userName);
				search.setSize(950, 600);
				search.setLocationRelativeTo(null);				
			}
		});
		
		JButton btnNewButton = new JButton("LogOut");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 dispose();
				 LoginUI log = new LoginUI();
				 log.setVisible(true);
				 log.setSize(950,600);
				 log.setLocationRelativeTo(null);
			}
		});
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnNewButton.setBounds(749, 0, 117, 34);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 119, 921, 357);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JTable table = new JTable(buildTableModel(result));
		
		JTableHeader header = table.getTableHeader();
		header.setDefaultRenderer(new HeaderRenderer(table));
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		
		
		table.getColumn("Select").setCellRenderer(new JTableButtonRenderer());
		table.getColumn("Select").setCellEditor(
				new ButtonEditor(new JCheckBox()));
		
		table.setPreferredScrollableViewportSize(new Dimension(550, 350));
		JScrollPane scroll = new JScrollPane(table);
//		getContentPane().add(scroll, FlowLayout.CENTER);
		setVisible(true);
		
		panel_1.add(scroll);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 488, 921, 63);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnSendEmail = new JButton("Send Email");
		btnSendEmail.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnSendEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMail = ButtonEditor.sendEmail;
				
				ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
				try {
					searchDisplay.sendEmail(sendMail, userName);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSendEmail.setBounds(383, 13, 155, 29);
		panel_2.add(btnSendEmail);
		
		
	}

	public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		
		ResultSetMetaData metaData = rs.getMetaData();
		
		Vector<String> columnNames = new Vector<String>();
		
	    int columnCount = metaData.getColumnCount();
	    
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }
	    columnNames.add("Select");
	    
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));     
	        }
	        vector.add("select");
	        data.add(vector);
	    }
	    
	    return new DefaultTableModel(data, columnNames);
	}
}
