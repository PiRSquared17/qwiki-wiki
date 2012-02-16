package org.venipedia.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.venipedia.QwikiWiki;
import org.venipedia.entities.DatabaseTable;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TableViewer extends JInternalFrame {
	private JTable table;

	private QwikiWiki parent;

	/**
	 * Create the frame.
	 */
	public TableViewer(QwikiWiki qw) {
		setTitle("Loading table...");
		setResizable(true);
		setFrameIcon(new ImageIcon(TableViewer.class.getResource("/icons/table.png")));
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		parent = qw;
		
		parent.setStatus("Displaying table...");
		
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.insets = new Insets(0, 0, 5, 5);
		gbc_btnClose.gridx = 0;
		gbc_btnClose.gridy = 0;
		getContentPane().add(btnClose, gbc_btnClose);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		ColumnsAutoSizer.sizeColumnsToFit(table);

		parent.setStatus("Enjoy your data.");
	}
	
	protected void closeWindow() {
		dispose();
	}

	public void setTable(DatabaseTable tb){
		setTitle(tb.getTitle());
		table.setModel(tb);
	}

}
