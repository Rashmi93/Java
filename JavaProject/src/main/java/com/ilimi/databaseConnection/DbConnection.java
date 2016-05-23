package com.ilimi.databaseConnection;

import java.sql.*;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ilimi.consumer.Consumer;
import com.ilimi.jsonParser.JsonPojo;

public class DbConnection {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/testdb";

	// Database credentials
	static final String USER = "testuser";
	static final String PASS = "password";

	ArrayList<String> insertToDb = new ArrayList<String>();
	Consumer consume = new Consumer();

	public void insertToDatabase(JsonPojo item) throws JsonProcessingException {

		Connection conn = null;
		Statement stmt = null;
		JsonPojo res = new JsonPojo();

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database... : ");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String query = "insert into telemetrydata (did, uid, sid, ver,type,eid,ts,timestamp) values (?,?,?,?,?,?,?,?)";

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, item.getDid());
			preparedStmt.setString(2, item.getUid());
			preparedStmt.setString(3, item.getSid());
			preparedStmt.setString(4, item.getVer());
			preparedStmt.setString(5, item.getType());
			preparedStmt.setString(6, item.getEid());
			preparedStmt.setString(7, item.getTs());
			preparedStmt.setString(8, item.getTimestamp());
			preparedStmt.execute();

			stmt = conn.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery("SELECT * FROM telemetrydata");

			while (rs.next())
				System.out.println("did :" + rs.getString(1) + " uid : " + rs.getString(2) + "sid :  " + rs.getString(3)
				+ "ver :" + rs.getString(4) + "type :" + rs.getString(5) + "eid :" + rs.getString(6) + "ts :"
				+ rs.getString(7) + "timestamp :" + rs.getString(8));
			conn.close();
		} catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
	}
}