import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.uga.miage.m1.polygons.gui.commands.CommandHistory;
import edu.uga.miage.m1.polygons.gui.commands.DrawCommand;
import edu.uga.miage.m1.polygons.gui.commands.Command;

class TestCommandHistory {

	@Test 

    void testPushAndPop() { 

        CommandHistory history = new CommandHistory(); 
        Command command1 = new DrawCommand(null, null,null); 
        Command command2 = new DrawCommand(null, null, null); 

        history.push(command1); 
        history.push(command2); 
        

        Assertions.assertEquals(command2, history.pop()); 
        Assertions.assertEquals(command1, history.pop()); 
       
    
        Assertions.assertEquals(null, history.pop() ); 


    } 

     

    @Test 

    void testIsEmpty() { 

        CommandHistory history = new CommandHistory(); 
        Assertions.assertTrue(history.isEmpty()); 

        history.push(new DrawCommand(null, null, null));
        Assertions.assertFalse(history.isEmpty()); 
        
        history.pop(); 
        Assertions.assertTrue(history.isEmpty()); 

    } 

}
