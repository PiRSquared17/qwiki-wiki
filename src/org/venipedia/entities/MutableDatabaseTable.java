package org.venipedia.entities;

import java.util.Hashtable;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class MutableDatabaseTable extends DatabaseTable {
	private Vector<String> headers;
	private Vector<Hashtable<String, String>> entries;
	private Vector<TableModelListener> listeners;

	public MutableDatabaseTable(String resp) {
		super(resp);
		
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
	
	public String getValueAt(int row, String colName){
		return entries.get(row).get(colName);
	}
	
	public int setValueAt(String str, int row, String colName){
		Hashtable<String,String> hash;
		if(row>=0 && row<entries.size()){
			hash = entries.get(row);
		}else{
			hash = new Hashtable<String,String>();
			row = hash.size();
			entries.add(hash);
		}
		if(!headers.contains(colName)){
			headers.add(colName);
		}
		hash.put(colName, str);
		for(TableModelListener list:listeners){
			TableModelEvent e = new TableModelEvent(this, row);
			list.tableChanged(e);
		}
		return row;
	}
	
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		listeners.add(arg0);
	}

	public int getColumnCount() {
		return headers.size();
	}

	public String getColumnName(int i) {
		return headers.get(i);
	}

	public int getRowCount() {
		return entries.size();
	}

	public Object getValueAt(int row, int col) {
		return getValueAt(row,headers.get(col));
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	public void removeTableModelListener(TableModelListener arg0) {
		listeners.remove(arg0);
	}

	public void setValueAt(Object arg0, int arg1, int arg2) {
		
	}
}
