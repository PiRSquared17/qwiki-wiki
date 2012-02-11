package org.venipedia.dbcore.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.jwbf.core.actions.util.ActionException;
import net.sourceforge.jwbf.core.actions.util.ProcessException;

public class JdbcTemplate {
	private JdbcDatabase db;
	private String sourceTable;
	private String templateBody;
	private String templateName;
	private String templateTitle;
	
	private Pattern fieldPattern;
	private static final String FIELD_PATTERN_STRING = "\\[::Field::((?`.*`)|(?\\w*))::\\]";

	public JdbcTemplate(String templateName, String sourceTable, JdbcDatabase db) {
		this.templateName = templateName;
		this.sourceTable = sourceTable;
		this.db=db;
	}

	public JdbcDatabase getDb() {
		return db;
	}

	public String getSourceTable() {
		return sourceTable;
	}

	public String getTemplateBody() {
		return templateBody;
	}

	public String getTemplateName() {
		return templateName;
	}

	public String getTemplateTitle() {
		return templateTitle;
	}

	public void setSourceTable(String sourceTable) {
		this.sourceTable = sourceTable;
	}

	public void setTemplateBody(String templateBody) {
		this.templateBody = templateBody;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public void setTemplateTitle(String templateTitle) {
		this.templateTitle = templateTitle;
	}
	
	public void setUpPatterns(){
		fieldPattern = Pattern.compile(FIELD_PATTERN_STRING);
	}
	
	/**
	 * Gets a list of all the new page titles that will be uploaded.
	 * 
	 * Intended for presenting the user with this list and saying "Are you sure you want to overwrite all these pages?"
	 * @return a String[] of all the page names
	 * @throws SQLException cannot connect to the DB
	 */
	public String[] getAllTitles() throws SQLException{
		// connect to the table based on the DB and table name
		JdbcTable table = new JdbcTable(db,sourceTable);
		// update the table
		table.getTableContents();
		// set up regex patterns
		setUpPatterns();
		// set up an ArrayList
		ArrayList<String> titleList = new ArrayList<String>();
		// for each row in the table
		for(int row = 0;row<table.getRowCount();row++)
		{
			// fill in the template title
			String title = fillTemplate(templateTitle,table,row);
			titleList.add(title);
		}
		return (String[])titleList.toArray();
	}
	
	/**
	 * Saves the template to the MySQL database.
	 * @throws SQLException database issues
	 */
	public void save() throws SQLException{
		db.setTemplateBody(templateName, templateBody);
		db.setTemplateTitle(templateName, templateTitle);
		db.setTemplateSourceTable(templateName, sourceTable);
	}
	
	/**
	 * Creates the Venipedia pages and uploads them. 
	 * @return the number of pages generated
	 * @throws SQLException problem with the database
	 * @throws ProcessException problem uploading to Venipedia
	 * @throws ActionException problem uploading to Venipedia
	 */
	public int createAndUploadPages(UploadManager mgr) throws SQLException, ActionException, ProcessException{
		// connect to the table based on the DB and table name
		JdbcTable table = new JdbcTable(db,sourceTable);
		// update the table
		table.getTableContents();
		// set up regex patterns
		setUpPatterns();
		// create a variable to count the pages uploaded
		int count = 0;
		// for each row in the table
		for(int row = 0;row<table.getRowCount();row++)
		{
			// fill in the template title
			String title = fillTemplate(templateTitle,table,row);
			// fill in the template body
			String body = fillTemplate(templateBody,table,row);
			// upload the page to Venipedia
			mgr.uploadArticle(title, body);
			// increment the counter
			count++;
			// increase the DB's progress bar
			db.setStatus(count+" pages of "+" uploaded");
		}
		db.setStatus("Successfully created and uploaded "+count+" pages to Venipedia.");
		return count;
	}
	
	private String fillTemplate(String template, JdbcTable table, int row){
		Matcher matcher = fieldPattern.matcher(template);
		String result = "";
		int lastMatchEnd = 0;
		while(matcher.find()){
			result+=template.substring(lastMatchEnd,matcher.start());
			String colName = matcher.group(1);
			String fieldValue = table.getStringValueAt(row, colName);
			result+=fieldValue;
		}
		result+=template.substring(lastMatchEnd);
		return result;
	}
	
}

/*
 * Format for field tags (example):
 * 
 * [::Field::latitude::]
 * 
 * Format for template insertion:
 * 
 * [::Template::segment_list::link_list_comma::]
 */
