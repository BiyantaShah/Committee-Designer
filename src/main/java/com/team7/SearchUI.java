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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

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
	public SearchUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 951, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JPanel panel_1 = new JPanel();
        panel_1.setVisible(false);
        
		final JPanel panel_2 = new JPanel();
        panel_2.setVisible(false);

		final JPanel panel_3 = new JPanel();
        panel_3.setVisible(false);

		final JPanel panel_4 = new JPanel();
        panel_4.setVisible(false);

		
		JPanel panel = new JPanel();
		panel.setBounds(0, 57, 933, 41);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Property");
		lblNewLabel.setBounds(12, 13, 56, 16);
		panel.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(67, 10, 107, 22);
		panel.add(comboBox);
		
		JLabel lblComparator = new JLabel("Comparator");
		lblComparator.setBounds(186, 13, 79, 16);
		panel.add(lblComparator);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(259, 10, 107, 22);
		panel.add(comboBox_1);
		
		JLabel lblValue = new JLabel("Value");
		lblValue.setBounds(380, 13, 56, 16);
		panel.add(lblValue);
		
		textField = new JTextField();
		textField.setBounds(417, 10, 116, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSave.setBounds(545, 9, 86, 25);
		panel.add(btnSave);
		
		JButton btnNewRow = new JButton("New row");
		btnNewRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_1.setVisible(true);
			}
		});
		btnNewRow.setBounds(635, 9, 86, 25);
		panel.add(btnNewRow);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(826, 12, 95, 19);
		panel.add(comboBox_2);
		
		JLabel lblJoinCondition = new JLabel("Join Condition");
		lblJoinCondition.setBounds(733, 13, 86, 19);
		panel.add(lblJoinCondition);
		
		panel_1.setLayout(null);
		panel_1.setBounds(0, 97, 933, 41);
		contentPane.add(panel_1);
		
		JLabel label = new JLabel("Property");
		label.setBounds(12, 13, 56, 16);
		panel_1.add(label);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(67, 10, 107, 22);
		panel_1.add(comboBox_3);
		
		JLabel label_1 = new JLabel("Comparator");
		label_1.setBounds(186, 13, 79, 16);
		panel_1.add(label_1);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(259, 10, 107, 22);
		panel_1.add(comboBox_4);
		
		JLabel label_2 = new JLabel("Value");
		label_2.setBounds(380, 13, 56, 16);
		panel_1.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(417, 10, 116, 22);
		panel_1.add(textField_1);
		
		JButton button = new JButton("Save");
		button.setBounds(545, 9, 86, 25);
		panel_1.add(button);
		
		JButton button_1 = new JButton("New row");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_2.setVisible(true);
			}
		});
		button_1.setBounds(635, 9, 86, 25);
		panel_1.add(button_1);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(826, 12, 95, 19);
		panel_1.add(comboBox_5);
		
		JLabel label_3 = new JLabel("Join Condition");
		label_3.setBounds(733, 13, 86, 19);
		panel_1.add(label_3);
		
		panel_2.setLayout(null);
		panel_2.setBounds(0, 137, 933, 41);
		contentPane.add(panel_2);
		
		JLabel label_4 = new JLabel("Property");
		label_4.setBounds(12, 13, 56, 16);
		panel_2.add(label_4);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setBounds(67, 10, 107, 22);
		panel_2.add(comboBox_6);
		
		JLabel label_5 = new JLabel("Comparator");
		label_5.setBounds(186, 13, 79, 16);
		panel_2.add(label_5);
		
		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setBounds(259, 10, 107, 22);
		panel_2.add(comboBox_7);
		
		JLabel label_6 = new JLabel("Value");
		label_6.setBounds(380, 13, 56, 16);
		panel_2.add(label_6);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(417, 10, 116, 22);
		panel_2.add(textField_2);
		
		JButton button_2 = new JButton("Save");
		button_2.setBounds(545, 9, 86, 25);
		panel_2.add(button_2);
		
		JButton button_3 = new JButton("New row");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_3.setVisible(true);
			}
		});
		button_3.setBounds(635, 9, 86, 25);
		panel_2.add(button_3);
		
		JComboBox comboBox_8 = new JComboBox();
		comboBox_8.setBounds(826, 12, 95, 19);
		panel_2.add(comboBox_8);
		
		JLabel label_7 = new JLabel("Join Condition");
		label_7.setBounds(733, 13, 86, 19);
		panel_2.add(label_7);
		
		panel_3.setLayout(null);
		panel_3.setBounds(0, 176, 933, 41);
		contentPane.add(panel_3);
		
		JLabel label_8 = new JLabel("Property");
		label_8.setBounds(12, 13, 56, 16);
		panel_3.add(label_8);
		
		JComboBox comboBox_9 = new JComboBox();
		comboBox_9.setBounds(67, 10, 107, 22);
		panel_3.add(comboBox_9);
		
		JLabel label_9 = new JLabel("Comparator");
		label_9.setBounds(186, 13, 79, 16);
		panel_3.add(label_9);
		
		JComboBox comboBox_10 = new JComboBox();
		comboBox_10.setBounds(259, 10, 107, 22);
		panel_3.add(comboBox_10);
		
		JLabel label_10 = new JLabel("Value");
		label_10.setBounds(380, 13, 56, 16);
		panel_3.add(label_10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(417, 10, 116, 22);
		panel_3.add(textField_3);
		
		JButton button_4 = new JButton("Save");
		button_4.setBounds(545, 9, 86, 25);
		panel_3.add(button_4);
		
		JButton button_5 = new JButton("New row");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_4.setVisible(true);
			}
		});
		button_5.setBounds(635, 9, 86, 25);
		panel_3.add(button_5);
		
		JComboBox comboBox_11 = new JComboBox();
		comboBox_11.setBounds(826, 12, 95, 19);
		panel_3.add(comboBox_11);
		
		JLabel label_11 = new JLabel("Join Condition");
		label_11.setBounds(733, 13, 86, 19);
		panel_3.add(label_11);
		
		panel_4.setBounds(0, 215, 933, 41);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel label_12 = new JLabel("Property");
		label_12.setBounds(12, 13, 56, 16);
		panel_4.add(label_12);
		
		JComboBox comboBox_12 = new JComboBox();
		comboBox_12.setBounds(67, 10, 107, 22);
		panel_4.add(comboBox_12);
		
		JLabel label_13 = new JLabel("Comparator");
		label_13.setBounds(186, 13, 79, 16);
		panel_4.add(label_13);
		
		JComboBox comboBox_13 = new JComboBox();
		comboBox_13.setBounds(259, 10, 107, 22);
		panel_4.add(comboBox_13);
		
		JLabel label_14 = new JLabel("Value");
		label_14.setBounds(380, 13, 56, 16);
		panel_4.add(label_14);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(417, 10, 116, 22);
		panel_4.add(textField_4);
		
		JButton button_6 = new JButton("Save");
		button_6.setBounds(545, 9, 86, 25);
		panel_4.add(button_6);
	}

}
