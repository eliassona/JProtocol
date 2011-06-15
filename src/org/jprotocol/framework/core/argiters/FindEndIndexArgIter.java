package org.jprotocol.framework.core.argiters;

import org.jprotocol.framework.core.IArgumentType;
import org.jprotocol.framework.core.IProtocolLayoutType;


public class FindEndIndexArgIter extends ArgTypeIter {
    private IArgumentType foundArg;
    public FindEndIndexArgIter(IProtocolLayoutType type) {
        super(type);
        if (type.getArguments().length > 0) {
            foundArg = type.getArguments()[0];
            iterate();
        }
    }

    @Override
    protected boolean iter(IArgumentType arg) {
        if (arg.getOffset() + arg.getSizeInBits() > foundArg.getOffset() + foundArg.getSizeInBits()) {
            foundArg = arg;
        }
        return true;
    }
    
    public IArgumentType getFoundArg() {
        return foundArg;
    }
}
