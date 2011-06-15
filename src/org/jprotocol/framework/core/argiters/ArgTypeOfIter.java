package org.jprotocol.framework.core.argiters;

import org.jprotocol.framework.core.IArgumentType;
import org.jprotocol.framework.core.IProtocolLayoutType;


public class ArgTypeOfIter extends ArgTypeIter {
    private final String name;
    public IArgumentType foundArgType;
    public ArgTypeOfIter(String name, IProtocolLayoutType type) {
        super(type);
        this.name = name;
        iterate();
    }

    @Override
    protected boolean iter(IArgumentType arg) {
        if (arg.getName().equals(name)) {
            foundArgType = arg;
            return false;
        }
        return true;
    }
    
}
