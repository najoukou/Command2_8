package edu.uga.miage.m1.polygons.gui.commands;

import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

public class DrawCommand implements Command {
	
	private SimpleShape shape;
	private JPanel panel;
    private List<SimpleShape> shapesList;

	public DrawCommand (SimpleShape shape,JPanel panel,List<SimpleShape> shapesList ) {
		this.panel=panel;
		this.shape=shape;
		this.shapesList= shapesList;
		
	}
	
	@Override
	public void execute() {
        shapesList.add(shape);

		shape.draw((Graphics2D) panel.getGraphics());
		
	}

	public SimpleShape getShape() {
        return shape;
    }

}
