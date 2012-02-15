package org.venipedia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;

import org.venipedia.bot.VeniBot;
import org.venipedia.credentials.DatabaseCredentials;
import org.venipedia.credentials.VenipediaCredentials;
import org.venipedia.ui.ListPages;
import org.venipedia.ui.login.DatabaseLoginFrame;
import org.venipedia.ui.login.VenipediaLoginFrame;

public class QwikiWiki {

	private JFrame frmQwikiwiki;
	private JProgressBar progressBar;
	private JDesktopPane desktopPane;

	private DatabaseCredentials bCred;
	
	private VeniBot veniBot;

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
		veniBot = new VeniBot(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmQwikiwiki = new JFrame();
		frmQwikiwiki.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmQwikiwiki.setTitle("QwikiWiki");
		frmQwikiwiki.setIconImage(Toolkit.getDefaultToolkit().getImage(QwikiWiki.class.getResource("/venicon.png")));
		frmQwikiwiki.setBounds(100, 100, 627, 421);
		frmQwikiwiki.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		desktopPane = new JDesktopPane();
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
		
		JMenu mnConnect = new JMenu("Connect");
		mnConnect.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/direction.png")));
		popupMenu.add(mnConnect);
		
		JMenuItem mntmLogInTo = new JMenuItem("Log in to Venipedia");
		mntmLogInTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				venipediaLogIn();
			}
		});
		mntmLogInTo.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/monitor-image.png")));
		mnConnect.add(mntmLogInTo);
		
		JMenuItem mntmLogInTo_1 = new JMenuItem("Log in to Bluehost");
		mntmLogInTo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bluehostLogIn();
			}
		});
		mntmLogInTo_1.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/monitor-window.png")));
		mnConnect.add(mntmLogInTo_1);
		
		JMenu mnUpload = new JMenu("Upload");
		mnUpload.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/upload-cloud.png")));
		popupMenu.add(mnUpload);
		
		JMenuItem mntmTablecsv = new JMenuItem("Table (CSV)");
		mntmTablecsv.setEnabled(false);
		mntmTablecsv.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/table-excel.png")));
		mnUpload.add(mntmTablecsv);
		
		JMenuItem mntmGoogleEarthkml = new JMenuItem("Google Earth (KML)");
		mntmGoogleEarthkml.setEnabled(false);
		mntmGoogleEarthkml.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/globe-green.png")));
		mnUpload.add(mntmGoogleEarthkml);
		
		JMenuItem mntmFolderOfPictures = new JMenuItem("Folder of pictures");
		mntmFolderOfPictures.setEnabled(false);
		mntmFolderOfPictures.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/photo-album.png")));
		mnUpload.add(mntmFolderOfPictures);
		
		JMenu mnCreate = new JMenu("Create");
		mnCreate.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/block.png")));
		popupMenu.add(mnCreate);
		
		JMenuItem mntmPage = new JMenuItem("Page");
		mntmPage.setEnabled(false);
		mntmPage.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/blog-blue.png")));
		mnCreate.add(mntmPage);
		
		JMenuItem mntmPagesFromDatabase = new JMenuItem("Pages from database table");
		mntmPagesFromDatabase.setEnabled(false);
		mntmPagesFromDatabase.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/blogs-stack.png")));
		mnCreate.add(mntmPagesFromDatabase);
		
		JMenu mnOrganize = new JMenu("Organize");
		mnOrganize.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/arrow-switch.png")));
		popupMenu.add(mnOrganize);
		
		JMenuItem mntmCategorizePages = new JMenuItem("Categorize pages");
		mntmCategorizePages.setEnabled(false);
		mntmCategorizePages.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/drawer-open.png")));
		mnOrganize.add(mntmCategorizePages);
		
		JMenuItem mntmDeletePages = new JMenuItem("Delete pages");
		mntmDeletePages.setEnabled(false);
		mntmDeletePages.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/bomb.png")));
		mnOrganize.add(mntmDeletePages);
		
		JMenu mnView = new JMenu("View");
		mnView.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/magnifier.png")));
		popupMenu.add(mnView);
		
		JMenuItem mntmVenipedia = new JMenuItem("Venipedia");
		mntmVenipedia.setEnabled(false);
		mntmVenipedia.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/application-browser.png")));
		mnView.add(mntmVenipedia);
		
		JMenuItem mntmListPages = new JMenuItem("List pages");
		mntmListPages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listPages();
			}
		});
		mntmListPages.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/edit-list.png")));
		mnView.add(mntmListPages);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/question.png")));
		popupMenu.add(mnHelp);
		
		JMenuItem mntmReportBug = new JMenuItem("Report bug");
		mntmReportBug.setEnabled(false);
		mntmReportBug.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/bug.png")));
		mnHelp.add(mntmReportBug);
		
		JMenuItem mntmAboutQwikiwiki = new JMenuItem("About QwikiWiki");
		mntmAboutQwikiwiki.setIcon(new ImageIcon(QwikiWiki.class.getResource("/venicon.png")));
		mnHelp.add(mntmAboutQwikiwiki);
		
		JSeparator separator = new JSeparator();
		popupMenu.add(separator);
		mntmExit.setIcon(new ImageIcon(QwikiWiki.class.getResource("/icons/door-open-out.png")));
		popupMenu.add(mntmExit);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(128, 128, 128));
		progressBar.setBorderPainted(false);
		progressBar.setBorder(null);
		progressBar.setBackground(new Color(102, 0, 0));
		progressBar.setString("");
		progressBar.setStringPainted(true);
		frmQwikiwiki.getContentPane().add(progressBar, BorderLayout.SOUTH);
	}
	

	private void listPages() {
		ListPages lp = new ListPages(this);
		showWindow(lp);
		lp.setVisible(false);
	}

	public VeniBot getVeniBot() {
		return veniBot;
	}

	protected void bluehostLogIn() {
		showWindow(new DatabaseLoginFrame(this));
	}

	public void setStatus(String s){
		getProgressBar().setString(s);
		System.out.println(s);
	}
	
	protected void venipediaLogIn() {
		showWindow(new VenipediaLoginFrame(this));
	}

	private void showWindow(JInternalFrame frame) {
		getDesktopPane().add(frame);
		frame.setVisible(true);
	}

	protected void exit() {
		int q = JOptionPane.showConfirmDialog(getDesktopPane(), "Are you sure you want to exit?", "Confirm exit", JOptionPane.YES_NO_OPTION);
		if(q==JOptionPane.OK_OPTION)
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
	protected JProgressBar getProgressBar() {
		return progressBar;
	}
	protected JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public VenipediaCredentials getvCred() {
		return veniBot.getCreds();
	}

	public void setvCred(VenipediaCredentials vCred) {
		veniBot.setCreds(vCred);
	}

	public DatabaseCredentials getbCred() {
		return bCred;
	}

	public void setbCred(DatabaseCredentials bCred) {
		this.bCred = bCred;
	}
	
	public void setFrozen(boolean b){
		getProgressBar().setIndeterminate(b);
	}
}
