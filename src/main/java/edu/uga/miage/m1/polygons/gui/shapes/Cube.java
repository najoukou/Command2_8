package edu.uga.miage.m1.polygons.gui.shapes;

import java.awt.Graphics2D;

import edu.uga.miage.m1.polygons.gui.persistence.Visitor;
import edu.uga.singleshape.CubePanel;

public class Cube implements SimpleShape{
	int m_x;
	int m_y;
	int m_s;
	CubePanel c;
	
	public Cube(int s, int x, int y) {
		this.m_s=s;
		this.m_x=x;
		this.m_y=y;
	}

	@Override
    public void accept(Visitor visitor) {
    	visitor.visit(this);
    }
	@Override
	public void draw(Graphics2D g2) {
		
	    c = new CubePanel(100, m_x, m_y);
		c.paintComponent(g2);
        
	}

	@Override
	public void setPosition(int x, int y) {
        m_x = x - 25;
        m_y = y - 25;
    }
    public int getX() {
        return m_x;
    }

    public int getY() {
        return m_y;
    
	}

	@Override
	public boolean isClicked(int clicledx, int clickedy) {
		return (clicledx >= m_x && clicledx <= m_x + 25 && clickedy >= m_y && clickedy<= m_y + 25);
	}

}
