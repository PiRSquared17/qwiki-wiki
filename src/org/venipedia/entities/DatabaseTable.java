package org.venipedia.entities;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DatabaseTable implements TableModel {
	private String title;
	private String[] headers;
	private String[][] data;
	
	public DatabaseTable(String resp){
		try {
			JSONObject ob = new JSONObject(resp);
			title = ob.getString("tableName");
			
			JSONArray hdrs = ob.getJSONArray("headers");
			JSONArray dta = ob.getJSONArray("data");
			
			headers = new String[hdrs.length()];
			data = new String[dta.length()][];
			
			for(int i=0;i<headers.length;i++){
				headers[i] = hdrs.getString(i);
			}
			
			for(int j=0;j<dta.length();j++){
				data[j] = new String[headers.length];
				for(int i=0;i<headers.length;i++){
					data[j][i] = dta.getJSONObject(j).getString(headers[i]); 
				}
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public int getColumnIndex(String col){
		int i=0;
		for(String s:headers){
			if(col.equals(s))
				return i;
			i++;
		}
		return -1;
	}
	
	public String getValuetA(int row, String colName){
		int col = getColumnIndex(colName);
		if(col<0) return "No data available for \""+colName+"\"";
		return data[row][col];
	}
	
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	public Class<?> getColumnClass(int arg0) {
		return String.class;
	}

	public int getColumnCount() {
		return headers.length;
	}

	public String getColumnName(int i) {
		return headers[i];
	}

	public int getRowCount() {
		return data.length;
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	public void removeTableModelListener(TableModelListener arg0) {
		
	}

	public void setValueAt(Object arg0, int arg1, int arg2) {
		
	}

	public String getTitle() {
		return title;
	}
	
}
