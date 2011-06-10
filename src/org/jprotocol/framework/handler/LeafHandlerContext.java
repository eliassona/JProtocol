package org.jprotocol.framework.handler;

import org.jprotocol.framework.handler.Handler.Type;

public class LeafHandlerContext extends HandlerContext {

	public LeafHandlerContext(
			Type type, 
			boolean msbFirst,
			int lowerHeaderRequestValue,
			int lowerHeaderResponseValue, 
			IProtocolState protocolState,
			IProtocolSniffer sniffer) {
		super(type, msbFirst, lowerHeaderRequestValue, lowerHeaderResponseValue, protocolState, sniffer);
	}

}
