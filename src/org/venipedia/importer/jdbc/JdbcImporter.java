package org.venipedia.importer.jdbc;

import javax.swing.JDesktopPane;

import org.venipedia.dbcore.jdbc.JdbcDatabase;

/**
 * Represents a generic data import. Could be from a database, could be from a
 * file, could be from some format we haven't even thought of yet. Flexibility
 * is the key here ;)
 * 
 * @author Ryan A Muller
 * 
 */
public abstract class JdbcImporter {

	/**
	 * Loads the record type into the database from the specified source.
	 */
	public abstract void load(JdbcDatabase db);

	/**
	 * Locates the help page for this module.
	 * 
	 * @return the URL, as a String, of the online help page.
	 */
	public abstract String getHelpURL();

	/**
	 * Gets the name for this importer, to be displayed atop the import window.
	 * 
	 * @return
	 */
	public abstract String getImporterName();

	/**
	 * Allows the user to connect to a database, select a file, or do whatever
	 * is necessary to specify where the data is coming from.
	 * 
	 * @param desktop
	 *            The JDesktopPane on which to display any windows (may have
	 *            more than one)
	 * @param impf
	 *            A reference to the importer frame, in case the subprocess
	 *            wants to hide it or make it go all wibbly or something.
	 */
	public abstract void chooseDataAccessMethod(JDesktopPane desktop);

}
