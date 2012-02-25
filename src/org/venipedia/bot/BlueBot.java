package org.venipedia.bot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.swing.SwingWorker;

import org.venipedia.QwikiWiki;
import org.venipedia.credentials.DatabaseCredentials;
import org.venipedia.entities.DatabaseTable;
import org.venipedia.ui.TableViewer;

public class BlueBot {
	/** The URL to connect to. **/
	private static final String BLUE_URL = "http://www.venipedia.org/QwikiWiki";
	/** The login credentials **/
	private DatabaseCredentials creds;

	/** The program instance we're working with */
	private QwikiWiki parent;

	public BlueBot(QwikiWiki parent) {
		this.parent = parent;
		creds = new DatabaseCredentials("","");
	}

	/** Reinitialize the connection to the Bluehost server **/
	private void connectThread() {
		parent.setStatus("Logging in to Bluehost...");
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
		parent.saveCreds();
	}
	
	public static String postRequest(String urlString, String data){
		try {
		    // Construct data
		    data = URLEncoder.encode("data", "UTF-8") + "=" + URLEncoder.encode(data, "UTF-8");
		    
		    // Send data
		    URL url = new URL(urlString);
		    URLConnection conn = url.openConnection();
		    conn.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write(data);
		    wr.flush();

		    // Get the response
		    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String line;
		    String out = "";
		    while ((line = rd.readLine()) != null) {
		        out+=line;
		    }
		    wr.close();
		    rd.close();
		    return out;
		} catch (Exception e) {
			return null;
		}
	}
	
	private void setTableThreaded(TableViewer tv, String db, String tableName){
		parent.setStatus("Downloading table \""+tableName+"\"...");
		parent.setFrozen(true);
		DatabaseTable table = getTable(db,tableName);
		tv.setTable(table);
		parent.setFrozen(false);
		parent.setStatus("Downloaded table.");
	}
	
	public void setTable(final TableViewer tv, final String db, final String tableName){
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() {
				setTableThreaded(tv,db,tableName);
				return null;
			}
		};
		worker.execute();
	}
	
	public DatabaseTable getTable(String db, String tableName){

		String data = "{\"tableName\":\""+tableName+"\",\"user\":\""+creds.getUsername()+"\",\"pass\":\""+
				creds.getPassword()+"\",\"db\":\""+db+"\"}";
		String response = postRequest(BLUE_URL+"/getTable.php",data);
		DatabaseTable table = new DatabaseTable(response);
		
		return table;
	}

}
