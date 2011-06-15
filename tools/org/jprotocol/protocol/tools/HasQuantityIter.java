package org.jprotocol.protocol.tools;

import org.jprotocol.framework.core.IArgumentType;
import org.jprotocol.framework.core.IProtocolLayoutType;
import org.jprotocol.framework.core.argiters.ArgTypeIter;

public class HasQuantityIter extends ArgTypeIter {
	private boolean quantityFound;
	protected HasQuantityIter(IProtocolLayoutType type) {
		super(type);
		iterate();
	}

	@Override
	protected boolean iter(IArgumentType arg) {
		if (arg.isReal()) {
			quantityFound = true;
			return false;
		}
		return true;
	}
	public boolean hasQuantity() {
		return quantityFound;
	}

}
