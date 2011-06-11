package org.jprotocol.framework.handler;

import org.jprotocol.framework.handler.Handler.Type;

public class HandlerContext {
	public final Type type;
	public final IProtocolState protocolState;
	public final IProtocolSniffer sniffer;
	public final boolean msbFirst;
	public final int lowerHeaderRequestValue;
	public final int lowerHeaderResponseValue;
	public HandlerContext(
			IHandlerHierarchy handlerHierarchy,
            int lowerHeaderRequestValue, 
            int lowerHeaderResponseValue) {
		this.type = handlerHierarchy.getType();
		this.msbFirst = handlerHierarchy.isMsbFirst();
        this.lowerHeaderRequestValue = lowerHeaderRequestValue;
        this.lowerHeaderResponseValue = lowerHeaderResponseValue;
		this.protocolState = handlerHierarchy.getProtocolState();
		this.sniffer = handlerHierarchy.getSniffer();
	}
}
