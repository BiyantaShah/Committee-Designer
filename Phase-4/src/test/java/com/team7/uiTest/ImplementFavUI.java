package com.team7.uiTest;
//package com.team7;
//
//import javax.swing.JCheckBox;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//
//import org.junit.Test;
//
//public class ImplementFavUI {
//	
//	FavoriteListUI favList = new FavoriteListUI();
//	AddToFavoriteList button = new AddToFavoriteList(new JCheckBox());
//	
//	@Test
//	// Testing whether the candidate details shows up some records
//	public void testCandidateDetails() {
//		String[] colNames = {"Name"};
//		Object [][] row = {{"author1"}, {"author2"}};
//		JTable table = new JTable(new DefaultTableModel(row, colNames));
//		button.getTableCellEditorComponent(table, "save", true, 1, 0);
//		favList.btnRemove.doClick();
//		favList.dispose();
//	}
//
//	@Test
//	// Testing logout button
//	public void testLogout() {
//		favList.btnLogout.doClick();
//		favList.dispose();
//	}
//	
//	@Test
//	// Testing the SearchUI function
//	public void testSearchUI() {
//		favList.btnSearch.doClick();
//		favList.dispose();
//	}
//	
//	@Test
//	// Testing the pop up for remove button
//	public void testRemoveEmpty() {
//		favList.btnRemove.doClick();
//		favList.dispose();
//	}
//}
