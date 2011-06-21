package org.jprotocol.plog.nodes;

import static org.jprotocol.quantity.Quantity.quantity;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import org.jprotocol.framework.core.IArgumentType;
import org.jprotocol.framework.core.IProtocolLayoutType.Direction;
import org.jprotocol.framework.core.IProtocolMessage;
import org.jprotocol.framework.handler.QualifiedName;
import org.jprotocol.plog.HandlerProtocolTuple;
import org.jprotocol.plog.LoggedProtocols;
import org.jprotocol.quantity.Unit;


public class ProtocolNode extends AbstractNode<LoggedProtocols> {
    private String str;
	private final int lineNr;

    protected ProtocolNode(LoggedProtocols target, AbstractNode<?> parent, int lineNr, IArgNodeFactory argNodeFactory) {
        super(target, parent);
        this.lineNr = lineNr;
        IProtocolMessage p = getRootProtocol();
        
        for (IArgumentType a: p.getProtocolType().getArguments()) {
            add(argNodeFactory.create(target.protocols, a, this));
        }
        if (target.protocols.get(0).handler.getHeaderFieldName() == null) {
            for (AbstractNode<?> pn: ArgNode.createKids(target.protocols, this, argNodeFactory)) {
                add(pn);
            }
        }
    }

    
    @Override
    public Component details() {
        return RawDataTableModel.createTable(getRootProtocol(), quantity(0, Unit.bitSize));
    }

    @Override
    public long getAbsoluteTimestamp() {
        return target.absoluteTimestamp;
    }

    @Override
    public long getTimestamp() {
        return target.timestamp;
    }
    
    
    @Override
    public String toString() {
        if (str == null) {
            String prefix = "<-";
            if (isRequest()) {
                prefix = "->";
            }
            str = prefix + " " + lineNr + " " + getLeafProtocol().getProtocolType().getName() + ": " + " " + getTimestamp() + " ms"; 
        }
        return str;
    }
    private IProtocolMessage getRootProtocol() {
    	return target.protocols.get(0).protocol;
    }
    private IProtocolMessage getLeafProtocol() {
        return target.protocols.get(target.protocols.size() - 1).protocol;
    }

    public String getOverviewText() {
        return getLeafProtocol().getProtocolType().getName();
    }

    public boolean isRequest() {
        return getLeafProtocol().getProtocolType().getDirection() == Direction.Request;
    }

    boolean isResponse() {
        return getLeafProtocol().getProtocolType().getDirection() == Direction.Response;
    }

    public List<HandlerProtocolTuple> getProtocolStack() {
        return target.protocols;
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
		if (isAnnotation()) return Color.BLACK;
		if (isRequest()) return Color.BLUE;
		if (isResponse()) return new Color(0, 128, 0);
		return Color.BLACK;
	}
    private boolean isAnnotation() {
    	if (target.protocols.size() < 1) return false;
    	return (target.protocols.get(target.protocols.size() - 1).protocol.getProtocolType().getProtocolName().equals("Annotation"));
    }


	@Override
	public IProtocolMessage getProtocol() {
		return getRootProtocol();
	}

}
