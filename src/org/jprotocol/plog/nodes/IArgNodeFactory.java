package org.jprotocol.plog.nodes;

import java.util.List;

import org.jprotocol.framework.core.IArgumentType;
import org.jprotocol.plog.HandlerProtocolTuple;

public interface IArgNodeFactory {
	AbstractNode<?> create(List<HandlerProtocolTuple> protocolStack, final IArgumentType arg, final AbstractNode<?> parent);
	AbstractNode<?> create(HandlerProtocolTuple hp, final IArgumentType arg, final AbstractNode<?> parent);
	
	public static class SimpleArgNodeFactory implements IArgNodeFactory {
		@Override
		public AbstractNode<?> create(List<HandlerProtocolTuple> protocolStack, IArgumentType arg, AbstractNode<?> parent) {
			return new ArgNode(protocolStack, arg, parent, this);
		}

		@Override
		public AbstractNode<?> create(HandlerProtocolTuple hp, IArgumentType arg, AbstractNode<?> parent) {
			return new ArgNode(hp, arg, parent, this);
		}
		
	}
}
