package org.venipedia.ui.template;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;

import org.venipedia.dbcore.jdbc.JdbcTemplate;

public class TemplateEditor extends JInternalFrame {
	private static final long serialVersionUID = -7998484936968131034L;
	private JTextField txtPageName;
	private JTextField txtTemplateName;
	private JEditorPane editorPane;
	
	JdbcTemplate template;

	/**
	 * Create the frame.
	 */
	public TemplateEditor(JdbcTemplate template) {
		this.template=template;
		
		setFrameIcon(new ImageIcon(TemplateEditor.class.getResource("/icons/blue-document--pencil.png")));
		setTitle("Editing template");
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setBounds(100, 100, 452, 521);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblPageNameTemplate = new JLabel("Page name template:");
		GridBagConstraints gbc_lblPageNameTemplate = new GridBagConstraints();
		gbc_lblPageNameTemplate.anchor = GridBagConstraints.EAST;
		gbc_lblPageNameTemplate.insets = new Insets(0, 0, 5, 5);
		gbc_lblPageNameTemplate.gridx = 0;
		gbc_lblPageNameTemplate.gridy = 0;
		getContentPane().add(lblPageNameTemplate, gbc_lblPageNameTemplate);
		
		txtPageName = new JTextField();
		txtPageName.setToolTipText("Example      Canal [Field::CanalName]");
		GridBagConstraints gbc_txtPageName = new GridBagConstraints();
		gbc_txtPageName.insets = new Insets(0, 0, 5, 5);
		gbc_txtPageName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPageName.gridx = 1;
		gbc_txtPageName.gridy = 0;
		getContentPane().add(txtPageName, gbc_txtPageName);
		txtPageName.setColumns(10);
		
		JButton btnInsertFieldPageName = new JButton("");
		btnInsertFieldPageName.setToolTipText("Add field to page name");
		btnInsertFieldPageName.setIcon(new ImageIcon(TemplateEditor.class.getResource("/icons/ui-combo-box-blue.png")));
		GridBagConstraints gbc_btnInsertFieldPageName = new GridBagConstraints();
		gbc_btnInsertFieldPageName.insets = new Insets(0, 0, 5, 5);
		gbc_btnInsertFieldPageName.gridx = 2;
		gbc_btnInsertFieldPageName.gridy = 0;
		getContentPane().add(btnInsertFieldPageName, gbc_btnInsertFieldPageName);
		
		JLabel lblTemplateName = new JLabel("Template name:");
		GridBagConstraints gbc_lblTemplateName = new GridBagConstraints();
		gbc_lblTemplateName.anchor = GridBagConstraints.EAST;
		gbc_lblTemplateName.insets = new Insets(0, 0, 5, 5);
		gbc_lblTemplateName.gridx = 0;
		gbc_lblTemplateName.gridy = 1;
		getContentPane().add(lblTemplateName, gbc_lblTemplateName);
		
		txtTemplateName = new JTextField();
		txtTemplateName.setToolTipText("Example:       Individual Canal Page");
		GridBagConstraints gbc_txtTemplateName = new GridBagConstraints();
		gbc_txtTemplateName.insets = new Insets(0, 0, 5, 5);
		gbc_txtTemplateName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTemplateName.gridx = 1;
		gbc_txtTemplateName.gridy = 1;
		getContentPane().add(txtTemplateName, gbc_txtTemplateName);
		txtTemplateName.setColumns(10);
		
		JButton btnHelp = new JButton("");
		btnHelp.setToolTipText("Help!");
		btnHelp.setIcon(new ImageIcon(TemplateEditor.class.getResource("/icons/question.png")));
		GridBagConstraints gbc_btnHelp = new GridBagConstraints();
		gbc_btnHelp.insets = new Insets(0, 0, 5, 5);
		gbc_btnHelp.gridx = 2;
		gbc_btnHelp.gridy = 1;
		getContentPane().add(btnHelp, gbc_btnHelp);
		
		JLabel lblTableToFill = new JLabel("  Table to fill in data from:");
		GridBagConstraints gbc_lblTableToFill = new GridBagConstraints();
		gbc_lblTableToFill.anchor = GridBagConstraints.EAST;
		gbc_lblTableToFill.insets = new Insets(0, 0, 5, 5);
		gbc_lblTableToFill.gridx = 0;
		gbc_lblTableToFill.gridy = 2;
		getContentPane().add(lblTableToFill, gbc_lblTableToFill);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		getContentPane().add(comboBox, gbc_comboBox);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		getContentPane().add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		panel.add(toolBar, BorderLayout.NORTH);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveTemplate();
			}
		});
		btnSave.setIcon(new ImageIcon(TemplateEditor.class.getResource("/icons/disk-black.png")));
		toolBar.add(btnSave);
		
		JButton btnPreview = new JButton("Preview");
		btnPreview.setIcon(new ImageIcon(TemplateEditor.class.getResource("/icons/blue-document-search-result.png")));
		btnPreview.setToolTipText("Preview how this template looks with a random table entry");
		toolBar.add(btnPreview);
		
		toolBar.addSeparator();
		
		JButton btnAddField = new JButton("Add field");
		btnAddField.setIcon(new ImageIcon(TemplateEditor.class.getResource("/icons/ui-combo-box-blue.png")));
		toolBar.add(btnAddField);
		
		editorPane = new JEditorPane();
		panel.add(editorPane, BorderLayout.CENTER);

	}

	protected void saveTemplate() {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() {
				template.setTemplateBody(editorPane.getText());
//				template.setTemplateTitle();
				return null;
			}
		};
		worker.execute();
	}
}
