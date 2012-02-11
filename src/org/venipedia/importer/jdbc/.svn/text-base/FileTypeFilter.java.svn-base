package org.venipedia.importer.jdbc;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileTypeFilter extends FileFilter{
	String extension, description;
	public FileTypeFilter(String extension, String description){
		this.extension=extension;
		this.description=description;
	}
	
	public boolean accept(File f) {
	    if (f.isDirectory()) {
	        return true;
	    }

	    return f.getName().endsWith(extension);
	}

	public String getDescription() {
		return description+" (."+extension+")";
	}
}
