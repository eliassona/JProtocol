package org.jprotocol.framework.core.argiters;

import org.jprotocol.framework.core.IArgumentType;
import org.jprotocol.framework.core.IProtocolLayoutType;


/**
 * Get the value name of a integer value
 */
public class ValueNameOfIter extends ArgTypeIter {
    private final String argName;
    private final int value;
    public String valueName;

    public ValueNameOfIter(String argName, int value, IProtocolLayoutType type) {
        super(type);
        this.argName = argName;
        this.value = value;
        iterate();
    }

    @Override
    protected boolean iter(IArgumentType arg) {
        if (arg.getName().equals(argName)) {
            if (arg.isEnumType()) {
                valueName = arg.nameOf(value);
            } else {
                valueName = "" + value;
            }
            return false;
        }
        return true;
    }
}
