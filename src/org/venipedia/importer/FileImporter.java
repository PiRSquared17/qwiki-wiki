package org.venipedia.importer;

import java.io.File;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * Represents an Importer that takes its input from any type of file, such as a
 * KML file or CSV file.
 * 
 * @author Ryan A. Muller
 * 
 */
public abstract class FileImporter extends Importer {
	/**
	 * The file that the data will be collected from
	 */
	protected File file;

	/**
	 * Empty constructor
	 */
	public FileImporter() {
		
	}

	/**
	 * Creates a new FileImporter with the given file
	 * 
	 * @param file
	 *            the file to load from
	 */
	public FileImporter(File file) {
		this.file = file;
	}

	/**
	 * Set the file that this importer will load from.
	 * 
	 * @param f
	 *            the desired file for loading
	 */
	public void setFile(File f) {
		file = f;
	}

	/**
	 * Get a short file format name for the filetype that this will load. For
	 * example, a Google Earth loader might use "KML" for the description. Will
	 * be used on the "load" button.
	 * 
	 * @return
	 */
	public abstract String getFileFormatName();

	/**
	 * Returns the file filter that will be used to ensure that the type of file
	 * opened is of the correct type.
	 * 
	 * @return
	 */
	public abstract FileFilter getFileFilter();

	/**
	 * Allows the user to select a file.
	 */
	public void chooseDataAccessMethod(JDesktopPane desktop) {
		JFileChooser chooser = new JFileChooser();
		if (file != null)
			chooser.setSelectedFile(file);
		chooser.setAcceptAllFileFilterUsed(false);
		FileFilter filter = getFileFilter();
		chooser.setFileFilter(filter);

		int retval = chooser.showOpenDialog(desktop);
		if (retval == JFileChooser.APPROVE_OPTION) {
			this.file = chooser.getSelectedFile();
		}
	}
}
