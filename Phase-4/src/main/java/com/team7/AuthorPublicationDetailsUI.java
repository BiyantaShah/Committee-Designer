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

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.awt.event.ActionEvent;

// Class which displays the publication details of the author (Paper and articles , along with URL)
public class AuthorPublicationDetailsUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Set<String> sendMail = new HashSet<String>();
	JButton btnNewButton;
	JButton btnSearch;
	JButton btnSendEmail;
	JButton btnCandidatesList;
	JButton btnFav;

	/**
	 * Create the frame.
	 * @throws SQLException  
	 */
	public AuthorPublicationDetailsUI(ResultSet result) {

		setVisible(true);
		setTitle("AUTHOR PUBLICATION DETAILS");
		setResizable(false);

		setSize(UIConstants.width, UIConstants.height);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); 

		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 1909, 173);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblSavedAuthors = new JLabel("Author Publication Details");
		lblSavedAuthors.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblSavedAuthors.setBounds(444, 81, 274, 28);
		panel.add(lblSavedAuthors);

		JLabel lblNewLabel = new JLabel("To select authors for the committee, click \"select\" beside the row");
		lblNewLabel.setBounds(381, 133, 433, 16);
		panel.add(lblNewLabel);

		btnSearch = new JButton("Search UI");
		btnSearch.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnSearch.setBounds(930, 10, 117, 34);
		panel.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonEditor.savedAuthors.clear();
				dispose();
				SearchUI search = new SearchUI();
				search.setVisible(true);

				setSize(UIConstants.width, UIConstants.height);
				
				search.setLocationRelativeTo(null);				
			}
		});

		btnNewButton = new JButton("LogOut");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ButtonEditor.savedAuthors.clear();
				// make the currentUser null and redirect to login page
				ImplementLogin login = new ImplementLogin();
				login.logout();
				dispose();
				LoginUI log = new LoginUI();
				log.setVisible(true);

				setSize(UIConstants.width, UIConstants.height);
				
				log.setLocationRelativeTo(null);
			}
		});
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnNewButton.setBounds(1059, 10, 117, 34);
		panel.add(btnNewButton);
		
		btnFav = new JButton("My Favorite List");
		btnFav.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnFav.setBounds(31, 10, 176, 34);
		panel.add(btnFav);
		
		btnFav.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImplementSearchDisplay search = new ImplementSearchDisplay();
				Set<String> favList;
				try {
					favList = search.favAuthors("userName", UIConstants.currentUser);
					FavoriteListUI fl = new FavoriteListUI(favList);
					dispose();
					fl.setVisible(true);
					fl.setSize(UIConstants.width, UIConstants.height);
					fl.setLocationRelativeTo(null);
					
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		
		btnCandidatesList = new JButton("Candidates List");
		btnCandidatesList.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnCandidatesList.setVisible(false);
		if(UIConstants.currentUserRole.equals(UIConstants.HighestRole)){
			btnCandidatesList.setVisible(true);
		}
		btnCandidatesList.setBounds(219, 10, 148, 34);
		panel.add(btnCandidatesList);
		btnCandidatesList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CandidateListUI cl = new CandidateListUI();
					  dispose();
					  cl.setVisible(true);
					  cl.setSize(UIConstants.width, UIConstants.height);
					  cl.setLocationRelativeTo(null);

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 191, 1188, 431);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JTable table;
		try {
			table = new JTable(buildTableModel(result));
			JTableHeader header = table.getTableHeader();
			header.setDefaultRenderer(new HeaderRenderer(table));

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			
			// Setting the values center aligned
			for (int i=0; i< table.getColumnModel().getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
			
			// Rendering a button for each table row
			table.getColumn("Select").setCellRenderer(new JTableButtonRenderer());
			table.getColumn("Select").setCellEditor(
					new AddToFavoriteList(new JCheckBox()));
			
			// Not allowing the columns to be dragged
			table.getTableHeader().setReorderingAllowed(false);

			table.setPreferredScrollableViewportSize(new Dimension(850, 400));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scroll = new JScrollPane(table);
			setVisible(true);

			panel_1.add(scroll);
		} catch (SQLException e2) {
		}

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 648, 1388, 47);
		contentPane.add(panel_2);
		panel_2.setLayout(null);


	}

	public TableModel buildTableModel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub

		ResultSetMetaData metaData = rs.getMetaData();

		Vector<String> columnNames = new Vector<String>();

		final int columnCount = metaData.getColumnCount();

		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
			
		}
		columnNames.add("Select");

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				if (rs.getObject(columnIndex) == null) {
					vector.add("----");
				}
				else {
					vector.add(rs.getObject(columnIndex));    
				}
				 
			}
			vector.add("select");
			data.add(vector);
		}
		
		TableModel model = new DefaultTableModel(data, columnNames) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
		    {
			  //This causes all cells except of the last column
			  // to be not editable
		      return column == 4; 
		    }
		};

		return model;

	}
}
