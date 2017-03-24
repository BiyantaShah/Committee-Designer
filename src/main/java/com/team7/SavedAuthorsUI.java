package com.team7;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;

public class SavedAuthorsUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	Set<String> sendEmail = new HashSet<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SavedAuthorsUI frame = new SavedAuthorsUI();
					frame.setVisible(true);
					frame.setTitle("SAVED AUTHORS");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SavedAuthorsUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, -22, 933, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		JLabel lblSavedAuthors = new JLabel("SAVED AUTHORS");
		lblSavedAuthors.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblSavedAuthors.setBounds(367, 43, 206, 24);
		panel_1.add(lblSavedAuthors);
		
		JLabel lblToSelectAuthors = new JLabel("To select authors for the committee, click \"select\" beside the row");
		lblToSelectAuthors.setBounds(249, 97, 471, 16);
		panel_1.add(lblToSelectAuthors);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		DefaultTableModel dm = new DefaultTableModel();
		dm.setDataVector(new Object[][] { {  "foo", "foo details", "select" },
			{ "bar", "bar details", "select" } }, new Object[] { "Author", "Author Details", "Select" });
		
		table = new JTable(dm);
		
		JTableHeader header = table.getTableHeader();
		header.setDefaultRenderer(new HeaderRenderer(table));
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		
		
		table.getColumn("Select").setCellRenderer(new JTableButtonRenderer());
		table.getColumn("Select").setCellEditor(
				new ButtonEditor(new JCheckBox()));
		
		table.setPreferredScrollableViewportSize(new Dimension(350, 200));
		JScrollPane scroll = new JScrollPane(table);
//		getContentPane().add(scroll, FlowLayout.CENTER);
		setVisible(true);
		
		panel_2.add(scroll);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnSendEmail = new JButton("Send Email");
		btnSendEmail.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnSendEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendEmail = ButtonEditor.sendEmail;
				for (String s : sendEmail) {
					System.out.println("Values "+ s);
				}
				
				// searchDisplay.sendEmail(List<String>), 
				// in the function, it will extract all the users from the currentUser whose conferences match and send this list to all of them	
			}
		});
		btnSendEmail.setBounds(395, 65, 117, 29);
		panel.add(btnSendEmail);
		
	}
}
