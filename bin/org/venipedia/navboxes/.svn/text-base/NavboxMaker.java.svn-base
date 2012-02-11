package org.venipedia.navboxes;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import org.venipedia.importer.FileTypeFilter;

public class NavboxMaker extends JFrame {
	private static final long serialVersionUID = 5920041528638416260L;
	private JPanel contentPane;
	
	JFileChooser chooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NavboxMaker frame = new NavboxMaker();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void chooseFile(){
//		int returnVal = chooser.showOpenDialog(this);
	}

	/**
	 * Create the frame.
	 */
	public NavboxMaker() {
		chooser = new JFileChooser();
		chooser.setAcceptAllFileFilterUsed(false);
	    FileFilter filter = new FileTypeFilter("csv","Comma Separated Values");
	    chooser.setFileFilter(filter);
	    
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblCsvFile = new JLabel("CSV file:");
		GridBagConstraints gbc_lblCsvFile = new GridBagConstraints();
		gbc_lblCsvFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblCsvFile.gridx = 0;
		gbc_lblCsvFile.gridy = 0;
		contentPane.add(lblCsvFile, gbc_lblCsvFile);
		
		JButton btnChoose = new JButton("Choose...");
		GridBagConstraints gbc_btnChoose = new GridBagConstraints();
		gbc_btnChoose.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnChoose.insets = new Insets(0, 0, 5, 0);
		gbc_btnChoose.gridx = 1;
		gbc_btnChoose.gridy = 0;
		contentPane.add(btnChoose, gbc_btnChoose);
		btnChoose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				chooseFile();
			}
		});
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		String text="Make sure your Excel file is in CSV format, and that the first and second " +
				"columns contain categories and item names, respectively. For example, " +
				"if you wanted to list canals, sorted by sestiere:" +
				"<table border=1><tr><td><b>Sestiere</b></td><td><b>Canal Name</b></td><td><b>Length</b></td><td>...etc....</td></tr>" +
				"<tr><td>Castello</td><td>Rio de la Panada</td><td>438.5 m</td><td></td></tr></table>" +
				"You must have at least one category.";
		editorPane.setText(text);
		GridBagConstraints gbc_editorPane = new GridBagConstraints();
		gbc_editorPane.gridwidth = 2;
		gbc_editorPane.insets = new Insets(0, 0, 5, 5);
		gbc_editorPane.fill = GridBagConstraints.BOTH;
		gbc_editorPane.gridx = 0;
		gbc_editorPane.gridy = 1;
		contentPane.add(editorPane, gbc_editorPane);
		
		JButton btnNext = new JButton("Next ->");
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNext.gridwidth = 2;
		gbc_btnNext.gridx = 0;
		gbc_btnNext.gridy = 2;
		contentPane.add(btnNext, gbc_btnNext);
		btnNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				NavboxMaker2 m2 = new NavboxMaker2(chooser.getSelectedFile());
				m2.setVisible(true);
				setVisible(false);
			}
		});
	}

}
