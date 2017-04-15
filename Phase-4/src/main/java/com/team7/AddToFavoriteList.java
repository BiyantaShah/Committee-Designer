package com.team7;

import java.awt.Component;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class AddToFavoriteList extends DefaultCellEditor {

	protected JButton button;

	private String label;

	LoginUI log = new LoginUI();
	// A set which stores names of authors who have been saved or selected
	static Set<String> savedAuthors = new HashSet<String>();

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
			AddtoList();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			log.messageShow("Already included in the list");
			return null;
		}
		button.setText(label);
		isPushed = true;
		return button;
	}

	public void AddtoList() throws IOException, SQLException {
		
		ImplementSchemaDB db =  new ImplementSchemaDB();
		Connection conn = db.getConnection();
		String conference= null;
		PreparedStatement p = conn.prepareStatement("select confName from User where username='"+UIConstants.currentUser+"'");
		ResultSet rs = p.executeQuery();
		while (rs.next()) {
			conference = rs.getString(1);
		}

		PreparedStatement stmt = conn.prepareStatement("insert into Favorite_list(userName,confName,selectedAuthor) values(?,?,?)");

		stmt.setString(1,(UIConstants.currentUser));
		stmt.setString(2,conference);
		stmt.setString(3,data);

		stmt.executeUpdate();

	}


	public Object getCellEditorValue() {
		if (isPushed) {}
		return new String(label);
	}


}
