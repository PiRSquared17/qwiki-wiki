package org.venipedia.ui.template;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewTemplateFrame extends JInternalFrame {
	private static final long serialVersionUID = -2177070560644783458L;
	private JTextField txtTemplateName;
	private JComboBox comboBox;

	/**
	 * Create the frame.
	 */
	public NewTemplateFrame() {
		setTitle("Create new template");
		setFrameIcon(new ImageIcon(NewTemplateFrame.class.getResource("/icons/blue-document--plus.png")));
		setBounds(100, 100, 450, 218);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblTemplateName = new JLabel("Template name:");
		GridBagConstraints gbc_lblTemplateName = new GridBagConstraints();
		gbc_lblTemplateName.insets = new Insets(0, 0, 5, 5);
		gbc_lblTemplateName.anchor = GridBagConstraints.EAST;
		gbc_lblTemplateName.gridx = 0;
		gbc_lblTemplateName.gridy = 0;
		getContentPane().add(lblTemplateName, gbc_lblTemplateName);
		
		txtTemplateName = new JTextField();
		GridBagConstraints gbc_txtTemplateName = new GridBagConstraints();
		gbc_txtTemplateName.gridwidth = 2;
		gbc_txtTemplateName.insets = new Insets(0, 0, 5, 0);
		gbc_txtTemplateName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTemplateName.gridx = 1;
		gbc_txtTemplateName.gridy = 0;
		getContentPane().add(txtTemplateName, gbc_txtTemplateName);
		txtTemplateName.setColumns(10);
		
		JLabel lblTemplateNameNote = new JLabel("<html><p align=\"center\">Note: this would be something like <tt>Individial Canal Page</tt>, <tt>Fountain Page</tt> or something similar\u2014some generic way of identifying this template.</p></html>");
		GridBagConstraints gbc_lblTemplateNameNote = new GridBagConstraints();
		gbc_lblTemplateNameNote.insets = new Insets(0, 0, 5, 0);
		gbc_lblTemplateNameNote.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTemplateNameNote.gridwidth = 3;
		gbc_lblTemplateNameNote.gridx = 0;
		gbc_lblTemplateNameNote.gridy = 1;
		getContentPane().add(lblTemplateNameNote, gbc_lblTemplateNameNote);
		
		JLabel lblSourceTable = new JLabel("Source table:");
		GridBagConstraints gbc_lblSourceTable = new GridBagConstraints();
		gbc_lblSourceTable.anchor = GridBagConstraints.EAST;
		gbc_lblSourceTable.insets = new Insets(0, 0, 5, 5);
		gbc_lblSourceTable.gridx = 0;
		gbc_lblSourceTable.gridy = 2;
		getContentPane().add(lblSourceTable, gbc_lblSourceTable);
		
		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		getContentPane().add(comboBox, gbc_comboBox);
		
		JLabel lblSourceTableNote = new JLabel("<html><center>This is the table where you'll be taking your data from.</center></html>");
		GridBagConstraints gbc_lblSourceTableNote = new GridBagConstraints();
		gbc_lblSourceTableNote.gridwidth = 2;
		gbc_lblSourceTableNote.insets = new Insets(0, 0, 5, 0);
		gbc_lblSourceTableNote.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSourceTableNote.gridx = 1;
		gbc_lblSourceTableNote.gridy = 3;
		getContentPane().add(lblSourceTableNote, gbc_lblSourceTableNote);
		
		JButton btnCreateTemplate = new JButton("Create template");
		btnCreateTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createTemplate();
			}
		});
		btnCreateTemplate.setIcon(new ImageIcon(NewTemplateFrame.class.getResource("/icons/tick-circle.png")));
		GridBagConstraints gbc_btnCreateTemplate = new GridBagConstraints();
		gbc_btnCreateTemplate.insets = new Insets(0, 0, 0, 5);
		gbc_btnCreateTemplate.gridx = 1;
		gbc_btnCreateTemplate.gridy = 4;
		getContentPane().add(btnCreateTemplate, gbc_btnCreateTemplate);
		
		JButton btnNeverMind = new JButton("Never mind");
		btnNeverMind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNeverMind.setIcon(new ImageIcon(NewTemplateFrame.class.getResource("/icons/cross-circle.png")));
		GridBagConstraints gbc_btnNeverMind = new GridBagConstraints();
		gbc_btnNeverMind.gridx = 2;
		gbc_btnNeverMind.gridy = 4;
		getContentPane().add(btnNeverMind, gbc_btnNeverMind);
		
		setVisible(true);

	}

	protected void createTemplate() {
		String templateName = txtTemplateName.getText();
		String tableName = (String)comboBox.getSelectedItem();
	}

	public JTextField getTxtTemplateName() {
		return txtTemplateName;
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
}
