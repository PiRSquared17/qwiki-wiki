package org.venipedia.ui.jdbc;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingWorker;

import org.venipedia.dbcore.jdbc.JdbcDatabase;
import org.venipedia.ui.QwikiWiki;

public class DatabaseLoginFrame extends JInternalFrame {
	private static final long serialVersionUID = 6026000881068341911L;
	private JTextField txtDbName;
	private JTextField txtUsername;
	private JPasswordField passwordField;

	private QwikiWiki parent;
	private JComboBox cmbDriver;
	private JComboBox cmbProtocol;
	private JComboBox cmbServer;
	private JSpinner spnPort;

	/**
	 * Create the frame.
	 */
	public DatabaseLoginFrame(QwikiWiki bot) {
		parent = bot;
		setTitle("Log in to remote database");
		setFrameIcon(new ImageIcon(
				DatabaseLoginFrame.class
						.getResource("/icons/database-network.png")));
		setBounds(100, 100, 304, 328);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblDriver = new JLabel("Driver:");
		GridBagConstraints gbc_lblDriver = new GridBagConstraints();
		gbc_lblDriver.anchor = GridBagConstraints.EAST;
		gbc_lblDriver.insets = new Insets(0, 0, 5, 5);
		gbc_lblDriver.gridx = 0;
		gbc_lblDriver.gridy = 0;
		getContentPane().add(lblDriver, gbc_lblDriver);

		cmbDriver = new JComboBox();
		cmbDriver.setModel(new DefaultComboBoxModel(
				new String[] { "com.mysql.jdbc.Driver" }));
		cmbDriver.setEditable(true);
		GridBagConstraints gbc_cmbDriver = new GridBagConstraints();
		gbc_cmbDriver.gridwidth = 2;
		gbc_cmbDriver.insets = new Insets(0, 0, 5, 0);
		gbc_cmbDriver.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbDriver.gridx = 1;
		gbc_cmbDriver.gridy = 0;
		getContentPane().add(cmbDriver, gbc_cmbDriver);

		JLabel lblProtocol = new JLabel("Protocol:");
		GridBagConstraints gbc_lblProtocol = new GridBagConstraints();
		gbc_lblProtocol.anchor = GridBagConstraints.EAST;
		gbc_lblProtocol.insets = new Insets(0, 0, 5, 5);
		gbc_lblProtocol.gridx = 0;
		gbc_lblProtocol.gridy = 1;
		getContentPane().add(lblProtocol, gbc_lblProtocol);

		cmbProtocol = new JComboBox();
		cmbProtocol.setEditable(true);
		cmbProtocol.setModel(new DefaultComboBoxModel(
				new String[] { "jdbc:mysql" }));
		GridBagConstraints gbc_cmbProtocol = new GridBagConstraints();
		gbc_cmbProtocol.gridwidth = 2;
		gbc_cmbProtocol.insets = new Insets(0, 0, 5, 5);
		gbc_cmbProtocol.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbProtocol.gridx = 1;
		gbc_cmbProtocol.gridy = 1;
		getContentPane().add(cmbProtocol, gbc_cmbProtocol);

		JLabel lblServer = new JLabel("Server:");
		GridBagConstraints gbc_lblServer = new GridBagConstraints();
		gbc_lblServer.anchor = GridBagConstraints.EAST;
		gbc_lblServer.insets = new Insets(0, 0, 5, 5);
		gbc_lblServer.gridx = 0;
		gbc_lblServer.gridy = 2;
		getContentPane().add(lblServer, gbc_lblServer);

		cmbServer = new JComboBox();
		cmbServer.setModel(new DefaultComboBoxModel(
				new String[] { "box681.bluehost.com" }));
		cmbServer.setEditable(true);
		GridBagConstraints gbc_cmbServer = new GridBagConstraints();
		gbc_cmbServer.gridwidth = 2;
		gbc_cmbServer.insets = new Insets(0, 0, 5, 0);
		gbc_cmbServer.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbServer.gridx = 1;
		gbc_cmbServer.gridy = 2;
		getContentPane().add(cmbServer, gbc_cmbServer);

		JLabel lblPort = new JLabel("Port:");
		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.anchor = GridBagConstraints.EAST;
		gbc_lblPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblPort.gridx = 0;
		gbc_lblPort.gridy = 3;
		getContentPane().add(lblPort, gbc_lblPort);

		spnPort = new JSpinner();
		spnPort.setModel(new SpinnerNumberModel(3306, 0, 65535, 1));
		GridBagConstraints gbc_spnPort = new GridBagConstraints();
		gbc_spnPort.gridwidth = 2;
		gbc_spnPort.anchor = GridBagConstraints.WEST;
		gbc_spnPort.insets = new Insets(0, 0, 5, 0);
		gbc_spnPort.gridx = 1;
		gbc_spnPort.gridy = 3;
		getContentPane().add(spnPort, gbc_spnPort);

		JLabel lblDatabase = new JLabel("Database:");
		GridBagConstraints gbc_lblDatabase = new GridBagConstraints();
		gbc_lblDatabase.anchor = GridBagConstraints.EAST;
		gbc_lblDatabase.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatabase.gridx = 0;
		gbc_lblDatabase.gridy = 4;
		getContentPane().add(lblDatabase, gbc_lblDatabase);

		txtDbName = new JTextField();
		txtDbName.setText("venicetw_preservenice");
		GridBagConstraints gbc_txtDbName = new GridBagConstraints();
		gbc_txtDbName.gridwidth = 2;
		gbc_txtDbName.insets = new Insets(0, 0, 5, 0);
		gbc_txtDbName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDbName.gridx = 1;
		gbc_txtDbName.gridy = 4;
		getContentPane().add(txtDbName, gbc_txtDbName);
		txtDbName.setColumns(10);

		JLabel lblUsername = new JLabel("Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 5;
		getContentPane().add(lblUsername, gbc_lblUsername);

		txtUsername = new JTextField();
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.gridwidth = 2;
		gbc_txtUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.gridx = 1;
		gbc_txtUsername.gridy = 5;
		getContentPane().add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 6;
		getContentPane().add(lblPassword, gbc_lblPassword);

		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 6;
		getContentPane().add(passwordField, gbc_passwordField);

		JButton btnHelp = new JButton("Help");
		btnHelp.setFont(btnHelp.getFont().deriveFont(
				btnHelp.getFont().getSize() + 3f));
		btnHelp.setIcon(new ImageIcon(DatabaseLoginFrame.class
				.getResource("/icons/question.png")));
		GridBagConstraints gbc_btnHelp = new GridBagConstraints();
		gbc_btnHelp.gridwidth = 2;
		gbc_btnHelp.insets = new Insets(0, 0, 0, 5);
		gbc_btnHelp.gridx = 0;
		gbc_btnHelp.gridy = 7;
		getContentPane().add(btnHelp, gbc_btnHelp);

		JButton btnConnect = new JButton("Connect!");
		btnConnect.setFont(btnConnect.getFont().deriveFont(
				btnConnect.getFont().getSize() + 3f));
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setVisible(false);
					connectInSeparateThread();
				} catch (Exception e1) {
					parent.showErrorDialog(e1);
					e1.printStackTrace();
				}
			}
		});
		btnConnect.setIcon(new ImageIcon(DatabaseLoginFrame.class
				.getResource("/icons/chain.png")));
		GridBagConstraints gbc_btnConnect = new GridBagConstraints();
		gbc_btnConnect.gridx = 2;
		gbc_btnConnect.gridy = 7;
		getContentPane().add(btnConnect, gbc_btnConnect);

		setVisible(true);
	}

	protected void connectInSeparateThread() throws ClassNotFoundException,
			SQLException {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() throws Exception {
				parent.setProgress(-1);
				connect();
				parent.setProgress(1000);
				return null;
			}
		};
		worker.execute();
	}

	protected void connect() throws ClassNotFoundException, SQLException {
		String driver = cmbDriver.getSelectedItem().toString();
		String connection = cmbProtocol.getSelectedItem().toString() + "://"
				+ cmbServer.getSelectedItem().toString() + ":"
				+ spnPort.getValue().toString() + "/" + txtDbName.getText();
		String user = txtUsername.getText();
		String password = new String(passwordField.getPassword());
		parent.setStatus("Loading driver \"" + driver + "\"...");
		Class.forName(driver);
		// blatantly expose all the login credentials to anyone looking over
		// your shoulder
		parent.setStatus("Connecting to \"" + connection + "\"...");
		// create a connection to the database
		Connection con = DriverManager
				.getConnection(connection, user, password);
		parent.setStatus("Initializing database.");
		// create a new database from the given connection
		JdbcDatabase newDb = new JdbcDatabase(con, parent);
		// update VenipediaBot with the new database
		parent.setDb(newDb);
		parent.setStatus("Database `" + txtDbName.getText() + "` connected!");
		// we have no more need for this window; get rid of it
		dispose();
	}

	public JComboBox getCmbDriver() {
		return cmbDriver;
	}

	public JComboBox getCmbProtocol() {
		return cmbProtocol;
	}

	public JComboBox getCmbServer() {
		return cmbServer;
	}

	public JSpinner getSpnPort() {
		return spnPort;
	}

	public JTextField getTxtDbName() {
		return txtDbName;
	}

	public JTextField getTxtUsername() {
		return txtUsername;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}
}
