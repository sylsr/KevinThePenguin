package org.kevin.main;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.kevin.controls.Controller;
import org.kevin.db.DatabaseController;
import org.kevin.graphics.Display;
import org.kevin.logger.KevinLogger;
import org.kevin.ui.UserInterface;

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
		startGameThreads();
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(960,540));
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Display panel = new Display();
		panel.init();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);		
	}
	
	private static void startGameThreads(){
		Thread UIThread = new Thread(new UserInterface());
		Thread controlThread = new Thread(new Controller());
		Thread DBThread = new Thread(new DatabaseController());
		
		UIThread.start();
		controlThread.start();
		DBThread.start();
	}
	
	
}
