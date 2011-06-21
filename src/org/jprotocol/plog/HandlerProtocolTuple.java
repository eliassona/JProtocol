package org.jprotocol.plog;

import org.jprotocol.framework.core.IProtocolMessage;
import org.jprotocol.framework.handler.IHandler;


public class HandlerProtocolTuple {
    public final IProtocolMessage protocol;
    public final IHandler handler;
    public HandlerProtocolTuple(IProtocolMessage protocol, IHandler handler) {
        this.protocol = protocol;
        this.handler = handler;
    }
}
