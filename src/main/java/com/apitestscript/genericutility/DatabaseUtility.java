package com.apitestscript.genericutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

/**
 * @author CHIDUSD
 */
public class DatabaseUtility {
	static Connection connection = null;
	static ResultSet resultSet = null;
	static FileUtility fileUtility = new FileUtility();

	/**
	 * Connect to database
	 * 
	 * @throws Throwable
	 */
	public static void connectToDB() throws Throwable {
		Driver driverRef;
		try {
			driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			connection = DriverManager.getConnection(fileUtility.getDataFromPropertiesFile("DBUrl"),
					fileUtility.getDataFromPropertiesFile("DB_Username"),
					fileUtility.getDataFromPropertiesFile("DB_Password"));
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * close the Db connection
	 * 
	 * @throws SQLException
	 */
	public static void closeDb() throws SQLException {
		connection.close();
	}

	/**
	 * getDataFromDB method to retrieve data from database as key value pairs
	 * 
	 * @param query
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static ResultSet execyteQuery(String query) throws Throwable {
		try {
			// executing the query
			resultSet = connection.createStatement().executeQuery(query);
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return resultSet;
	}

	/**
	 * 
	 * @param query
	 * @param columnName
	 * @param expectedData
	 * @return
	 * @throws Throwable
	 */
	public boolean executeQueryVerifyAndGetData(String query, int columnIndex, String expectedData) throws Throwable {
		boolean flag = false;
		resultSet = connection.createStatement().executeQuery(query);

		while (resultSet.next()) {
			if (resultSet.getString(columnIndex).equals(expectedData)) {
				flag = true;
				break;
			}
		}
		if (flag) {
			System.out.println(expectedData + "===> data verified in data base table");
			return true;
		} else {
			System.out.println(expectedData + "===> data not verified in data base table");
			return false;
		}
	}
}
