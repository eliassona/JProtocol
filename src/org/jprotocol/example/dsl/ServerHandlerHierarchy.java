package org.jprotocol.example.dsl;

import org.jprotocol.framework.handler.Handler.Type;
import org.jprotocol.framework.handler.IFlushable;
import org.jprotocol.framework.handler.IProtocolSniffer;
import org.jprotocol.framework.handler.IProtocolState;
import org.jprotocol.framework.logger.IProtocolLogger;

public class ServerHandlerHierarchy extends HandlerHierarchy {

	public ServerHandlerHierarchy(IFlushable flushable, IProtocolState protocolState, IProtocolSniffer sniffer, IProtocolLogger logger) {
		super(Type.Server, flushable, protocolState, sniffer, logger);
	}

}
