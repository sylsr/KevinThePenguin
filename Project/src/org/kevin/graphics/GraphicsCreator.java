package org.kevin.graphics;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URI;

/**
 * @author Josh
 * Basic SVG render tool
 */
public class GraphicsCreator {
	int x;
	int y;
	
	
	/**
	 * @param file the svg document uri to render
	 * @return rendered, transformed 2d graphic
	 * @throws SVGException
	 */
	public Graphics2D render(URI file) throws SVGException{
		BufferedImage image = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		SVGUniverse container = new SVGUniverse();
		SVGDiagram doc = new SVGDiagram(file, container);
		doc = transform(doc);
		doc.render(g);
		return g;
	}
	
	
	/**
	 * @param toTransform the svg diagram that needs transformation to fit the screen or whatever
	 * @return the transformed svg diagram
	 */
	public SVGDiagram transform(SVGDiagram toTransform){
		//apply transformations to fit image to selected resolution
		return toTransform;
	}
}
