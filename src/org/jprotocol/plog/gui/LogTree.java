package org.jprotocol.plog.gui;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.jprotocol.plog.nodes.AbstractNode;
import org.jprotocol.plog.nodes.RootNode;

@SuppressWarnings("serial")
public class LogTree extends JTree {
	public LogTree(RootNode root) {
        super(new NodeTreeModel(root));
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        setCellRenderer(new DefaultTreeCellRenderer() {
             public Component getTreeCellRendererComponent(JTree pTree, Object pValue, boolean pIsSelected, boolean pIsExpanded, boolean pIsLeaf, int pRow, boolean pHasFocus) {
            	AbstractNode<?> node = (AbstractNode<?>) pValue;
            	super.getTreeCellRendererComponent(pTree, pValue, pIsSelected, pIsExpanded, pIsLeaf, pRow, pHasFocus);
            	setForeground(node.getColor());
                return this;
             }
        });
        addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                AbstractNode<?> to = (AbstractNode<?>)getLastSelectedPathComponent();
                if (to == null) return;
                if (!isRoot()) {
                    expandTree(getSelectionPath());
                }
            }
        }); 

    }
    private void expandTree(final TreePath path) {
        final Object lastNode = path.getLastPathComponent();
        for (int i = 0; i < getModel().getChildCount(lastNode); i++) {
            final Object child = getModel().getChild(lastNode, i);
            TreePath pathToChild = path.pathByAddingChild(child);
            expandTree(pathToChild);
        }
        try {
            expandPath(path);
        } catch (final Exception e) {
            e.printStackTrace();
            //try next
        }
    }   
    public void expandAll() {
    	expandTree(new TreePath(getModel().getRoot()));
    }
    
	public boolean isRoot() {
		return getSelectionPath().equals(new TreePath(getModel().getRoot()));
	}
	
}

