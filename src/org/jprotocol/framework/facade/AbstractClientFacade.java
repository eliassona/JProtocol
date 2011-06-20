package org.jprotocol.framework.facade;

import org.jprotocol.framework.handler.Handler.Type;
import org.jprotocol.framework.handler.IFlushable;
import org.jprotocol.framework.handler.QualifiedName;
import org.jprotocol.framework.list.Expr;
import org.jprotocol.framework.logger.IProtocolLogger;
import org.jprotocol.framework.test.Request;
import org.jprotocol.framework.test.Response;

abstract public class AbstractClientFacade extends AbstractFacade {

	protected AbstractClientFacade(IFlushable flushable, Type type, IProtocolLogger logger) {
		super(flushable, type, logger);
	}
	public void expect(Response response) {
		expect(response, new QualifiedName());
	}
	public void expect(Response response, QualifiedName qName) {
		getMockery().expect(Expr.create(response.toString()), qName);
	}
	public void allow(Response response) {
		getMockery().allow(response.toString());
	}
	public void send(Request request) {
		send(request, new QualifiedName());
	}
	public void send(Request request, QualifiedName qName) {
		getMockery().send(Expr.create(request.toString()), qName);
	}
}
