package org.jprotocol.plog.gui;

import java.awt.Rectangle;

import javax.swing.JPanel;

import org.jprotocol.plog.nodes.AbstractNode;


@SuppressWarnings("serial")
abstract class AnnotationPanel extends JPanel {   
    protected Annotation selectedAnnotation;
 
    abstract void refresh();
    abstract void setSelectedObject(AbstractNode<?> to);    
    abstract Rectangle getSelectedRect();
}
