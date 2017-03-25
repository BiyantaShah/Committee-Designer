package com.team7;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class SearchUI extends JFrame {

	private JPanel contentPane;
	private JTextField ValueField_1;
	private JTextField ValueField_2;
	private JTextField ValueField_3;
	private JTextField ValueField_4;
	private JTextField ValueField_5;

	SearchParameter sp1 = new SearchParameter();
	SearchParameter sp2 = new SearchParameter();
	SearchParameter sp3 = new SearchParameter();
	SearchParameter sp4 = new SearchParameter();
	SearchParameter sp5 = new SearchParameter();

	boolean q1 = true;
	boolean q2 = false;
	boolean q3 = false;
	boolean q4 = false;
	boolean q5 = false;

	List<SearchParameter> finalList = new ArrayList<SearchParameter>();
	LoginUI log = new LoginUI();
	ImplementSearchDisplay sd = new ImplementSearchDisplay();
	

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SearchUI() {

	    setVisible(true);
	    setTitle("Search UI");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblSearch.setBounds(401, 39, 153, 32);
		contentPane.add(lblSearch);

		
		final JPanel panel_2 = new JPanel();
		panel_2.setVisible(false);

		final JPanel panel_3 = new JPanel();
		panel_3.setVisible(false);

		final JPanel panel_4 = new JPanel();
		panel_4.setVisible(false);

		final JPanel panel_5 = new JPanel();
		panel_5.setVisible(false);

		String[] CriteriaList = {"Author Name" ,"Committe Year","Committe Conf Name", "Conference Name", "Count Of Papers", "Keyword in Title", "Paper Published Year"};
		String[] JoinList = {"AND","OR"};


		final JComboBox ComparatorComboBox_1 = new JComboBox();
		final JComboBox ComparatorComboBox_2 = new JComboBox();
		final JComboBox ComparatorComboBox_3 = new JComboBox();
		final JComboBox ComparatorComboBox_4 = new JComboBox();
		final JComboBox ComparatorComboBox_5 = new JComboBox();


		final JLabel JoinLabel_1 = new JLabel("Join Condition");
		JoinLabel_1.setVisible(false);

		final JLabel JoinLabel_2 = new JLabel("Join Condition");
		JoinLabel_2.setVisible(false);

		final JLabel JoinLabel_3 = new JLabel("Join Condition");
		JoinLabel_3.setVisible(false);

		final JLabel JoinLabel_4 = new JLabel("Join Condition");
		JoinLabel_4.setVisible(false);


		final JComboBox JoinComboBox_1= new JComboBox(JoinList);
		JoinComboBox_1.setSelectedItem(JoinList[0]);	
		sp1.joinFilter = (String) JoinComboBox_1.getItemAt(0);
		JoinComboBox_1.setVisible(false);

		final JComboBox JoinComboBox_2= new JComboBox(JoinList);
		JoinComboBox_2.setSelectedItem(JoinList[0]);	
		sp2.joinFilter = (String) JoinComboBox_2.getItemAt(0);
		JoinComboBox_2.setVisible(false);

		final JComboBox JoinComboBox_3= new JComboBox(JoinList);
		JoinComboBox_3.setSelectedItem(JoinList[0]);	
		sp3.joinFilter = (String) JoinComboBox_3.getItemAt(0);
		JoinComboBox_3.setVisible(false);

		final JComboBox JoinComboBox_4= new JComboBox(JoinList);
		JoinComboBox_4.setSelectedItem(JoinList[0]);	
		sp4.joinFilter = (String) JoinComboBox_4.getItemAt(0);
		JoinComboBox_4.setVisible(false);


		/****************************************************** Panel -1 *******************************************/		

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(23, 134, 880, 41);
		contentPane.add(panel_1);
		panel_1.setLayout(null);


		JLabel CriteriaLabel_1 = new JLabel("Criteria");
		CriteriaLabel_1.setBounds(12, 13, 56, 16);
		panel_1.add(CriteriaLabel_1);


		final JComboBox CriteriaComboBox_1 = new JComboBox(CriteriaList);
		CriteriaComboBox_1.setBounds(67, 10, 129, 22);
		panel_1.add(CriteriaComboBox_1);

		CriteriaComboBox_1.setSelectedItem(CriteriaList[0]);
		sp1.searchFilter = (String) CriteriaComboBox_1.getSelectedItem();
		ComparatorComboBox_1.addItem("=");
		ComparatorComboBox_1.addItem("!=");

		CriteriaComboBox_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				ComparatorComboBox_1.removeAllItems();				
				if(CriteriaComboBox_1.getSelectedItem() == "Keyword in Title"){

					ComparatorComboBox_1.addItem("LIKE");
					ComparatorComboBox_1.addItem("NOT LIKE");


				} else if(CriteriaComboBox_1.getSelectedItem() == "Author Name" || CriteriaComboBox_1.getSelectedItem() == "Conference Name" || CriteriaComboBox_1.getSelectedItem() == "Committe Conf Name" ){

					ComparatorComboBox_1.addItem("=");	
					ComparatorComboBox_1.addItem("!=");

				}else if(CriteriaComboBox_1.getSelectedItem() == "Committe Year" || CriteriaComboBox_1.getSelectedItem() == "Count Of Papers" || CriteriaComboBox_1.getSelectedItem() == "Paper Published Year" ){

					ComparatorComboBox_1.addItem(">");
					ComparatorComboBox_1.addItem("<");
					ComparatorComboBox_1.addItem("=");
					ComparatorComboBox_1.addItem(">=");
					ComparatorComboBox_1.addItem("<=");
					ComparatorComboBox_1.addItem("!=");

				}
				sp1.searchFilter = (String) CriteriaComboBox_1.getSelectedItem();
			}
		});

		JLabel ComparatorLabel_1 = new JLabel("Comparator");
		ComparatorLabel_1.setBounds(212, 13, 79, 16);
		panel_1.add(ComparatorLabel_1);

		sp1.searchComparator = (String) ComparatorComboBox_1.getItemAt(0);
		ComparatorComboBox_1.setBounds(291, 10, 107, 22);
		panel_1.add(ComparatorComboBox_1);
		ComparatorComboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sp1.searchComparator = (String) ComparatorComboBox_1.getSelectedItem();
			}
		});


		JLabel ValueLabel_1 = new JLabel("Value");
		ValueLabel_1.setBounds(410, 13, 56, 16);
		panel_1.add(ValueLabel_1);

		ValueField_1 = new JTextField();
		ValueField_1.setBounds(456, 10, 116, 22);
		panel_1.add(ValueField_1);
		ValueField_1.setColumns(10);

		final JButton NewRowButton_1 = new JButton("New row");
		NewRowButton_1.setBounds(590, 9, 86, 25);
		panel_1.add(NewRowButton_1);
		NewRowButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				sp1.searchValue = ValueField_1.getText();
				if(sp1.searchValue.equals("")){

					log.messageShow("please enter value for search before proceeding further");

				}else{

					JoinLabel_1.setVisible(true);
					JoinComboBox_1.setVisible(true);
//					System.out.println(sp1.searchFilter +"---"+sp1.searchComparator + "---" + sp1.searchValue+ "-----"+sp1.joinFilter);
					panel_2.setVisible(true);
					q2 = true;

				}
			}

		});

		//label for join comparator
		JoinLabel_1.setBounds(688, 12, 86, 19);
		panel_1.add(JoinLabel_1);

		//combo box for join comparator
		JoinComboBox_1.setBounds(777, 10, 70, 22);
		panel_1.add(JoinComboBox_1);
		JoinComboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sp1.joinFilter =(String) JoinComboBox_1.getSelectedItem(); 
			}
		});



		/****************************************************** Panel -2 ********************************************/		
		panel_2.setLayout(null);
		panel_2.setBounds(23, 175, 880, 41);
		contentPane.add(panel_2);

		JLabel CriteriaLabel_2 = new JLabel("Criteria");
		CriteriaLabel_2.setBounds(12, 13, 56, 16);
		panel_2.add(CriteriaLabel_2);

		final JComboBox CriteriaComboBox_2 = new JComboBox(CriteriaList);
		CriteriaComboBox_2.setBounds(67, 10, 129, 22);
		panel_2.add(CriteriaComboBox_2);

		CriteriaComboBox_2.setSelectedItem(CriteriaList[0]);
		sp2.searchFilter = (String) CriteriaComboBox_2.getSelectedItem();
		ComparatorComboBox_2.addItem("=");
		ComparatorComboBox_2.addItem("!=");

		CriteriaComboBox_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				ComparatorComboBox_2.removeAllItems();				
				if(CriteriaComboBox_2.getSelectedItem() == "Keyword in Title"){

					ComparatorComboBox_2.addItem("LIKE");
					ComparatorComboBox_2.addItem("NOT LIKE");


				} else if(CriteriaComboBox_2.getSelectedItem() == "Author Name" || CriteriaComboBox_2.getSelectedItem() == "Conference Name" || CriteriaComboBox_2.getSelectedItem() == "Committe Conf Name" ){

					ComparatorComboBox_2.addItem("=");	
					ComparatorComboBox_2.addItem("!=");

				}else if(CriteriaComboBox_2.getSelectedItem() == "Committe Year" || CriteriaComboBox_2.getSelectedItem() == "Count Of Papers" || CriteriaComboBox_2.getSelectedItem() == "Paper Published Year" ){

					ComparatorComboBox_2.addItem(">");
					ComparatorComboBox_2.addItem("<");
					ComparatorComboBox_2.addItem("=");
					ComparatorComboBox_2.addItem(">=");
					ComparatorComboBox_2.addItem("<=");
					ComparatorComboBox_2.addItem("!=");

				}
				sp2.searchFilter = (String) CriteriaComboBox_2.getSelectedItem();

			}
		});

		JLabel ComparatorLabel_2 = new JLabel("Comparator");
		ComparatorLabel_2.setBounds(212, 13, 79, 16);
		panel_2.add(ComparatorLabel_2);

		sp2.searchComparator = (String) ComparatorComboBox_2.getSelectedItem();
		ComparatorComboBox_2.setBounds(291, 10, 107, 22);
		panel_2.add(ComparatorComboBox_2);
		ComparatorComboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sp2.searchComparator = (String) ComparatorComboBox_2.getSelectedItem();
			}
		});


		JLabel ValueLabel_2 = new JLabel("Value");
		ValueLabel_2.setBounds(410, 13, 56, 16);
		panel_2.add(ValueLabel_2);

		ValueField_2 = new JTextField();
		ValueField_2.setColumns(10);
		ValueField_2.setBounds(456, 10, 116, 22);
		panel_2.add(ValueField_2);

		JButton NewRowButton_2 = new JButton("New row");
		NewRowButton_2.setBounds(590, 9, 86, 25);
		panel_2.add(NewRowButton_2);
		NewRowButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				sp2.searchValue = ValueField_2.getText();
				if(sp2.searchValue.equals("")){

					log.messageShow("please enter value for search before proceeding further");

				}else{
					JoinLabel_2.setVisible(true);
					JoinComboBox_2.setVisible(true);
//					System.out.println(sp2.searchFilter +"---"+sp2.searchComparator + "---" + sp2.searchValue+ "-----"+sp2.joinFilter);
					panel_3.setVisible(true);
					q3 = true;
				}
			}
		});

		//label for join comparator
		JoinLabel_2.setBounds(688, 12, 86, 19);
		panel_2.add(JoinLabel_2);


		//combo box for join comparator
		JoinComboBox_2.setBounds(777, 10, 70, 22);
		panel_2.add(JoinComboBox_2);
		JoinComboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sp2.joinFilter =(String) JoinComboBox_2.getSelectedItem(); 
			}
		});

		/************************************ Panel -3 ******************************************************/

		panel_3.setLayout(null);
		panel_3.setBounds(23, 214, 880, 41);
		contentPane.add(panel_3);

		JLabel CriteriaLabel_3 = new JLabel("Criteria");
		CriteriaLabel_3.setBounds(12, 13, 56, 16);
		panel_3.add(CriteriaLabel_3);

		final JComboBox CriteriaComboBox_3 = new JComboBox(CriteriaList);
		CriteriaComboBox_3.setBounds(67, 10, 129, 22);
		panel_3.add(CriteriaComboBox_3);

		CriteriaComboBox_3.setSelectedItem(CriteriaList[0]);
		ComparatorComboBox_3.addItem("="); //As initial criteria is AuthorName
		ComparatorComboBox_3.addItem("!=");//it's corresponding comparator should be = and !=

		CriteriaComboBox_3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				ComparatorComboBox_3.removeAllItems();				
				ComparatorComboBox_3.removeAllItems();				
				if(CriteriaComboBox_3.getSelectedItem() == "Keyword in Title"){

					ComparatorComboBox_3.addItem("LIKE");
					ComparatorComboBox_3.addItem("NOT LIKE");


				} else if(CriteriaComboBox_3.getSelectedItem() == "Author Name" || CriteriaComboBox_3.getSelectedItem() == "Conference Name" || CriteriaComboBox_3.getSelectedItem() == "Committe Conf Name" ){

					ComparatorComboBox_3.addItem("=");	
					ComparatorComboBox_3.addItem("!=");

				}else if(CriteriaComboBox_3.getSelectedItem() == "Committe Year" || CriteriaComboBox_3.getSelectedItem() == "Count Of Papers" || CriteriaComboBox_3.getSelectedItem() == "Paper Published Year" ){

					ComparatorComboBox_3.addItem(">");
					ComparatorComboBox_3.addItem("<");
					ComparatorComboBox_3.addItem("=");
					ComparatorComboBox_3.addItem(">=");
					ComparatorComboBox_3.addItem("<=");
					ComparatorComboBox_3.addItem("!=");

				}
				sp3.searchFilter = (String) CriteriaComboBox_1.getSelectedItem();
			}
		});

		JLabel ComparatorLabel_3 = new JLabel("Comparator");
		ComparatorLabel_3.setBounds(212, 13, 79, 16);
		panel_3.add(ComparatorLabel_3);

		sp3.searchComparator = (String) ComparatorComboBox_3.getItemAt(0);
		ComparatorComboBox_3.setBounds(291, 10, 107, 22);
		panel_3.add(ComparatorComboBox_3);
		ComparatorComboBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sp3.searchComparator = (String) ComparatorComboBox_3.getSelectedItem();
			}
		});


		JLabel ValueLabel_3 = new JLabel("Value");
		ValueLabel_3.setBounds(410, 13, 56, 16);
		panel_3.add(ValueLabel_3);

		ValueField_3 = new JTextField();
		ValueField_3.setColumns(10);
		ValueField_3.setBounds(456, 10, 116, 22);
		panel_3.add(ValueField_3);

		JButton NewRowButton_3 = new JButton("New row");
		NewRowButton_3.setBounds(590, 9, 86, 25);
		panel_3.add(NewRowButton_3);
		NewRowButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sp3.searchValue = ValueField_3.getText();
				if(sp3.searchValue.equals("")){

					log.messageShow("please enter value for search before proceeding further");

				}else{
					JoinLabel_3.setVisible(true);
					JoinComboBox_3.setVisible(true);
//					System.out.println(sp3.searchFilter +"---"+sp3.searchComparator + "---" + sp3.searchValue+ "-----"+sp3.joinFilter);
					panel_4.setVisible(true);
					q4 = true;
				}
			}
		});

		//label for join comparator
		JoinLabel_3.setBounds(688, 12, 86, 19);
		panel_3.add(JoinLabel_3);

		//combo box for join comparator
		JoinComboBox_3.setBounds(777, 10, 70, 22);
		panel_3.add(JoinComboBox_3);
		JoinComboBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sp3.joinFilter =(String) JoinComboBox_3.getSelectedItem(); 
			}
		});

		/****************************************** Panel-4 ******************************************************************/

		panel_4.setLayout(null);
		panel_4.setBounds(23, 255, 880, 41);
		contentPane.add(panel_4);

		JLabel CriteriaLabel_4 = new JLabel("Criteria");
		CriteriaLabel_4.setBounds(12, 13, 56, 16);
		panel_4.add(CriteriaLabel_4);

		final JComboBox CriteriaComboBox_4 = new JComboBox(CriteriaList);
		CriteriaComboBox_4.setBounds(67, 10, 129, 22);
		panel_4.add(CriteriaComboBox_4);

		CriteriaComboBox_4.setSelectedItem(CriteriaList[0]);
		sp4.searchFilter = (String) CriteriaComboBox_4.getSelectedItem();
		ComparatorComboBox_4.addItem("=");
		ComparatorComboBox_4.addItem("!=");

		CriteriaComboBox_4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				ComparatorComboBox_4.removeAllItems();

				if(CriteriaComboBox_4.getSelectedItem() == "Keyword in Title"){

					ComparatorComboBox_4.addItem("LIKE");
					ComparatorComboBox_4.addItem("NOT LIKE");


				} else if(CriteriaComboBox_4.getSelectedItem() == "Author Name" || CriteriaComboBox_4.getSelectedItem() == "Conference Name" || CriteriaComboBox_4.getSelectedItem() == "Committe Conf Name" ){

					ComparatorComboBox_4.addItem("=");	
					ComparatorComboBox_4.addItem("!=");

				}else if(CriteriaComboBox_4.getSelectedItem() == "Committe Year" || CriteriaComboBox_4.getSelectedItem() == "Count Of Papers" || CriteriaComboBox_4.getSelectedItem() == "Paper Published Year" ){

					ComparatorComboBox_4.addItem(">");
					ComparatorComboBox_4.addItem("<");
					ComparatorComboBox_4.addItem("=");
					ComparatorComboBox_4.addItem(">=");
					ComparatorComboBox_4.addItem("<=");
					ComparatorComboBox_4.addItem("!=");

				}

				sp4.searchFilter = (String) CriteriaComboBox_4.getSelectedItem();

			}
		});


		JLabel ComparatorLabel_4 = new JLabel("Comparator");
		ComparatorLabel_4.setBounds(212, 13, 79, 16);
		panel_4.add(ComparatorLabel_4);

		sp4.searchComparator = (String) ComparatorComboBox_4.getItemAt(0);
		ComparatorComboBox_4.setBounds(291, 10, 107, 22);
		panel_4.add(ComparatorComboBox_4);
		ComparatorComboBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sp4.searchComparator = (String) ComparatorComboBox_4.getSelectedItem();
			}
		});


		JLabel ValueLabel_4 = new JLabel("Value");
		ValueLabel_4.setBounds(410, 13, 56, 16);
		panel_4.add(ValueLabel_4);

		ValueField_4 = new JTextField();
		ValueField_4.setColumns(10);
		ValueField_4.setBounds(456, 10, 116, 22);
		panel_4.add(ValueField_4);

		JButton NewRowButton_4 = new JButton("New row");  
		NewRowButton_4.setBounds(590, 9, 86, 25);
		panel_4.add(NewRowButton_4);
		NewRowButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sp4.searchValue = ValueField_4.getText();
				if(sp4.searchValue.equals("")){

					log.messageShow("please enter value for search before proceeding further");

				}else{

					JoinLabel_4.setVisible(true);
					JoinComboBox_4.setVisible(true);
//					System.out.println(sp4.searchFilter +"---"+sp4.searchComparator + "---" + sp4.searchValue+ "-----"+sp4.joinFilter);
					panel_5.setVisible(true);
					q5 = true;
				}
			}
		});

		//label for join comparator
		JoinLabel_4.setBounds(688, 12, 86, 19);
		panel_4.add(JoinLabel_4);

		//combo box for join comparator
		JoinComboBox_4.setBounds(777, 10, 70, 22);
		panel_4.add(JoinComboBox_4);
		JoinComboBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sp4.joinFilter =(String) JoinComboBox_4.getSelectedItem(); 
			}
		});


		/*********************************** Panel - 5 **********************************************/	

		panel_5.setBounds(23, 294, 880, 41);
		contentPane.add(panel_5);
		panel_5.setLayout(null);

		JLabel CriteriaLabel_5 = new JLabel("Criteria");
		CriteriaLabel_5.setBounds(12, 13, 56, 16);
		panel_5.add(CriteriaLabel_5);

		final JComboBox CriteriaComboBox_5 = new JComboBox(CriteriaList);
		CriteriaComboBox_5.setBounds(67, 10, 129, 22);
		panel_5.add(CriteriaComboBox_5);

		CriteriaComboBox_5.setSelectedItem(CriteriaList[0]);
		sp5.searchFilter = (String) CriteriaComboBox_5.getSelectedItem();
		ComparatorComboBox_5.addItem("=");
		ComparatorComboBox_5.addItem("!=");

		CriteriaComboBox_5.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				ComparatorComboBox_5.removeAllItems();	

				if(CriteriaComboBox_5.getSelectedItem() == "Keyword in Title"){

					ComparatorComboBox_5.addItem("LIKE");
					ComparatorComboBox_5.addItem("NOT LIKE");


				} else if(CriteriaComboBox_5.getSelectedItem() == "Author Name" || CriteriaComboBox_5.getSelectedItem() == "Conference Name" || CriteriaComboBox_5.getSelectedItem() == "Committe Conf Name" ){

					ComparatorComboBox_5.addItem("=");	
					ComparatorComboBox_5.addItem("!=");

				}else if(CriteriaComboBox_5.getSelectedItem() == "Committe Year" || CriteriaComboBox_5.getSelectedItem() == "Count Of Papers" || CriteriaComboBox_5.getSelectedItem() == "Paper Published Year" ){

					ComparatorComboBox_5.addItem(">");
					ComparatorComboBox_5.addItem("<");
					ComparatorComboBox_5.addItem("=");
					ComparatorComboBox_5.addItem(">=");
					ComparatorComboBox_5.addItem("<=");
					ComparatorComboBox_5.addItem("!=");

				}	
				sp5.searchFilter = (String) CriteriaComboBox_5.getSelectedItem();
			}
		});


		JLabel ComparatorLabel_5 = new JLabel("Comparator");
		ComparatorLabel_5.setBounds(212, 13, 79, 16);
		panel_5.add(ComparatorLabel_5);

		sp5.searchComparator = (String) ComparatorComboBox_5.getItemAt(0);
		ComparatorComboBox_5.setBounds(291, 10, 107, 22);
		panel_5.add(ComparatorComboBox_5);
		ComparatorComboBox_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sp5.searchComparator = (String) ComparatorComboBox_5.getSelectedItem();
			}
		});


		JLabel ValueLabel_5 = new JLabel("Value");
		ValueLabel_5.setBounds(410, 13, 56, 16);
		panel_5.add(ValueLabel_5);

		ValueField_5 = new JTextField();
		ValueField_5.setColumns(10);
		ValueField_5.setBounds(456, 10, 116, 22);
		panel_5.add(ValueField_5);
				
				
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnNewButton.setBounds(394, 395, 97, 25);
		contentPane.add(btnNewButton);
			
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				sp1.searchValue = ValueField_1.getText();
				sp2.searchValue = ValueField_2.getText();
				sp3.searchValue = ValueField_3.getText();
				sp4.searchValue = ValueField_4.getText();
				sp5.searchValue = ValueField_5.getText();

				if(q1 == true)
				{
					finalList.clear();
					if(!sp1.searchValue.equals("")){
						finalList.add(sp1);
						if(q2 == true)
						{
							if(!sp2.searchValue.equals("")){
								finalList.add(sp2);
								if(q3 == true)
								{
									if(!sp3.searchValue.equals("")){
										finalList.add(sp3);
										if(q4 == true)
										{
											if(!sp4.searchValue.equals("")){
												finalList.add(sp4);
												if(q5 == true)
												{
													if(!sp5.searchValue.equals("")){
														finalList.add(sp5);
													}else{
														log.messageShow("Please enter a  value for search in fifth row");
													}
												}												   
											}										       
											else{
												log.messageShow("Please enter a  value for search in fourth row");  
											}										   
										}
									}else{
										log.messageShow("Please enter a  value for search in third row");
									}
								}
							}else{
								log.messageShow("Please enter a  value for search in second row");  
							} 
						}	   
					}else{
						log.messageShow("Please enter a  value for search in first row"); 
					}
				}

				if(finalList.size() != 0){

					//searchDisplay(sd.search(finalList));					
					log.messageShow("list size"+finalList.size());

				}				   
			}
		});

	}
}
