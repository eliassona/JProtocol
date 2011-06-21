package org.jprotocol.plog.nodes;

import static org.jprotocol.util.DBC.check;
import static org.jprotocol.util.DBC.isNotNull;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import org.jprotocol.framework.core.IProtocolMessage;


public abstract class AbstractNode<T> {
    protected final T target;
    private final List<AbstractNode<?>> kids = new ArrayList<AbstractNode<?>>(); 
    private AbstractNode<?>[] sortedKids;
    public final AbstractNode<?> parent;
    protected AbstractNode(T target, AbstractNode<?> parent) {
        this.target = target;
        this.parent = parent;
    }
    public final String getAbsolutName() {
    	String name = _getAbsolutName();
    	if (name == null) {
    		check(isNotNull(parent));
    		name = parent.getAbsolutName();
    	}
    	return name;
    }
    
    public Color getColor() {
    	if (isError()) {
    		return Color.RED;
    	}
    	return _getColor();
    }
    
    protected Color _getColor() {
    	if (parent != null) {
    		return parent._getColor();
    	}
    	return Color.BLACK;
    }
    
	protected final boolean isError() {
    	if (_isError()) return true;
    	for (AbstractNode<?> kid: children()) {
    		if (kid.isError()) return true;
    	}
    	return false;
    }
    
    abstract protected boolean _isError();

    abstract protected String _getAbsolutName();

    abstract public Component details();
    
    abstract public long getTimestamp();
    abstract public long getAbsoluteTimestamp();
    
    abstract public IProtocolMessage getProtocol();
    
    protected void add(AbstractNode<?> kid) {
        kids.add(kid);
    }
    
    public T getTarget() {
        return target;
    }
    
    protected boolean isSorted() {
    	return true;
    }
    public AbstractNode<?>[] children() {
    	if (sortedKids == null) {
    		sortedKids = kids.toArray(new AbstractNode<?>[kids.size()]);
    		if (isSorted()) {
//	    		Arrays.sort(sortedKids, new Comparator<PNode<?>>() {
//					@Override
//					public int compare(PNode<?> o1, PNode<?> o2) {
//						return o1.toString().compareTo(o2.toString());
//					}
//				});
    		}
    	}
        return sortedKids;
    }
}