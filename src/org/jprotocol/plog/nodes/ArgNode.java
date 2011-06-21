package org.jprotocol.plog.nodes;

import static org.jprotocol.quantity.Quantity.quantity;

import java.awt.Component;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import org.jprotocol.framework.core.IArgumentType;
import org.jprotocol.framework.core.INameValuePair;
import org.jprotocol.framework.core.IProtocolMessage;
import org.jprotocol.framework.core.IllegalByteArrayValue;
import org.jprotocol.framework.handler.QualifiedName;
import org.jprotocol.plog.HandlerProtocolTuple;
import org.jprotocol.quantity.Unit;


public class ArgNode extends AbstractNode<IArgumentType> {
    private final IProtocolMessage protocol;
    private final String valueStr;
	private final QualifiedName context;
	private boolean error;
    private final static DecimalFormatSymbols fs = new DecimalFormatSymbols();
    private final static  DecimalFormat df        = new DecimalFormat("0.0#");
    static {
        fs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(fs);
    }

    public ArgNode(HandlerProtocolTuple hp, final IArgumentType arg, final AbstractNode<?> parent, IArgNodeFactory argNodeFactory) {
        this(listOf(hp), arg, parent, argNodeFactory);
    }
    
    private static List<HandlerProtocolTuple> listOf(HandlerProtocolTuple hp) {
        List<HandlerProtocolTuple> l = new ArrayList<HandlerProtocolTuple>();
        l.add(hp);
        return l;
    }
    public ArgNode(List<HandlerProtocolTuple> protocolStack, final IArgumentType arg, final AbstractNode<?> parent, IArgNodeFactory argNodeFactory) {
        super(arg, parent);
        this.protocol = protocolStack.get(0).protocol;
        this.context = protocolStack.get(0).handler.getQualifiedName();
        String str = null;
        if (arg.isEnumType()) {
        	try {
	        	INameValuePair nvp = protocol.getValueAsNameValuePair(arg);
	        	for (IArgumentType subArg : nvp.getArgTypes()) {
	        		add(argNodeFactory.create(protocolStack, subArg, this));
	        	}
	        	if (arg.getName().equals(protocolStack.get(0).handler.getHeaderFieldName())) {
	        		addAllKids(protocolStack, argNodeFactory);
	        	}
        	} catch (IllegalByteArrayValue e) {
        		error = true;
        		str = e.getMessage();
        	}
        } else if (arg.isIndexedType()) {
            for (int i = 0; i< protocol.noOfEntriesOf(arg); i++) {
                add(new IndexedArgNode(protocolStack.get(0), new int[]{i}, arg, parent, argNodeFactory));
            }
        }
        if (str == null) {
        	str = createString();
        }
        valueStr = str;
    }

    
    private String createString() {
    	String str;
    	try {
            str = target.getSimpleName();
            if (!target.isIndexedType()) {
                if (target.isEnumType() || target.isStr()) {
                    str += ": " + protocol.getValue(target);
                } else {
                    str += ": Raw=" + protocol.getValue(target) + ", Real=" + df.format(protocol.getRealQuantity(target).getValue()) + " " + target.getUnit();
                }
            }
        } catch (IllegalByteArrayValue e) {
        	error = true;
            str = e.getMessage();
        }
        return str;
	}

	private void addAllKids(List<HandlerProtocolTuple> protocolStack, IArgNodeFactory argNodeFactory) {
        for (AbstractNode<?> p: createKids(protocolStack, this, argNodeFactory)) {
            add(p);
        }
    }
    
    public static List<AbstractNode<?>> createKids(List<HandlerProtocolTuple> protocolStack, AbstractNode<?> parent, IArgNodeFactory argNodeFactory) {
        List<AbstractNode<?>> l = new ArrayList<AbstractNode<?>>();
        if (protocolStack.size() > 1) {
            List<HandlerProtocolTuple> ps = stripHeader(protocolStack);
            for (IArgumentType a: ps.get(0).protocol.getProtocolType().getArguments()) {
                l.add(argNodeFactory.create(ps, a, parent));
            }
            if (ps.get(0).handler.getHeaderFieldName() == null) {
                for(AbstractNode<?> pn: createKids(ps, parent, argNodeFactory)) {
                    l.add(pn);
                }
            }            
        }
        return l;
    }
    
    public static List<HandlerProtocolTuple> stripHeader(List<HandlerProtocolTuple> protocolStack) {
        List<HandlerProtocolTuple> l = new ArrayList<HandlerProtocolTuple>();
        for (int i = 1; i < protocolStack.size(); i++) {
            l.add(protocolStack.get(i));
        }
        return l;
    }


    
    public String toString() {
        return valueStr;
    }
    @Override
    public Component details() {
        return RawDataTableModel.createTable(protocol, quantity(target.getOffset(), Unit.bitSize));
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
	protected String _getAbsolutName() {
		return context.toString();
	}

	@Override
	protected boolean _isError() {
		return error;
	}

	@Override
	public IProtocolMessage getProtocol() {
		return protocol;
	}

}
