import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class SetupJNDIDataSource {
	public static void main(String args[]) {
		try {
			startRegistry();
			MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();

			dataSource.setUser("venicetw_pv10");
			dataSource.setPassword("pv10pv");
			dataSource.setServerName("box681.bluehost.com");
			dataSource.setPort(3306);
			dataSource.setDatabaseName("venicetw_preservenice");

			InitialContext context = createContext();
			context.rebind("HrDS", dataSource);
			
//			//System.out.println("Connecting to "+dataSource.getServerName()+"...");

			Connection con = dataSource.getConnection();
//			//System.out.println("Connection established.");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SHOW TABLES;");
//			//System.out.println("Executing query...");
			ResultSetMetaData meta = rs.getMetaData();
//			//System.out.println(rs.)
			while (rs.next()) {
				//System.out.println(rs.getString(1));
			}
//			//System.out.println("Query complete.");

			con.close();
		} catch (Exception e) {
			//System.out.println("SetupJNDIDataSource err: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void startRegistry() throws RemoteException {
		LocateRegistry.createRegistry(1099);
		//System.out.println("RMI registry ready.");
	}

	private static InitialContext createContext() throws NamingException {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.rmi.registry.RegistryContextFactory");
		env.put(Context.PROVIDER_URL, "rmi://localhost:1099");
		InitialContext context = new InitialContext(env);
		return context;
	}
}
