package org.jprotocol.framework.handler;

import org.jprotocol.framework.handler.Handler.Type;

public class RootHandlerContext extends HandlerContext {

	public RootHandlerContext(
			Type type, 
			boolean msbFirst,
			IProtocolState protocolState,
			IProtocolSniffer sniffer) {
		super(type, msbFirst, 0,0, protocolState, sniffer);
	}

}
