package org.jprotocol.plog.gui;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.jprotocol.plog.nodes.AbstractNode;


public class NodeTreeModel implements TreeModel {

    final private AbstractNode<?> root;

    public NodeTreeModel(AbstractNode<?> root) {
        this.root = root;
    }
    public void addTreeModelListener(final TreeModelListener l) {
        //
    }

    public Object getChild(final Object parent, int index) {
        return pNodeOf(parent).children()[index];
    }

    public int getChildCount(final Object parent) {
        return pNodeOf(parent).children().length;
    }

    public int getIndexOfChild(final Object parent, Object child) {
        for (int i = 0; i < pNodeOf(parent).children().length; i++) {
            if (pNodeOf(parent).children()[i] == child) {
                return i;
            }
        }
        return -1;
    }

    public Object getRoot() {
        return root;
    }

    public boolean isLeaf(Object node) {
        return pNodeOf(node).children().length == 0;
    }

    public void removeTreeModelListener(final TreeModelListener l) {
        //
    }

    public void valueForPathChanged(final TreePath path, final Object newValue) {
        //
    }
    
    private AbstractNode<?> pNodeOf(Object o) { return (AbstractNode<?>)o; }
}