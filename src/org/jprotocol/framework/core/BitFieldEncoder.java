package org.jprotocol.framework.core;

import static org.jprotocol.framework.core.BitFilterUtil.arrayOf;


class BitFieldEncoder extends Encoder {

    BitFieldEncoder(IProtocolMessage protocol) {
		super(protocol);
	}

	@Override
    boolean encode(IArgumentType argType, String value) {
        if (!isBitField(argType)) return false;
        protocol.setData(arrayOf(argType.valueOf(value), protocol.getDataAsInts(), argType.getOffset(), argType.getSizeInBits()), 0);
        return true;
    }
}
