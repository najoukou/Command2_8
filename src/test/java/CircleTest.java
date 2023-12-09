import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import edu.uga.miage.m1.polygons.gui.shapes.Circle;

class CircleTest {

	@Test 

    void testSetPosition() { 

		Circle circle = new Circle(0, 0);
        circle.setPosition(50, 50);
        Assertions.assertEquals(25, circle.getX());
        Assertions.assertEquals(25, circle.getY());

    } 
	 @Test
	  void testGetX() {
	        Circle circle = new Circle(100, 200);
	        Assertions.assertEquals(75, circle.getX());
	    }

	    @Test
	    void testGetY() {
	        Circle circle = new Circle(50, 75);
	        Assertions.assertEquals(50, circle.getY());
	    }

	    @Test
	     void testIsClicked() {
	        Circle circle = new Circle(100, 100);

	        Assertions.assertTrue(circle.isClicked(125, 125)); 
	    }
     


}
