import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
  public static void main(String[] argv) throws Exception {
		String driver = "com.mysql.jdbc.Driver";
		String connection = "jdbc:mysql://box681.bluehost.com:3306/venicetw_preservenice";
		String user = "venicetw_maint";
		String password = "100institute";
		Class.forName(driver);
		Connection con = DriverManager
				.getConnection(connection, user, password);
 if (!con.isClosed()) {
      con.close();
    }
  }
}