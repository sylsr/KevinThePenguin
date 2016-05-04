package org.kevin.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.kevin.logger.KevinLogger;

/**
 * @author Tyler
 *Class to add a layer of encapsulation between the DatabaseController 
 *and the actual database. Is a simple set of methods that you would want to insert
 *any piece of data into the database.
 */
public class NativeController {
	Connection c = null;
	KevinLogger log=null;
	
	/**
	 * Default constructor.
	 * @throws SQLException When the Native controller object was unable to connec to the database. This will have to be handled by the UI.
	 * @throws ClassNotFoundException When the org.sqlite.JDBC driver class was not found. Check the class path.
	 */
	public NativeController() throws SQLException, ClassNotFoundException{
		log = new KevinLogger();
		log.log("Connecting to database", KevinLogger.MessageType.WARNING);
		//Register driver
	    Class.forName("org.sqlite.JDBC");
	    //Create the connection
	    c = DriverManager.getConnection("jdbc:sqlite:kevin.db");
	}
	
	
	/**
	 * Creates a table
	 * @param tableName name of the table you want to create
	 * @throws SQLException thrown when the command could not be executeded, the database failed to create.
	 */
	public void CreateTable(String tableName) throws SQLException{
		Statement stmt = c.createStatement();
		//TODO: create the method so that you can choose the layout of the table
		String execute = "CREATE TABLE " +tableName+ " " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " NAME           TEXT    NOT NULL, " + 
                " AGE            INT     NOT NULL, " + 
                " ADDRESS        CHAR(50), " + 
                " SALARY         REAL)";
		log.log("Attemping to create table: "+tableName, KevinLogger.MessageType.STATUS);
		stmt.executeUpdate(execute);
	    if(stmt!=null){
	    	stmt.close();
	    }
	}
	
	/**
	 * Deletes a table
	 * @param tableName name of the table you would like to delete
	 * @throws SQLException thrown when the command could not be executed.
	 */
	public void DeleteTable(String tableName) throws SQLException{
		Statement stmt = c.createStatement();
		String execute = "DROP TABLE kevin.db."+tableName;
		log.log("Attemping to drop table: "+tableName, KevinLogger.MessageType.STATUS);
		stmt.executeUpdate(execute);
	    if(stmt!=null){
	    	stmt.close();
	    }
	}
	
}
