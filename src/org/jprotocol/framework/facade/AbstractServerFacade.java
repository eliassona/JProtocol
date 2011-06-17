package org.jprotocol.framework.facade;

import org.jprotocol.framework.handler.Handler.Type;
import org.jprotocol.framework.handler.IFlushable;
import org.jprotocol.framework.handler.QualifiedName;
import org.jprotocol.framework.list.Expr;
import org.jprotocol.framework.logger.IProtocolLogger;
import org.jprotocol.framework.test.Request;
import org.jprotocol.framework.test.Response;

abstract public class AbstractServerFacade extends AbstractFacade {

	protected AbstractServerFacade(IFlushable flushable, Type type, IProtocolLogger logger) {
		super(flushable, type, logger);
	}
	public void expect(Request request) {
		expect(request, new QualifiedName());
	}
	public void expect(Request request, QualifiedName qName) {
		getMockery().expect(Expr.create(request.toString()), qName);
	}
	public void allow(Request request) {
		allow(request, new QualifiedName());
	}
	public void allow(Request request, QualifiedName qName) {
		getMockery().allow(Expr.create(request.toString()), qName);
	}
	public ResponseAction when(Request request) {
		return when(request, new QualifiedName());
	}
	public ResponseAction when(Request request, QualifiedName qName) {
		return new ResponseAction(request, getMockery(), qName);
	}
	public void send(Response response) {
		send(response, new QualifiedName());
	}
	public void send(Response response, QualifiedName qName) {
		getMockery().send(Expr.create(response.toString()), qName);
	}
}
