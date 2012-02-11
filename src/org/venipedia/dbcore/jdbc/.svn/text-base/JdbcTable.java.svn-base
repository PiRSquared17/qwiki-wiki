package org.venipedia.dbcore.jdbc;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.table.AbstractTableModel;

public class JdbcTable extends AbstractTableModel {
	private static final long serialVersionUID = -8130783216278423340L;

	/**
	 * The name of this table
	 */
	private String name;

	public String getName() {
		return name;
	}

	/**
	 * The database that this table belongs to
	 */
	private JdbcDatabase db;

	/**
	 * Holds the data so we don't have to send a million and one requests.
	 */
	private Object[][] contents;
	String[] columnNames;
	Class<?>[] columnClasses;

	/**
	 * Creates a reference to a database table. This is awesome because all it
	 * actually holds a reference to is the database connection and the table
	 * name; EVERYTHING else is handled within the methods! Isn't that elegant?
	 * I think it's elegant. ~Ryan
	 * 
	 * @param db
	 *            A reference to the database connection
	 * @param name
	 *            The name of the table
	 * @throws SQLException
	 */
	public JdbcTable(JdbcDatabase db, String name) throws SQLException {
		this.db = db;
		this.name = name;
	}

	public void insertRow(DataPair... data) {
		String query = "INSERT INTO ";
		query += getEscapedName();
		query += " (";
		boolean comma = false;
		for (DataPair d : data) {
			query += "`" + d.getColumnName() + "`";
			if (comma)
				query += ", ";
			comma = true;
		}
		query += ") VALUES (";
		comma = false;
		for (DataPair d : data) {
			query += "`" + d.getValueString() + "`";
			if (comma)
				query += ", ";
			comma = true;
		}
	}

	@SuppressWarnings("rawtypes")
	public void getTableContents() throws SQLException {

		Connection conn = db.getConnection();

		String tableName = name;

		// get metadata: what columns exist and what
		// types (classes) are they?
		DatabaseMetaData meta = conn.getMetaData();
		// //System.out.println("got meta = " + meta);
		ResultSet results = meta.getColumns(null, null, tableName, null);
		// //System.out.println("got column results");
		ArrayList<String> colNamesList = new ArrayList<String>();
		ArrayList<Class> colClassesList = new ArrayList<Class>();
		while (results.next()) {
			String colName = results.getString("COLUMN_NAME");
			colNamesList.add(colName);
			// //System.out.println("name: " +
			// results.getString("COLUMN_NAME"));
			int dbType = results.getInt("DATA_TYPE");
			switch (dbType) {
			case Types.INTEGER:
				colClassesList.add(Integer.class);
				break;
			case Types.FLOAT:
				colClassesList.add(Float.class);
				break;
			case Types.DOUBLE:
			case Types.REAL:
			case Types.DECIMAL:
				colClassesList.add(Double.class);
				break;
			case Types.DATE:
			case Types.TIME:
			case Types.TIMESTAMP:
				colClassesList.add(java.sql.Date.class);
				break;
			case Types.VARCHAR:
				if (colName.contains("URL")) {
					colClassesList.add(URL.class);
				}
			default:
				colClassesList.add(String.class);
				break;
			}
			// //System.out.println("type: " + results.getInt("DATA_TYPE"));
		}
		int numCols = (colNamesList.size() < colClassesList.size() ? colNamesList
				.size() : colClassesList.size());
		columnNames = new String[numCols];
		colNamesList.toArray(columnNames);
		columnClasses = new Class[numCols];
		colClassesList.toArray(columnClasses);

		// get all data from table and put into
		// contents array

		Statement statement = conn.createStatement();
		results = statement.executeQuery("SELECT * FROM " + tableName);
		ArrayList<Object[]> rowList = new ArrayList<Object[]>();
		while (results.next()) {
			ArrayList<Object> cellList = new ArrayList<Object>();
			for (int i = 0; i < numCols; i++) {
				Object cellValue = null;

				if (columnClasses[i] == String.class)
					cellValue = results.getString(columnNames[i]);
				else if (columnClasses[i] == Integer.class)
					cellValue = new Integer(results.getInt(columnNames[i]));
				else if (columnClasses[i] == Float.class)
					cellValue = new Float(results.getInt(columnNames[i]));
				else if (columnClasses[i] == Double.class)
					cellValue = new Double(results.getDouble(columnNames[i]));
				else if (columnClasses[i] == java.sql.Date.class)
					cellValue = results.getDate(columnNames[i]);
				else if (columnClasses[i] == URL.class)
					cellValue = formURL(results.getString(columnNames[i]));
				else
					// //System.out.println("Can't assign " + columnNames[i]);
					cellValue = results.getString(columnNames[i]);
				cellList.add(cellValue);
			}// for
			Object[] cells = cellList.toArray();
			rowList.add(cells);

		} // while
			// finally create contents two-dim array
		contents = new Object[rowList.size()][];
		for (int i = 0; i < contents.length; i++)

			contents[i] = rowList.get(i);
		// System.out.println("Created model with " + contents.length +
		// " rows");

		// close stuff
		results.close();
		statement.close();

	}

	private Object formURL(String string) {
		try {
			return new URL(string);
		} catch (MalformedURLException e) {
			return null;
		}
	}

