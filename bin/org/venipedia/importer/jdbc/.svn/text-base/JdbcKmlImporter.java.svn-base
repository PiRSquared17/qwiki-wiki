package org.venipedia.importer.jdbc;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import javax.swing.filechooser.FileFilter;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;

import org.venipedia.dbcore.jdbc.JdbcDatabase;
import org.venipedia.dbcore.jdbc.JdbcTable;
import org.venipedia.importer.FileImporter;

public class JdbcKmlImporter extends FileImporter{
	
	public JdbcKmlImporter(){
		super();
	}
	
	public JdbcKmlImporter(File file) {
		super(file);
	}
	
	private String convertDataTypeToMySqlFormat(String kmlTypeName){
		if(kmlTypeName.contains("float"))
			return "double";
		else if(kmlTypeName.contains("int"))
			return "int";
		return "varchar";
	}

	public JdbcTable loadIntoDb(JdbcDatabase db) throws SQLException {
		try {
			Builder parser = new Builder();
			Document doc = parser.build(file);
			Element elKml = doc.getRootElement();
			Element elDoc = getFirst(elKml, "Document");
			Element elFolder = getFirst(elDoc, "Folder");
			Element elName = getFirst(elFolder, "name");
			String typeName = elName.getChild(0).getValue();
			JdbcTable t = new JdbcTable(db,typeName);
			Element elSchema = getFirst(elFolder, "Schema");
			Elements fields = elSchema.getChildElements();
			for (int i = 0; i < fields.size(); i++) {
				Element elField = fields.get(i);
				String name = elField.getAttributeValue("name");
				String fieldName = elField.getAttributeValue("type");
				if(fieldName.equals("id"))
					fieldName="id_kml";
//				t.addColumn(name,JdbcTable.getFieldTypeByName(convertDataTypeToMySqlFormat(fieldName)));
			}
			Collection<Element> placemarks = getAll(elFolder,"Placemark");
			
			int index=0;
			for(Element elPmk:placemarks){
				Element elEx = getFirst(elPmk,"ExtendedData");
				Element elSch = getFirst(elEx,"SchemaData");
				Collection<Element> elsSData = getAll(elSch,"SimpleData");
//				Record record = t.addNewRecord();
//				for(Element elSData:elsSData){
//					String fieldName = elSData.getAttribute("name").getValue();
//					String fieldValue = ((Text)elSData.getChild(0)).getValue();
//					record.set(fieldName, fieldValue);
//				}
			}
			return t;
		} catch (ParsingException ex) {
			ex.printStackTrace();
			return null;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private static Element getFirst(Element elKml, String s) {
		Elements elsDoc = elKml.getChildElements();
		for (int i = 0; i < elsDoc.size(); i++) {
			if (elsDoc.get(i) instanceof Element) {
				Element eltemp = (Element) elsDoc.get(i);
				if (eltemp.getLocalName().equals(s)) {
					return eltemp;
				}
			}
		}
		return null;
	}

	private static Collection<Element> getAll(Element elKml, String s) {
		Elements elsDoc = elKml.getChildElements();
		Vector<Element> els = new Vector<Element>(0, 1);
		for (int i = 0; i < elsDoc.size(); i++) {
			if (elsDoc.get(i) instanceof Element) {
				Element eltemp = (Element) elsDoc.get(i);
				if (eltemp.getLocalName().equals(s)) {
					els.add(eltemp);
				}
			}
		}
		return (els.size() == 0) ? null : els;
	}

	/**
	 * Returns a String containing the URL of the help file for this topic.
	 */
	public String getHelpURL() {
		return "http://code.google.com/p/venipedia-bot/wiki/KMLImportHelp";
	}

	public String getImporterName() {
		return "Google Earth KML Importer";
	}

	public String getFileFormatName() {
		return "KML";
	}

	/**
	 * Returns a FileFilter appropriate for use in a JFileChooser.
	 */
	public FileFilter getFileFilter() {
		return new FileTypeFilter("kml","Google Earth KML File");
	}

}
