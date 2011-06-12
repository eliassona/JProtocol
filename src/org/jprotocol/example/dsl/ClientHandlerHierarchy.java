package org.jprotocol.example.dsl;
import org.jprotocol.framework.handler.Handler.Type;
import org.jprotocol.framework.handler.*;
import org.jprotocol.framework.logger.IProtocolLogger;
public class ClientHandlerHierarchy extends HandlerHierarchy {
    public ClientHandlerHierarchy(IFlushable flushable, IProtocolState protocolState, IProtocolSniffer sniffer, IProtocolLogger logger) {
        super(Type.Client, flushable, protocolState, sniffer, logger);
        init();
    }
}
