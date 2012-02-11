package org.venipedia.dbcore.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class MySqlFieldReader<T> {
	/**
	 * Take a ResultSet that's pointed at a particular row, and format the
	 * contents of a given column into an object of type T.
	 * 
	 * @param rs
	 *            The ResultSet, pointed at a given row.
	 * @param columnName
	 *            The name of the column you'd like results from.
	 * @return The converted object
	 * @throws SQLException 
	 */
	public abstract T processFieldIntoObject(ResultSet rs, String columnName) throws SQLException;

	/**
	 * Take a ResultSet that's pointed at a particular row, and format the
	 * given object of type T into the contents of a given column.
	 * 
	 * @param t
	 *            The object that will be formatted into the table
	 * @param rs
	 *            The ResultSet that will hold the finished product
	 * @param columnName
	 *            The column where you want this entry to be stored
	 * @throws SQLException 
	 */
	public abstract void processObjectIntoField(T t, ResultSet rs, String columnName) throws SQLException;
	
	/**
	 * Convert this object into a String suitable for use in a WHERE clause.
	 * @param t The object to be converted.
	 * @return The original object, in String form.
	 */
	public abstract String processObjectIntoString(T t);
}