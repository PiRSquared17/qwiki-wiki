package org.venipedia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.JMenu;
import javax.swing.JSeparator;

public class QwikiWiki {

	private JFrame frmQwikiwiki;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QwikiWiki window = new QwikiWiki();
					window.frmQwikiwiki.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public QwikiWiki() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmQwikiwiki = new JFrame();
		frmQwikiwiki.setTitle("QwikiWiki");
		frmQwikiwiki.setIconImage(Toolkit.getDefaultToolkit().getImage(QwikiWiki.class.getResource("/venicon.png")));
		frmQwikiwiki.setBounds(100, 100, 627, 421);
		frmQwikiwiki.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(102, 0, 0));
		frmQwikiwiki.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(desktopPane, popupMenu);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exit();
			}
		});
		
		JMenu mnUpload = new JMenu("Upload");
		mnUpload.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/upload-cloud.png")));
		popupMenu.add(mnUpload);
		
		JMenuItem mntmTablecsv = new JMenuItem("Table (CSV)");
		mntmTablecsv.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/table-excel.png")));
		mnUpload.add(mntmTablecsv);
		
		JMenuItem mntmGoogleEarthkml = new JMenuItem("Google Earth (KML)");
		mntmGoogleEarthkml.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/globe-green.png")));
		mnUpload.add(mntmGoogleEarthkml);
		
		JMenuItem mntmFolderOfPictures = new JMenuItem("Folder of pictures");
		mntmFolderOfPictures.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/photo-album.png")));
		mnUpload.add(mntmFolderOfPictures);
		
		JMenu mnCreate = new JMenu("Create");
		mnCreate.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/block.png")));
		popupMenu.add(mnCreate);
		
		JMenuItem mntmPage = new JMenuItem("Page");
		mntmPage.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/blog-blue.png")));
		mnCreate.add(mntmPage);
		
		JMenuItem mntmPagesFromDatabase = new JMenuItem("Pages from database table");
		mntmPagesFromDatabase.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/blogs-stack.png")));
		mnCreate.add(mntmPagesFromDatabase);
		
		JMenu mnOrganize = new JMenu("Organize");
		mnOrganize.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/arrow-switch.png")));
		popupMenu.add(mnOrganize);
		
		JMenuItem mntmCategorizePages = new JMenuItem("Categorize pages");
		mntmCategorizePages.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/drawer-open.png")));
		mnOrganize.add(mntmCategorizePages);
		
		JMenuItem mntmDeletePages = new JMenuItem("Delete pages");
		mntmDeletePages.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/bomb.png")));
		mnOrganize.add(mntmDeletePages);
		
		JMenu mnView = new JMenu("View");
		mnView.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/magnifier.png")));
		popupMenu.add(mnView);
		
		JMenuItem mntmVenipedia = new JMenuItem("Venipedia");
		mntmVenipedia.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/application-browser.png")));
		mnView.add(mntmVenipedia);
		
		JMenuItem mntmListPages = new JMenuItem("List pages");
		mntmListPages.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/edit-list.png")));
		mnView.add(mntmListPages);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/question.png")));
		popupMenu.add(mnHelp);
		
		JMenuItem mntmReportBug = new JMenuItem("Report bug");
		mntmReportBug.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/bug.png")));
		mnHelp.add(mntmReportBug);
		
		JMenuItem mntmAboutQwikiwiki = new JMenuItem("About QwikiWiki");
		mntmAboutQwikiwiki.setIcon(new ImageIcon(QwikiWiki.class.getResource("/venicon.png")));
		mnHelp.add(mntmAboutQwikiwiki);
		
		JSeparator separator = new JSeparator();
		popupMenu.add(separator);
		mntmExit.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/door-open-out.png")));
		popupMenu.add(mntmExit);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBorderPainted(false);
		progressBar.setBorder(null);
		progressBar.setBackground(new Color(102, 0, 0));
		progressBar.setString("");
		progressBar.setStringPainted(true);
		frmQwikiwiki.getContentPane().add(progressBar, BorderLayout.SOUTH);
	}
	
	protected void exit() {
		System.exit(0);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
