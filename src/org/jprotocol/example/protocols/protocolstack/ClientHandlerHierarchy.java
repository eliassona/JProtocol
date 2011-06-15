package org.jprotocol.example.protocols.protocolstack;
import org.jprotocol.framework.handler.Handler.Type;
import org.jprotocol.framework.handler.*;
import org.jprotocol.framework.logger.IProtocolLogger;
/**
* Override create methods defined in AbstractDefaultHandlerHierarchy to provide specific implementation for handlers
* @note Do not extend this class!
*/
public final class ClientHandlerHierarchy extends HandlerHierarchy {
    public ClientHandlerHierarchy(IFlushable flushable, IProtocolState protocolState, IProtocolSniffer sniffer, IProtocolLogger logger) {
        super(Type.Client, flushable, protocolState, sniffer, logger);
        init();
    }
    /**
    * This factory method is needed for pLog. The implementation can be altered but the signature must remain the same
    */
    public static ClientHandlerHierarchy pLog(IProtocolState protocolState, IProtocolSniffer sniffer) {
        return new ClientHandlerHierarchy(new IFlushable.NullFlushable(), protocolState, sniffer, new IProtocolLogger.NullProtocolLogger());
    }
}
