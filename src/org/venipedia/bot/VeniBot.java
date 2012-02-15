package org.venipedia.bot;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.swing.SwingWorker;

import net.sourceforge.jwbf.core.actions.util.ActionException;
import net.sourceforge.jwbf.core.actions.util.ProcessException;
import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.mediawiki.actions.editing.PostDelete;
import net.sourceforge.jwbf.mediawiki.actions.queries.AllPageTitles;
import net.sourceforge.jwbf.mediawiki.actions.util.VersionException;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;

import org.venipedia.QwikiWiki;
import org.venipedia.credentials.VenipediaCredentials;
import org.venipedia.entities.Page;

public class VeniBot {
	/** The URL to connect to. **/
	private static final String VENI_URL = "http://www.venipedia.org/";
	/** The login credentials **/
	private VenipediaCredentials creds;
	/** The bot that's working behind-the-scenes */
	private MediaWikiBot bot;
	/** The program instance we're working with */
	private QwikiWiki parent;

	public VeniBot(QwikiWiki parent) {
		this.parent = parent;
	}

	/** Reinitialize the connection to Venipedia.org **/
	private void connect(boolean thread) {
		parent.setStatus("Logging in to Venipedia... Please wait. This may take a while.");
		try {
			bot = new MediaWikiBot(VENI_URL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bot.login(creds.getUsername(), creds.getPassword());
			parent.setStatus("Logged in to Venipedia as " + creds.getUsername()
					+ ".");
		} catch (ActionException e) {
			e.printStackTrace();
			parent.setStatus("Could not log in to Venipedia: " + e.getMessage());
		}
	}

	private void connect() {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() {
				connect(true);
				return null;
			}
		};
		worker.execute();
	}

	public Page getPage(String title) {
		try {
			Article a = bot.readContent(title);
			String body = a.getText();
			return new Page(title, body);
		} catch (Exception e) {
			e.printStackTrace();
			parent.setStatus("Could not read \"" + title + "\": "
					+ e.getMessage());
			return new Page(title, "Could not find " + title + " on Venipedia.");
		}

	}

	public Collection<String> listPages(int... namespaces) {
		ArrayList<String> ret = new ArrayList<String>();
		try {
			AllPageTitles apt = new AllPageTitles(bot, namespaces);
			for (Iterator<String> i = apt.iterator(); i.hasNext();) {
				ret.add(i.next());
			}
		} catch (VersionException e) {
			e.printStackTrace();
			return null;
		}
		return ret;
	}

	public Collection<String> listNamespaces() {
		try {
			Map<Integer, String> namespaces = bot.getSiteinfo().getNamespaces();
			return namespaces.values();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public VenipediaCredentials getCreds() {
		return creds;
	}

	public void setCreds(VenipediaCredentials creds) {
		this.creds = creds;
		connect();
	}

	public void deletePages(Object[] selectedValues) {
		for (Object o : selectedValues) {
			String name = o.toString();
			deletePage(name);
		}
	}

	public void deletePage(String name) {
		try {
			PostDelete del = new PostDelete(bot, name);
			bot.performAction(del);
		} catch (ProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ActionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
