package org.kevin.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
		log.log("Connecting to database", KevinLogger.MessageType.STATUS);
		//Register driver
	    Class.forName("org.sqlite.JDBC");
	    //Create the connection
	    c = DriverManager.getConnection("jdbc:sqlite:kevin.db");
	}
	
	
	/**
	 * Creates a table
	 * Example:
	 * Say tableName=KEVIN
	 * and dataArgs = 
	 * 			ID INT PRIMARY KEY     NOT NULL," +
     *          " NAME           TEXT    NOT NULL, " + 
     *          " AGE            INT     NOT NULL, " + 
     *          " ADDRESS        CHAR(50), " + 
     *          " SALARY         REAL        
	 * Then this method will run:
	 * CREATE TABLE KEVIN "(ID INT PRIMARY KEY     NOT NULL," +
     *           " NAME           TEXT    NOT NULL, " + 
     *           " AGE            INT     NOT NULL, " + 
     *           " ADDRESS        CHAR(50), " + 
     *           " SALARY         REAL)"
	 * @param tableName name of the table you want to create
	 * @throws SQLException thrown when the command could not be executeded, the database failed to create.
	 */
	public void CreateTable(String tableName, String dataArgs) throws SQLException{
		Statement stmt = c.createStatement();
		//TODO: create the method so that you can choose the layout of the table
		String execute = "CREATE TABLE " +tableName+ " " +"(" + dataArgs+")";
		log.log("Attemping to create table: "+tableName, KevinLogger.MessageType.STATUS);
		stmt.executeUpdate(execute);
	    if(stmt!=null){
	    	stmt.close();
	    }
	}
	
	/**
	 * Deletes a table
	 * Example:
	 * Say tableName=KEVIN
	 * Then this method will run:
	 * DROP TABLE kevin.db.KEVIN
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
	
	/**
	 * This method will insert data into a table.
	 * Example:
	 * if tableName=KEVIN
	 * and dataArgs=ID,NAME,AGE,ADDRESS,SALARY
	 * and dataVals=2, 'Allen', 25, 'Texas', 15000.00
	 * Then this method will run:
	 * INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)
     * VALUES (2, 'Allen', 25, 'Texas', 15000.00 );
	 * @param tableName the name of the table to insert data into
	 * @param dataArgs the table data parameters
	 * @param dataVals the values to be plugged into the table
	 * @throws SQLException thrown when the command could not be executed.o
	 */
	public void InsertInto(String tableName, String dataArgs, String dataVals) throws SQLException{
		Statement stmt = c.createStatement();
		String execute = "INSERT INTO "+tableName +"("+dataArgs+") "+
						"VALUES ("+dataVals+" );";
		log.log("Attemping INSERT into "+tableName+" with dataArgs: "+dataArgs+" and dataVals: "+dataVals, KevinLogger.MessageType.STATUS);
		stmt.executeUpdate(execute);
	    if(stmt!=null){
	    	stmt.close();
	    }
	}
	
	public void SelectAllFrom(String tableName) throws SQLException{
		Statement stmt = c.createStatement();
		String execute = "SELECT * FROM "+tableName;
		log.log("Attempting to select all data from "+tableName, KevinLogger.MessageType.STATUS);
		ResultSet rs=stmt.executeQuery(execute);
	    if(stmt!=null){
	    	stmt.close();
	    }
	}
	
	
	
}
