package org.jprotocol.framework.handler;

import org.jprotocol.framework.core.AbstractDecoratedProtocolMessage;
import org.jprotocol.framework.core.IProtocolLayoutFactory;
import org.jprotocol.framework.core.IProtocolMessage;



/**
 * A leaf handler is at the top of the handler hierarchy
 * @author Anders Eliasson
 *
 * @param <R>
 * @param <S>
 */
abstract public class LeafHandler<R extends AbstractDecoratedProtocolMessage, S extends AbstractDecoratedProtocolMessage> extends RegularHandler<R, S> {

    protected LeafHandler(IProtocolLayoutFactory factory, Type type, boolean msbFirst, int lowerHandlerValue, int headerSendValue, IProtocolState protocolState) {
        this(factory, type, msbFirst, lowerHandlerValue, headerSendValue, protocolState, null);
    }
    protected LeafHandler(IProtocolLayoutFactory factory, Type type, boolean msbFirst, int lowerHandlerValue, int headerSendValue, IProtocolState protocolState, IProtocolSniffer sniffer) {
        super(factory, type, msbFirst, null, lowerHandlerValue, headerSendValue, protocolState, sniffer);
    }
    
    @Override
    final protected void makeHeader(IProtocolMessage p, IProtocolMessage payload, int headerValue) {
        //
    }
    protected void unsupportedProtocol(IProtocolMessage p) {
        //
    }
}
