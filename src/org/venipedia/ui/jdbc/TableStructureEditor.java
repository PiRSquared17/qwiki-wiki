package org.venipedia.ui.jdbc;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JList;
import javax.swing.JScrollPane;

import org.venipedia.dbcore.jdbc.JdbcDatabase;
import org.venipedia.dbcore.jdbc.JdbcTable;

public class TableStructureEditor extends JInternalFrame {
	private static final long serialVersionUID = 634264891133338776L;

	/**
	 * Create the frame.
	 * @param tableSelectionFrame 
	 * @param table 
	 * @throws SQLException 
	 */
	public TableStructureEditor(JdbcTable table, TableSelectionFrame tableSelectionFrame) throws SQLException {
		setResizable(true);
		setIconifiable(true);
		setFrameIcon(new ImageIcon(TableStructureEditor.class.getResource("/icons/table-select-column.png")));
		setTitle("Edit "+table.getEscapedName()+" structure");
		setBounds(100, 100, 290, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		JList list = new JList(table.getTableStructureListModel());
		scrollPane.setViewportView(list);
		
		JButton btnAddColumn = new JButton("Add column");
		btnAddColumn.setIcon(new ImageIcon(TableStructureEditor.class.getResource("/icons/table-insert-column.png")));
		GridBagConstraints gbc_btnAddColumn = new GridBagConstraints();
		gbc_btnAddColumn.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddColumn.gridx = 0;
		gbc_btnAddColumn.gridy = 1;
		getContentPane().add(btnAddColumn, gbc_btnAddColumn);
		
		JButton btnModifyColumn = new JButton("Modify column");
		btnModifyColumn.setIcon(new ImageIcon(TableStructureEditor.class.getResource("/icons/table--pencil.png")));
		GridBagConstraints gbc_btnModifyColumn = new GridBagConstraints();
		gbc_btnModifyColumn.insets = new Insets(0, 0, 5, 0);
		gbc_btnModifyColumn.gridx = 1;
		gbc_btnModifyColumn.gridy = 1;
		getContentPane().add(btnModifyColumn, gbc_btnModifyColumn);
		
		JButton btnDeleteColumn = new JButton("Delete column");
		btnDeleteColumn.setIcon(new ImageIcon(TableStructureEditor.class.getResource("/icons/table-delete-column.png")));
		GridBagConstraints gbc_btnDeleteColumn = new GridBagConstraints();
		gbc_btnDeleteColumn.insets = new Insets(0, 0, 0, 5);
		gbc_btnDeleteColumn.gridx = 0;
		gbc_btnDeleteColumn.gridy = 2;
		getContentPane().add(btnDeleteColumn, gbc_btnDeleteColumn);
		
		JButton btnDone = new JButton("Done");
		btnDone.setIcon(new ImageIcon(TableStructureEditor.class.getResource("/icons/tick-circle.png")));
		GridBagConstraints gbc_btnDone = new GridBagConstraints();
		gbc_btnDone.gridx = 1;
		gbc_btnDone.gridy = 2;
		getContentPane().add(btnDone, gbc_btnDone);

	}

}
