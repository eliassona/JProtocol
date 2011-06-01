package org.jprotocol.example.handler;

import org.jprotocol.framework.dsl.IProtocolMessage;
import org.jprotocol.framework.dsl.IProtocolLayoutType.Direction;
import org.jprotocol.framework.handler.HandlerContext;
import org.jprotocol.framework.handler.IFlushable;
import org.jprotocol.framework.logger.IProtocolLogger;

public class Root extends DefaultMyRootProtocolHandler {
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
