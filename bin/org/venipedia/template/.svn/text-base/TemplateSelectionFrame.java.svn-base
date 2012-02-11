package org.venipedia.template;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.venipedia.dbcore.jdbc.JdbcDatabase;

public class TemplateSelectionFrame extends JInternalFrame {
	private static final long serialVersionUID = -7220163198302849031L;

	JdbcDatabase db;

	private DefaultListModel listModel;

	JList list;
	JDesktopPane desktop;

	/**
	 * Create the frame.
	 */
	public TemplateSelectionFrame(JdbcDatabase db, JDesktopPane desktop) {
		setFrameIcon(new ImageIcon(
				TemplateSelectionFrame.class.getResource("/icons/blogs.png")));
		setClosable(true);
		setIconifiable(true);
		// get the frame all set up
		setTitle("Templates");
		this.desktop = desktop;
		this.db = db;
		setResizable(true);
		setBounds(100, 100, 288, 332);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);

		// Set up the scroll pane for the list
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);

		// Set up the list of datatypes and the corresponding model
		listModel = new DefaultListModel();
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		loadTemplates();

		JButton btnCreateNew = new JButton("Create new template");
		btnCreateNew.setIcon(new ImageIcon(TemplateSelectionFrame.class
				.getResource("/icons/blog--plus.png")));
		GridBagConstraints gbc_btnCreateNew = new GridBagConstraints();
		gbc_btnCreateNew.insets = new Insets(0, 0, 5, 0);
		gbc_btnCreateNew.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCreateNew.gridwidth = 2;
		gbc_btnCreateNew.gridx = 0;
		gbc_btnCreateNew.gridy = 1;
		getContentPane().add(btnCreateNew, gbc_btnCreateNew);
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newTemplate();
			}
		});

		JButton btnEdit = new JButton("Edit template");
		btnEdit.setIcon(new ImageIcon(TemplateSelectionFrame.class
				.getResource("/icons/blog--pencil.png")));
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEdit.insets = new Insets(0, 0, 5, 5);
		gbc_btnEdit.gridx = 0;
		gbc_btnEdit.gridy = 2;
		getContentPane().add(btnEdit, gbc_btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doEditButton();
			}
		});

		JButton btnDelete = new JButton("Delete template");
		btnDelete.setIcon(new ImageIcon(TemplateSelectionFrame.class
				.getResource("/icons/blog--minus.png")));
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.gridx = 1;
		gbc_btnDelete.gridy = 2;
		getContentPane().add(btnDelete, gbc_btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doDeleteButton();
			}
		});
		
		loadTemplates();

		desktop.add(this);
		
		setVisible(true);
	}

	/**
	 * Loads the templates into the JList component to allow the user to select
	 * one.
	 */
	private void loadTemplates() {
//		listModel.clear();
//
//		int len = db.getNumberOfTemplates();
//
//		// Add all of the templates to the list model
//		for (int i = 0; i < len; i++) {
//			Template template = db.getTemplateByIndex(i);
//			listModel.add(i, template.getName());
//		}
	}

	private void newTemplate() {
//		this.setVisible(false);
//		if (db == null || db.getNumberOfRecordTypes() == 0) {
//			JOptionPane
//					.showMessageDialog(
//							desktop,
//							"The database is empty. Please connect to a database to create templates.",
//							"Null Database", JOptionPane.WARNING_MESSAGE);
//			return;
//		}
//		Template template = db.addNewTemplate(null);
//		TemplateBuilderFrame tbf = new TemplateBuilderFrame(template, db,
//				desktop);
//		desktop.add(tbf);
//		tbf.showOptions();
//		dispose();
	}

	private void doEditButton() {
//		this.setVisible(false);
//		Template template = db.getTemplateByIndex(list.getSelectedIndex());
//		TemplateBuilderFrame tbf = new TemplateBuilderFrame(template, db,
//				desktop);
//		desktop.add(tbf);
//		dispose();
	}

	private void doDeleteButton() {
//		int selectedIndex = list.getSelectedIndex();
//		Table type = db.getTableByIndex(selectedIndex);
//		db.removeTable(type);
//		loadTemplates();
	}

}
