package org.jprotocol.example.handler.server;

import org.jprotocol.example.api.RequestAPIFactory;
import org.jprotocol.example.api.ResponseAPIFactory;
import org.jprotocol.framework.facade.AbstractServerFacade;
import org.jprotocol.framework.handler.IFlushable;
import org.jprotocol.framework.test.ProtocolMockery;

public class ServerFacade extends AbstractServerFacade {
	private final ServerHandlerHierarchy hierarchy;
	private final RequestAPIFactory requestFactory;
	private final ResponseAPIFactory responseFactory;

	public ServerFacade(IFlushable flushable) {
		hierarchy = new ServerHandlerHierarchy(flushable);
		requestFactory = new RequestAPIFactory();
		responseFactory = new ResponseAPIFactory();
	}
	public RequestAPIFactory requests() {
		return requestFactory;
	}
	public ResponseAPIFactory responses() {
		return responseFactory;
	}

	@Override
	protected ProtocolMockery getMockery() {
		return hierarchy.mockery;
	}
	public void receive(byte[] data) {
		hierarchy.receive(data);
	}
	
}
