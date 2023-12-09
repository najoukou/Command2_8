package edu.uga.miage.m1.polygons.gui.commands;

import java.util.List;


import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

// Implémentation de l'interface Command pour la commande "Undo".


public class UndoCommand implements Command {

    private SimpleShape shape;
    private List<SimpleShape> shapesList;

    public UndoCommand(SimpleShape shape, List<SimpleShape> shapesList ) {
    	
  		this.shape=shape;
  		this.shapesList= shapesList;
         

  	}

      public void execute() {
       shapesList.remove(shape);
      }

    @Override
    public SimpleShape getShape() {
        throw new UnsupportedOperationException("Unimplemented method 'getShape'");
    }

}
