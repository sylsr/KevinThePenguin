package org.kevin.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * @author Josh
 * This is the main graphics class. It receives data from the UI controller
 * and displays it on the screen using the data it calls from RenderedGraphics.
 */
public class Display extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RenderedGraphics bank = new RenderedGraphics();
	Graphics2D g;
	BufferedImage bi;
	
	/**
	 * Initiates the image bank and (for now) paints a simple graphic.
	 */
	public void init(){
		bank.init();
		Graphics2D g = bank.dat[0][0].createGraphics();
		this.setFocusable(true);
		this.requestFocus();
		bi = bank.dat[2][27];
		paintComponent(g);
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		final int width = getWidth();
		final int height = getHeight();
		g.setColor(getBackground());
		g.fillRect(0, 0, width, height);
		g.drawImage(bi, 0, 0, 540, 540, null);
		
	}
}
