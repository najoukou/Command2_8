
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.when;

import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import edu.uga.miage.m1.polygons.gui.commands.UndoCommand;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

class TestUndoCommand {

	@Test
	void test() {
		SimpleShape mockshape= mock(SimpleShape.class);
		JPanel mockPanel = mock(JPanel.class); 
        Graphics2D mockGraphics = mock(Graphics2D.class); 

        @SuppressWarnings("unchecked")
		List<SimpleShape>  mockShapesList = mock(List.class);

        UndoCommand undoCommand = new UndoCommand(mockshape, mockShapesList ); 
  

        when(mockPanel.getGraphics()).thenReturn(mockGraphics); 

        undoCommand.execute(); 

        Assertions.assertFalse(mockShapesList.contains(mockshape));        
	}

}
