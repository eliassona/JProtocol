package org.jprotocol.plog;

import java.util.List;

import org.jprotocol.framework.core.IProtocolMessage;
import org.jprotocol.framework.handler.IProtocolSniffer;


public interface ISniffedProtocol extends IProtocolSniffer {
    IProtocolMessage getSniffedProtocol();
    void clear();
    List<HandlerProtocolTuple> getProtocols();
}
