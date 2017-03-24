package com.team7;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SearchUI extends JFrame {

	private JPanel contentPane;
	private JTextField ValueField_1;
	private JTextField ValueField_2;
	private JTextField ValueField_3;
	private JTextField ValueField_4;
	private JTextField ValueField_5;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchUI frame = new SearchUI();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SearchUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 951, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JPanel panel_2 = new JPanel();
        panel_2.setVisible(false);
        
		final JPanel panel_3 = new JPanel();
        panel_3.setVisible(false);

		final JPanel panel_4 = new JPanel();
        panel_4.setVisible(false);

		final JPanel panel_5 = new JPanel();
        panel_5.setVisible(false);

		String[] CriteriaList = {"AuthorName" , "ConfName", "CountNoOfPapers", "Keyword", "Year"};
		String[] JoinList = {"AND","OR"};
		

		final JComboBox ComparatorComboBox_1 = new JComboBox();
		final JComboBox ComparatorComboBox_2 = new JComboBox();
		final JComboBox ComparatorComboBox_3 = new JComboBox();
		final JComboBox ComparatorComboBox_4 = new JComboBox();
		final JComboBox ComparatorComboBox_5 = new JComboBox();


/****************************************************** Panel -1 *******************************************/		
       
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 57, 933, 41);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		JLabel CriteriaLabel_1 = new JLabel("Criteria");
		CriteriaLabel_1.setBounds(12, 13, 56, 16);
		panel_1.add(CriteriaLabel_1);
		
		
		final JComboBox CriteriaComboBox_1 = new JComboBox(CriteriaList);
		CriteriaComboBox_1.setBounds(67, 10, 107, 22);
		panel_1.add(CriteriaComboBox_1);
		
		CriteriaComboBox_1.setSelectedItem(CriteriaList[0]);
		ComparatorComboBox_1.addItem("=");
		ComparatorComboBox_1.addItem("!=");
		
		CriteriaComboBox_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				ComparatorComboBox_1.removeAllItems();				
				if(CriteriaComboBox_1.getSelectedItem() == "Keyword"){
					
					ComparatorComboBox_1.addItem("LIKE");
					ComparatorComboBox_1.addItem("NOT LIKE");

					 
				} else if(CriteriaComboBox_1.getSelectedItem() == "ConfName" || CriteriaComboBox_1.getSelectedItem() == "AuthorName"){
					
					ComparatorComboBox_1.addItem("=");	
					ComparatorComboBox_1.addItem("!=");
					
				}else if(CriteriaComboBox_1.getSelectedItem() == "Year" || CriteriaComboBox_1.getSelectedItem() == "CountNoOfPapers"){
					
					ComparatorComboBox_1.addItem(">");
					ComparatorComboBox_1.addItem("<");
					ComparatorComboBox_1.addItem("=");
					ComparatorComboBox_1.addItem(">=");
					ComparatorComboBox_1.addItem("<=");
					ComparatorComboBox_1.addItem("!=");
					
				}				
		    }
		});
		
		JLabel ComparatorLabel_1 = new JLabel("Comparator");
		ComparatorLabel_1.setBounds(186, 13, 79, 16);
		panel_1.add(ComparatorLabel_1);
		
			
		ComparatorComboBox_1.setBounds(259, 10, 107, 22);
		panel_1.add(ComparatorComboBox_1);
		
		JLabel ValueLabel_1 = new JLabel("Value");
		ValueLabel_1.setBounds(380, 13, 56, 16);
		panel_1.add(ValueLabel_1);
		
		ValueField_1 = new JTextField();
		ValueField_1.setBounds(417, 10, 116, 22);
		panel_1.add(ValueField_1);
		ValueField_1.setColumns(10);
		
		JButton SaveButton_1 = new JButton("Save");
		SaveButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		SaveButton_1.setBounds(545, 9, 86, 25);
		panel_1.add(SaveButton_1);
		
		JButton NewRowButton_1 = new JButton("New row");
		NewRowButton_1.setBounds(635, 9, 86, 25);
		panel_1.add(NewRowButton_1);
		NewRowButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_2.setVisible(true);
			}
		});
		
		JLabel JoinLabel_1 = new JLabel("Join Condition");
		JoinLabel_1.setBounds(733, 13, 86, 19);
		panel_1.add(JoinLabel_1);
		
		JComboBox JoinComboBox_1 = new JComboBox(JoinList);
		JoinComboBox_1.setBounds(826, 12, 95, 19);
		panel_1.add(JoinComboBox_1);
		
		
