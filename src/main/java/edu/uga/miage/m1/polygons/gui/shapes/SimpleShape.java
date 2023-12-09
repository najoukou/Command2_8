package edu.uga.miage.m1.polygons.gui.shapes;

import java.awt.Graphics2D;

import edu.uga.miage.m1.polygons.gui.persistence.Visitor;

/**
 * This interface defines the <tt>SimpleShape</tt> extension. This extension
 * is used to draw shapes.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public interface SimpleShape {
    void accept(Visitor visitor);

    /**
     * Method to draw the shape of the extension.
     * @param g2 The graphics object used for painting.
     */
    void draw(Graphics2D g2);
    int getX();

    int getY();

	boolean isClicked(int x, int y);

	void setPosition(int x, int y);

}
