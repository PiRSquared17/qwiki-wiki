package org.venipedia.bot;

public class ImportPair {
	private String column;
	private String templateField;
	
	public ImportPair(String column, String templateField) {
		this.column = column;
		this.templateField = templateField;
	}
	/**
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}
	/**
	 * @param column the column to set
	 */
	public void setColumn(String column) {
		this.column = column;
	}
	/**
	 * @return the templateField
	 */
	public String getTemplateField() {
		return templateField;
	}
	/**
	 * @param templateField the templateField to set
	 */
	public void setTemplateField(String templateField) {
		this.templateField = templateField;
	}
	
}
