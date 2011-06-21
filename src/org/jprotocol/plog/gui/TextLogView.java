package org.jprotocol.plog.gui;

import static org.jprotocol.util.Sugar.doNothing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;


@SuppressWarnings("serial")
class TextLogView extends JPanel {
    private final ITextLog textLog;
    private boolean initialized;
    private int lineHeight;
    private int selectedLine;
    TextLogView(final ITextLog textLog) {
        this.textLog = textLog;        
    }
    private int lineOf(int y) {
        return y / lineHeight;
    }   
    @Override
    public void paint(final Graphics g) {
        if (!initialized) {
            initialized = true;
            lineHeight = g.getFontMetrics().getHeight();
            setPreferredSize(new Dimension(getMaxLineWidth(g), lineHeight * textLog.getLines().size()));
            addMouseMotionListener(new MouseMotionListener() {
                public void mouseDragged(MouseEvent e) {
                    doNothing();
                }
                public void mouseMoved(MouseEvent e) {
                    String line = textLog.getLines().get(lineOf(e.getY()));
                    if (g.getFontMetrics().stringWidth(line) > getVisibleRect().width) {
                        setToolTipText(line);
                    } else {
                        setToolTipText(null);
                    }
                }
            });
        }
        super.paint(g);
        int y = lineHeight;
        g.setColor(Color.BLACK);
        int lineNr = 0;
        for (String line : textLog.getLines()) {
            if (y > g.getClipBounds().y + g.getClipBounds().height) return;
            if (y > g.getClipBounds().y) {
                if (lineNr == selectedLine) {
                    final Color c = g.getColor();
                    g.setColor(Color.RED);
                    g.drawString(line, 0, y);
                    g.setColor(c);
                } else {
                    g.drawString(line, 0, y);
                }
            }
            lineNr++;
            y += lineHeight;
        }
    }
    public void scrollToTimestamp(long timestamp) {
        if (textLog.hasLines()) {
            selectedLine = textLog.lineNrOf(timestamp);
            scrollRectToVisible(new Rectangle(0, textLog.lineNrOf(timestamp) * lineHeight, getWidth(), lineHeight));
            repaint();
        }
    }
    private int getMaxLineWidth(final Graphics g) {
        int w = 0;
        for (String line : textLog.getLines()) {
            w = Math.max(w, g.getFontMetrics().stringWidth(line));
        }        
        return w;
    }
}
