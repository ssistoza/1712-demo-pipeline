package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Purpose:
 * - Removing all connectors out of the DAO itself and must extend this connector.
 * 
 * @author Shane Avery Sistoza
 *
 */
public abstract class ErsConnectable {
	private static final String URL = "jdbc:oracle:thin:@usfdbjava.cxy6km2p6gzo.us-east-2.rds.amazonaws.com:1521:orcl";
	private static final String USERNAME = "ers";
	private static final String PASSWORD = "p4ssw0rd";
	
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Purpose: DAO Retrieves connections. Its methods must catch the exception.
	 * @return Connection
	 * @throws SQLException
	 */
	protected Connection getConnection() throws SQLException{
		return DriverManager.getConnection(ErsConnectable.URL, ErsConnectable.USERNAME, ErsConnectable.PASSWORD);
	}
	
}	
