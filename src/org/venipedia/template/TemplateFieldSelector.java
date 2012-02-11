package org.venipedia.template;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;

import org.venipedia.dbcore.jdbc.JdbcTable;

public class TemplateFieldSelector extends JInternalFrame {
	private static final long serialVersionUID = 8477106051398209299L;

	
	FieldSelectorListener lsn;
	
	DefaultComboBoxModel model;
	JComboBox comboBox;
	
	/**
	 * Create the frame.
	 */
	public TemplateFieldSelector(JInternalFrame parent, JdbcTable rtype, FieldSelectorListener lsn) {
		this.lsn=lsn;
		setResizable(true);
		setTitle("Insert Field");
		setFrameIcon(new ImageIcon(TemplateFieldSelector.class.getResource("/icons/application-form.png")));
		setBounds(100, 100, 450, 115);
		setLocation(parent.getLocation());
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		model = new DefaultComboBoxModel();
//		for(Field f: rtype.getFieldTypes()){
//			model.addElement(f.getPin());
//		}
		
		comboBox = new JComboBox(model);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		getContentPane().add(comboBox, gbc_comboBox);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setIcon(new ImageIcon(TemplateFieldSelector.class.getResource("/icons/tick-button.png")));
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insertButtonClicked();
			}
		});
		GridBagConstraints gbc_btnInsert = new GridBagConstraints();
		gbc_btnInsert.insets = new Insets(0, 0, 0, 5);
		gbc_btnInsert.gridx = 0;
		gbc_btnInsert.gridy = 1;
		getContentPane().add(btnInsert, gbc_btnInsert);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setIcon(new ImageIcon(TemplateFieldSelector.class.getResource("/icons/cross-button.png")));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelButtonClicked();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 1;
		getContentPane().add(btnCancel, gbc_btnCancel);

	}
	
	public interface FieldSelectorListener{
		public void fieldSelected(long id);
	}

	public void insertButtonClicked(){
//		lsn.fieldSelected(((FieldPin)comboBox.getSelectedItem()).getParent().getID());
//		dispose();
	}
	
	public void cancelButtonClicked(){
		lsn.fieldSelected(-1);
		dispose();
	}
	
}
