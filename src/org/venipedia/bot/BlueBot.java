package org.venipedia.bot;

import java.net.MalformedURLException;

import javax.swing.SwingWorker;

import net.sourceforge.jwbf.core.actions.util.ActionException;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;

import org.venipedia.QwikiWiki;
import org.venipedia.credentials.DatabaseCredentials;
import org.venipedia.credentials.VenipediaCredentials;

public class BlueBot {
	/** The URL to connect to. **/
	private static final String BLUE_URL = "http://www.venipedia.org/";
	/** The login credentials **/
	private DatabaseCredentials creds;

	/** The program instance we're working with */
	private QwikiWiki parent;

	public BlueBot(QwikiWiki parent) {
		this.parent = parent;
	}

	/** Reinitialize the connection to the Bluehost server **/
	private void connectThread() {
		parent.setStatus("Logging in to Bluehost... Please wait. This may take a while.");
		parent.setStatus("Logged in to Bluehost as " + creds.getUsername()
				+ ".");
	}

	private void connect() {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() {
				connectThread();
				return null;
			}
		};
		worker.execute();
	}

	public DatabaseCredentials getCreds() {
		return creds;
	}

	public void setCreds(DatabaseCredentials creds) {
		this.creds = creds;
		connect();
	}

}