/****************************************************** Panel -2 ********************************************/		
		panel_2.setLayout(null);
		panel_2.setBounds(0, 97, 933, 41);
		contentPane.add(panel_2);
		
		JLabel CriteriaLabel_2 = new JLabel("Criteria");
		CriteriaLabel_2.setBounds(12, 13, 56, 16);
		panel_2.add(CriteriaLabel_2);
		
		final JComboBox CriteriaComboBox_2 = new JComboBox(CriteriaList);
		CriteriaComboBox_2.setBounds(67, 10, 107, 22);
		panel_2.add(CriteriaComboBox_2);
		
		JLabel ComparatorLabel_2 = new JLabel("Comparator");
		ComparatorLabel_2.setBounds(186, 13, 79, 16);
		panel_2.add(ComparatorLabel_2);
		

		
		ComparatorComboBox_2.setBounds(259, 10, 107, 22);
		panel_2.add(ComparatorComboBox_2);
		
		CriteriaComboBox_2.setSelectedItem(CriteriaList[0]);
		ComparatorComboBox_2.addItem("=");
		ComparatorComboBox_2.addItem("!=");
		
		CriteriaComboBox_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				ComparatorComboBox_2.removeAllItems();				
				if(CriteriaComboBox_2.getSelectedItem() == "Keyword"){
					
					ComparatorComboBox_2.addItem("LIKE");
					ComparatorComboBox_2.addItem("NOT LIKE");

					 
				} else if(CriteriaComboBox_2.getSelectedItem() == "ConfName" || CriteriaComboBox_2.getSelectedItem() == "AuthorName"){
					
					ComparatorComboBox_2.addItem("=");	
					ComparatorComboBox_2.addItem("!=");
					
				}else if(CriteriaComboBox_2.getSelectedItem() == "Year" || CriteriaComboBox_2.getSelectedItem() == "CountNoOfPapers"){
					
					ComparatorComboBox_2.addItem(">");
					ComparatorComboBox_2.addItem("<");
					ComparatorComboBox_2.addItem("=");
					ComparatorComboBox_2.addItem(">=");
					ComparatorComboBox_2.addItem("<=");
					ComparatorComboBox_2.addItem("!=");
					
				}				
		    }
		});

		
		JLabel ValueLabel_2 = new JLabel("Value");
		ValueLabel_2.setBounds(380, 13, 56, 16);
		panel_2.add(ValueLabel_2);
		
		ValueField_2 = new JTextField();
		ValueField_2.setColumns(10);
		ValueField_2.setBounds(417, 10, 116, 22);
		panel_2.add(ValueField_2);
		
		JButton SaveButton_2 = new JButton("Save");
		SaveButton_2.setBounds(545, 9, 86, 25);
		panel_2.add(SaveButton_2);
		
		JButton NewRowButton_2 = new JButton("New row");
		NewRowButton_2.setBounds(635, 9, 86, 25);
		panel_2.add(NewRowButton_2);
		NewRowButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_3.setVisible(true);
			}
		});
		
		JLabel JoinLabel_2 = new JLabel("Join Condition");
		JoinLabel_2.setBounds(733, 13, 86, 19);
		panel_2.add(JoinLabel_2);

		JComboBox JoinComboBox_2 = new JComboBox(JoinList);
		JoinComboBox_2.setBounds(826, 12, 95, 19);
		panel_2.add(JoinComboBox_2);
		
