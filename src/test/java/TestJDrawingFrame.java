

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

class TestJDrawingFrame {
	  
	@Test
	void TestexportToXML() throws IOException {
		SimpleShape circle = new Circle(10, 30);
	    SimpleShape square = new Square(23, 33);
	    SimpleShape triangle = new Triangle(12, 11);

        File tempFile = File.createTempFile("test_output", ".xml");
     

        JDrawingFrame drawingFrame = new JDrawingFrame("frametest");
        drawingFrame.addShapes(circle);
        drawingFrame.addShapes(square);
        drawingFrame.addShapes(triangle);

        drawingFrame.exportToXML(tempFile.getAbsolutePath());

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }

	    String expectedXMLContent = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
	            "<root>\n" +
	            "<shapes>\n" +
	            "<shape>\n" +
	            "<type>circle</type>\n" +
	            "<x>-15</x>\n" +
	            "<y>5</y>\n" +
	            "</shape>\n" +
	            "<shape>\n" +
	            "<type>square</type>\n" +
	            "<x>-2</x>\n" +
	            "<y>8</y>\n" +
	            "</shape>\n" +
	            "<shape>\n" +
	            "<type>triangle</type>\n" +
	            "<x>-13</x>\n" +
	            "<y>-14</y>\n" +
	            "</shape>\n" +
	            "</shapes>\n" +
	            "</root>";

	    Assertions.assertEquals(expectedXMLContent, content.toString().trim());
	    
        tempFile.delete();
	}
	@Test
	void TestExportTojson()throws IOException {
			
		SimpleShape circle= new Circle(125,125);
		SimpleShape square = new Square(125,125);
		SimpleShape triangle= new Triangle(125,125);
		
		File temp_file2= File.createTempFile("test_output", ".json");
		

		JDrawingFrame jdrawingframe= new JDrawingFrame("testframe");
		
		jdrawingframe.addShapes(circle);
		jdrawingframe.addShapes(square);
		jdrawingframe.addShapes(triangle);
        

		jdrawingframe.exportToJSON(temp_file2.getAbsolutePath());
		
		StringBuilder content= new StringBuilder();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(temp_file2))) {
			String line;
			while((line = reader.readLine())!=null){
				content.append(line).append("\n");
			}
		}
		String expectedJSONcontent="{\"shapes\":[{\"type\":\"circle\",\"x\":100,\"y\":100},{\"type\":\"square\",\"x\":100,\"y\":100},{\"type\":\"triangle\",\"x\":100,\"y\":100}]}";
		
		Assertions.assertEquals(expectedJSONcontent, content.toString().trim());
		temp_file2.delete();
		
		
		
	}
	
	@Test
	void testDraw() { 

	

	        Circle circle = new Circle(100, 100); 
	        Graphics2D graphicsMock = mock(Graphics2D.class); 
	        circle.draw(graphicsMock); 

	        verify(graphicsMock).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
	        verify(graphicsMock).setPaint(any(GradientPaint.class)); 
	        verify(graphicsMock).fill(any(Ellipse2D.Double.class)); 
	        verify(graphicsMock).setColor(Color.black); 
	        verify(graphicsMock).setStroke(any()); 
	        verify(graphicsMock).draw(any(Ellipse2D.Double.class)); 

	 
	    } 

	

}
