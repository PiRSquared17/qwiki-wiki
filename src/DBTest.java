import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 */

/**
 * @author ryan
 * 
 */
public class DBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String locationUrl = "//box681.bluehost.com:3306/venicetw_preservenice";
		String url = "jdbc:mysql:" + locationUrl;
		try {
			String driverName = "com.mysql.jdbc.Driver";
		    Class.forName(driverName);
			
			//System.out.println("Connecting to "+url);
			Connection con = DriverManager.getConnection(url, "venicetw_pv10",
					"pv10pv");
			//System.out.println("Connection established.");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("show tables;");
			//System.out.println("Executing query...");
			ResultSetMetaData meta = rs.getMetaData();
			//System.out.println(meta.getColumnCount());
			while(rs.next()){
				//System.out.println(rs.getString(1));
			}
			//System.out.println("Query complete.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