/************************************ Panel -3 ******************************************************/
		
		panel_3.setLayout(null);
		panel_3.setBounds(0, 137, 933, 41);
		contentPane.add(panel_3);
		
		JLabel CriteriaLabel_3 = new JLabel("Criteria");
		CriteriaLabel_3.setBounds(12, 13, 56, 16);
		panel_3.add(CriteriaLabel_3);
		
		final JComboBox CriteriaComboBox_3 = new JComboBox(CriteriaList);
		CriteriaComboBox_3.setBounds(67, 10, 107, 22);
		panel_3.add(CriteriaComboBox_3);
		
		JLabel ComparatorLabel_3 = new JLabel("Comparator");
		ComparatorLabel_3.setBounds(186, 13, 79, 16);
		panel_3.add(ComparatorLabel_3);
				
		ComparatorComboBox_3.setBounds(259, 10, 107, 22);
		panel_3.add(ComparatorComboBox_3);
		CriteriaComboBox_3.setSelectedItem(CriteriaList[0]);
		ComparatorComboBox_3.addItem("=");
		ComparatorComboBox_3.addItem("!=");
		
		CriteriaComboBox_3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				ComparatorComboBox_3.removeAllItems();				
				if(CriteriaComboBox_3.getSelectedItem() == "Keyword"){
					
					ComparatorComboBox_3.addItem("LIKE");
					ComparatorComboBox_3.addItem("NOT LIKE");

					 
				} else if(CriteriaComboBox_3.getSelectedItem() == "ConfName" || CriteriaComboBox_3.getSelectedItem() == "AuthorName"){
					
					ComparatorComboBox_3.addItem("=");	
					ComparatorComboBox_3.addItem("!=");
					
				}else if(CriteriaComboBox_3.getSelectedItem() == "Year" || CriteriaComboBox_3.getSelectedItem() == "CountNoOfPapers"){
					
					ComparatorComboBox_3.addItem(">");
					ComparatorComboBox_3.addItem("<");
					ComparatorComboBox_3.addItem("=");
					ComparatorComboBox_3.addItem(">=");
					ComparatorComboBox_3.addItem("<=");
					ComparatorComboBox_3.addItem("!=");
					
				}				
		    }
		});

		
		JLabel ValueLabel_3 = new JLabel("Value");
		ValueLabel_3.setBounds(380, 13, 56, 16);
		panel_3.add(ValueLabel_3);
		
		ValueField_3 = new JTextField();
		ValueField_3.setColumns(10);
		ValueField_3.setBounds(417, 10, 116, 22);
		panel_3.add(ValueField_3);
		
		JButton SaveButton_3 = new JButton("Save");
		SaveButton_3.setBounds(545, 9, 86, 25);
		panel_3.add(SaveButton_3);
		
		JButton NewRowButton_3 = new JButton("New row");
		NewRowButton_3.setBounds(635, 9, 86, 25);
		panel_3.add(NewRowButton_3);
		NewRowButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_4.setVisible(true);
			}
		});
		
		JLabel JoinLabel_3 = new JLabel("Join Condition");
		JoinLabel_3.setBounds(733, 13, 86, 19);
		panel_3.add(JoinLabel_3);

		JComboBox JoinComboBox_3 = new JComboBox(JoinList);
		JoinComboBox_3.setBounds(826, 12, 95, 19);
		panel_3.add(JoinComboBox_3);
		
/****************************************** Panel-4 ******************************************************************/
		
		panel_4.setLayout(null);
		panel_4.setBounds(0, 176, 933, 41);
		contentPane.add(panel_4);
		
		JLabel CriteriaLabel_4 = new JLabel("Criteria");
		CriteriaLabel_4.setBounds(12, 13, 56, 16);
		panel_4.add(CriteriaLabel_4);
		
		final JComboBox CriteriaComboBox_4 = new JComboBox(CriteriaList);
		CriteriaComboBox_4.setBounds(67, 10, 107, 22);
		panel_4.add(CriteriaComboBox_4);
		
		JLabel ComparatorLabel_4 = new JLabel("Comparator");
		ComparatorLabel_4.setBounds(186, 13, 79, 16);
		panel_4.add(ComparatorLabel_4);
		

		ComparatorComboBox_4.setBounds(259, 10, 107, 22);
		panel_4.add(ComparatorComboBox_4);
		CriteriaComboBox_4.setSelectedItem(CriteriaList[0]);
		ComparatorComboBox_4.addItem("=");
		ComparatorComboBox_4.addItem("!=");
		
		CriteriaComboBox_4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				ComparatorComboBox_4.removeAllItems();				
				if(CriteriaComboBox_4.getSelectedItem() == "Keyword"){
					
					ComparatorComboBox_4.addItem("LIKE");
					ComparatorComboBox_4.addItem("NOT LIKE");

					 
				} else if(CriteriaComboBox_4.getSelectedItem() == "ConfName" || CriteriaComboBox_4.getSelectedItem() == "AuthorName"){
					
					ComparatorComboBox_4.addItem("=");	
					ComparatorComboBox_4.addItem("!=");
					
				}else if(CriteriaComboBox_4.getSelectedItem() == "Year" || CriteriaComboBox_4.getSelectedItem() == "CountNoOfPapers"){
					
					ComparatorComboBox_4.addItem(">");
					ComparatorComboBox_4.addItem("<");
					ComparatorComboBox_4.addItem("=");
					ComparatorComboBox_4.addItem(">=");
					ComparatorComboBox_4.addItem("<=");
					ComparatorComboBox_4.addItem("!=");
					
				}				
		    }
		});

		
		JLabel ValueLabel_4 = new JLabel("Value");
		ValueLabel_4.setBounds(380, 13, 56, 16);
		panel_4.add(ValueLabel_4);
		
		ValueField_4 = new JTextField();
		ValueField_4.setColumns(10);
		ValueField_4.setBounds(417, 10, 116, 22);
		panel_4.add(ValueField_4);
		
		JButton SaveButton_4 = new JButton("Save");
		SaveButton_4.setBounds(545, 9, 86, 25);
		panel_4.add(SaveButton_4);
		
		JButton NewRowButton_4 = new JButton("New row");
		NewRowButton_4.setBounds(635, 9, 86, 25);
		panel_4.add(NewRowButton_4);
		NewRowButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_5.setVisible(true);
			}
		});
		
		JLabel JoinLabel_4 = new JLabel("Join Condition");
		JoinLabel_4.setBounds(733, 13, 86, 19);
		panel_4.add(JoinLabel_4);

		JComboBox JoinComboBox_4 = new JComboBox(JoinList);
		JoinComboBox_4.setBounds(826, 12, 95, 19);
		panel_4.add(JoinComboBox_4);
		
