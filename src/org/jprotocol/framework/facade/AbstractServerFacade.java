package org.jprotocol.framework.facade;

import org.jprotocol.framework.handler.Handler.Type;
import org.jprotocol.framework.handler.IFlushable;
import org.jprotocol.framework.logger.IProtocolLogger;
import org.jprotocol.framework.test.Request;
import org.jprotocol.framework.test.Response;

abstract public class AbstractServerFacade extends AbstractFacade {

	protected AbstractServerFacade(IFlushable flushable, Type type, IProtocolLogger logger) {
		super(flushable, type, logger);
	}
	public void expect(Request request) {
		getMockery().expect(request.toString());
	}
	public void allow(Request request) {
		getMockery().allow(request.toString());
	}
	public ResponseAction when(Request request) {
		return new ResponseAction(request, getMockery());
//		hierarchy.mockery.addResponse(requestResponse, removeWhenMatched)
	}
	public void send(Response response) {
		getMockery().send(response.toString());
	}
}
