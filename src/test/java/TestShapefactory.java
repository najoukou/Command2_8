import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame.Shapes;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Cube;
import edu.uga.miage.m1.polygons.gui.shapes.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

class TestShapefactory {

	@Test
	void testcreateCircle() {
		ShapeFactory factory= ShapeFactory.getInstance() ;
		SimpleShape shape = factory.createShape(Shapes.CIRCLE, 10, 10);
		Assertions.assertEquals( Circle.class, shape.getClass() ); 
	}
	@Test
	void testcreateTriangle() {
		ShapeFactory factory= ShapeFactory.getInstance() ;
		SimpleShape shape = factory.createShape(Shapes.TRIANGLE, 10, 10);
		Assertions.assertEquals( Triangle.class, shape.getClass() ); 
	}
	@Test
	void testcreateSquare() {
		ShapeFactory factory= ShapeFactory.getInstance() ;
		SimpleShape shape = factory.createShape(Shapes.SQUARE, 10, 10);
		Assertions.assertEquals( Square.class, shape.getClass() ); 
	}
	@Test
	void testcreateCube() {
		ShapeFactory factory= ShapeFactory.getInstance() ;
		SimpleShape shape = factory.createShape(Shapes.CUBE, 10, 10);
		Assertions.assertEquals( Cube.class, shape.getClass() ); 
	}
	


}
