package org.jprotocol.framework.core;

class BinaryDataEncoder extends Encoder {
    BinaryDataEncoder(IProtocolMessage protocol) {
		super(protocol);
	}

	@Override
    boolean encode(IArgumentType argType, String value) {
        return false;
    }
}
