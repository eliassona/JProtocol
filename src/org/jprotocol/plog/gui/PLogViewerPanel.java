package org.jprotocol.plog.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jprotocol.plog.nodes.RootNode;

@SuppressWarnings("serial")
public class PLogViewerPanel extends JPanel {
    public PLogViewerPanel(RootNode root, String title, ITextLog textLog, JFrame frame) {
        super(new BorderLayout());
        PLogViewerTreePanel treeView = new PLogViewerTreePanel(root, textLog, frame);
        add(new SearchView(treeView.tree), BorderLayout.NORTH);
        add(treeView, BorderLayout.CENTER);
    }

}
