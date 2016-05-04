package org.kevin.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author Josh
 * This is the main graphics class. It receives data from the UI controller
 * and displays it on the screen using the data it calls from RenderedGraphics.
 */
public class Display extends JPanel{
	RenderedGraphics bank = new RenderedGraphics();
	Graphics2D g;
	
	public void init(){
		bank.init();
		Graphics2D g = bank.img1[0].createGraphics();
		this.setFocusable(true);
		this.requestFocus();
		paintComponent(g, bank.img1[0]);
	}
	
	public void paintComponent(Graphics g, BufferedImage bi){
		super.paintComponent(g);
		final int width = getWidth();
		final int height = getHeight();
		g.setColor(getBackground());
		g.fillRect(0, 0, width, height);
		g.drawImage(bi, 0, 0, 500, 500, null);
		
	}
}
