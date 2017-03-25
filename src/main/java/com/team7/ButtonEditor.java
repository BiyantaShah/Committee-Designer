package com.team7;

import java.awt.Component;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class ButtonEditor extends DefaultCellEditor {

	protected JButton button;

	private String label;
	static Set<String> sendEmail = new HashSet<String>();
	
	String data;

	private boolean isPushed;

	public ButtonEditor(JCheckBox checkBox) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		// TODO Auto-generated constructor stub
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value == null) ? "" : value.toString();
		Object o = table.getValueAt(row, 0);
		data = o.toString();
		makeList();
		button.setText(label);
		isPushed = true;
		return button;
	}

	public Set<String> makeList() {
		// TODO Auto-generated method stub
		sendEmail.add(data);
		return sendEmail;
		
	}

	public Object getCellEditorValue() {
		if (isPushed) {
			
		}
		isPushed = false;
		return new String(label);
	}

}
