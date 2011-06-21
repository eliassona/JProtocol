package org.jprotocol.plog.nodes;

import static org.jprotocol.util.Contract.check;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

import org.jprotocol.framework.core.IProtocolLayoutType.Direction;
import org.jprotocol.framework.core.IProtocolMessage;
import org.jprotocol.framework.handler.QualifiedName;
import org.jprotocol.plog.HandlerProtocolTuple;
import org.jprotocol.plog.LoggedProtocols;
 

public class RootNode extends AbstractNode<List<LoggedProtocols>> {
	public static interface IProtocolListener {
		void notify(int lineNr, LoggedProtocols lp);
	}
    private final List<ProtocolNode> protocolsNodes = new ArrayList<ProtocolNode>();
	private final List<QualifiedName> requestFilters;
	private final List<QualifiedName> responseFilters;
	private final IArgNodeFactory argNodeFactory;
	private int lineNr;
	private final List<IProtocolListener> listeners = new ArrayList<IProtocolListener>();
    public RootNode(List<LoggedProtocols> loggedProtocols) {
    	this(loggedProtocols, Arrays.asList(new QualifiedName()), Arrays.asList(new QualifiedName()), new IArgNodeFactory.SimpleArgNodeFactory());
    }
    public RootNode(List<LoggedProtocols> loggedProtocols, List<QualifiedName> requestFilters, List<QualifiedName> responseFilters, IArgNodeFactory argNodeFactory) {
        super(loggedProtocols, null);
        this.requestFilters = requestFilters;
        this.responseFilters = responseFilters;
        this.argNodeFactory = argNodeFactory;
        for (LoggedProtocols lp: loggedProtocols) {
        	_add(lp);
        }
    }
    public void addProtocolListener(IProtocolListener listener) {
    	listeners.add(listener);
    }
    public void add(LoggedProtocols lp) {
    	_add(lp);
    	notifyListeners(lp);
    }
    
    private void notifyListeners(LoggedProtocols lp) {
    	for (IProtocolListener pl: listeners) {
    		pl.notify(lineNr, lp);
    	}
	}

    private void _add(LoggedProtocols lp) {
        try {
        	LoggedProtocols filteredLp;
        	if (lp.protocols.get(0).protocol.getProtocolType().getDirection() == Direction.Request) {
        		filteredLp = requestFilter(lp);
        	} else {
        		check(lp.protocols.get(0).protocol.getProtocolType().getDirection() == Direction.Response);
        		filteredLp = responseFilter(lp);
        	}
        	if (filteredLp.protocols.size() > 0) {
                ProtocolNode ppn = new ProtocolNode(filteredLp, this, lineNr + 1, argNodeFactory);
                add(ppn);
                protocolsNodes.add(ppn);
        	}
        } catch (Throwable e) {
        	add(new ErrorNode(e.getMessage(), lp.protocols.get(0).protocol.getData(), lineNr + 1, this));
        }
        lineNr++;
    }
    
    @Override
    protected boolean isSorted() {
    	return false;
    }
    LoggedProtocols requestFilter(LoggedProtocols lp) {
    	final List<HandlerProtocolTuple> l = new ArrayList<HandlerProtocolTuple>();
    	for (HandlerProtocolTuple hpt: lp.protocols) {
    		if (isRequestPart(hpt.handler.getQualifiedName())) {
    			l.add(hpt);
    		}
    	}
    	return new LoggedProtocols(l, lp.timestamp, lp.absoluteTimestamp);
    }
    
    private boolean isRequestPart(QualifiedName qName) {
    	for (QualifiedName qn: requestFilters) {
    		if (qName.startsWith(qn)) {
    			return true;
    		}
    	}
    	return false;
    }
    LoggedProtocols responseFilter(LoggedProtocols lp) {
    	final List<HandlerProtocolTuple> l = new ArrayList<HandlerProtocolTuple>();
    	for (HandlerProtocolTuple hpt: lp.protocols) {
    		if (isResponsePart(hpt.handler.getQualifiedName())) {
    			l.add(hpt);
    		}
    	}
    	return new LoggedProtocols(l, lp.timestamp, lp.absoluteTimestamp);
    }
    
    private boolean isResponsePart(QualifiedName qName) {
    	for (QualifiedName qn: responseFilters) {
    		if (qName.startsWith(qn)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public List<ProtocolNode> getProtocolNodes() {
        return Collections.unmodifiableList(protocolsNodes);
    }
    
    public long getTimeSpan() {
        return target.get(target.size() - 1).timestamp;
    }

    @Override
    public Component details() {
        return new JPanel();
    }

    @Override
    public long getAbsoluteTimestamp() {
        return target.get(0).absoluteTimestamp;
    }

    @Override
    public long getTimestamp() {
        return getAbsoluteTimestamp();
    }
    
    @Override
    public String toString() {
        return "Root";
    }
    
	@Override
	protected String _getAbsolutName() {
		return new QualifiedName().toString();
	}

	@Override
	protected boolean _isError() {
		return false;
	}
	@Override
	protected Color _getColor() {
		return Color.BLACK;
	}

	@Override
	public IProtocolMessage getProtocol() {
		return null;
	}

    
}
class ErrorNode extends AbstractNode<String> {
	public ErrorNode(String message, byte[] data, int lineNr, RootNode parent) {
		super(message, parent);
	}

	@Override
	protected boolean _isError() {
		return true;
	}

	@Override
	protected String _getAbsolutName() {
		return parent.getAbsolutName();
	}

	@Override
	public Component details() {
		return null;
	}

	@Override
	public long getTimestamp() {
		return parent.getTimestamp();
	}

	@Override
	public long getAbsoluteTimestamp() {
		return parent.getAbsoluteTimestamp();
	}

	@Override
	public IProtocolMessage getProtocol() {
		return parent.getProtocol();
	}
	
	@Override
	public String toString() {
		return target; 
	}
}