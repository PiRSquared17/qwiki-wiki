package org.venipedia.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import org.venipedia.QwikiWiki;
import org.venipedia.bot.ImportPair;
import org.venipedia.bot.ImportSpec;
import org.venipedia.entities.DatabaseTable;
import org.venipedia.entities.Page;

public class MassPageCreation extends JInternalFrame {
	private static final long serialVersionUID = -3512602067595201245L;
	
	QwikiWiki parent;
	private JTextField comboBox;

	/**
	 * Create the frame.
	 */
	public MassPageCreation(QwikiWiki qw) {
		
		parent = qw;
		
		setTitle("Mass-upload pages");
		setFrameIcon(new ImageIcon(MassPageCreation.class.getResource("/icons/blogs-stack.png")));
		setClosable(true);
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblSelectImportSpecification = new JLabel("Select import specification:");
		GridBagConstraints gbc_lblSelectImportSpecification = new GridBagConstraints();
		gbc_lblSelectImportSpecification.gridwidth = 2;
		gbc_lblSelectImportSpecification.insets = new Insets(0, 0, 5, 0);
		gbc_lblSelectImportSpecification.gridx = 0;
		gbc_lblSelectImportSpecification.gridy = 0;
		getContentPane().add(lblSelectImportSpecification, gbc_lblSelectImportSpecification);
		
		comboBox = new JTextField();
		comboBox.setColumns(24);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 1;
		getContentPane().add(comboBox, gbc_comboBox);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.setIcon(new ImageIcon(MassPageCreation.class.getResource("/icons/question.png")));
		GridBagConstraints gbc_btnHelp = new GridBagConstraints();
		gbc_btnHelp.anchor = GridBagConstraints.WEST;
		gbc_btnHelp.insets = new Insets(0, 0, 0, 5);
		gbc_btnHelp.gridx = 0;
		gbc_btnHelp.gridy = 2;
		getContentPane().add(btnHelp, gbc_btnHelp);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				upload();
			}
		});
		btnUpload.setIcon(new ImageIcon(MassPageCreation.class.getResource("/icons/upload-cloud.png")));
		GridBagConstraints gbc_btnUpload = new GridBagConstraints();
		gbc_btnUpload.gridx = 1;
		gbc_btnUpload.gridy = 2;
		getContentPane().add(btnUpload, gbc_btnUpload);
		
		pack();

	}

	protected void upload() {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() {
				uploadData();
				return null;
			}
		};
		worker.execute();
	}
	
	protected void uploadData() {
		String specName = getComboBox().getText();
		// first we ask VeniBot to get us our specification file
		ImportSpec spec = parent.getVeniBot().getImportSpec(specName);
		
		String db = spec.getDatabase();
		String tableName = spec.getTableName();
		// then we ask BlueBot to go grab the data table
		DatabaseTable table = parent.getBlueBot().getTable(db, tableName);
		
		String template = spec.getTemplate();
		
		int totalArticles = table.getRowCount();
		parent.setMaximum(totalArticles);
		
		for(int row=0;row<totalArticles;row++){
			String title = table.getValuetA(row, spec.getTitle());
			parent.setStatus("Uploading \""+title+"\" (page "+row+" of "+totalArticles+")...");
			Page page = new Page(title);
			page.append("{{");
			page.append(template);
			page.append("\n");
			for(ImportPair pair:spec.getPairs()){
				page.append("|");
				page.append(pair.getTemplateField());
				page.append(" = ");
				page.append(table.getValuetA(row, pair.getColumn()));
				page.append("\n");
			}
			page.append("}}");
			parent.getVeniBot().upload(page);
			parent.setProgress(row);
		}
		parent.setProgress(0);
		parent.setStatus("Done with upload.");
		
		dispose();
	}

	protected JTextField getComboBox() {
		return comboBox;
	}
}
