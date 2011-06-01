package org.jprotocol.example.handler;

import static org.jprotocol.framework.handler.HandlerDsl.handler;

import org.jprotocol.example.api.MyMiddleProtocolA_Request_API;
import org.jprotocol.example.api.MyMiddleProtocolA_Response_API;
import org.jprotocol.example.api.MyMiddleProtocolB_Request_API;
import org.jprotocol.example.api.MyMiddleProtocolB_Response_API;
import org.jprotocol.example.api.MyRootProtocol_Request_API;
import org.jprotocol.example.api.MyRootProtocol_Response_API;
import org.jprotocol.framework.dsl.IProtocolLayoutType.Direction;
import org.jprotocol.framework.dsl.IProtocolMessage;
import org.jprotocol.framework.handler.AbstractHandlerHierarchy;
import org.jprotocol.framework.handler.Handler;
import org.jprotocol.framework.handler.Handler.Type;
import org.jprotocol.framework.handler.HandlerContext;
import org.jprotocol.framework.handler.HandlerDsl.UpperHandler;
import org.jprotocol.framework.handler.IFlushable;
import org.jprotocol.framework.handler.IProtocolSniffer;
import org.jprotocol.framework.handler.IProtocolState;
import org.jprotocol.framework.logger.IProtocolLogger;

public class DefaultHandlerHierarchy extends AbstractHandlerHierarchy<Root>{
	
	public DefaultHandlerHierarchy(Type type, final IFlushable flushable, IProtocolState protocolState, IProtocolSniffer sniffer, IProtocolLogger logger) {
		super(type, flushable, protocolState, sniffer, logger);
	}
	
	
	@Override
	protected UpperHandler[] upperHandlers() {
		return upperHandlers( 
			handler(createMiddleA(new HandlerContext(type, msbFirst, MyMiddleProtocolA_Request_API.MiddleSwitch.MiddleSwitch_ArgName, MyMiddleProtocolA_Response_API.MiddleSwitch.MiddleSwitch_ArgName, MyRootProtocol_Request_API.RootSwitch.A, MyRootProtocol_Response_API.RootSwitchResp.A, protocolState, sniffer)), 
			  handler(createLeafA(new HandlerContext(type, msbFirst, null, null, MyMiddleProtocolA_Request_API.MiddleSwitch.A, MyMiddleProtocolA_Response_API.MiddleSwitch.A, protocolState, sniffer)))
			),
			handler(createMiddleB(new HandlerContext(type, msbFirst, MyMiddleProtocolB_Request_API.MiddleSwitch.MiddleSwitch_ArgName, MyMiddleProtocolB_Response_API.MiddleSwitch.MiddleSwitch_ArgName, MyRootProtocol_Request_API.RootSwitch.B, MyRootProtocol_Response_API.RootSwitchResp.B, protocolState, sniffer)), 
			  handler(createLeafB(new HandlerContext(type, msbFirst, null, null, MyMiddleProtocolB_Request_API.MiddleSwitch.B, MyMiddleProtocolB_Response_API.MiddleSwitch.B, protocolState, sniffer)))
			)
		);
	}
	private UpperHandler[] upperHandlers(UpperHandler...handlers) {
		return handlers;
	}

	@Override
	protected Root createRoot(IFlushable flushable) {
		return new Root(getRootContext(), flushable, logger);
	}
	protected final HandlerContext getRootContext() {
		return new HandlerContext(type, msbFirst, MyRootProtocol_Request_API.RootSwitch.RootSwitch_ArgName, MyRootProtocol_Response_API.RootSwitchResp.RootSwitchResp_ArgName, 0, 0, protocolState, sniffer);
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
class Root extends DefaultMyRootProtocolHandler {
	private final IFlushable flushable;
	private final IProtocolLogger logger;

	Root(HandlerContext context, IFlushable flushable, IProtocolLogger logger) {
		super(context);
		this.flushable = flushable;
		this.logger = logger;
	}
	@Override
	protected void flush(IProtocolMessage p) {
		byte[] data = p.getData();
		logger.write("JProtocol", flushDirection(), data);
		flushable.flush(data);
	}
	@Override
	public void receive(byte[] data) {
		logger.write("JProtocol", receiveDirection(), data);
		super.receive(data);
	}
	private Direction receiveDirection() {
		return isServer() ? Direction.Request : Direction.Response;
	}
	private Direction flushDirection() {
		return isServer() ? Direction.Response : Direction.Request;
	}
	
}
