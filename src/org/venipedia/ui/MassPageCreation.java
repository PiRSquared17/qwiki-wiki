package org.venipedia.ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JComboBox;

public class MassPageCreation extends JInternalFrame {
	private static final long serialVersionUID = -3512602067595201245L;

	/**
	 * Create the frame.
	 */
	public MassPageCreation() {
		setTitle("Mass-upload pages");
		setFrameIcon(new ImageIcon(MassPageCreation.class.getResource("/icons/blogs-stack.png")));
		setClosable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, "name_178220776614637");
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblSelectUploadTemplate = new JLabel("Select upload specification:");
		GridBagConstraints gbc_lblSelectUploadTemplate = new GridBagConstraints();
		gbc_lblSelectUploadTemplate.anchor = GridBagConstraints.EAST;
		gbc_lblSelectUploadTemplate.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectUploadTemplate.gridx = 0;
		gbc_lblSelectUploadTemplate.gridy = 0;
		panel.add(lblSelectUploadTemplate, gbc_lblSelectUploadTemplate);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel.add(comboBox, gbc_comboBox);
		
		JButton btnEdit = new JButton("Edit...");
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.insets = new Insets(0, 0, 5, 0);
		gbc_btnEdit.gridx = 2;
		gbc_btnEdit.gridy = 0;
		panel.add(btnEdit, gbc_btnEdit);
		
		JButton btnNext1 = new JButton("Next");
		GridBagConstraints gbc_btnNext1 = new GridBagConstraints();
		gbc_btnNext1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNext1.anchor = GridBagConstraints.EAST;
		gbc_btnNext1.gridwidth = 3;
		gbc_btnNext1.gridx = 2;
		gbc_btnNext1.gridy = 1;
		panel.add(btnNext1, gbc_btnNext1);

	}

}
