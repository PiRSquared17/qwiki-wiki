package org.venipedia.template;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import org.venipedia.dbcore.jdbc.JdbcDatabase;
import org.venipedia.template.TemplateFieldSelector.FieldSelectorListener;

public class TemplateBuilderFrame extends JInternalFrame implements
		TemplateOptionFrameClosedListener, FieldSelectorListener {
	private static final long serialVersionUID = 7667672044853173617L;

	Template template;

	JDesktopPane desktop;
	JdbcDatabase db;

	JTextArea textArea;

	/**
	 * Create the frame.
	 */
	public TemplateBuilderFrame(Template template, JdbcDatabase db,
			JDesktopPane desktop) {
		this.desktop = desktop;
		this.db = db;
		this.template = template;

		setFrameIcon(new ImageIcon(
				TemplateBuilderFrame.class.getResource("/icons/blog.png")));
		setClosable(true);
		setIconifiable(true);
		setResizable(true);
		setTitle("Edit template");
		setBounds(100, 100, 450, 300);

		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);

		JButton btnSave = new JButton("Save");
		btnSave.setIcon(new ImageIcon(TemplateBuilderFrame.class
				.getResource("/icons/disk.png")));
		toolBar.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});

		JButton btnHelp = new JButton("Help");
		btnHelp.setIcon(new ImageIcon(TemplateBuilderFrame.class
				.getResource("/icons/question.png")));
		toolBar.add(btnHelp);

		toolBar.addSeparator();

		JButton btnInsertField = new JButton("Insert Field");
		btnInsertField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insertField();
			}
		});
		btnInsertField.setIcon(new ImageIcon(TemplateBuilderFrame.class
				.getResource("/icons/book-open-list.png")));
		toolBar.add(btnInsertField);

		JPanel panel = new JPanel();
		toolBar.add(panel);

		JButton btnOptions = new JButton("Options");
		btnOptions.setIcon(new ImageIcon(TemplateBuilderFrame.class
				.getResource("/icons/blog--pencil.png")));
		toolBar.add(btnOptions);
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showOptions();
			}
		});

		JButton btnPreview = new JButton("Preview");
		btnPreview.setIcon(new ImageIcon(TemplateBuilderFrame.class
				.getResource("/icons/magnifier-left.png")));
		toolBar.add(btnPreview);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scrollPane.setViewportView(textArea);

		textArea.setText(template.getBody());

		setVisible(true);

	}

	public void save() {
		template.setBody(textArea.getText());
	}

	public void showOptions() {
//		setVisible(false);
//		TemplateOptionFrame optFrame = new TemplateOptionFrame(db, template,
//				this);
//		optFrame.setLocation(getLocation());
//		desktop.add(optFrame);
	}

	public void optionFrameClosed() {
		setVisible(true);
	}
	
	public void insertField(){
//		TemplateFieldSelector sel = new TemplateFieldSelector(this, template.getRecordType(), this);
//		setVisible(false);
//		desktop.add(sel);
//		sel.setVisible(true);
	}

	public void fieldSelected(long id) {
//		if (id < 0)
//			return;
//		Table type = template.getRecordType();
//		String printVal = "<!--$$ ";
//		printVal+=type.getName()+" ";
//		printVal+=type.getFieldNameByID(id)+" @";
//		printVal+=type.getId()+":";
//		printVal+=id+"$$-->";
//		textArea.insert(printVal, textArea.getCaretPosition());
		setVisible(true);
	}
}
