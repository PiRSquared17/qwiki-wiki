package org.venipedia.ui.jdbc;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;

import org.venipedia.dbcore.jdbc.JdbcTable;
import org.venipedia.ui.QwikiWiki;

public class TableViewer extends JInternalFrame {
	private static final long serialVersionUID = -7666460617279943393L;
	JdbcTable dbTable;
	JTable uiTable;

	QwikiWiki vb;
	
	int numRows;

	/**
	 * Create the frame.
	 */
	public TableViewer(JdbcTable t, QwikiWiki vb) {
		
		//System.out.println("TableViewer constructor");
		
		try {
			t.getTableContents();
		} catch (SQLException e1) {
			vb.showErrorDialog(e1);
			e1.printStackTrace();
		}
		
		setFrameIcon(new ImageIcon(TableViewer.class.getResource("/icons/application-table.png")));
		
		dbTable = t;
		this.vb=vb;
		
		numRows=t.getRowCount();
		//System.out.println("rows = "+numRows);
		
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Records - "+t.getEscapedName());
		setBounds(100, 100, 688, 484);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{434, 0};
		gridBagLayout.rowHeights = new int[]{0, 271, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JToolBar toolBar = new JToolBar();
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		getContentPane().add(toolBar, gbc_toolBar);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshInNewThread();
			}
		});
		btnRefresh.setIcon(new ImageIcon(TableViewer.class.getResource("/icons/arrow-circle-double-135.png")));
		toolBar.add(btnRefresh);
		
		JButton btnEditCell = new JButton("Edit cell");
		btnEditCell.setEnabled(false);
		btnEditCell.setIcon(new ImageIcon(TableViewer.class.getResource("/icons/table-select.png")));
		toolBar.add(btnEditCell);

		toolBar.addSeparator();
		
		JButton btnViewInTemplate = new JButton("View in Template");
		btnViewInTemplate.setEnabled(false);
		btnViewInTemplate.setIcon(new ImageIcon(TableViewer.class.getResource("/icons/blog--arrow.png")));
		toolBar.add(btnViewInTemplate);
		toolBar.addSeparator();
		
		JButton btnAddRow = new JButton("Add row");
		btnAddRow.setEnabled(false);
		btnAddRow.setIcon(new ImageIcon(TableViewer.class.getResource("/icons/table-insert-row.png")));
		toolBar.add(btnAddRow);
		
		JButton btnDeleteRow = new JButton("Delete row");
		btnDeleteRow.setEnabled(false);
		btnDeleteRow.setIcon(new ImageIcon(TableViewer.class.getResource("/icons/table-delete-row.png")));
		toolBar.add(btnDeleteRow);
		btnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRecordInNewThread();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		getContentPane().add(scrollPane, gbc_scrollPane);

		uiTable = new JTable(dbTable);
		uiTable.setColumnSelectionAllowed(true);
		uiTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		uiTable.setShowVerticalLines(true);
		uiTable.setShowHorizontalLines(true);
		uiTable.setBorder(null);
		uiTable.setCellSelectionEnabled(true);
		uiTable.setGridColor(Color.gray);
		uiTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(uiTable);
		
		setVisible(true);
		
		requestFocus();
	}

	public void addRecordInNewThread(){
		this.setVisible(false);
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() {
				addRecord();
				return null;
			}
		};
		worker.execute();
	}

	public void addRecord(){
		try {
			dbTable.getTableContents();
			setVisible(true);
		} catch (SQLException e) {
			vb.showErrorDialog(e);
			e.printStackTrace();
		}
	}
	

	public void refreshInNewThread(){
		this.setVisible(false);
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() {
				addRecord();
				return null;
			}
		};
		worker.execute();
	}

	public void refresh(){
		try {
			dbTable.getTableContents();
			setVisible(true);
		} catch (SQLException e) {
			vb.showErrorDialog(e);
			e.printStackTrace();
		}
	}
	
	public void deleteRecord(){
		
	}
	
}