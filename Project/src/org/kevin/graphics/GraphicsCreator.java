package org.kevin.graphics;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;

/**
 * @author Josh
 * Basic SVG render tool
 */
public class GraphicsCreator {
	int x=540;
	int y=540;
	/**
	 * Todo: add constructor variables and stuff
	 */
	public GraphicsCreator(){
	}
	
	/**
	 * @param file the svg document uri to render
	 * @return rendered, transformed 2d graphic
	 * @throws MalformedURLException 
	 * @throws SVGException
	 */
	@SuppressWarnings("deprecation")
	public BufferedImage render(File file){
		BufferedImage image = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		SVGUniverse container = new SVGUniverse();
		SVGDiagram doc = null;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		try {
			doc = container.getDiagram(container.loadSVG(file.toURL()));
			doc = transform(doc);
			doc.render(g);
		} catch (MalformedURLException | SVGException e1){
			System.out.println("Failed to load/render svg properly");
			
			e1.printStackTrace();
		}
		
		return image;
	}
	
	
	/**
	 * @param toTransform the svg diagram that needs transformation to fit the screen or whatever
	 * @return the transformed svg diagram.
	 */
	public SVGDiagram transform(SVGDiagram toTransform){
		//apply transformations to fit image to selected resolution
		//todo: actually add transformation formula
		return toTransform;
	}
}
