package org.jprotocol.framework.handler;


public class LeafHandlerContext extends HandlerContext {

	public LeafHandlerContext(
			IHandlerHierarchy handlerHierarchy, 
			int lowerHeaderRequestValue,
			int lowerHeaderResponseValue) {
		super(handlerHierarchy, lowerHeaderRequestValue, lowerHeaderResponseValue);
	}

}
