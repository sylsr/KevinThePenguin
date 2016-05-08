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
	//TODO: Set a timeout for all commands
	
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
	 * @param dataArgs the data args for the table being created
	 * @throws SQLException thrown when the command could not be executeded, the database failed to create.
	 */
	public void CreateTable(String tableName, String dataArgs) throws SQLException{
		Statement stmt = c.createStatement();
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
	
	/**
	 * This method will select ALL data from a table and return the ENTIRE result set.
	 * Example:
	 * if tableName=KEVIN
	 * Then this method will run:
	 * SELECT * FROM KEVIN
	 * @param tableName the table name to select the data name
	 * @return the result set I.E. ALL data from the table name
	 * @throws SQLException thrown when the data could not be elected.
	 */
	public ResultSet SelectAllFrom(String tableName) throws SQLException{
		Statement stmt = c.createStatement();
		String execute = "SELECT * FROM "+tableName+";";
		log.log("Attempting to select all data from "+tableName, KevinLogger.MessageType.STATUS);
		ResultSet rs=stmt.executeQuery(execute);
		
	    if(stmt!=null){
	    	stmt.close();
	    }
	    return rs;
	}
	
	/**
	 * This method will select all data from a table with the matched record string
	 * Example:
	 * if record=foo
	 * and tableName=KEVIN
	 * Then this method will run:
	 * SELECT foo FROM KEVIN
	 * @param record the data record to retrieve
	 * @param tableName the table to retrieve the table from
	 * @return the result set of the query
	 * @throws SQLException thrown when the command could not be executed
	 */
	public ResultSet SelectSpecificRecord(String record, String tableName) throws SQLException{
		Statement stmt = c.createStatement();
		String execute = "SELECT "+record+" FROM "+tableName+";";
		log.log("Attempting to select record "+record+" from "+tableName, KevinLogger.MessageType.STATUS);
		ResultSet rs=stmt.executeQuery(execute);
	    if(stmt!=null){
	    	stmt.close();
	    }
	    return rs;
	}
	
	/**
	 * This method will select all data from a table with the matched record string where data has matched
	 * Example:
	 * if record=foo
	 * and tableName=KEVIN
	 * and Snum=201
	 * Then this method will run:
	 * SELECT foo FROM KEVIN WHERE Snum = 201;
	 * @param record record the data record to retrieve
	 * @param tableName tableName the table to retrieve the table from
	 * @param where returns the data that matches a condition
	 * @return the ResultSet of the matched data
	 * @throws SQLException thrown when the command could not be executed
	 */
	public ResultSet SelectSpecificRecordWhere(String record, String tableName, String where) throws SQLException{
		Statement stmt = c.createStatement();
		String execute = "SELECT "+record+" FROM "+tableName+" WHERE "+where+";";
		log.log("Attempting to select record "+record+" FROM "+tableName+" WHERE "+where, KevinLogger.MessageType.STATUS);
		ResultSet rs=stmt.executeQuery(execute);
	    if(stmt!=null){
	    	stmt.close();
	    }
	    return rs;
	}
	
	/**
	 * This method will select all data from a table with the matched record string where data has matched
	 * Example:
	 * and tableName=KEVIN
	 * and Snum=201
	 * Then this method will run:
	 * SELECT * FROM KEVIN WHERE Snum = 201;
	 * @param tableName tableName the table to retrieve the table from
	 * @param where returns the data that matches a condition
	 * @return the ResultSet of the matched data
	 * @throws SQLException thrown when the command could not be executed
	 */
	public ResultSet SelectAllWhere(String tableName, String where) throws SQLException{
		Statement stmt = c.createStatement();
		String execute = "SELECT * FROM "+tableName+" WHERE "+where+";";
		log.log("Attempting to select record ALL FROM "+tableName+" WHERE "+where, KevinLogger.MessageType.STATUS);
		ResultSet rs=stmt.executeQuery(execute);
	    if(stmt!=null){
	    	stmt.close();
	    }
	    return rs;
	}
	
	/**
	 * This method will select all data from a table with the matched record string where data has matched
	 * Example:
	 * and tableName=KEVIN
	 * and setData=SALARY = 25000.00
	 * and where=ID=1
	 * Then this method will run:
	 * UPDATE KEVIN SET SALARY = 25000.00 WHERE ID=1;
	 * @param tableName the table to update
	 * @param setData the data to update
	 * @param where update the data where a condition is met
	 * @throws SQLException throws when the update could not be performed
	 */
	public void UpdateTableWhere(String tableName, String setData, String where) throws SQLException{
		Statement stmt = c.createStatement();
		String execute = "UPDATE "+tableName+" SET "+setData+" WHERE "+where+";";
		log.log("Attempting to update table data in table: "+tableName+" and setting data to: "+setData+" WHERE: "+where, KevinLogger.MessageType.STATUS);
		stmt.executeUpdate(execute);
	    if(stmt!=null){
	    	stmt.close();
	    }
	}
	
	
}
