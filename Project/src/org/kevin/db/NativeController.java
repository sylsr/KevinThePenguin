package org.kevin.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Tyler
 *Class to add a layer of encapsulation between the DatabaseController 
 *and the actual database. Is a simple set of methods that you would want to insert
 *any piece of data into the database.
 */
public class NativeController {
	Connection c = null;
	
	/**
	 * Default constructor.
	 * @throws SQLException When the Native controller object was unable to connec to the database. This will have to be handled by the UI.
	 * @throws ClassNotFoundException When the org.sqlite.JDBC driver class was not found. Check the class path.
	 */
	public NativeController() throws SQLException, ClassNotFoundException{
		//Register driver
	    Class.forName("org.sqlite.JDBC");
	    //Create the connection
	    c = DriverManager.getConnection("jdbc:sqlite:test.db");
	}
	
	
	/**
	 * Creates a table
	 * @param tableName name of the table you want to create
	 * @throws SQLException thrown when the command could not be executeded, the database failed to create.
	 */
	public void CreateTable(String tableName) throws SQLException{
		Statement stmt = c.createStatement();
		String execute = "CREATE TABLE "+tableName+" ();";
		System.out.println(execute);
		stmt.executeUpdate(execute);
	    if(stmt!=null){
	    	stmt.close();
	    }
	}
	
}
