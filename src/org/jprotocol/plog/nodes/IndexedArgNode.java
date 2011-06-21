package org.jprotocol.plog.nodes;

import java.awt.Component;

import javax.swing.JLabel;

import org.jprotocol.framework.core.IArgumentType;
import org.jprotocol.framework.core.IProtocolMessage;
import org.jprotocol.plog.HandlerProtocolTuple;


class IndexedArgNode extends AbstractNode<IArgumentType> {
    private final IProtocolMessage protocol;
    private final int[] indexes;
    private String str;
    IndexedArgNode(final HandlerProtocolTuple protocol, int[] indexes, final IArgumentType arg, final AbstractNode<?> parent, IArgNodeFactory argNodeFactory) {
        super(arg, parent);
        this.protocol = protocol.protocol;
        this.indexes = indexes;
        for (final IArgumentType subArg : arg.getChildren()) {
        	if (subArg.isIndexedType()) {
                for (int i = 0; i < this.protocol.noOfEntriesOf(subArg); i++) {
                    add(new IndexedArgNode(protocol, arrayOf(indexes, i), subArg, this, argNodeFactory));
                }
        	} else {
        		add(argNodeFactory.create(protocol, protocol.protocol.argOf(subArg.getName(), indexes), this));
        	}
        }
    }
    private static int[] arrayOf(int[] indexes, int i) {
    	int[] result = new int[indexes.length + 1];
    	System.arraycopy(indexes, 0, result, 0, indexes.length);
    	result[indexes.length] = i;
		return result;
	}
	public String toString() {
        if (str == null) {
            str = target.toString() + ", indexes: " + indexesStr();
        }
        return str;
    }

    private String indexesStr() {
    	String str = "";
    	for (int i: indexes) {
    		if (str.length() > 0) {
    			str += ", ";
    		}
    		str += i;
    	}
		return str;
	}
	@Override
    public Component details() {
        return new JLabel();
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
		return null;
	}
	@Override
	protected boolean _isError() {
		return false;
	}
	@Override
	public IProtocolMessage getProtocol() {
		return protocol;
	}
}
