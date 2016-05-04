package org.kevin.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import com.kitfox.svg.SVGException;

/**
 * @author Josh
 *
 */
public class RenderedGraphics {
	String Location = "";
	String IMG1 = "";
	int img1_max = 0;
	String IMG2 = "";
	int img2_max = 0;
	
	BufferedImage img1[];
	BufferedImage img2[];
	GraphicsCreator renderTool = new GraphicsCreator();
	URI f;
	public void init(){
		Properties prop = new Properties();
		InputStream input = null;
		

		System.out.println(System.getProperty("user.dir"));
		try {
			input = new FileInputStream(System.getProperty("user.dir") + "\\config\\imgs.properties");
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Location = prop.getProperty("Location");
		IMG1 = prop.getProperty("img1");
		IMG2 = prop.getProperty("img2");
		System.out.println(prop.getProperty("img1_length"));
		img1_max = Integer.parseInt(prop.getProperty("img1_length"));
		img2_max = Integer.parseInt(prop.getProperty("img2_length"));
		System.out.println(prop.getProperty("img2_length"));
		
		
		render();
		
	}
	
	public void render(){
		File file;
		for(int a = 0; a<=img1_max; a++){
			System.out.println("Test1");
			file = new File(System.getProperty("user.dir") + Location + IMG1 + a + ".svg");
			System.out.println("Test2");
			System.out.println(file);
			System.out.println("Test3");
			img1[a] = renderTool.render(file);
			System.out.println("Test4");
		}
		
	}
	
}
