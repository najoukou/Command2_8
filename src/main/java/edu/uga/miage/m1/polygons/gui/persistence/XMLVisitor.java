package edu.uga.miage.m1.polygons.gui.persistence;


import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Cube;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class XMLVisitor implements Visitor {

  
    private String representation = null;


  
    @Override
    public void visit(Circle circle) {
        
        representation = "<shape>\n" +
                "<type>circle</type>\n" +
                "<x>" + circle.getX() + "</x>\n" +
                "<y>" + circle.getY() + "</y>\n" +
                "</shape>\n";
    }

    @Override
    public void visit(Square square) {
        representation = "<shape>\n" +
                "<type>square</type>\n" +
                "<x>" + square.getX() + "</x>\n" +
                "<y>" + square.getY() + "</y>\n" +
                "</shape>\n";
    }

    @Override
    public void visit(Triangle triangle) {
        representation = "<shape>\n" +
                "<type>triangle</type>\n" +
                "<x>" + triangle.getX() + "</x>\n" +
                "<y>" + triangle.getY() + "</y>\n" +
                "</shape>\n";
    }
    @Override
	public void visit(Cube cube) {
    	representation = "<shape>\n" +
                "<type>cube</type>\n" +
                "<x>" + cube.getX() + "</x>\n" +
                "<y>" + cube.getY() + "</y>\n" +
                "</shape>\n";
		
	}

   
    public String getRepresentation() {
        return representation;
    }

	

}
    

