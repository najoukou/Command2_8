
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock; 


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import edu.uga.miage.m1.polygons.gui.commands.DrawCommand;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

class TestDrawCommand {

	@SuppressWarnings("unchecked")
	@Test
	void test() {
		SimpleShape mockshape= mock(SimpleShape.class);
		JPanel mockPanel = mock(JPanel.class); 
        Graphics2D mockGraphics = mock(Graphics2D.class); 

        List<SimpleShape>  mockShapesList = mock(List.class);

        DrawCommand drawCommand = new DrawCommand(mockshape, mockPanel, mockShapesList); 


        when(mockPanel.getGraphics()).thenReturn(mockGraphics); 

        drawCommand.execute(); 

        verify(mockshape).draw(mockGraphics); 

        verify(mockPanel).getGraphics(); 
	}

}
