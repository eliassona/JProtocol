package org.jprotocol.example.dsl;
import org.jprotocol.example.handler.DefaultMyMiddleProtocolAHandler;
import org.jprotocol.framework.dsl.IProtocolMessage;
import org.jprotocol.framework.handler.Handler.Type;
import org.jprotocol.framework.handler.*;
import org.jprotocol.framework.logger.IProtocolLogger;
public class ServerHandlerHierarchy extends HandlerHierarchy {
    public ServerHandlerHierarchy(IFlushable flushable, IProtocolState protocolState, IProtocolSniffer sniffer, IProtocolLogger logger) {
        super(Type.Server, flushable, protocolState, sniffer, logger);
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
