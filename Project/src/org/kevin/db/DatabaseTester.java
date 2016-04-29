package org.kevin.db;

import java.sql.SQLException;

/**
 * @author Tyler
 *Test class for the database- will likely move once a testing suite is setup.
 */
public class DatabaseTester {
	
	/**
	 * @param args cmd line args
	 */
	public static void main(String[]args){
		try {
			NativeController db = new NativeController();
			db.CreateTable("TEST");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
	}
}
