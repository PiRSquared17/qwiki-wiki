package org.venipedia.record;

import java.io.File;
import java.util.Collection;
import java.util.Vector;

import javax.swing.filechooser.FileFilter;

import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Text;

import org.venipedia.dbcore.jdbc.JdbcDatabase;
import org.venipedia.dbcore.jdbc.JdbcTable;
import org.venipedia.importer.FileTypeFilter;

public class XMLLoader {

	public XMLLoader(File file) {
		this.file = file;
	}

	File file;

	public JdbcDatabase loadDatabase() {
		JdbcDatabase db = new JdbcDatabase(null,null);
//		Builder parser = new Builder();
//		Document doc;
//		try {
//			doc = parser.build(file);
//			Element elDb = doc.getRootElement();
//			Collection<Element> elsType = getAll(elDb, "RecordType");
//			for (Element elType : elsType) {
//				db.addTable(loadRecordType(elType));
//			}
//		} catch (ValidityException e) {
//			e.printStackTrace();
//		} catch (ParsingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return db;
	}

	public JdbcTable loadRecordType(Element elType) {
//		String typeName = elType.getAttribute("Name").getValue();
//		JdbcTable t = new JdbcTable(db, typeName);
//		Collection<Element> elsField = getAll(elType, "Field");
//		for (Element elField : elsField) {
//			String name = elField.getAttributeValue("Name");
//			long id = Long.parseLong(elField.getAttributeValue("ID"));
//			t.addFieldType(name,id);
////			//System.out.println("from loadRecordType()\t{{"+name+":"+id+"}}");
//		}
//		Collection<Element> records = getAll(elType, "Record");
//		for (Element elRecord : records) {
//			long recId = Long.parseLong(elRecord.getAttributeValue("ID"));
//			Collection<Element> elsfData = getAll(elRecord, "FieldValue");
//			Record record = t.addNewRecord(recId);
//			for (Element elSData : elsfData) {
//				if(elSData.getChildCount()==0) continue;
//				long id = Long.parseLong(elSData.getAttribute("ID").getValue());
//				String fieldValue = ((Text) elSData.getChild(0)).getValue();
//				record.set(id, fieldValue);
//			}
//		}
//		return t;
		return null;
	}
/*
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
	}*/

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
		return new FileTypeFilter(".kml", "Google Earth KML File");
	}

}
