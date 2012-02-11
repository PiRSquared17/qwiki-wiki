/**
 * 
 */
package org.venipedia.template;

import nu.xom.Element;

import org.venipedia.dbcore.jdbc.JdbcTable;

/**
 * Represents a fillable wiki template. Can represent an entire article, just a
 * section, or something smaller like an infobox. Templates can be reused within
 * other templates allowing for molto flexibility!
 * 
 * @author Ryan A Muller
 * 
 */
public class Template {
	public static final String FIELD_MATCH_EXPR = "\\<\\!-{2}\\${2}([\\w\\s]*)@(\\d+)\\:(\\d+)\\${2}-{2}\\>";
	/**
	 * The body of the article
	 */
	String body;

	/**
	 * The name of the article. This is a template too! So you can use fields
	 * and stuff to dynamically generate the name. No sub-templates though.
	 */
	String name;

	/**
	 * The RecordType that this template acts on
	 */
	JdbcTable table;
	
	/**
	 * The id of this template
	 */
	long id;
	
	

	public Template(JdbcTable table) {
		super();
		this.table = table;
	}

	/**
	 * Fills in this template with data from the given record.
	 * 
	 * @param record
	 *            the record which will supply the required data
	 * @return wiki markup for the formatted article
	 */
	public String toWikiMarkup(String record) {
//		// compile a regular expression from FIELD_MATCH_EXPR and set things up for some matching
//		Pattern pattern = Pattern.compile(FIELD_MATCH_EXPR);
//		Matcher matcher = pattern.matcher(body);
//		// create an empty String to store the output
//		// change this to a StringBuffer later to improve performance
//		String retval = "";
//		// set the end of the previous match to 0
//		int lastMatchEnd=0;
//		// for each match:
//		while(matcher.find())
//		{
//			// add all text in the template between the end of the previous and beginning of the current match to the output
//			retval+=body.substring(lastMatchEnd, matcher.start());
//			// get the contents of the second capture group; this is the record type id
//			// in regex the capture groups are numbered starting from 1 and not 0
//			// String s1 = matcher.group(2);
//			// get the contents of the third capture group; this is the field id
//			String s2 = matcher.group(3);
//			long fieldId = Long.parseLong(s2);
//			// fetch the contents of the field
//			String value = record.get(fieldId);
//			// add the field contents to the output
//			retval+=value;
//			// store the ending location of this match
//			lastMatchEnd = matcher.end();
//		}
//		// add the remaining text from the template body onto the end
//		retval+=body.substring(lastMatchEnd);
//		// return the output
//		return retval;
		return null;
	}
	
	public String toWikiTitle(String record) {
//		// TODO this method MUST be written!
//		// compile a regular expression from FIELD_MATCH_EXPR and set things up for some matching
//		Pattern pattern = Pattern.compile(FIELD_MATCH_EXPR);
//		Matcher matcher = pattern.matcher(name);
//		// create an empty String to store the output
//		// change this to a StringBuffer later to improve performance
//		String retval = "";
//		// set the end of the previous match to 0
//		int lastMatchEnd=0;
//		// for each match:
//		while(matcher.find())
//		{
//			// add all text in the template between the end of the previous and beginning of the current match to the output
//			retval+=name.substring(lastMatchEnd, matcher.start());
//			// get the contents of the second capture group; this is the record type id
//			// in regex the capture groups are numbered starting from 1 and not 0
//			// String s1 = matcher.group(2);
//			// get the contents of the third capture group; this is the field id
//			String s2 = matcher.group(3);
//			long fieldId = Long.parseLong(s2);
//			// fetch the contents of the field
//			String value = record.get(fieldId);
//			// add the field contents to the output
//			retval+=value;
//			// store the ending location of this match
//			lastMatchEnd = matcher.end();
//		}
//		// add the remaining text from the template body onto the end
//		retval+=name.substring(lastMatchEnd);
//		// return the output
//		return retval;
		return null;
	}
	
	/**
	 * Change the id of this template
	 * @param id the new id
	 */
	public void setId(long id){
		this.id=id;
	}
	
	/**
	 * Get this template's id
	 * @return the id for this template
	 */
	public long getId(){
		return id;
	}

	/**
	 * @return the body of the template
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Change the template body.
	 * @param body the new body of the template
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Returns the template for page names using this template.
	 * @return the name template for this template
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the new name template for this template
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the recordType
	 */
	public JdbcTable getTable() {
		return table;
	}
	
	protected void setTable(JdbcTable t){
		this.table = t;
	}
	
	public static Template loadFromXML(Element elTemplate){
		return null;
	}
}
