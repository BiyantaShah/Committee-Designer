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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

// Search display results page is created here.
public class DisplayUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	// List of Authors whose candidate details are to be viewed
 	Set<String> saveAuthors = new HashSet<String>();
 	
	JButton btnSavedAuthors;
	JButton btnLogout;
	JButton btnSearch; 
	JButton btnSimilarAuthors;
	JTable table;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DisplayUI(List<String> authors) {

		setTitle("SEARCH RESULTS");
		setResizable(false);

		setSize(1400,900);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 6, 1394, 103);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblSearchResults = new JLabel("SEARCH RESULTS");
		lblSearchResults.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblSearchResults.setBounds(559, 62, 234, 28);
		panel.add(lblSearchResults);

		btnLogout = new JButton("LogOut");
		btnLogout.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnLogout.setBounds(1188, 10, 117, 34);
		panel.add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonEditor.savedAuthors.clear();
				// make the currentUser null and redirect to login page
				ImplementLogin login = new ImplementLogin();
				login.logout();

				dispose();
				LoginUI log = new LoginUI();
				log.setVisible(true);
				setSize(1400,900);
				
				log.setLocationRelativeTo(null);
			}
		});
		
		JButton btnNewButton = new JButton("My Favorite List");
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnNewButton.setBounds(92, 10, 168, 34);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FavoriteListUI fl;
					fl = new FavoriteListUI();
					dispose();
					fl.setVisible(true);
					fl.setSize(1400,900);
					fl.setLocationRelativeTo(null);

			}
		});

		JButton btnCandidatesList = new JButton("Candidates List");
		btnCandidatesList.setVisible(false);
		if(UIConstants.currentuserRole.equals(UIConstants.HighestRole)){
			btnCandidatesList.setVisible(true);
		}
		btnCandidatesList.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnCandidatesList.setBounds(272, 10, 129, 34);
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


		btnSearch = new JButton("Search UI");
		btnSearch.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnSearch.setBounds(1059, 10, 117, 34);
		panel.add(btnSearch);
				
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonEditor.savedAuthors.clear();
				dispose();
				SearchUI search = new SearchUI();
				search.setVisible(true);

				setSize(1400,900);
				
				search.setLocationRelativeTo(null);				
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 150, 1394, 442);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));


		
		try {
			table = new JTable(buildTableModel(authors));
			JTableHeader header = table.getTableHeader();
			header.setDefaultRenderer(new HeaderRenderer(table));

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			
			// Setting the values in the cells center aligned
			for (int i=0; i< table.getColumnModel().getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}

			// Rendering a button for each table row
			table.getColumn("Save").setCellRenderer(new JTableButtonRenderer());
			table.getColumn("Save").setCellEditor(
					new ButtonEditor(new JCheckBox()));
			
			// Not allowing the columns to be dragged
			table.getTableHeader().setReorderingAllowed(false);
			
			table.setPreferredScrollableViewportSize(new Dimension(550, 430));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scroll = new JScrollPane(table);
			setVisible(true);
			panel_1.add(scroll);
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
		}

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(247, 605, 921, 55);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		btnSimilarAuthors = new JButton("Similar Authors");
		btnSimilarAuthors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int index = table.getSelectedRow();
				String author = (String) model.getValueAt(index, 0);

				try {
					ImplementSearchDisplay search = new ImplementSearchDisplay();
					Set<String> similarAuth = search.similarAuthor(author);
					
					SimilarAuthors sa = new SimilarAuthors(similarAuth);
					
					sa.setVisible(true);

					setSize(1400,900);
					similarAuth.clear();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				
				
				
							
				
			}
		});
		btnSimilarAuthors.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnSimilarAuthors.setBounds(218, 6, 171, 29);
		panel_2.add(btnSimilarAuthors);

		btnSavedAuthors = new JButton("Candidate Details");

//		btnSavedAuthors.setFont(new Font("Lucida Grande", Font.BOLD, 16));
//		btnSavedAuthors.setBounds(413, 13, 149, 34);
//		panel_2.add(btnSavedAuthors);

		btnSavedAuthors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// List of authors who have been saved by the user
				saveAuthors = ButtonEditor.savedAuthors;

				boolean flag = true;
  
				if (saveAuthors.size() == 0) {
					LoginUI log = new LoginUI();
					log.messageShow("Please select some authors to view details");
					flag = false;
				} 
				ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
				ResultSet result = null;
				if (flag == true) {		
					// candidate details will give all the details of the authors present in 
					// the saved list
					try {
						result = searchDisplay.candidateDetails(saveAuthors);

						// clearing the list once its displayed.
						saveAuthors.clear();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}

					dispose();
					SavedAuthorsUI saved = new SavedAuthorsUI(result);
					saved.setVisible(true);
					setSize(1400,900);
					saved.setLocationRelativeTo(null);

				}

			}
		});

		btnSavedAuthors.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnSavedAuthors.setBounds(518, 6, 171, 29);
		panel_2.add(btnSavedAuthors);
	}

	// rendering the data obtained from the query engine into a tabular format 
	private TableModel buildTableModel(List<String> authors) throws SQLException {

		Vector<String> columnNames = new Vector<String>();

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
		
		TableModel model = new DefaultTableModel(data, columnNames) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
		    {
			  //This causes all cells in column 0 to be not editable
		      return column == 1; 
		    }
		};

		return model;		
	}
}
