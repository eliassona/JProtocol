package org.jprotocol.plog.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

import org.jprotocol.plog.nodes.AbstractNode;


@SuppressWarnings("serial")
public class SearchView extends JPanel {
    private static final String SEARCH_RESULTS = "Search Results: ";
    final private JTree tree;

    public SearchView(final JTree tree) {
        super(new GridLayout(1, 0));
        this.tree = tree;
        final JTextField searchText = new JTextField(30);
        add(searchText);
        JButton search = new JButton("Search");
        final JCheckBox ignoreCase = new JCheckBox("Ignore Case");
        final JCheckBox exact = new JCheckBox("Complete Phrase");
        final JLabel searchResultsLabel = new JLabel(SEARCH_RESULTS);
        final JComboBox results = new JComboBox();
        results.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    PNodeAndTimestamp pts = (PNodeAndTimestamp) e.getItem();
                    setSelectedObject(pts.pNode);
                }
            }
        });
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Search searchObject = new Search(selectedObjectOf(), searchText.getText(), ignoreCase.isSelected(), exact.isSelected());
                results.removeAllItems();
                for (AbstractNode<?> to : searchObject.getFoundObjects()) {
                    results.addItem(new PNodeAndTimestamp(to));
                }
                if (results.getItemCount() > 0) {
                    PNodeAndTimestamp pst = (PNodeAndTimestamp) results.getItemAt(0);
                    setSelectedObject(pst.pNode); 
                }
                searchResultsLabel.setText(SEARCH_RESULTS + results.getItemCount() + " objects");
            }


            private AbstractNode<?> selectedObjectOf() {
                TreePath path = tree.getSelectionModel().getLeadSelectionPath();
                if (path == null || path.getLastPathComponent() == null) return (AbstractNode<?>) tree.getModel().getRoot();
                return (AbstractNode<?>) path.getLastPathComponent();
            }
        });
        add(search);
        add(searchResultsLabel);
        add(results);
        add(ignoreCase);
        add(exact);
    }
    private void setSelectedObject(AbstractNode<?> to) {
        TreePath path = pathOf(to);
        tree.getSelectionModel().setSelectionPath(path);
        tree.scrollPathToVisible(path);
    }

    void makeList(List<AbstractNode<?>> l, AbstractNode<?> to) {
        if (to == null) return;
        makeList(l, to.parent);
        l.add(to);
    }
    
    private TreePath pathOf(AbstractNode<?> to) {
        List<AbstractNode<?>> l = new ArrayList<AbstractNode<?>>();
        makeList(l, to);
        return new TreePath(l.toArray());
    }
}

class Search {
    final private String searchText;
    final private boolean ignoreCase;
    final private boolean wholeWord;
    final private List<AbstractNode<?>> foundObjects = new ArrayList<AbstractNode<?>>();
    
    protected Search(AbstractNode<?> root, String searchText, boolean ignoreCase, boolean wholeWord) {
        this.ignoreCase = ignoreCase;
        this.wholeWord = wholeWord;
        this.searchText = checkIgnoreCase(searchText);
        iterate(root);
    }
    
    public List<AbstractNode<?>> getFoundObjects() {
        return Collections.unmodifiableList(foundObjects);
    }
    
    private void iterate(AbstractNode<?> target) {
        if (isMatch(target)) {
            foundObjects.add(target);
        } else {
            for (AbstractNode<?> kid : target.children()) {
                iterate(kid);
            }
        }
    }

    private boolean isMatch(AbstractNode<?> target) {
//        if (searchText.indexOf("==") >= 0) {
//            return isPropertyValueMatch(target);
//        }
        try {
            return isMatch(target.toString());// || isPropertyMatch(target);
        } catch (Exception e) {
            //TODO swallow for now, must fix later
        }
        return false;
    }

//    private boolean isPropertyValueMatch(PNode target) {
//        int equalsIx = searchText.indexOf("==");
//        String propertySearchStr = checkIgnoreCase(searchText.substring(0, equalsIx).trim());
//        String valueSearchStr = checkIgnoreCase(searchText.substring(equalsIx + 2).trim());
//        
//        for (int i = 0; i < target.getProperties()[0].size(); i++) {
//            if (isMatch(target.getProperties()[0].get(i).toString(), propertySearchStr) &&
//                isMatch(target.getProperties()[1].get(i).toString(), valueSearchStr)) {    
//                return true;
//            }
//        }
//        return false;
//    }

    private String checkIgnoreCase(String string) {
        return ignoreCase ? string.toLowerCase() : string;
    }

//    private boolean isPropertyMatch(PNode target) {
//        for (Object o : target.getProperties()[1]) {
//            if (isMatch(o.toString())) {
//                return true;
//            }
//        }
//        return false;
//    }

    private boolean isMatch(String str) {
        return isMatch(str, searchText);
    }
    
    private boolean isMatch(String str, String searchStr) {
        String targetStr = checkIgnoreCase(str); 
        if (wholeWord) {
            return targetStr.equals(searchStr);
        }
        return targetStr.indexOf(searchStr) >= 0;
    }

    public String toString() { return "Search Result"; }
}

class PNodeAndTimestamp {
    public final AbstractNode<?> pNode;

    PNodeAndTimestamp(AbstractNode<?> pNode) {
        this.pNode = pNode;
    }
    public String toString() {
        return pNode.getTimestamp() + ": " + pNode.toString();
    }
}