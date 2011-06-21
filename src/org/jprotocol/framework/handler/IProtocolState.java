package org.jprotocol.framework.handler;

import java.util.Map;

import org.jprotocol.framework.core.IArgumentType;
import org.jprotocol.framework.core.INameValuePair;
import org.jprotocol.framework.core.IProtocolLayoutType;


/**
 * The protocol state is used in conjunction with virtual arguments.
 *
 */
public interface IProtocolState {
    INameValuePair getValue(IProtocolLayoutType type, IArgumentType arg);
    void setValue(IProtocolLayoutType type, IArgumentType arg, String value);
    Map<String, INameValuePair> makeFlattenedSnapshot();
    String flattenNameOf(IProtocolLayoutType type, IArgumentType arg);
    IProtocolState makeCopy();
}
