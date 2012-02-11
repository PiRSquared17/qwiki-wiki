

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sourceforge.jwbf.mediawiki.actions.MediaWiki;
import net.sourceforge.jwbf.mediawiki.actions.editing.PostDelete;
import net.sourceforge.jwbf.mediawiki.actions.queries.AllPageTitles;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;

import org.venipedia.WaitingWindow;
import javax.swing.ImageIcon;

public class DeleteBot {

	private JFrame frmVenipediaManager;

	private MediaWikiBot bot;

	private JList articleList;
	private DefaultListModel listModel;

	WaitingWindow w;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteBot window = new DeleteBot();
					window.frmVenipediaManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DeleteBot() {

		WaitingWindow w = new WaitingWindow();
		w.setVisible(true);

		initialize();
		loadArticles();

		w.kill();
	}

	private void loadArticles() {

		try {
			bot = new MediaWikiBot("http://www.venipedia.org/");
			bot.login("Ve11-maint-bot", "100institute");

			AllPageTitles apt = new AllPageTitles(bot, MediaWiki.NS_MAIN);
			int i = 0;
			for (String articleName : apt) {
				// //System.out.println(articleName);
				listModel.add(i++, articleName);
			}

			frmVenipediaManager.setVisible(true);

			// w.setVisible(false);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVenipediaManager = new JFrame();
		frmVenipediaManager.setTitle("Venipedia Manager");
		frmVenipediaManager.setBounds(100, 100, 450, 300);
		frmVenipediaManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmVenipediaManager.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0};
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		frmVenipediaManager.getContentPane().setLayout(gridBagLayout);

		JLabel lblArticles = new JLabel("Articles");
		GridBagConstraints gbc_lblArticles = new GridBagConstraints();
		gbc_lblArticles.insets = new Insets(0, 0, 5, 5);
		gbc_lblArticles.gridx = 0;
		gbc_lblArticles.gridy = 0;
		frmVenipediaManager.getContentPane().add(lblArticles, gbc_lblArticles);

		listModel = new DefaultListModel();
		
				JButton btnDeleteArticle = new JButton("Delete article");
				btnDeleteArticle.setIcon(new ImageIcon(DeleteBot.class.getResource("/icons/bin.png")));
				GridBagConstraints gbc_btnDeleteArticle = new GridBagConstraints();
				gbc_btnDeleteArticle.insets = new Insets(0, 0, 5, 5);
				gbc_btnDeleteArticle.gridx = 0;
				gbc_btnDeleteArticle.gridy = 1;
				frmVenipediaManager.getContentPane().add(btnDeleteArticle,
						gbc_btnDeleteArticle);
				btnDeleteArticle.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						deleteArticle((String) articleList.getSelectedValue());
					}
				});
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridheight = 3;
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 2;
		frmVenipediaManager.getContentPane()
				.add(scrollPane_1, gbc_scrollPane_1);
		JList list = new JList(listModel);
		scrollPane_1.setViewportView(list);
		articleList = list;
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				// onArticleSelect((String)articleList.getSelectedValue());
			}
		});
	}

	protected void deleteArticle(String articleName) {
		try {
			bot.performAction(new PostDelete(bot, articleName));
			listModel.removeElement(articleName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// protected void onArticleSelect(String articleName) {
	// try{
	// SimpleArticle article = new SimpleArticle(bot.readContent(articleName));
	// // editorPane.setPage(new URL("http://www.java2s.com"));
	// }catch(Exception e){
	// e.printStackTrace();
	// }
	// }

}
