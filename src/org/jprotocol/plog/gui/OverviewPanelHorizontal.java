package org.jprotocol.plog.gui;

import static org.jprotocol.util.Contract.neverGetHere;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

import org.jprotocol.plog.nodes.AbstractNode;
import org.jprotocol.plog.nodes.ProtocolNode;
import org.jprotocol.plog.nodes.RootNode;


@SuppressWarnings("serial")
class OverviewPanelHorizontal extends OverviewPanel {

    public OverviewPanelHorizontal(final JTree tree, List<ProtocolNode> protocolNodes, final TextLogView textLogView) {
        super(new BorderLayout());
        setMinimumSize(new Dimension(800, 400));
        RootNode root = (RootNode) tree.getModel().getRoot();
//        if (root.getTarget().size() <= 0) {
//            return;
//        }
        int totalTimespan = (int) (root.getTimeSpan() * 1.1);
        
        defaultDivisor = Math.max(1, totalTimespan / 800);
        divisor = defaultDivisor;
        ap = new HorizontalAnnotationPanel(tree, protocolNodes, textLogView, totalTimespan);
        add(new HorizontalControlPanel(ap), BorderLayout.NORTH);
        scrollPanel = new JScrollPane(ap);
        scrollPanel.getHorizontalScrollBar().setUnitIncrement(15);
        add(scrollPanel, BorderLayout.CENTER);
    }