/*********************************** Panel - 5 **********************************************/	
		
		panel_5.setBounds(0, 215, 933, 41);
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel CriteriaLabel_5 = new JLabel("Criteria");
		CriteriaLabel_5.setBounds(12, 13, 56, 16);
		panel_5.add(CriteriaLabel_5);
		
		final JComboBox CriteriaComboBox_5 = new JComboBox(CriteriaList);
		CriteriaComboBox_5.setBounds(67, 10, 107, 22);
		panel_5.add(CriteriaComboBox_5);
		
		JLabel ComparatorLabel_5 = new JLabel("Comparator");
		ComparatorLabel_5.setBounds(186, 13, 79, 16);
		panel_5.add(ComparatorLabel_5);
				
		ComparatorComboBox_5.setBounds(259, 10, 107, 22);
		panel_5.add(ComparatorComboBox_5);
		CriteriaComboBox_5.setSelectedItem(CriteriaList[0]);
		ComparatorComboBox_5.addItem("=");
		ComparatorComboBox_5.addItem("!=");
		
		CriteriaComboBox_5.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				ComparatorComboBox_5.removeAllItems();				
				if(CriteriaComboBox_5.getSelectedItem() == "Keyword"){
					
					ComparatorComboBox_5.addItem("LIKE");
					ComparatorComboBox_5.addItem("NOT LIKE");

					 
				} else if(CriteriaComboBox_5.getSelectedItem() == "ConfName" || CriteriaComboBox_5.getSelectedItem() == "AuthorName"){
					
					ComparatorComboBox_5.addItem("=");	
					ComparatorComboBox_5.addItem("!=");
					
				}else if(CriteriaComboBox_5.getSelectedItem() == "Year" || CriteriaComboBox_5.getSelectedItem() == "CountNoOfPapers"){
					
					ComparatorComboBox_5.addItem(">");
					ComparatorComboBox_5.addItem("<");
					ComparatorComboBox_5.addItem("=");
					ComparatorComboBox_5.addItem(">=");
					ComparatorComboBox_5.addItem("<=");
					ComparatorComboBox_5.addItem("!=");
					
				}				
		    }
		});

		
		JLabel ValueLabel_5 = new JLabel("Value");
		ValueLabel_5.setBounds(380, 13, 56, 16);
		panel_5.add(ValueLabel_5);
		
		ValueField_5 = new JTextField();
		ValueField_5.setColumns(10);
		ValueField_5.setBounds(417, 10, 116, 22);
		panel_5.add(ValueField_5);
		
		JButton SaveButton_5 = new JButton("Save");
		SaveButton_5.setBounds(545, 9, 86, 25);
		panel_5.add(SaveButton_5);
	}
}
