package org.jprotocol.plog.gui;

import java.awt.Graphics;

import org.jprotocol.plog.nodes.ProtocolNode;


abstract class Annotation {
    protected final ProtocolNode pNode;

    Annotation(final ProtocolNode pNode) {
        this.pNode = pNode;
    }
    abstract public boolean intersectsWith(int x, int y);
    abstract public void draw(Graphics g);
    abstract public void setSelected(boolean selected);
    abstract int getY();
    abstract int getX();
}
