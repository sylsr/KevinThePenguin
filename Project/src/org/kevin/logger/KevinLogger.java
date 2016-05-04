package org.kevin.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The logger to be used in all kevin related operations
 * @author Tyler
 *
 */
public class KevinLogger {
	
	static boolean debug=false;
	/**
	 * Enumeration for the different types of log messages you can write
	 * @author Tyler
	 *
	 */
	public static enum MessageType{
		/**
		 * Specify this message if there was an error thrown in the code
		 */
		ERROR,
		/**
		 * Specify this message before risky code that may result in a crash
		 */
		WARNING,
		/**
		 * Specify this message when the code is not likely to crash, but you still want to update the log.
		 */
		STATUS
	}
	
	/**
	 * Default constructor for everything EXCEPT the main entry point of the program (i.e. LaunchKevin)
	 */
	public KevinLogger(){
		
	}
	
	/**
	 * Constructor to be used ONLY in the main entry point of the program (i.e. LaunchKevin)
	 * @param buildArgs if the build args is equal to zxcvbnmasdfghjklqwertyuiop then it was a debug, else it was a release.
	 */
	public KevinLogger(String buildArgs){
		if(buildArgs == "zxcvbnmasdfghjklqwertyuiop"){
			debug=true;
		}else{
			debug=false;
		}
	}
	
	/**
	 * Write out to the console log or to the log file depending on the build. DO not provide a message formatted like ERROR: blah blah blah
	 * or WARNING: blah blah blah or STATUS: blah blah blah. That information will be automatically appended and printed out in the correct stream.
	 * Also do not timestamp your message, that will be done automatically. Simply provide a basic string that describes what is happening.
	 * @param msg the message to log
	 * @param type specify the type of message. An error would mean something has gone wrong, a warning identifies something that could potentially
	 * go wrong but may not, and a status message is just a message that may be helpful in debugging but will not relate directly with an error.
	 */
	public void log(String msg, MessageType type){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);//Gets the formatted date
		if(debug==true){
			switch(type){
			case ERROR:
				System.err.println((char)27 + "[31mERROR "+formattedDate+" : "+msg);
				break;
			case WARNING:
				System.out.println((char)27 + "[33mWARNING "+formattedDate+" : "+msg);
				break;
			case STATUS:
				System.out.println((char)27 + "[37m"+formattedDate+" : "+msg);
				break;
			}
		}else{
			switch(type){
			case ERROR:
				
				try {
					FileWriter fout= new FileWriter("log.txt",true);
					fout.append((char)27 + "[31mERROR "+formattedDate+" : "+msg);
					fout.append(System.getProperty("line.separator"));
					fout.close();
				} catch (IOException e) {
					//Nothing we can do.
				}
				break;
			case WARNING:
				
				try {
					FileWriter fout = new FileWriter("log.txt",true);
					fout.append((char)27 + "[33mWARNING "+formattedDate+" : "+msg);
					fout.append(System.getProperty("line.separator"));
					fout.close();
				} catch (IOException e) {
					//Nothing we can do.
				}
				break;
			case STATUS:
				
				try {
					FileWriter fout = new FileWriter("log.txt",true);
					fout.append(formattedDate+" : "+msg);
					fout.append(System.getProperty("line.separator"));
					fout.close();
				} catch (IOException e) {
					//Nothing we can do.
				}
				break;
			}
		}
	}
}
