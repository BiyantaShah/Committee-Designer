package com.team7;

import java.awt.Component;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

// Adding each author selected to the favorite list
public class AddToFavoriteList extends DefaultCellEditor {

	protected JButton button;

	private String label;

	LoginUI log = new LoginUI();
	
	String data;

	private boolean isPushed;

	public AddToFavoriteList(JCheckBox checkBox) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
	}
	private static final long serialVersionUID = 1L;

	// extracts information about the row where the button is clicked
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

		Object rowData = table.getValueAt(row, 0);
		data = rowData.toString();
		
		try {
			// adding the selected author to the favorites list
			ImplementSchemaDB db = new ImplementSchemaDB();
			db.insertIntoFavList(UIConstants.currentUser, UIConstants.currentUserConf, data);

		} catch (IOException | SQLException e) {
			log.messageShow("Already included in the list");
			return null;
		}
		button.setText(label);
		isPushed = true;
		return button;
	}


	public Object getCellEditorValue() {
		if (isPushed) {}
		return new String(label);
	}


}
