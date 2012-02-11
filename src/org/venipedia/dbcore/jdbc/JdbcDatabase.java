package org.venipedia.dbcore.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Vector;

import javax.swing.DefaultListModel;

import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;

import org.venipedia.ui.QwikiWiki;

/**
 * Represents a database that uses the JDBC framework to communicate with the
 * underlying database.
 * 
 * Note that fields, tables, etc. do not need ids anymore because we can just
 * refer to them by their names. Templates are stored in their own table so they
 * will need some sort of naming scheme.
 * 
 * @author Ryan Muller
 * 
 */
public class JdbcDatabase {
	private Connection con;

	public static final int COL_BASE = 1;
	public static final int ROW_BASE = 1;

	private static final String TEMPLATE_TABLE_NAME = "WikiTemplates";

	private QwikiWiki bot;
	
	private JdbcTable templateTable;

	public JdbcDatabase(Connection con, QwikiWiki bot) {
		this.con = con;
		this.bot = bot;
	}

	public void setStatus(String status) {
		if (bot != null)
			bot.setStatus(status);
	}

	public void setProgress(int prog, int total){
		if(bot!=null){
			bot.setProgress(prog*1000/total);
		}
	}

	public void setTemplateBody(String templateName, String templateBody) throws SQLException{
		// escape backticks, no SQL injection allowed here!
		String escapedBody = templateBody.replaceAll("`", "#BACKTICK#");
		String escapedName = templateName.replaceAll("`", "");
		String query = "UPDATE "+ TEMPLATE_TABLE_NAME + " SET TemplateBody = `"+escapedBody+"` WHERE TemplateName = `"+escapedName+"`;";
		executeUpdate(query);
	}
	
	public void setTemplateTitle(String templateName, String templateTitle) throws SQLException{
		// escape backticks, no SQL injection allowed here!
		String escapedTitle = templateTitle.replaceAll("`", "#BACKTICK#");
		String escapedName = templateName.replaceAll("`", "");
		String query = "UPDATE "+ TEMPLATE_TABLE_NAME + " SET TemplateTitle = `"+escapedTitle+"` WHERE TemplateName = `"+escapedName+"`;";
		executeUpdate(query);
	}
	
	public void renameTemplate(String templateName, String newName) throws SQLException{
		// escape backticks, no SQL injection allowed here!
		String escapedTitle = newName.replaceAll("`", "#BACKTICK#");
		String escapedName = templateName.replaceAll("`", "");
		String query = "UPDATE "+ TEMPLATE_TABLE_NAME + " SET TemplateName = `"+escapedTitle+"` WHERE TemplateName = `"+escapedName+"`;";
		executeUpdate(query);
	}
	
	private boolean templateTableExists() throws SQLException{
		for(JdbcTable table:listTables()){
			if(table.getName().equals(TEMPLATE_TABLE_NAME))
				return true;
		}
		return false;
	}
	
	public void setTemplateSourceTable(String templateName, String tableName) throws SQLException{
		// escape backticks, no SQL injection allowed here!
		String escapedTable = tableName.replaceAll("`", "#BACKTICK#");
		String escapedName = templateName.replaceAll("`", "");
		String query = "UPDATE "+ TEMPLATE_TABLE_NAME + " SET SourceTable = `"+escapedTable+"` WHERE TemplateName = `"+escapedName+"`;";
		executeUpdate(query);
	}
	
	public JdbcTable addTable(String name) throws SQLException {
		// create the table under the correct name and with just an id field
		String query = "CREATE TABLE " + name
				+ " (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY);";
		executeUpdate(query);
		return new JdbcTable(this, name);
	}

	protected int executeUpdate(String query) throws SQLException {
		Statement stmt = con.createStatement();
		setStatus(query);
		return stmt.executeUpdate(query);
	}

	protected ResultSet executeQuery(String query) throws SQLException {
		Statement stmt = con.createStatement();
		setStatus(query);
		return stmt.executeQuery(query);
	}

	protected ResultSet executeQueryScrollable(String query)
			throws SQLException {
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		setStatus(query);
		return stmt.executeQuery(query);
	}
	
	public void addTemplate(JdbcTemplate t) throws SQLException{
		if(!templateTableExists()){
			templateTable = addTable(TEMPLATE_TABLE_NAME);
		}else{
			templateTable = new JdbcTable(this,TEMPLATE_TABLE_NAME);
		}
		templateTable.insertRow(new DataPair[]{
				new DataPair("TemplateName",t.getTemplateName()),
				new DataPair("SourceTable",t.getSourceTable())
		});
	}

	/**
	 * Lists all of the tables in this database.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Collection<JdbcTable> listTables() throws SQLException {
		String query = "SHOW TABLES;";
		ResultSet set = executeQuery(query);
		Vector<JdbcTable> tables = new Vector<JdbcTable>(0, 1);
		while (set.next()) {
			String tableName = set.getString(COL_BASE);
			if (tableName.equals(TEMPLATE_TABLE_NAME))
				continue;
			// //System.out.println("Found table:\t"+tableName);
			JdbcTable table = new JdbcTable(this, tableName);
			tables.add(table);
		}
		return tables;
	}

	public static DefaultListModel getListModelFromResultSet(ResultSet rs,
			int columnIndex) throws SQLException {
		DefaultListModel model = new DefaultListModel();
		while (rs.next()) {
			model.addElement(rs.getString(columnIndex));
		}
		return model;
	}

	public static DefaultListModel getListModelFromResultSet(ResultSet rs,
			int columnIndex1, int columnIndex2) throws SQLException {
		DefaultListModel model = new DefaultListModel();
		while (rs.next()) {
			model.addElement(rs.getString(columnIndex1 + COL_BASE) + "\t("
					+ rs.getString(columnIndex2 + COL_BASE) + ")");
		}
		return model;
	}

	public void showErrorDialog(Exception e) {
		if (bot != null)
			bot.showErrorDialog(e);
	}

	public DefaultListModel getTableList() throws SQLException {
		String query = "SHOW TABLES;";
		ResultSet set = executeQuery(query);
		return getListModelFromResultSet(set, COL_BASE);
	}

	public JdbcTable getTableByIndex(int i) throws SQLException {
		Vector<JdbcTable> tables = (Vector<JdbcTable>) listTables();
		return tables.get(i);
	}

	public Connection getConnection() {
		return con;
	}
}
