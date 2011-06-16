package org.jprotocol.framework.facade;

import org.jprotocol.framework.list.Expr;
import org.jprotocol.framework.test.ProtocolMockery;
import org.jprotocol.framework.test.Request;
import org.jprotocol.framework.test.Response;

public class ResponseAction {
	private final Request request;
	private ProtocolMockery mockery;
	public ResponseAction(Request request, ProtocolMockery mockery) {
		this.request = request;
		this.mockery = mockery;
	}
	public void thenRespond(Response response) {
		mockery.addResponse(Expr.create(request.toString()), Expr.create(response.toString()), true);
	}
}
