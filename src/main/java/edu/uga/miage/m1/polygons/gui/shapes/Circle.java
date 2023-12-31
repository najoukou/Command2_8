/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package edu.uga.miage.m1.polygons.gui.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import edu.uga.miage.m1.polygons.gui.persistence.Visitable;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;

public class Circle implements SimpleShape, Visitable {

    int m_x;

    int m_y;

    public Circle(int x, int y) {
        m_x = x - 25;
        m_y = y - 25;
    }

    /**
     * Implements the <tt>SimpleShape.draw()</tt> method for painting
     * the shape.
     * @param g2 The graphics object used for painting.
     */
    public void draw(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(m_x, m_y, Color.RED, m_x + 50, m_y, Color.WHITE);
        g2.setPaint(gradient);
        g2.fill(new Ellipse2D.Double(m_x, m_y, 50, 50));
        BasicStroke wideStroke = new BasicStroke(2.0f);
        g2.setColor(Color.black);
        g2.setStroke(wideStroke);
        g2.draw(new Ellipse2D.Double(m_x, m_y, 50, 50));
    }

    @Override
    public void accept(Visitor visitor) {
    	visitor.visit(this);
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
    public boolean isClicked(int x, int y) {
        return (x >= m_x && x <= m_x + 50 && y >= m_y && y <= m_y + 50);
    }
}
