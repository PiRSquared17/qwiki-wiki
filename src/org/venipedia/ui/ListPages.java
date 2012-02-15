package org.venipedia.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

import org.venipedia.QwikiWiki;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListPages extends JInternalFrame {

	private QwikiWiki parent;
	private JList pagesList;

	/**
	 * Create the frame.
	 */
	public ListPages(QwikiWiki p) {
		setMaximizable(true);
		parent = p;
		
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		setTitle("List pages");
		setFrameIcon(new ImageIcon(ListPages.class.getResource("/icons/edit-list.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pagesTab = new JPanel();
		getContentPane().add(pagesTab, BorderLayout.CENTER);
		GridBagLayout gbl_pagesTab = new GridBagLayout();
		gbl_pagesTab.columnWidths = new int[]{0, 0, 0, 0};
		gbl_pagesTab.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_pagesTab.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_pagesTab.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		pagesTab.setLayout(gbl_pagesTab);
		
		JLabel lblPages = new JLabel("Pages:");
		GridBagConstraints gbc_lblPages = new GridBagConstraints();
		gbc_lblPages.insets = new Insets(0, 0, 5, 5);
		gbc_lblPages.gridx = 0;
		gbc_lblPages.gridy = 0;
		pagesTab.add(lblPages, gbc_lblPages);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		pagesTab.add(scrollPane, gbc_scrollPane);
		
		pagesList = new JList();
		scrollPane.setViewportView(pagesList);
		
		JButton btnNewPage = new JButton("New page");
		btnNewPage.setMnemonic('n');
		btnNewPage.setIcon(new ImageIcon(ListPages.class.getResource("/icons/blue-document--plus.png")));
		GridBagConstraints gbc_btnNewPage = new GridBagConstraints();
		gbc_btnNewPage.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewPage.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewPage.gridx = 2;
		gbc_btnNewPage.gridy = 0;
		pagesTab.add(btnNewPage, gbc_btnNewPage);
		
		JButton btnEditPage = new JButton("Edit page");
		btnEditPage.setMnemonic('e');
		btnEditPage.setIcon(new ImageIcon(ListPages.class.getResource("/icons/blue-document--pencil.png")));
		GridBagConstraints gbc_btnEditPage = new GridBagConstraints();
		gbc_btnEditPage.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEditPage.insets = new Insets(0, 0, 5, 0);
		gbc_btnEditPage.gridx = 2;
		gbc_btnEditPage.gridy = 1;
		pagesTab.add(btnEditPage, gbc_btnEditPage);
		
		JButton btnView = new JButton("View");
		btnView.setMnemonic('v');
		btnView.setIcon(new ImageIcon(ListPages.class.getResource("/icons/application-text.png")));
		GridBagConstraints gbc_btnView = new GridBagConstraints();
		gbc_btnView.insets = new Insets(0, 0, 5, 0);
		gbc_btnView.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnView.gridx = 2;
		gbc_btnView.gridy = 2;
		pagesTab.add(btnView, gbc_btnView);
		
		JButton btnViewInBrowser = new JButton("View in browser");
		btnViewInBrowser.setMnemonic('b');
		btnViewInBrowser.setIcon(new ImageIcon(ListPages.class.getResource("/icons/application-blog.png")));
		GridBagConstraints gbc_btnViewInBrowser = new GridBagConstraints();
		gbc_btnViewInBrowser.insets = new Insets(0, 0, 5, 0);
		gbc_btnViewInBrowser.gridx = 2;
		gbc_btnViewInBrowser.gridy = 3;
		pagesTab.add(btnViewInBrowser, gbc_btnViewInBrowser);
		
		JButton btnDeletePage = new JButton("Delete page");
		btnDeletePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteSelection();
			}
		});
		btnDeletePage.setMnemonic('d');
		btnDeletePage.setIcon(new ImageIcon(ListPages.class.getResource("/icons/blue-document--minus.png")));
		GridBagConstraints gbc_btnDeletePage = new GridBagConstraints();
		gbc_btnDeletePage.anchor = GridBagConstraints.SOUTH;
		gbc_btnDeletePage.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeletePage.gridx = 2;
		gbc_btnDeletePage.gridy = 4;
		pagesTab.add(btnDeletePage, gbc_btnDeletePage);
		
		populatePagesList();

	}
	
	private void deleteSelection(){
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() {
				deleteSelectionInner();
				return null;
			}
		};
		worker.execute();
	}
	
	private void deleteSelectionInner(){
		Object[] selection = getPagesList().getSelectedValues();
		parent.getVeniBot().deletePages(selection);
	}

	public void populatePagesList(){
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() {
				populatePagesListInner();
				return null;
			}
		};
		worker.execute();
	}
	
	private void populatePagesListInner(){
		setVisible(false);
		parent.setStatus("Downloading page names...");
		getPagesList().setListData(parent.getVeniBot().listPages().toArray());
		setVisible(true);
		parent.setStatus("Done.");
	}
	
	protected JList getPagesList() {
		return pagesList;
	}
}
