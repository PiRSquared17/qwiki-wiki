package org.venipedia.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 * A simple Web browser based on code found at
 * http://jmvidal.cse.sc.edu/csce790/PS1/Browser.java.html
 * 
 * @author Ryan Muller
 * @author Jose M. Vidal
 * 
 */
public class SimpleBrowser extends JInternalFrame {
	private static final long serialVersionUID = -7611244598843233502L;
	private JTextField urlField;

	/**
	 * Create the frame.
	 */
	public SimpleBrowser(String url) {
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);

		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		urlField = new JTextField();
		urlField.setText(url);
		GridBagConstraints gbc_urlField = new GridBagConstraints();
		gbc_urlField.insets = new Insets(0, 0, 5, 0);
		gbc_urlField.fill = GridBagConstraints.HORIZONTAL;
		gbc_urlField.gridx = 0;
		gbc_urlField.gridy = 0;
		getContentPane().add(urlField, gbc_urlField);
		urlField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		getContentPane().add(scrollPane, gbc_scrollPane);

		JEditorPane webPane = new JEditorPane();
		scrollPane.setViewportView(webPane);
		webPane.setEditable(false);
		webPane.addHyperlinkListener(new LinkFollower(webPane, urlField));

		setPage(webPane, url);

		setVisible(true);

	}

	protected static void setPage(final JEditorPane jep, final String url) {
		new Thread(new Runnable() {
			public void run() {
				try {
					jep.setPage(url);
				} catch (IOException e) {
					System.err.println(e);
					System.exit(-1);
				}
			}
		}).start();

	}

	/** An inner class that listens for hyperlinkEvent. */
	class LinkFollower implements HyperlinkListener {
		protected JEditorPane jep;
		protected JTextField urlField;

		public LinkFollower(JEditorPane jep, JTextField urlField) {
			this.jep = jep;
			this.urlField = urlField;
		}

		/**
		 * The action is to show the page of the URL the user clicked on.
		 * 
		 * @param evt
		 *            the event. We only care when its type is ACTIVATED.
		 */
		public void hyperlinkUpdate(HyperlinkEvent evt) {
			if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
				try {
					String url = evt.getURL().toString();
					urlField.setText(url);
					setPage(jep, url);
				} catch (Exception e) {
					//System.out.println("ERROR: Trouble fetching url");
				}
			}
		}

	}

}
