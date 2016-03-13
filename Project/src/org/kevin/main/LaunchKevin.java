package org.kevin.main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class LaunchKevin {
	public static void main(String[]args){
		System.out.println("Starting Frame");
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(500,500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
	
}
