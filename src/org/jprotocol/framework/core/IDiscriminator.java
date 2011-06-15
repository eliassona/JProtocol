package org.jprotocol.framework.core;


public interface IDiscriminator {
    boolean isInUse(IProtocolMessage protocol, IArgumentType argType);
}
