package org.kevin.db;

import java.sql.SQLException;

import org.kevin.logger.KevinLogger;

/**
 * @author Tyler
 *
 */
public class DatabaseController implements Runnable {
	NativeController nc;
	KevinLogger log = new KevinLogger();
	
	/**
	 * Default constructor
	 */
	public DatabaseController(){
		try {
			nc = new NativeController();
		} catch (ClassNotFoundException e) {
			log.log(e, KevinLogger.MessageType.ERROR);
		} catch (SQLException e) {
			log.log(e, KevinLogger.MessageType.ERROR);
		}
	}

	@Override
	public void run() {
		while(true){
		}
		
	}
	
}
