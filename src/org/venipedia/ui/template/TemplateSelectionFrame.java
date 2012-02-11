package org.venipedia.ui.template;

import net.sourceforge.jwbf.mediawiki.actions.MediaWiki;
import net.sourceforge.jwbf.mediawiki.actions.queries.AllPageTitles;
import net.sourceforge.jwbf.mediawiki.actions.util.VersionException;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;

public class TemplateSelectionFrame extends JInternalFrame {
	private static final long serialVersionUID = 2007179066085757125L;

	private DefaultListModel listModel;
	MediaWikiBot bot;
	private JList list;

	/**
	 * Create the frame.
	 */
	public TemplateSelectionFrame(MediaWikiBot bot) {
		this.bot = bot;

		setTitle("Select template");
		setFrameIcon(new ImageIcon(
				TemplateSelectionFrame.class
						.getResource("/icons/blogs-stack.png")));
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);

		list = new JList();
		scrollPane.setViewportView(list);
		
		populateListModelThreaded();

		JButton btnSelect = new JButton("Select");
		btnSelect.setIcon(new ImageIcon(TemplateSelectionFrame.class
				.getResource("/icons/tick-circle.png")));
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.gridx = 0;
		gbc_btnSelect.gridy = 1;
		getContentPane().add(btnSelect, gbc_btnSelect);

		setVisible(true);
		
	}

	public void populateListModelThreaded() {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() {
				listModel = populateListModel();
				list.setModel(listModel);
				return null;
			}
		};
		worker.execute();
	}

	private DefaultListModel populateListModel() {
		DefaultListModel model = new DefaultListModel();
		try {
			AllPageTitles apt = new AllPageTitles(bot, MediaWiki.NS_TEMPLATE);
			for (String articleName : apt) {
				model.addElement(articleName);
			}
		} catch (VersionException e) {
			e.printStackTrace();
			model.addElement("There's been some horrible mistake.");
		}
		return model;
	}

	public JList getList() {
		return list;
	}
}