	/**
	 * The name of this database table
	 * 
	 * @return a String representing the name of this table
	 */
	public String getEscapedName() {
		return '`' + name + '`';
	}

	/**
	 * A reference to the database connection
	 * 
	 * @return a <tt>JdbcDatabase</tt> referring to the connection
	 */
	public JdbcDatabase getDatabase() {
		return db;
	}

	/**
	 * Add a column to this table.
	 * 
	 * @param columnName
	 *            the name of the new column
	 * @param t
	 *            the FieldType of the new column
	 * @throws SQLException
	 *             if we screw up
	 */
	public void addColumn(String columnName, Class<?> klass)
			throws SQLException {
		String query = "ALTER TABLE ";
		query += getEscapedName();
		query += " ADD ";
		query += columnName;
		query += " DATATYPE ";
		query += getMysqlDatatypeName(klass, columnName);
		db.executeUpdate(query);
		db.setStatus(query);
	}

	private String getMysqlDatatypeName(Class<?> klass, String columnName) {
		if (klass.equals(String.class)) {
			if (columnName.contains("memo")) {
				return "longtext";
			}
			return "varchar(255)";
		} else if (klass.equals(Double.class)) {
			return "double";
		} else if (klass.equals(Integer.class)) {
			return "int";
		} else if (klass.equals(Float.class)) {
			return "float";
		}
		return "varchar(255)";
	}

	/**
	 * Find the name of a particular column.
	 * 
	 * @param index
	 *            the index of the column. This is zero-based like Java, not
	 *            one-based like SQL. Remember that, kiddies.
	 * @return The name of the column.
	 */
	public String getColumnName(int index) {

		return columnNames[index];

	}

	/**
	 * Queries the structure of this table and spits out a ResultSet that talks
	 * about the columns and their types. Literally, talks about them. In a
	 * really cool accent. Trust me. You should call this method as often as you
	 * can.
	 * 
	 * @return a ResultSet containing information about the columns of this
	 *         table
	 * @throws SQLException
	 *             if your bowtie comes undone
	 */
	public ResultSet getStructure() throws SQLException {
		/*
		 * Table-structure table structure:
		 * ----------------------------------------------- Field Type Null Key
		 * Default Extra
		 */
		ResultSet rs = db.executeQueryScrollable("DESCRIBE " + getEscapedName()
				+ ";");
		return rs;
	}

	public int getColumnIndex(String colName) {
		int i = 0;
		for (String s : columnNames) {
			if (s.equals(colName))
				return i;
			i++;
		}
		return -1;
	}

	@Override
	public Object getValueAt(int row, int column) {
		if (row < 0 || row >= contents.length || column < 0
				|| column >= contents[row].length)
			return null;
		return contents[row][column];
	}
	
	public String getStringValueAt(int row, String column) {
		return getStringValueAt(row, getColumnIndex(column));
	}

	public Class<?> getColumnClass(int col) {
		return columnClasses[col];
	}

	public void setObjectAt(Object val, int row, int col) {

	}

	public int getRowCount() {
		return contents.length;
	}

	public int getColumnCount() {
		if (contents.length == 0)
			return 0;
		else
			return contents[0].length;
	}

	public DefaultListModel getTableStructureListModel() throws SQLException {
		DefaultListModel model = new DefaultListModel();
		// get metadata: what columns exist and what
		// types (classes) are they?
		DatabaseMetaData meta = db.getConnection().getMetaData();
		// //System.out.println("got meta = " + meta);
		ResultSet results = meta.getColumns(null, null, name, null);
		// //System.out.println("got column results");
		ArrayList<String> colNamesList = new ArrayList<String>();
		ArrayList<Class<?>> colClassesList = new ArrayList<Class<?>>();
		while (results.next()) {
			String colName = results.getString("COLUMN_NAME");
			colNamesList.add(colName);
			// //System.out.println("name: " +
			// results.getString("COLUMN_NAME"));
			int dbType = results.getInt("DATA_TYPE");
			switch (dbType) {
			case Types.INTEGER:
				colClassesList.add(Integer.class);
				break;
			case Types.FLOAT:
				colClassesList.add(Float.class);
				break;
			case Types.DOUBLE:
			case Types.REAL:
			case Types.DECIMAL:
				colClassesList.add(Double.class);
				break;
			case Types.DATE:
			case Types.TIME:
			case Types.TIMESTAMP:
				colClassesList.add(java.sql.Date.class);
				break;
			case Types.VARCHAR:
				if (colName.contains("URL")) {
					colClassesList.add(URL.class);
				}
			default:
				colClassesList.add(String.class);
				break;
			}
			// //System.out.println("type: " + results.getInt("DATA_TYPE"));
		}
		columnNames = new String[colNamesList.size()];
		colNamesList.toArray(columnNames);
		columnClasses = new Class[colClassesList.size()];
		colClassesList.toArray(columnClasses);
		if (columnClasses == null) {
			// System.out.println("columnClasses == null, uh oh");
		}
		for (int i = 0; i < columnNames.length; i++) {
			String dataTypeName = "null";
			if (columnClasses[i] != null) {
				dataTypeName = columnClasses[i].getSimpleName();
			}
			model.addElement(columnNames[i] + "\t(" + dataTypeName + ")");
		}
		return model;
	}

	public String getStringValueAt(int row, int col) {
		Object value = contents[row][col];
		return DataPair.getValueString(value);
	}
}
