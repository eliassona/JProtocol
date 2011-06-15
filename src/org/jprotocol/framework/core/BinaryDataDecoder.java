package org.jprotocol.framework.core;


class BinaryDataDecoder extends Decoder {
    BinaryDataDecoder(IProtocolMessage protocol) {
		super(protocol);
	}

	@Override
    NameValuePair decodeToNV(IArgumentType argType) {
        return null;
    }
}
