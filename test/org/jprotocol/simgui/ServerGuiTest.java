package org.jprotocol.simgui;

import org.jprotocol.example.protocols.protocolstack.ServerHandlerHierarchy;
import org.jprotocol.framework.handler.IFlushable;
import org.jprotocol.framework.handler.IProtocolSniffer;
import org.jprotocol.framework.handler.ProtocolState;
import org.jprotocol.framework.logger.IProtocolLogger;
import org.jprotocol.simgui.server.ServerGui;
import org.junit.Test;

public class ServerGuiTest {
	@Test 
	public void test() {
		ServerGui sg = new ServerGui(new ServerHandlerHierarchy(new IFlushable.NullFlushable(), new ProtocolState(), new IProtocolSniffer.NullProtocolSniffer(), new IProtocolLogger.NullProtocolLogger()));
		
	}
}
