package org.jprotocol.plog.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import org.jprotocol.plog.nodes.AbstractNode;
import org.jprotocol.plog.nodes.ProtocolNode;
import org.jprotocol.plog.nodes.RootNode;


@SuppressWarnings("serial")
public class PLogViewerTreePanel extends JPanel {
    public final LogTree tree;
    private final RootNode root;
    private final JFrame frame;
    private final TextLogView textLogView;
    private final OverviewPanel overviewPanelHorizontal;
    private final JLabel selectionPath;
    
    
    public PLogViewerTreePanel(RootNode root, final ITextLog textLog, final JFrame frame) {
        super(new BorderLayout());
        this.frame = frame;
        this.root = root;
        
        System.out.print("\nParsing lines...");

        this.selectionPath = new JLabel("");
        this.tree = createTree();
        
        textLogView = new TextLogView(textLog);
        overviewPanelHorizontal = new OverviewPanelHorizontal(tree, root.getProtocolNodes(), textLogView);
        createSplitPaneLayout(textLog, selectionPath, overviewPanelHorizontal);
        
        createMenu();

        System.out.print("done.");
    }

    private LogTree createTree() { 
    	return new LogTree(this.root);
    }
    
    private JSplitPane createSplitPaneLayout(final ITextLog textLog, final JLabel theSelectionPath, final OverviewPanel overviewPanel) {
        final Dimension minimumSize = new Dimension(200, 50);

        final JSplitPane theTopBottomSplitPane = createByteArraySplitPane(overviewPanel, minimumSize);
        final JSplitPane leftRightSplitPane = createTreeViewSplitPane(theTopBottomSplitPane, minimumSize);
        final JSplitPane textLogSplitPlane = createTextLogSplitPlaneIfNeeded(textLog, leftRightSplitPane);
        
        add(textLogSplitPlane, BorderLayout.CENTER);       
        
        //add(new SearchView(tree), BorderLayout.NORTH);
        add(theSelectionPath, BorderLayout.SOUTH);
        
        return theTopBottomSplitPane;
    }

    private JSplitPane createTextLogSplitPlaneIfNeeded(final ITextLog textLog, final JSplitPane leftRightSplitPane) {
        final JSplitPane chosenSplitPlane;
        if (textLog.hasLines()) {
            final JSplitPane textLogSplitPane = createTextLogSplitPane(leftRightSplitPane);
            chosenSplitPlane = textLogSplitPane;
        } else {
            chosenSplitPlane = leftRightSplitPane;
        }
        return chosenSplitPlane;
    }

    private JSplitPane createTextLogSplitPane(final JSplitPane leftRightSplitPane) {
        return new JSplitPane(JSplitPane.VERTICAL_SPLIT, leftRightSplitPane, scrollPaneOf(textLogView));
    }

    private JSplitPane createTreeViewSplitPane(final JSplitPane theTopBottomSplitPane, final Dimension minimumSize) {
        final JScrollPane leftProtocolTreeView = new JScrollPane(tree);
        leftProtocolTreeView.setMinimumSize(minimumSize);
        final JSplitPane leftRightSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftProtocolTreeView, theTopBottomSplitPane);
        leftRightSplitPane.setOneTouchExpandable(true);
        leftRightSplitPane.setDividerLocation(150);
        return leftRightSplitPane;
    }

    private JSplitPane createByteArraySplitPane(final OverviewPanel overviewPanel, final Dimension minimumSize) {
        final JScrollPane byteArrayView = new JScrollPane();
        byteArrayView.setMinimumSize(minimumSize);
        final JSplitPane theTopBottomSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, overviewPanel, byteArrayView);
        
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                AbstractNode<?> to = (AbstractNode<?>)tree.getLastSelectedPathComponent();
                if (to == null) return;
                theTopBottomSplitPane.setBottomComponent(new JScrollPane(to.details()));
                selectionPath.setText(to.getAbsolutName());
                if (!tree.getSelectionPath().equals(new TreePath(tree.getModel().getRoot()))) {
                    if (to instanceof ProtocolNode) {
                        overviewPanel.setSelectedObject(to);
                    }
                }
            }
        }); 
        
        return theTopBottomSplitPane;
    }
    
    private Component scrollPaneOf(TextLogView textLogView2) {
        final JScrollPane scrollPane = new JScrollPane(textLogView2);
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        return scrollPane;
    }
    private void createMenu() {
    	if (frame == null) return;
        final JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        menuBar.add(createView());
    }
    private JMenu createView() {
        final JMenu viewMenu = new JMenu("View");
        viewMenu.add(createExpandAll());
        return viewMenu;
    }
    private JMenuItem createExpandAll() {
        final JMenuItem expandAll = new JMenuItem("Expand All");
        expandAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tree.expandAll();
            }
        });
        return expandAll;
    }
    public Object[] getSelectedPath() {
        if (tree.getSelectionModel().getSelectionPath() == null) return null;
        return tree.getSelectionModel().getSelectionPath().getPath();
    }
}







// ------------------------------------------------------
// Overview panel
//------------------------------------------------------

@SuppressWarnings("serial")
class OverviewPanel extends JPanel {

    protected int divisor;
    protected int defaultDivisor;
    protected AnnotationPanel ap;
    protected JScrollPane scrollPanel;

    public OverviewPanel(final BorderLayout borderLayout) {
        super(borderLayout);
    }

    public void setSelectedObject(final AbstractNode<?> to)
    {
            ap.setSelectedObject(to);
    //        scrollPanel.scrollRectToVisible(ap.getSelectedRect());
            repaint();
    }
}






