package org.jprotocol.framework.core.argiters;

import org.jprotocol.framework.core.IArgumentType;
import org.jprotocol.framework.core.IProtocolMessage;


public class ArgOfIter extends AbstractArgIter {
    public IArgumentType foundArg;
    private final String name;
    public ArgOfIter(IProtocolMessage db, String name) {
        super(db);
        this.name = name;
        iterate();
    }

    @Override
    protected boolean iter(IArgumentType arg) {
        if (arg.getName().equals(name)) {
            foundArg = arg;
            return false;
        }
        return true;
    }
    
}
