package org.jprotocol.framework.dsl.argiters;

import org.jprotocol.framework.dsl.IArgumentType;
import org.jprotocol.framework.dsl.IProtocolLayoutType;

public class FindSwitchIter extends ArgTypeIter {

	private IArgumentType foundSwitch;

	public FindSwitchIter(IProtocolLayoutType type) {
		super(type);
		iterate();
	}

	@Override
	protected boolean iter(IArgumentType arg) {
		if (arg.isSwitch()) {
			foundSwitch = arg;
			return false;
		}
		return true;
	}
	
	
	public IArgumentType getSwitch() {
		return foundSwitch;
	}

}
