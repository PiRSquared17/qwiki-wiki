package org.venipedia.ui.jdbc;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;

import org.venipedia.dbcore.jdbc.JdbcDatabase;
import org.venipedia.dbcore.jdbc.JdbcTable;
import org.venipedia.record.TableDoneEditingListener;
import org.venipedia.record.TableSelectionListener;

public class TableSelectionFrame extends JInternalFrame implements
		TableDoneEditingListener {
	private static final long serialVersionUID = -7220163198302849031L;

	JdbcDatabase db;

	private DefaultListModel listModel;

	private TableSelectionListener selLis;

	JList list;
	JDesktopPane desktop;
	JButton btnSelect;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public TableSelectionFrame(JdbcDatabase db, JDesktopPane desktop,
			TableSelectionListener selLis) throws SQLException {
		setFrameIcon(new ImageIcon(
				TableSelectionFrame.class.getResource("/icons/table.png")));
		// get the frame all set up
		setTitle("Tables");
		this.desktop = desktop;
		this.selLis = selLis;
		this.db = db;
		setResizable(true);
		setBounds(100, 100, 275, 332);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);

		// Set up the scroll pane for the list
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);

		// Set up the list of datatypes and the corresponding model

		listModel = new DefaultListModel();

		loadTables();

		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);

		JButton btnCreateNew = new JButton("New table");
		btnCreateNew.setIcon(new ImageIcon(TableSelectionFrame.class
				.getResource("/icons/table--plus.png")));
		GridBagConstraints gbc_btnCreateNew = new GridBagConstraints();
		gbc_btnCreateNew.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCreateNew.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreateNew.gridx = 0;
		gbc_btnCreateNew.gridy = 1;
		getContentPane().add(btnCreateNew, gbc_btnCreateNew);
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newTable();
			}
		});

		JButton btnEdit = new JButton("Edit columns");
		btnEdit.setIcon(new ImageIcon(TableSelectionFrame.class
				.getResource("/icons/table--pencil.png")));
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEdit.insets = new Insets(0, 0, 5, 0);
		gbc_btnEdit.gridx = 1;
		gbc_btnEdit.gridy = 1;
		getContentPane().add(btnEdit, gbc_btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					doEditButton();
				} catch (SQLException e) {
					showErrorDialog(e);
					e.printStackTrace();
				}
			}
		});

		JButton btnDelete = new JButton("Delete table");
		btnDelete.setIcon(new ImageIcon(TableSelectionFrame.class
				.getResource("/icons/table--minus.png")));
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 2;
		getContentPane().add(btnDelete, gbc_btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doDeleteButton();
			}
		});

		btnSelect = new JButton("Select");
		btnSelect.setIcon(new ImageIcon(TableSelectionFrame.class
				.getResource("/icons/tick-circle.png")));
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSelect.insets = new Insets(0, 0, 5, 0);
		gbc_btnSelect.gridx = 1;
		gbc_btnSelect.gridy = 2;
		getContentPane().add(btnSelect, gbc_btnSelect);
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doSelectButton();
				} catch (SQLException e1) {
					showErrorDialog(e1);
					e1.printStackTrace();
				}
			}
		});

		desktop.add(this);

		setVisible(true);
	}

	private void showErrorDialog(Exception e) {
		db.showErrorDialog(e);
	}

	private void loadTables() {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() {
				try {
					listModel = db.getTableList();
					list.setModel(listModel);
				} catch (SQLException e) {
					db.showErrorDialog(e);
					e.printStackTrace();
				}
				return null;
			}
		};
		worker.execute();
	}

	private void newTable() {
		// this.setVisible(false);
		// Table type = new Table("Type name");
		// RecordTypeEditor ed = new RecordTypeEditor(db, type, this);
		// db.addTable(type);
		// ed.setVisible(true);
		// desktop.add(ed);
	}

	public JdbcTable getSelectedTable() throws SQLException {
		return db.getTableByIndex(list.getSelectedIndex());
	}

	private void doEditButton() throws SQLException {
		this.setVisible(false);
		JdbcTable table = getSelectedTable();
		TableStructureEditor ed = new TableStructureEditor(table, this);
		ed.setVisible(true);
		desktop.add(ed);
	}

	private void doSelectButton() throws SQLException {
		final JdbcTable t = db.getTableByIndex(list.getSelectedIndex());
		if (selLis != null) {
			SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
				protected Void doInBackground() {
					selLis.tableSelected(t);
					return null;
				}
			};
			worker.execute();
		}
		dispose();
	}

	private void doDeleteButton() {
		// int selectedIndex = list.getSelectedIndex();
		// Table type = db.getTableByIndex(selectedIndex);
		// db.removeTable(type);
	}

	public void tableDoneEditing() {
		setVisible(true);
		loadTables();
	}

	public JList getList() {
		return list;
	}
}
