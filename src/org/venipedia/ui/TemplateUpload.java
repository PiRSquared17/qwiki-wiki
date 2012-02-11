package org.venipedia.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class TemplateUpload extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8857367647498060172L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TemplateUpload frame = new TemplateUpload();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TemplateUpload() {
		setTitle("Fill template");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TemplateUpload.class.getResource("/icons/blog--arrow.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblCsvFile = new JLabel("CSV file:");
		GridBagConstraints gbc_lblCsvFile = new GridBagConstraints();
		gbc_lblCsvFile.anchor = GridBagConstraints.EAST;
		gbc_lblCsvFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblCsvFile.gridx = 0;
		gbc_lblCsvFile.gridy = 0;
		contentPane.add(lblCsvFile, gbc_lblCsvFile);
		
		JButton btnSelectFile = new JButton("Select file...");
		btnSelectFile.setIcon(new ImageIcon(TemplateUpload.class.getResource("/icons/document-excel-csv.png")));
		GridBagConstraints gbc_btnSelectFile = new GridBagConstraints();
		gbc_btnSelectFile.anchor = GridBagConstraints.WEST;
		gbc_btnSelectFile.insets = new Insets(0, 0, 5, 0);
		gbc_btnSelectFile.gridx = 1;
		gbc_btnSelectFile.gridy = 0;
		contentPane.add(btnSelectFile, gbc_btnSelectFile);
		
		JLabel lblXmlFile = new JLabel("XML file:");
		GridBagConstraints gbc_lblXmlFile = new GridBagConstraints();
		gbc_lblXmlFile.anchor = GridBagConstraints.EAST;
		gbc_lblXmlFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblXmlFile.gridx = 0;
		gbc_lblXmlFile.gridy = 1;
		contentPane.add(lblXmlFile, gbc_lblXmlFile);
		
		JButton btnSelectFile_1 = new JButton("Select file...");
		btnSelectFile_1.setIcon(new ImageIcon(TemplateUpload.class.getResource("/icons/document-code.png")));
		GridBagConstraints gbc_btnSelectFile_1 = new GridBagConstraints();
		gbc_btnSelectFile_1.anchor = GridBagConstraints.WEST;
		gbc_btnSelectFile_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnSelectFile_1.gridx = 1;
		gbc_btnSelectFile_1.gridy = 1;
		contentPane.add(btnSelectFile_1, gbc_btnSelectFile_1);
		
		JLabel lblTemplate = new JLabel("Template:");
		GridBagConstraints gbc_lblTemplate = new GridBagConstraints();
		gbc_lblTemplate.anchor = GridBagConstraints.EAST;
		gbc_lblTemplate.insets = new Insets(0, 0, 5, 5);
		gbc_lblTemplate.gridx = 0;
		gbc_lblTemplate.gridy = 2;
		contentPane.add(lblTemplate, gbc_lblTemplate);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		contentPane.add(comboBox, gbc_comboBox);
		
		JLabel lblWikiTitlePrefix = new JLabel("Wiki title prefix:");
		GridBagConstraints gbc_lblWikiTitlePrefix = new GridBagConstraints();
		gbc_lblWikiTitlePrefix.anchor = GridBagConstraints.EAST;
		gbc_lblWikiTitlePrefix.insets = new Insets(0, 0, 5, 5);
		gbc_lblWikiTitlePrefix.gridx = 0;
		gbc_lblWikiTitlePrefix.gridy = 3;
		contentPane.add(lblWikiTitlePrefix, gbc_lblWikiTitlePrefix);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnGo = new JButton("Go!");
		btnGo.setIcon(new ImageIcon(TemplateUpload.class.getResource("/icons/arrow-curve-000-double.png")));
		GridBagConstraints gbc_btnGo = new GridBagConstraints();
		gbc_btnGo.fill = GridBagConstraints.VERTICAL;
		gbc_btnGo.gridwidth = 2;
		gbc_btnGo.gridx = 0;
		gbc_btnGo.gridy = 4;
		contentPane.add(btnGo, gbc_btnGo);
	}

}
