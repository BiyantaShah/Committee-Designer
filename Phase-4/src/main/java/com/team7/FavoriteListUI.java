package com.team7;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class FavoriteListUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton btnLogout;
	JButton btnSearch;
	JTable table;
	JButton btnRemove;
	JButton btnCandidatesList;

	public FavoriteListUI() {
		
		setVisible(true);
		setTitle("Favorite List");
		setSize(1400,900);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); 

		JPanel panel = new JPanel();
		panel.setBounds(0, 6, 1382, 101);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblSavedAuthors = new JLabel("My Favorite List");
		lblSavedAuthors.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblSavedAuthors.setBounds(604, 38, 197, 34);
		panel.add(lblSavedAuthors);

		btnSearch = new JButton("Search UI");
		btnSearch.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnSearch.setBounds(1124, 13, 117, 34);
		panel.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonEditor.savedAuthors.clear();
				dispose();
				SearchUI search = new SearchUI();
				search.setVisible(true);
				search.setSize(1400,900);
				search.setLocationRelativeTo(null);				
			}
		});

		btnLogout = new JButton("LogOut");
		btnLogout.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnLogout.setBounds(1253, 13, 117, 34);
		panel.add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ButtonEditor.savedAuthors.clear();
				// make the currentUser null and redirect to login page
				ImplementLogin login = new ImplementLogin();
				login.logout();
				dispose();
				LoginUI log = new LoginUI();
				log.setVisible(true);
				log.setSize(1400,900);
				log.setLocationRelativeTo(null);
			}
		});

		btnCandidatesList = new JButton("Candidates List");
		btnCandidatesList.setVisible(false);
		if(UIConstants.currentuserRole.equals("Program Chair")){
			btnCandidatesList.setVisible(true);
		}
		btnCandidatesList.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnCandidatesList.setBounds(59, 13, 179, 34);
		panel.add(btnCandidatesList);
		btnCandidatesList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CandidateListUI cl = new CandidateListUI();
					  dispose();
					  cl.setVisible(true);
					  cl.setSize(1400,900);
					  cl.setLocationRelativeTo(null);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 119, 1382, 330);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		try {
			ImplementSchemaDB db =  new ImplementSchemaDB();
			Connection conn = db.getConnection();

			PreparedStatement p = conn.prepareStatement("select selectedAuthor from Favorite_list where username='"+UIConstants.currentUser+"'" + "order by selectedAuthor");
			ResultSet rs = p.executeQuery();

			table = new JTable(buildTableModel(rs));
			JTableHeader header = table.getTableHeader();
			header.setDefaultRenderer(new HeaderRenderer(table));

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

			// Setting the values center aligned
			for (int i=0; i< table.getColumnModel().getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}


			// Not allowing the columns to be dragged
			table.getTableHeader().setReorderingAllowed(false);

			table.setPreferredScrollableViewportSize(new Dimension(400, 300));

			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			JScrollPane scroll = new JScrollPane(table);
			setVisible(true);

			panel_1.add(scroll);
			DefaultTableModel model = (DefaultTableModel) table.getModel();

			btnRemove = new JButton("Remove");
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					int index = table.getSelectedRow();
					if (index == -1) {
						LoginUI log = new LoginUI();
						log.messageShow("Please select an author to remove");
					}
					else{
						String author = (String) model.getValueAt(index, 0);
						ImplementSchemaDB db =  new ImplementSchemaDB();
						Connection conn;
						try {
							conn = db.getConnection();
							PreparedStatement stmt = conn.prepareStatement("Delete from Favorite_list where selectedAuthor=?");								
							stmt.setString(1,author);							
							stmt.executeUpdate();		
							model.removeRow(index);; 
						} catch (IOException | SQLException e) {
							e.printStackTrace();
						}
					}
				}
			});
			btnRemove.setBounds(643, 486, 97, 30);
			contentPane.add(btnRemove);
		} catch (SQLException | IOException e2) {
			
		}
	}


	public TableModel buildTableModel(ResultSet rs) throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();

		Vector<String> columnNames = new Vector<String>();

		final int columnCount = metaData.getColumnCount();

		// To understand what each column name means in the UI
		for (int column = 1; column <= columnCount; column++) {

			if (metaData.getColumnName(column).equals("selectedAuthor"))			
				columnNames.add("Author Name");			
		}

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));     
			}
			data.add(vector);
		}


		TableModel model = new DefaultTableModel(data, columnNames) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
			{
				//This causes all cells except of the last column
				// to be not editable
				return false; 
			}

		};

		return model;

	}

}