    class HorizontalControlPanel extends JPanel {
        private final AnnotationPanel annotataionPanel;
        HorizontalControlPanel(final AnnotationPanel ap) {
            super(new GridLayout(0, 3));
            this.annotataionPanel = ap;
            add(getDefaultZoom());
            add(getZoomIn());
            add(getZoomOut());
        }
        private JComponent getDefaultZoom() {
            JButton zoom = new JButton("Default Zoom");
            zoom.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    divisor = defaultDivisor;
                    annotataionPanel.refresh();
                }
            });
            return zoom;
        }
        
        private JComponent getZoomOut() {
            JButton zoomOut = new JButton("Zoom out");
            zoomOut.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    divisor++;
                    annotataionPanel.refresh();
                }
            });
            return zoomOut;
        }
        private JComponent getZoomIn() {
            JButton zoomIn = new JButton("Zoom in");
            zoomIn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (divisor <= 1) {
                        return;
                    }
                    divisor--;
                    annotataionPanel.refresh();
                }
            });
            return zoomIn;
        }
        
    }
    class HorizontalAnnotationPanel extends AnnotationPanel {
        private final int totalTimespan;
        private final List<Annotation> annotations = new ArrayList<Annotation>();
        private final JTree tree;
        private MouseEvent mousePressedEvent;
        private MouseEvent mouseDraggedEvent;

        private final TextLogView textLogView;
        HorizontalAnnotationPanel(final JTree tree, List<ProtocolNode> protocolNodes, final TextLogView textLogView, final int totalTimespan) {
            this.tree = tree;
            this.textLogView = textLogView;
            this.totalTimespan = totalTimespan;
            int yOffset = 0;
            for (ProtocolNode p: protocolNodes) {
                annotations.add(new HorizontalAnnotation(p, yOffset));
                yOffset += 12;
                if (yOffset > 300) yOffset = 0;
            }
            resize();
            addAnnotationMouseListener();
        }
        public void refresh() {
            resize();
            updateUI();
            repaint();
        }
        public Rectangle getSelectedRect() {
            return new Rectangle(selectedAnnotation.getX(), selectedAnnotation.getY(), 100, 10);
        }

        public void setSelectedObject(final AbstractNode<?> to) {
            if (selectedAnnotation != null) {
                selectedAnnotation.setSelected(false);
            }
            selectedAnnotation = annotationOf(to);
            selectedAnnotation.setSelected(true);
            scrollRectToVisible(getSelectedRect());
            textLogView.scrollToTimestamp(to.getAbsoluteTimestamp());
        }

        private Annotation annotationOf(final AbstractNode<?> to) {
            for (Annotation a : annotations) {
                if (a.pNode == to) {
                    return a;
                }
            }
            neverGetHere();
            return null;
        }
        private void resize() {
            setPreferredSize(new Dimension(totalTimespan / divisor, 300));
            invalidate();
        }

        public void paint(final Graphics g) {
            super.paint(g);
            Rectangle r = g.getClipBounds();
            g.clearRect(r.x, r.y, r.width, r.height);
            for (Annotation a : annotations) {
                a.draw(g);
            }
            if (mousePressedEvent == null || mouseDraggedEvent == null) {
                return;
            }
            g.setColor(Color.BLACK);
            g.drawLine(mousePressedEvent.getX(), 0, mousePressedEvent.getX(), getHeight());
            g.drawLine(mouseDraggedEvent.getX(), 0, mouseDraggedEvent.getX(), getHeight());
        }

        private void setSelectedObjectInTree(final AbstractNode<?> to) {
            TreePath path = pathOf(to);
            tree.getSelectionModel().setSelectionPath(path);
            tree.scrollPathToVisible(path);
        }

        void makeList(final List<AbstractNode<?>> l, final AbstractNode<?> to) {
            if (to == null) return;
            makeList(l, to.parent);
            l.add(to);
        }
        
        private TreePath pathOf(final AbstractNode<?> to) {
            List<AbstractNode<?>> l = new ArrayList<AbstractNode<?>>();
            makeList(l, to);
            return new TreePath(l.toArray());
        }
        private void selectInTree(int x, int y) {
            AbstractNode<?> pNode = pNodeOf(x, y);
            if (pNode != null) {
                setSelectedObjectInTree(pNode);
            }
        }
        private ProtocolNode pNodeOf(int x, int y) {
            for (Annotation a : annotations) {
                if (a.intersectsWith(x, y)) {
                    return a.pNode;
                }
            }
            return null;
        }
        private void addAnnotationMouseListener() {
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(final MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        selectInTree(e.getX(), e.getY());
                    }
                    refresh();
                }
                public void mousePressed(final MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        mousePressedEvent = e;
                        repaint();
                    }
                    refresh();
                }
                public void mouseReleased(final MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        if (e.getX() != mousePressedEvent.getX() && mouseDraggedEvent != null) {
                            zoom(mousePressedEvent.getX(), e.getX());
                        }
                        mousePressedEvent = null;
                        mouseDraggedEvent = null;
                    }
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        refresh();
                    }
                    refresh();
                }
            });
            addMouseMotionListener(new MouseMotionListener() {
                public void mouseDragged(final MouseEvent e) {
                    if (Math.abs(mousePressedEvent.getX() - e.getX()) < 6) return;
                    mouseDraggedEvent = e;
                    repaint();
                }
                public void mouseMoved(final MouseEvent e) {
//                    ProtocolNode pNode = pNodeOf(e.getX(), e.getY());
//                    if (pNode == null) {
//                        setToolTipText(null);
//                    } else {
//                        setToolTipText(textOf(pNode.getTarget().protocol));
//                    }
                }
//                private String textOf(IProtocol protocol) {
//                    
//                    StringBuffer str = new StringBuffer(protocol.getProtocolType().getName());
//                    for (IArgType a : protocol.getArguments()) {
//                        str.append(", ");
//                        str.append(a.getName());
//                        str.append("=");
//                        str.append(protocol.getValue(a));
//                    }
//                    return str.toString();
//                }
            });
        }
        public void zoom(int x1, int x2) {
            double oldDivisor = divisor;
            divisor = Math.max(1, divisor * Math.abs(x1 - x2) / getWidth());
            resize();
            double factor = oldDivisor / divisor;
            int newX = (int)(x1 * factor);
            int newW = (int)(Math.abs(x1 - x2) * factor);
            scrollRectToVisible(new Rectangle(newX, 0, newW, getHeight()));
            updateUI();
        }
    }
    class HorizontalAnnotation extends Annotation {
        private final ProtocolNode _pNode;
        private final int yOffset;
        private boolean selected;

        HorizontalAnnotation(final ProtocolNode pNode, int yOffset) {
            super(pNode);
            this._pNode = pNode;
            this.yOffset = yOffset;
        }
        public boolean intersectsWith(int x, int y) {
            Rectangle r = new Rectangle(getX(), getY() - 12, 100, 12);
            return r.intersects(x, y, 1, 1);
        }
        public void draw(final Graphics g) {
            Rectangle2D stringRect = g.getFontMetrics().getStringBounds(_pNode.getOverviewText(), g);
            if (!g.getClipBounds().intersects(new Rectangle(getX(), getY(), stringRect.getBounds().width, stringRect.getBounds().height))) {
                return;
            }
            if (isSelected()) {
                g.setColor(Color.BLACK);
                g.drawRect(getX(), yOffset + 1, stringRect.getBounds().width, stringRect.getBounds().height - 2);
            }
            setColor(g);
            g.drawString(_pNode.getOverviewText(), getX(), getY());
        }
        private void setColor(final Graphics g) {
        	g.setColor(_pNode.getColor());
        }   
        public void setSelected(boolean selected) {
            this.selected = selected;
        }   
        private boolean isSelected() {
            return selected;
        }
        int getY() {
            return 12 + yOffset;
        }
        int getX() {
            return (int) _pNode.getTimestamp() / divisor;
        }
    }
}
