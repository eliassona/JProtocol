package org.jprotocol.framework.handler;

import java.util.Date;

import org.jprotocol.framework.core.AbstractDecoratedProtocolMessage;
import org.jprotocol.framework.core.IProtocolLayoutFactory;
import org.jprotocol.framework.core.IProtocolMessage;
import org.jprotocol.framework.core.IProtocolLayoutType.Direction;
import org.jprotocol.framework.logger.IProtocolLogger;


/**
 * The root handler is at the bottom of the handler hierarchy. 
 * Note that there can only be one root in a handler hierarchy
 * @author Anders Eliasson
 *
 * @param <R>
 * @param <S>
 */
abstract public class RootHandler<R extends AbstractDecoratedProtocolMessage, S extends AbstractDecoratedProtocolMessage> extends Handler<R, S> {
	private final IFlushable flushable;
	private final IProtocolLogger logger;
	protected RootHandler(
			IProtocolLayoutFactory factory,
			String upperHeaderRequestFieldName,
			String upperHeaderResponseFieldName, 
			HandlerContext context,
			IFlushable flushable, 
			IProtocolLogger logger) {
		super(factory, upperHeaderRequestFieldName, upperHeaderResponseFieldName, context);
		this.flushable = flushable;
		this.logger = logger;
	}
	@Override
	protected void flush(IProtocolMessage p) {
		byte[] data = p.getData();
		logger.write(new Date(), flushDirection(), data);
		flushable.flush(data);
	}
	@Override
	public void receive(byte[] data) {
		logger.write(new Date(), receiveDirection(), data);
		super.receive(data);
	}
	private Direction receiveDirection() {
		return isServer() ? Direction.Request : Direction.Response;
	}
	private Direction flushDirection() {
		return isServer() ? Direction.Response : Direction.Request;
	}
    
    
}
