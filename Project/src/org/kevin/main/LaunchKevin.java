package org.kevin.main;

import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JFrame;

import org.kevin.db.NativeController;
import org.kevin.graphics.Display;
import org.kevin.logger.KevinLogger;

/**
 * Main class for the game.
 * @author Tyler
 *
 */
public class LaunchKevin {
	/**
	 * Entry point
	 * @param args cmd line args
	 */
	public static void main(String[]args){
		KevinLogger log= null;
		if(args.length>0){
			boolean debug=false;
			for(int i=0; i<args.length; i++){
				if(args[i].equals("-zxcvbnmasdfghjklqwertyuiop")){
					debug=true;
				}
			}
			if(debug==true){
				log = new KevinLogger("zxcvbnmasdfghjklqwertyuiop");
			}
		}else{
			log = new KevinLogger("releaseMode");
		}
		log.log("Starting frame", KevinLogger.MessageType.STATUS);
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(960,540));
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Display panel = new Display();
		panel.init();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
		
		try {
			NativeController db = new NativeController();
			db.CreateTable("another", "Nothing");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
	}
	
	
}
