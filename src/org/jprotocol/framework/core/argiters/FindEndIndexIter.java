package org.jprotocol.framework.core.argiters;

import org.jprotocol.framework.core.IArgumentType;
import org.jprotocol.framework.core.IProtocolMessage;


public class FindEndIndexIter extends AbstractArgIter {
    public int endIndex;
    public FindEndIndexIter(IProtocolMessage p) {
        super(p);
        iterate();
    }

    @Override
    protected boolean iter(IArgumentType arg) {
        endIndex = Math.max(endIndex, arg.getEndByteIndex());
        return true;
    }
    
}
