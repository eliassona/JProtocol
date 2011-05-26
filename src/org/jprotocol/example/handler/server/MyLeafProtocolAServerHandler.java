package org.jprotocol.example.handler.server;

import org.jprotocol.example.api.MyMiddleProtocolA_Request_API;
import org.jprotocol.example.api.MyMiddleProtocolA_Response_API;
import org.jprotocol.example.handler.AbstractMyLeafProtocolAHandler;
import org.jprotocol.framework.dsl.IProtocolMessage;
import org.jprotocol.framework.handler.IProtocolSniffer;
import org.jprotocol.framework.handler.IProtocolState;

public class MyLeafProtocolAServerHandler extends AbstractMyLeafProtocolAHandler {

	public MyLeafProtocolAServerHandler(boolean msbFirst, IProtocolState protocolState, IProtocolSniffer sniffer) {
		super(Type.Server, msbFirst, null, MyMiddleProtocolA_Request_API.MiddleSwitch.A, MyMiddleProtocolA_Response_API.MiddleSwitch.A, protocolState, sniffer);
	}

	@Override
	protected void flush(IProtocolMessage p) {
	}

	@Override
	protected void makeHeader(IProtocolMessage header, IProtocolMessage payload, int headerValue) {
		
	}

}
