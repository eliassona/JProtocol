package org.jprotocol.framework.handler;

import org.jprotocol.framework.handler.Handler.Type;
import org.jprotocol.framework.logger.IProtocolLogger;

public interface IHandlerHierarchy {
	boolean isMsbFirst();
	IProtocolSniffer getSniffer();
	IProtocolLogger getLogger();
	IProtocolState getProtocolState();
	Type getType();
}
