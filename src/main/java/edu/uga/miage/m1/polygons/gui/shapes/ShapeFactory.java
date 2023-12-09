package edu.uga.miage.m1.polygons.gui.shapes;
import java.util.logging.Logger;
import edu.uga.miage.m1.polygons.gui.JDrawingFrame.Shapes;

public class ShapeFactory {

		   
		private static ShapeFactory instance;
	    private static final Logger logger = Logger.getLogger(ShapeFactory.class.getName());
		
		private ShapeFactory() {
			
		}
		public static synchronized ShapeFactory getInstance() {
			if (instance== null) {
				instance= new ShapeFactory();
			}
			return instance;
		}
		
	    public SimpleShape createShape(Shapes shapetype, int x, int y) {
	    	switch (shapetype) {
			case CIRCLE:
				return new Circle(x, y);
			case TRIANGLE:
				return new Triangle(x, y);
			case SQUARE:
				return new Square(x, y);
			case CUBE:
				return new Cube(100,x, y);	
			default:
	            logger.warning("No shape");				}
			return null;
	    	
	    }
	}

