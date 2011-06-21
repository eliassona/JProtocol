package org.jprotocol.plog.gui;

import static java.awt.BorderLayout.CENTER;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.jprotocol.plog.nodes.RootNode;
 

@SuppressWarnings("serial")
public class PLogViewer extends JFrame {
    public PLogViewer(final RootNode root, String title, long timestamp, ITextLog textLog) {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().add(new PLogViewerPanel(root, title, textLog, this), CENTER);
        pack();
        setVisible(true);
    }
}

