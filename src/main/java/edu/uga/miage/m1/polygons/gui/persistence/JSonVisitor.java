package edu.uga.miage.m1.polygons.gui.persistence;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Cube;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSonVisitor implements Visitor {

    private String representation = null;
    private JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();


    public JSonVisitor() {
    }
    
    @Override
    public void visit(Circle circle) {
    	 JsonObject circleObject = Json.createObjectBuilder()
                 .add("type", "circle")
                 .add("x", circle.getX())
                 .add("y", circle.getY())
                 .build();

         jsonObjectBuilder.add("shape", circleObject); 
    }

    @Override
    public void visit(Square square) {
    	JsonObject squareObject = Json.createObjectBuilder()
                .add("type", "square")
                .add("x", square.getX())
                .add("y", square.getY())
                .build();

        jsonObjectBuilder.add("shape", squareObject);    }

    @Override
    public void visit(Triangle triangle) {
    	JsonObject triangleObject = Json.createObjectBuilder()
                .add("type", "triangle")
                .add("x", triangle.getX())
                .add("y", triangle.getY())
                .build();

        jsonObjectBuilder.add("shape", triangleObject);    }
    @Override
	public void visit(Cube cube) {
    	JsonObject cubeObject = Json.createObjectBuilder()
                .add("type", "cube")
                .add("x", cube.getX())
                .add("y", cube.getY())
                .build();

        jsonObjectBuilder.add("shape", cubeObject);  
	}

    public String getRepresentation() {
        JsonObject jsonObject = jsonObjectBuilder.build().getJsonObject("shape"); 
        representation= jsonObject.toString();
        return representation;
      
    }

	
}
