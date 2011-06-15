package org.jprotocol.framework.core;

import org.jprotocol.framework.core.argiters.ArgTypeOfIter;



public class AbstractDecoratedIndexedArgument extends AbstractDecorated {
    protected AbstractDecoratedIndexedArgument(IProtocolMessage protocol) {
        super(protocol);
    }
    
    protected int nrOf(String name) {
        return protocol.noOfEntriesOf(new ArgTypeOfIter(name, protocol.getProtocolType()).foundArgType);
    }
    
}


