package org.jprotocol.example.handler;

import static org.jprotocol.framework.handler.HandlerDsl.handler;

import org.jprotocol.example.api.MyMiddleProtocolA_Request_API;
import org.jprotocol.example.api.MyMiddleProtocolA_Response_API;
import org.jprotocol.example.api.MyMiddleProtocolB_Request_API;
import org.jprotocol.example.api.MyMiddleProtocolB_Response_API;
import org.jprotocol.example.api.MyRootProtocol_Request_API;
import org.jprotocol.example.api.MyRootProtocol_Response_API;
import org.jprotocol.framework.handler.AbstractHandlerHierarchy;
import org.jprotocol.framework.handler.Handler;
import org.jprotocol.framework.handler.Handler.Type;
import org.jprotocol.framework.handler.HandlerContext;
import org.jprotocol.framework.handler.HandlerDsl.UpperHandler;
import org.jprotocol.framework.handler.IFlushable;
import org.jprotocol.framework.handler.IProtocolSniffer;
import org.jprotocol.framework.handler.IProtocolState;
import org.jprotocol.framework.handler.LeafHandlerContext;
import org.jprotocol.framework.handler.RootHandlerContext;
import org.jprotocol.framework.logger.IProtocolLogger;

public class DefaultHandlerHierarchy extends AbstractHandlerHierarchy<DefaultMyRootProtocolHandler>{
	
	public DefaultHandlerHierarchy(Type type, final IFlushable flushable, IProtocolState protocolState, IProtocolSniffer sniffer, IProtocolLogger logger) {
		super(type, flushable, protocolState, sniffer, logger);
		init();
	}
	
	
	@Override
	protected UpperHandler[] upperHandlers() {
		return upperHandlers( 
			handler(createMiddleA(new HandlerContext(type, msbFirst, MyRootProtocol_Request_API.RootSwitch.A, MyRootProtocol_Response_API.RootSwitchResp.A, protocolState, sniffer)), 
			  handler(createLeafA(new LeafHandlerContext(type, msbFirst, MyMiddleProtocolA_Request_API.MiddleSwitch.A, MyMiddleProtocolA_Response_API.MiddleSwitch.A, protocolState, sniffer)))
			),
			handler(createMiddleB(new HandlerContext(type, msbFirst, MyRootProtocol_Request_API.RootSwitch.B, MyRootProtocol_Response_API.RootSwitchResp.B, protocolState, sniffer)), 
			  handler(createLeafB(new LeafHandlerContext(type, msbFirst, MyMiddleProtocolB_Request_API.MiddleSwitch.B, MyMiddleProtocolB_Response_API.MiddleSwitch.B, protocolState, sniffer)))
			)
		);
	}
	private UpperHandler[] upperHandlers(UpperHandler...handlers) {
		return handlers;
	}

	@Override
	protected DefaultMyRootProtocolHandler createRoot(IFlushable flushable) {
		return new DefaultMyRootProtocolHandler(getRootContext(), flushable, logger);
	}
	protected Handler<?, ?> createLeafB(HandlerContext context) {
		return new DefaultMyLeafProtocolBHandler(context);
	}
	protected Handler<?, ?> createMiddleB(HandlerContext context) {
		return new DefaultMyMiddleProtocolBHandler(context);
	}
	protected Handler<?, ?> createLeafA(HandlerContext context) {
		return new DefaultMyLeafProtocolAHandler(context);
	}
	protected Handler<?, ?> createMiddleA(HandlerContext context) {
		return new DefaultMyMiddleProtocolAHandler(context);
	}

	
}
