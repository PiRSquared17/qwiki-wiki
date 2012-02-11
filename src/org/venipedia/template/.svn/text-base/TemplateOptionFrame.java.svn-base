package org.venipedia.template;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.venipedia.dbcore.jdbc.JdbcDatabase;

public class TemplateOptionFrame extends JInternalFrame {
	private static final long serialVersionUID = 8410935999136972908L;
	private JTextField pageTitleField;

	Template template;

	TemplateOptionFrameClosedListener clLis;
	JdbcDatabase db;
	
	JComboBox comboBox;
	
//	Vector<RecordTypeNamePin> pins;

	/**
	 * Create the frame.
	 */
	public TemplateOptionFrame(JdbcDatabase db, Template t,
			TemplateOptionFrameClosedListener clLis) {
		this.db=db;
		setIconifiable(true);
		setFrameIcon(new ImageIcon(
				TemplateOptionFrame.class
						.getResource("/icons/blog--pencil.png")));
		template = t;
		this.clLis = clLis;
		
		setTitle("Template options");
		setBounds(100, 100, 450, 140);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblRecordType = new JLabel("Record type:");
		GridBagConstraints gbc_lblRecordType = new GridBagConstraints();
		gbc_lblRecordType.anchor = GridBagConstraints.EAST;
		gbc_lblRecordType.insets = new Insets(0, 0, 5, 5);
		gbc_lblRecordType.gridx = 0;
		gbc_lblRecordType.gridy = 0;
		getContentPane().add(lblRecordType, gbc_lblRecordType);

		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		getContentPane().add(comboBox, gbc_comboBox);

		JLabel lblPageTitle = new JLabel("Page title:");
		GridBagConstraints gbc_lblPageTitle = new GridBagConstraints();
		gbc_lblPageTitle.anchor = GridBagConstraints.EAST;
		gbc_lblPageTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblPageTitle.gridx = 0;
		gbc_lblPageTitle.gridy = 1;
		getContentPane().add(lblPageTitle, gbc_lblPageTitle);

		pageTitleField = new JTextField();
		GridBagConstraints gbc_pageTitleField = new GridBagConstraints();
		gbc_pageTitleField.gridwidth = 2;
		gbc_pageTitleField.insets = new Insets(0, 0, 5, 5);
		gbc_pageTitleField.fill = GridBagConstraints.HORIZONTAL;
		gbc_pageTitleField.gridx = 1;
		gbc_pageTitleField.gridy = 1;
		getContentPane().add(pageTitleField, gbc_pageTitleField);
		pageTitleField.setColumns(10);
		pageTitleField.setText("");

		JButton btnFields = new JButton("Fields...");
		GridBagConstraints gbc_btnFields = new GridBagConstraints();
		gbc_btnFields.insets = new Insets(0, 0, 5, 0);
		gbc_btnFields.gridx = 3;
		gbc_btnFields.gridy = 1;
		getContentPane().add(btnFields, gbc_btnFields);

		JButton btnHelp = new JButton("Help");
		btnHelp.setIcon(new ImageIcon(TemplateOptionFrame.class
				.getResource("/icons/question.png")));
		GridBagConstraints gbc_btnHelp = new GridBagConstraints();
		gbc_btnHelp.insets = new Insets(0, 0, 0, 5);
		gbc_btnHelp.gridx = 0;
		gbc_btnHelp.gridy = 2;
		getContentPane().add(btnHelp, gbc_btnHelp);

		JButton btnOk = new JButton("OK");
		btnOk.setIcon(new ImageIcon(TemplateOptionFrame.class
				.getResource("/icons/tick-circle.png")));
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.insets = new Insets(0, 0, 0, 5);
		gbc_btnOk.gridx = 2;
		gbc_btnOk.gridy = 2;
		getContentPane().add(btnOk, gbc_btnOk);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onOk();
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setIcon(new ImageIcon(TemplateOptionFrame.class
				.getResource("/icons/cross-circle.png")));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridx = 3;
		gbc_btnCancel.gridy = 2;
		getContentPane().add(btnCancel, gbc_btnCancel);
		
		pageTitleField.setText(t.getName());
		
		populateTypeDropdown();
		
		setVisible(true);

		requestFocus();
	}
	
	public void populateTypeDropdown(){
//		Enumeration<Table> types = db.getRecordTypes();
//		pins = new Vector<RecordTypeNamePin>(0,1);
//		for(;types.hasMoreElements();){
//			pins.add(types.nextElement().getPin());
//		}
//		DefaultComboBoxModel model = new DefaultComboBoxModel(pins);
//		comboBox.setModel(model);
//		selectCorrectType();
	}

	public void selectCorrectType(){
//		for(RecordTypeNamePin pin:pins){
//			if(pin.getParent()==template.getRecordType()){
//				comboBox.setSelectedItem(pin);
//				break;
//			}
//		}
	}
	
	public void onOk(){
//		template.setName(pageTitleField.getText());
//		template.setTable(((RecordTypeNamePin)comboBox.getSelectedItem()).getParent());
//		clLis.optionFrameClosed();
//		dispose();
	}

}
