package com.team7;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class CandidateListUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	JTable table1;
	JTable table2;
	JPanel panel_3 = new JPanel();



	public CandidateListUI() throws IOException {

		setTitle("CANDIDATE LIST OF THE PROGRAM CHAIR");
		setResizable(false);
		setSize(UIConstants.width,UIConstants.height);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		DefaultTableModel model2 ;

		
		JPanel panel = new JPanel();
		panel.setBounds(6, 18, 1188, 113);
		contentPane.add(panel);
		panel.setLayout(null);


		JLabel lblCommittee = new JLabel("Committee List");
		lblCommittee.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblCommittee.setBounds(521, 18, 134, 22);
		panel.add(lblCommittee);


		JLabel lblSelectedCandidates = new JLabel("Selected Candidates");
		lblSelectedCandidates.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblSelectedCandidates.setBounds(182, 91, 176, 16);
		panel.add(lblSelectedCandidates);


		JLabel lblFinalCandidates = new JLabel("Final Candidates");
		lblFinalCandidates.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblFinalCandidates.setBounds(797, 91, 141, 16);
		panel.add(lblFinalCandidates);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnLogout.setBounds(1065, 6, 117, 34);
		panel.add(btnLogout);		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// make the currentUser null and redirect to login page
				ImplementLogin login = new ImplementLogin();
				login.logout();
				dispose();
				LoginUI log = new LoginUI();
				log.setVisible(true);
				setSize(UIConstants.width,UIConstants.height);
				log.setLocationRelativeTo(null);
			}
		});

		
		JButton btnSearchui = new JButton("SearchUI");
		btnSearchui.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnSearchui.setBounds(932, 6, 117, 34);
		panel.add(btnSearchui);
		btnSearchui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SearchUI search = new SearchUI();
				search.setVisible(true);
				setSize(UIConstants.width,UIConstants.height);			
				search.setLocationRelativeTo(null);				
			}
		});
		
		JButton btnMyFavoriteList = new JButton("My Favorite List");
		btnMyFavoriteList.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnMyFavoriteList.setBounds(17, 6, 176, 34);
		panel.add(btnMyFavoriteList);
		btnMyFavoriteList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FavoriteListUI fl = new FavoriteListUI();
				dispose();
				fl.setVisible(true);
				fl.setSize(UIConstants.width,UIConstants.height);
				fl.setLocationRelativeTo(null);
			}
		});



		// first table

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(16, 143, 578, 465);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		ImplementSchemaDB db =  new ImplementSchemaDB();
		Connection conn = db.getConnection();
		PreparedStatement p;
		ResultSet rs;

		try {

			p = conn.prepareStatement("select selectedAuthor from Favorite_list order by selectedAuthor");
			rs = p.executeQuery();

			table1 = new JTable(buildTableModel(rs));
			JTableHeader header1 = table1.getTableHeader();
			header1.setDefaultRenderer(new HeaderRenderer(table1));

			DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
			centerRenderer1.setHorizontalAlignment(SwingConstants.CENTER);

			// Setting the values center aligned
			for (int i=0; i< table1.getColumnModel().getColumnCount(); i++) {
				table1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer1);
			}


			table1.getTableHeader().setReorderingAllowed(false);
			table1.setPreferredScrollableViewportSize(new Dimension(550, 430));
			table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			JScrollPane scroll1 = new JScrollPane(table1);
			setVisible(true);
			panel_1.add(scroll1);

			DefaultTableModel model = (DefaultTableModel) table1.getModel();

			JButton button = new JButton("Select");
			button.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			button.setBounds(216, 5, 97, 30);
			panel_3.add(button);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					DefaultTableModel model2 = (DefaultTableModel) table2.getModel();

					//System.out.println(table1.getSelectedRows()[0]);
					int index = table1.getSelectedRow();
					if (index == -1) {
						LoginUI log = new LoginUI();
						log.messageShow("Please select an author");
					}
					else{
						String author = (String) model.getValueAt(index, 0);
						ImplementSchemaDB db =  new ImplementSchemaDB();
						Connection conn;
						try {
							conn = db.getConnection();
							PreparedStatement stmt = conn.prepareStatement("insert into Candidate_list(selectedAuthor) values(?)");								
							stmt.setString(1,author);							
							stmt.executeUpdate();	
							Object rows[] = new Object[1];
							rows[0] = model.getValueAt(index, 0); 
							model2.addRow(rows);
						} catch (IOException | SQLException e1) {
							LoginUI log = new LoginUI();
							log.messageShow("Author already included in the list");
						}
					}
				}
			});
		}catch (SQLException e2) {
			

		}

		// second table

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(606, 143, 578, 465);
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		try {
			p = conn.prepareStatement("select selectedAuthor from Candidate_list");
			rs = p.executeQuery();

			table2 = new JTable(buildTableModel(rs));
			JTableHeader header2 = table2.getTableHeader();
			header2.setDefaultRenderer(new HeaderRenderer(table2));

			DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
			centerRenderer2.setHorizontalAlignment(SwingConstants.CENTER);

			// Setting the values center aligned
			for (int i=0; i< table2.getColumnModel().getColumnCount(); i++) {
				table2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer2);
			}


			table2.getTableHeader().setReorderingAllowed(false);
			table2.setPreferredScrollableViewportSize(new Dimension(550, 430));
			table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			JScrollPane scroll2 = new JScrollPane(table2);
			setVisible(true);
			panel_2.add(scroll2);

			model2 = (DefaultTableModel) table2.getModel();


			panel_3.setBounds(6, 612, 1188, 85);
			contentPane.add(panel_3);
			panel_3.setLayout(null);


			JButton btnSendEmail = new JButton("Send Email");
			btnSendEmail.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			btnSendEmail.setBounds(539, 50, 117, 29);
			panel_3.add(btnSendEmail);
			Set<String> sendMail = new HashSet<String>();

			btnSendEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int count = 0; count < model2.getRowCount(); count++){
					  sendMail.add(model2.getValueAt(count, 0).toString());
					}

				Boolean flag = true;

				if (sendMail.size() == 0) {
					LoginUI log = new LoginUI();
					log.messageShow("Please select some authors for your committee");
					flag = false;
				}
				
				ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
				if (flag == true) {
					try {
						String res = searchDisplay.sendEmail(sendMail, UIConstants.currentUser);
                        if(res == "success"){
        					LoginUI log = new LoginUI();
        					log.messageShow("Email sent successfully");
                        }
					}  catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						
					}
				}
			}
		});




			JButton btnRemove = new JButton("Remove");
			btnRemove.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			btnRemove.setBounds(855, 6, 117, 29);
			panel_3.add(btnRemove);
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					int index = table2.getSelectedRow();
					if (index == -1) {
						LoginUI log = new LoginUI();
						log.messageShow("Please select an author to remove");
					}
					else{
						String author = (String) model2.getValueAt(index, 0);
						ImplementSchemaDB db =  new ImplementSchemaDB();
						Connection conn;
						try {
							conn = db.getConnection();
							PreparedStatement stmt = conn.prepareStatement("Delete from Candidate_list where selectedAuthor=?");								
							stmt.setString(1,author);							
							stmt.executeUpdate();		
							model2.removeRow(index);; 
						} catch (IOException | SQLException e) {
							e.printStackTrace();
						}
					}
				}
			});

		}catch (SQLException e2) {

		}

	}


	private TableModel buildTableModel(ResultSet rs) throws SQLException {

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