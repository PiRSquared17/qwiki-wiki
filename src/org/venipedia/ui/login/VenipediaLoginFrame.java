package org.venipedia.ui.login;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.beans.PropertyVetoException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import org.venipedia.QwikiWiki;
import org.venipedia.credentials.VenipediaCredentials;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VenipediaLoginFrame extends JInternalFrame {
	private JTextField txtUsername;
	private JPasswordField passwordField;
	
	private QwikiWiki parent;

	/**
	 * Create the frame.
	 * @param qwikiWiki 
	 */
	public VenipediaLoginFrame(QwikiWiki qwikiWiki) {
		parent = qwikiWiki;
		setTitle("Log in to Venipedia");
		setFrameIcon(new ImageIcon(VenipediaLoginFrame.class.getResource("/icons/monitor-image.png")));
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblUserName = new JLabel("User name:");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.anchor = GridBagConstraints.EAST;
		gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserName.gridx = 0;
		gbc_lblUserName.gridy = 0;
		getContentPane().add(lblUserName, gbc_lblUserName);
		
		txtUsername = new JTextField();
		txtUsername.setToolTipText("Type your Venipedia username");
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.gridwidth = 2;
		gbc_txtUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.gridx = 1;
		gbc_txtUsername.gridy = 0;
		getContentPane().add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		getContentPane().add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		getContentPane().add(passwordField, gbc_passwordField);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logIn();
			}
		});
		btnLogIn.setIcon(new ImageIcon(VenipediaLoginFrame.class.getResource("/icons/thumb-up.png")));
		GridBagConstraints gbc_btnLogIn = new GridBagConstraints();
		gbc_btnLogIn.gridwidth = 2;
		gbc_btnLogIn.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogIn.gridx = 0;
		gbc_btnLogIn.gridy = 2;
		getContentPane().add(btnLogIn, gbc_btnLogIn);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		btnCancel.setIcon(new ImageIcon(VenipediaLoginFrame.class.getResource("/icons/cross.png")));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridx = 2;
		gbc_btnCancel.gridy = 2;
		getContentPane().add(btnCancel, gbc_btnCancel);
		
		populateFromCreds();
		
		pack();
	}
	
	private void populateFromCreds(){
		if(parent.getvCred()==null) return;
		txtUsername.setText(parent.getvCred().getUsername());
		passwordField.setText(parent.getvCred().getPassword());
	}

	protected void cancel() {
		closeOut();
	}

	protected void logIn() {
		String username = getTxtUsername().getText();
		String password = new String(getPasswordField().getPassword());
		VenipediaCredentials vCreds =new VenipediaCredentials(username, password); 
		parent.setvCred(vCreds);
		parent.setStatus("Logged in to Venipedia as "+username+".");
		closeOut();
	}
	
	protected void closeOut(){
		dispose();
	}

	protected JTextField getTxtUsername() {
		return txtUsername;
	}
	public JPasswordField getPasswordField() {
		return passwordField;
	}
}
