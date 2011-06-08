package org.jprotocol.framework.handler;

import org.jprotocol.framework.handler.Handler.Type;

public class RootHandlerContext extends HandlerContext {

	public RootHandlerContext(
			Type type, 
			boolean msbFirst,
			String upperHeaderRequestFieldName,
			String upperHeaderResponseFieldName, 
			IProtocolState protocolState,
			IProtocolSniffer sniffer) {
		super(type, msbFirst, upperHeaderRequestFieldName, upperHeaderResponseFieldName, 0,0, protocolState, sniffer);
	}

}
