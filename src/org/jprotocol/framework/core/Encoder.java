package org.jprotocol.framework.core;

abstract class Encoder extends EncoderDecoder {
	Encoder(IProtocolMessage protocol) {
		super(protocol);
	}
    abstract boolean encode(IArgumentType argType, String value);
}
