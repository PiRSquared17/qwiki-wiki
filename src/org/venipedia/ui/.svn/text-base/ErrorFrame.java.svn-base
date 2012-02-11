package org.venipedia.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ErrorFrame extends JInternalFrame {
	private static final long serialVersionUID = -8266852424226726338L;

	/**
	 * Create the frame.
	 */
	public ErrorFrame(Exception ex) {
		setTitle("Oops! An error has occurred.");
		setFrameIcon(new ImageIcon(
				ErrorFrame.class.getResource("/icons/exclamation-red.png")));
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 426 };
		gridBagLayout.rowHeights = new int[] { 254, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0 };
		getContentPane().setLayout(gridBagLayout);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridheight = 2;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		getContentPane().add(tabbedPane, gbc_tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab(
				"Error",
				new ImageIcon(ErrorFrame.class
						.getResource("/icons/bandaid--exclamation.png")),
				panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);

		JEditorPane lblErrorMessage = new JEditorPane();
		lblErrorMessage.setContentType("text/html");
		String errorInfo = "<html>Some sort of error has occurred. It's probably very technical and boring. "
				+ "Most likely, if you're in Venice, this was caused by a problem with your internet connection."
				+ "<br>If you really want to know, click the \"Details\" tab above for more information.<hr>"
				+ "<tt><big><br>"
				+ ex.getLocalizedMessage() + "</big></tt></html>";
		lblErrorMessage.setText(errorInfo);
		scrollPane.setViewportView(lblErrorMessage);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 1;
		panel.add(btnOk, gbc_btnOk);
		String trace = "";
		for(StackTraceElement el:ex.getStackTrace()){
			trace+=el.toString()+"\n";
		}
		
				JTextArea textArea = new JTextArea();
				textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
				textArea.setText(trace);
				
		JScrollPane scrollPane_1 = new JScrollPane(textArea);
		tabbedPane.addTab(
				"Details",
				new ImageIcon(ErrorFrame.class
						.getResource("/icons/blue-document-list.png")),
				scrollPane_1, null);
		

	}

}
