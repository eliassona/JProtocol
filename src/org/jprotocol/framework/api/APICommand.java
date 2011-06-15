package org.jprotocol.framework.api;

import org.jprotocol.framework.core.AbstractDecoratedProtocolMessage;

public interface APICommand<T extends AbstractDecoratedProtocolMessage> {
	T execute(T target);
}
