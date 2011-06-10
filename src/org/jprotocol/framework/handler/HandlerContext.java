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
            Type type, 
            boolean msbFirst, 
            int lowerHeaderRequestValue, 
            int lowerHeaderResponseValue, 
            IProtocolState protocolState,
            IProtocolSniffer sniffer) {
		this.type = type;
		this.msbFirst = msbFirst;
        this.lowerHeaderRequestValue = lowerHeaderRequestValue;
        this.lowerHeaderResponseValue = lowerHeaderResponseValue;
		this.protocolState = protocolState;
		this.sniffer = sniffer;
	}
}
