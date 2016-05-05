package org.kevin.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

/**
 * @author Josh
 * Bank of all the buffered images. dat[][] is where they are all stored.
 */
public class RenderedGraphics {
	String Location = "";
	String IMG1 = "";
	int images;
	int img1_max = 0;
	String IMG2 = "";
	String img_name[];
	int img2_max = 0;
	int img_max[];
	BufferedImage dat[][];
	BufferedImage img1[];
	BufferedImage img2[];
	GraphicsCreator renderTool = new GraphicsCreator();
	URI f;
	
	
	/**
	 * Reads the imgs.properties file and allocates memory as appropriate
	 */
	public void init(){
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(System.getProperty("user.dir") + "\\config\\imgs.properties");
			prop.load(input);
		} catch (IOException e) {
			System.out.println("Failed to load imgs.config");
			e.printStackTrace();
		}
		Location = prop.getProperty("Location");
		images = Integer.parseInt(prop.getProperty("images"));
		dat = new BufferedImage[images][];
		img_max = new int[images];
		img_name = new String[images];
		
		for(int a = 0; a< images; a++){
			img_max[a] = Integer.parseInt(prop.getProperty("length" + a));
			img_name[a]= prop.getProperty(("img" +a));
		}
		for(int a = 0; a< images; a++){
			dat[a] = new BufferedImage[img_max[a]];
		}
		render();
		
	}
	
	/**
	 * Systematically fills the allocated dat[][] array with rendered images
	 */
	public void render(){
		File file;
		for(int b = 0; b<images; b++){
			for(int a = 0; a<img_max[b]; a++){
				file = new File(System.getProperty("user.dir") + Location + img_name[b] + a + ".svg");
				this.dat[b][a] = new BufferedImage(540, 540, BufferedImage.TYPE_INT_ARGB);
				dat[b][a] = renderTool.render(file);
			}
		}
	}
}
