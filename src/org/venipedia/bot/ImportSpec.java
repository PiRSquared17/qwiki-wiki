package org.venipedia.bot;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;
import org.venipedia.entities.Page;

public class ImportSpec {
	
	private String database;
	private String tableName;
	private String template;
	private String title;
	
	private ImportPair[] pairs;
	
	public ImportSpec(Page specPage){
		System.out.println(specPage.getBody());
		String jsonRaw = specPage.getBody();
		try {
			JSONObject obj = new JSONObject(jsonRaw);
			database = obj.getString("db");
			tableName = obj.getString("table");
			title = obj.getString("title");
			template = obj.getString("template");
			JSONObject fields = obj.getJSONObject("fields");
			
			pairs = new ImportPair[fields.length()];
			int i=0;
			
			for(Iterator<String> iter = fields.keys(); iter.hasNext();){
				String field = iter.next();
				ImportPair pair = new ImportPair(fields.getString(field),field);
				pairs[i++] = pair;
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	public Collection<ImportPair> getPairs(){
		Vector<ImportPair> p = new Vector<ImportPair>();
		for(ImportPair pr : pairs){
			p.add(pr);
		}
		return p;
	}

	/**
	 * @return the database
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
}
