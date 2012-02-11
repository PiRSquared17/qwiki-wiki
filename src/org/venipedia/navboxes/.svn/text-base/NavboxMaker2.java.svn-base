package org.venipedia.navboxes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.sourceforge.jwbf.core.contentRep.SimpleArticle;
import net.sourceforge.jwbf.mediawiki.actions.editing.PostModifyContent;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;

import org.venipedia.CSVParser;

public class NavboxMaker2 extends JFrame {
	private static final long serialVersionUID = -6323503816918637664L;
	
	Hashtable<String, Vector<String>> map;
	String sideItem = "";

	private JPanel contentPane;
	private JTextField shortNameField;
	private JTextField titleField;

	/**
	 * Create the frame.
	 */
	public NavboxMaker2(File csvFile) {

		map = new Hashtable<String, Vector<String>>();

		try {
			BufferedReader in = new BufferedReader(new FileReader(csvFile));
			String str;
			boolean headers = true;
			while ((str = in.readLine()) != null) {
				if (headers) {
					headers = false;
					continue;
				}
				CSVParser parser = new CSVParser();
				String[] split = parser.parse(str);
				if (split == null || split.length < 2)
					continue;
				sideItem = split[0];
				add(escapeUnicode(split[0]), escapeUnicode(split[1]));
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNavboxName = new JLabel("Navbox short name:");
		GridBagConstraints gbc_lblNavboxName = new GridBagConstraints();
		gbc_lblNavboxName.anchor = GridBagConstraints.WEST;
		gbc_lblNavboxName.insets = new Insets(0, 0, 5, 5);
		gbc_lblNavboxName.gridx = 0;
		gbc_lblNavboxName.gridy = 0;
		contentPane.add(lblNavboxName, gbc_lblNavboxName);

		shortNameField = new JTextField();
		GridBagConstraints gbc_shortNameField = new GridBagConstraints();
		gbc_shortNameField.insets = new Insets(0, 0, 5, 0);
		gbc_shortNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_shortNameField.gridx = 1;
		gbc_shortNameField.gridy = 0;
		contentPane.add(shortNameField, gbc_shortNameField);
		shortNameField.setColumns(10);

		JLabel lblNavboxTitle = new JLabel("Navbox title:");
		GridBagConstraints gbc_lblNavboxTitle = new GridBagConstraints();
		gbc_lblNavboxTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblNavboxTitle.anchor = GridBagConstraints.EAST;
		gbc_lblNavboxTitle.gridx = 0;
		gbc_lblNavboxTitle.gridy = 1;
		contentPane.add(lblNavboxTitle, gbc_lblNavboxTitle);

		titleField = new JTextField();
		GridBagConstraints gbc_titleField = new GridBagConstraints();
		gbc_titleField.insets = new Insets(0, 0, 5, 0);
		gbc_titleField.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleField.gridx = 1;
		gbc_titleField.gridy = 1;
		contentPane.add(titleField, gbc_titleField);
		titleField.setColumns(10);

		JButton btnCreate = new JButton("Create!");
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.gridwidth = 2;
		gbc_btnCreate.insets = new Insets(0, 0, 0, 5);
		gbc_btnCreate.gridx = 0;
		gbc_btnCreate.gridy = 2;
		contentPane.add(btnCreate, gbc_btnCreate);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				publish();
			}
		});
	}

	public void add(String category, String item) {
		if (!map.containsKey(category))
			map.put(category, new Vector<String>(0, 1));
		map.get(category).add(item);
	}

	public void publish() {
		try {
			MediaWikiBot bot = new MediaWikiBot("http://www.venipedia.org/");
			bot.login("Ve11-maint-bot", "100institute");

			SimpleArticle article = new SimpleArticle("Template:"
					+ shortNameField.getText());
			article.addText("{{Navbox\n");
			article.addText("|bodyclass = \n");
			article.addText("|name = " + shortNameField.getText() + "\n");
			article.addText("|title = " + titleField.getText() + "\n");
			article.addText("|titleclass = \n");
			article.addText("|image = \n");
			article.addText("|above = \n");
			Vector<String> keyVector = new Vector<String>(0, 1);
			for (Enumeration<String> e = map.keys(); e.hasMoreElements();) {
				keyVector.add(e.nextElement());
			}
			String[] keys = new String[keyVector.size()];
			keys = keyVector.toArray(keys);
			Arrays.sort(keys);
			int n = 1;
			for (String key : keys) {
				if (keys.length > 1)
					article.addText("|group" + n + " = " + key + "\n");
				article.addText("|list" + n + " = ");
				boolean w = false;
				for (String item : map.get(key)) {
					if (w)
						article.addText("{{w}}");
					else
						w = true;
					article.addText("[[" + item + "]]");
				}
				n++;
			}

			article.addText("|below = \n}}\n<noinclude>[[Category:Navbox templates]]</noinclude>");
			article.setMinorEdit(false);

			PostModifyContent pmc = new PostModifyContent(bot, article);

			bot.performAction(pmc);

			JOptionPane.showMessageDialog(this,
					"<html><center>Navbox created! Use<br><br>{{"
							+ shortNameField.getText()
							+ "}}<br><br>to insert it into the wiki.");

			System.exit(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String escapeUnicode(String unicode) {
		try {
			return new String(unicode.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}

/*
 * {{Navbox |bodyclass = |name = Canals |title = Canals of Venice |titleclass =
 * |image = |above = |group1 = Canals |list1 =
 */