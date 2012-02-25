package org.venipedia.ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JTextField;

public class XmlImportUi extends JInternalFrame {
	private JTextField txtKmlFile;
	private JTextField txtTableName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XmlImportUi frame = new XmlImportUi();
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
	public XmlImportUi() {
		setIconifiable(true);
		setClosable(true);
		setTitle("Import KML File");
		setFrameIcon(new ImageIcon(XmlImportUi.class.getResource("/icons/map-pin.png")));
		setBounds(100, 100, 485, 305);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblSelectKmlFile = new JLabel("Select KML file:");
		GridBagConstraints gbc_lblSelectKmlFile = new GridBagConstraints();
		gbc_lblSelectKmlFile.anchor = GridBagConstraints.EAST;
		gbc_lblSelectKmlFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectKmlFile.gridx = 0;
		gbc_lblSelectKmlFile.gridy = 0;
		getContentPane().add(lblSelectKmlFile, gbc_lblSelectKmlFile);
		
		txtKmlFile = new JTextField();
		GridBagConstraints gbc_txtKmlFile = new GridBagConstraints();
		gbc_txtKmlFile.insets = new Insets(0, 0, 5, 5);
		gbc_txtKmlFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtKmlFile.gridx = 1;
		gbc_txtKmlFile.gridy = 0;
		getContentPane().add(txtKmlFile, gbc_txtKmlFile);
		txtKmlFile.setColumns(20);
		
		JButton btnChoose = new JButton("Choose...");
		btnChoose.setIcon(new ImageIcon(XmlImportUi.class.getResource("/icons/folder-horizontal-open.png")));
		GridBagConstraints gbc_btnChoose = new GridBagConstraints();
		gbc_btnChoose.insets = new Insets(0, 0, 5, 0);
		gbc_btnChoose.gridx = 2;
		gbc_btnChoose.gridy = 0;
		getContentPane().add(btnChoose, gbc_btnChoose);
		
		JLabel lblTableName = new JLabel("Table name:");
		GridBagConstraints gbc_lblTableName = new GridBagConstraints();
		gbc_lblTableName.anchor = GridBagConstraints.EAST;
		gbc_lblTableName.insets = new Insets(0, 0, 5, 5);
		gbc_lblTableName.gridx = 0;
		gbc_lblTableName.gridy = 1;
		getContentPane().add(lblTableName, gbc_lblTableName);
		
		txtTableName = new JTextField();
		GridBagConstraints gbc_txtTableName = new GridBagConstraints();
		gbc_txtTableName.gridwidth = 2;
		gbc_txtTableName.insets = new Insets(0, 0, 5, 5);
		gbc_txtTableName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTableName.gridx = 1;
		gbc_txtTableName.gridy = 1;
		getContentPane().add(txtTableName, gbc_txtTableName);
		txtTableName.setColumns(10);
		
		JButton btnUploadKml = new JButton("Upload KML");
		btnUploadKml.setIcon(new ImageIcon(XmlImportUi.class.getResource("/icons/upload-cloud.png")));
		GridBagConstraints gbc_btnUploadKml = new GridBagConstraints();
		gbc_btnUploadKml.gridx = 2;
		gbc_btnUploadKml.gridy = 2;
		getContentPane().add(btnUploadKml, gbc_btnUploadKml);
		
		pack();

	}

}
