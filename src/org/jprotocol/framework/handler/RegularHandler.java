package org.jprotocol.framework.handler;

import static org.jprotocol.util.DBC.check;
import static org.jprotocol.util.DBC.isNull;
import static org.jprotocol.util.DBC.notNull;
import static org.jprotocol.util.DBC.require;

import org.jprotocol.framework.core.AbstractDecoratedProtocolMessage;
import org.jprotocol.framework.core.IProtocolLayoutFactory;
import org.jprotocol.framework.core.IProtocolMessage;



/**
 * This abstract handler is in between a lower handler and upper handlers
 * @author eliasa01
 *
 * @param <R>
 * @param <S>
 */
abstract public class RegularHandler<R extends AbstractDecoratedProtocolMessage, S extends AbstractDecoratedProtocolMessage> extends Handler<R, S> {

    protected RegularHandler(IProtocolLayoutFactory factory, 
            Type type, 
            boolean msbFirst, 
            String upperHandlerFieldName, 
            int headerReceiveValue, 
            int headerSendValue, 
            IProtocolState protocolState) {
        this(factory, type, msbFirst, upperHandlerFieldName, headerReceiveValue, headerSendValue, protocolState, null);
    }

    protected RegularHandler(IProtocolLayoutFactory factory, 
                             Type type, 
                             boolean msbFirst, 
                             String upperHandlerFieldName, 
                             int headerReceiveValue, 
                             int headerSendValue, 
                             IProtocolState protocolState,
                             IProtocolSniffer sniffer) {
        super(factory, type, msbFirst, upperHandlerFieldName, headerReceiveValue, headerSendValue, protocolState, sniffer);
    }

    public void setLowerHandler(ILowerHandler lowerHandler) {
        require(notNull(lowerHandler));
        check(isNull(this.lowerHandler));
        this.lowerHandler = lowerHandler;
        lowerHandler.register(headerReceiveValue, this);
    }

    @Override
    protected final void flush(IProtocolMessage p) {
        //Do nothing
    }
}
