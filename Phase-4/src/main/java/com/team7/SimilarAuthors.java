package com.team7;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.util.Set;
import java.util.Vector;

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

public class SimilarAuthors extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTable table;

	/**
	 * Create the frame.
	 */
	public SimilarAuthors(Set<String> similarAuthors) {
		
		setSize(700,450);
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); 
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 6, 700, 103);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblSavedAuthors = new JLabel("Similar Authors");
		lblSavedAuthors.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblSavedAuthors.setBounds(267, 38, 172, 28);
		panel.add(lblSavedAuthors);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 121, 700, 284);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		try {
			table = new JTable(buildTableModel(similarAuthors));
			JTableHeader header = table.getTableHeader();
			header.setDefaultRenderer(new HeaderRenderer(table));

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

			// Setting the values center aligned
			for (int i=0; i< table.getColumnModel().getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
			table.setPreferredScrollableViewportSize(new Dimension(350, 230));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scroll = new JScrollPane(table);
			setVisible(true);
			panel_1.add(scroll);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private TableModel buildTableModel(Set<String> similarAuthors) throws SQLException {
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Author Name");		


		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		
		for (String author: similarAuthors) {
			Vector<Object> vector = new Vector<Object>();
			
			for (int columnIndex= 1; columnIndex <=1; columnIndex++) {
				vector.add(author);
			}
			data.add(vector);
		}

		TableModel model = new DefaultTableModel(data, columnNames) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
			{
				//This causes all cells to be not editable
				return false; 
			}

		};

		return model;
	}

}
