package org.kevin.main;

import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JFrame;

import org.kevin.db.NativeController;
import org.kevin.worldbuilder.Creator;

public class LaunchKevin {
	public static void main(String[]args){
		System.out.println("Starting Frame");
		JFrame frame = new JFrame();
		Creator ct = new Creator();
		ct.testMessage();
		frame.setPreferredSize(new Dimension(500,500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.pack();
		frame.setVisible(true);
		
		try {
			NativeController db = new NativeController();
			db.CreateTable("another");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
	}
	
	
}
