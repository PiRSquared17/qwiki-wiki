package org.venipedia.dbcore.jdbc;

import java.net.URL;
import java.text.DecimalFormat;

public class DataPair {
	private String columnName;
	private Object value;
	public DataPair(String columnName, Object value){
		this.columnName = columnName;
		this.value = value;
	}
	public String getColumnName(){
		return columnName;
	}
	public String getValueString(){
		if(value instanceof String){
			return "'"+value+"'";
		}else if(value instanceof URL){
			URL url = (URL) value;
			return "'"+url.getPath()+"'";
		}else if(value instanceof Double){
			Double d = (Double) value;
			DecimalFormat format = new DecimalFormat("##########.##########;-##########.##########");
			return format.format(d);
		}else if(value instanceof Integer){
			return ""+((Integer)value);
		}
		return value.toString();
	}
	public static String getValueString(Object value){
		if(value instanceof String){
			return "'"+value+"'";
		}else if(value instanceof URL){
			URL url = (URL) value;
			return "'"+url.getPath()+"'";
		}else if(value instanceof Double){
			Double d = (Double) value;
			DecimalFormat format = new DecimalFormat("##########.##########;-##########.##########");
			return format.format(d);
		}else if(value instanceof Integer){
			return ""+((Integer)value);
		}
		return value.toString();
	}
}
