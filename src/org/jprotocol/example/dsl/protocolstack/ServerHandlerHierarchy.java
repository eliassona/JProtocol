package org.jprotocol.example.dsl.protocolstack;
import org.jprotocol.example.handler.DefaultMyMiddleProtocolAHandler;
import org.jprotocol.framework.core.IProtocolMessage;
import org.jprotocol.framework.handler.Handler;
import org.jprotocol.framework.handler.Handler.Type;
import org.jprotocol.framework.handler.HandlerContext;
import org.jprotocol.framework.handler.IFlushable;
import org.jprotocol.framework.handler.IProtocolSniffer;
import org.jprotocol.framework.handler.IProtocolState;
import org.jprotocol.framework.logger.IProtocolLogger;
/**
* Override create methods defined in AbstractDefaultHandlerHierarchy to provide specific implementation for handlers
* @note Do not extend this class!
*/
public final class ServerHandlerHierarchy extends HandlerHierarchy {
    public ServerHandlerHierarchy(IFlushable flushable, IProtocolState protocolState, IProtocolSniffer sniffer, IProtocolLogger logger) {
        super(Type.Server, flushable, protocolState, sniffer, logger);
        init();
    }
    /**
     * This factory method is needed for pLog. The implementation can be altered but the signature must remain the same
     */
    public static ServerHandlerHierarchy pLog(IProtocolState protocolState, IProtocolSniffer sniffer) {
        return new ServerHandlerHierarchy(new IFlushable.NullFlushable(), protocolState, sniffer, new IProtocolLogger.NullProtocolLogger());
    }
	@Override
	protected Handler<?, ?> createMyMiddleProtocolAHandler(HandlerContext context) {
		return new SpecializedMyMiddleProtocolAHandler(context);
	}

}
class SpecializedMyMiddleProtocolAHandler extends DefaultMyMiddleProtocolAHandler {
	protected SpecializedMyMiddleProtocolAHandler(HandlerContext context) {
		super(context);
	}
	
	@Override
	protected void makeHeader(IProtocolMessage header, IProtocolMessage payload, int headerValue) {
		createResponse(header).getMiddleHeader().setZ();
	}
}
