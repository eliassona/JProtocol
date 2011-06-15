package org.jprotocol.framework.core;

import org.jprotocol.quantity.Quantity;

public interface IDynamicCalcStrategy {
	Quantity calc(IArgumentType a, IProtocolMessage p); 
}
