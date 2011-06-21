package org.jprotocol.plog;

import java.util.ArrayList;
import java.util.List;

import org.jprotocol.framework.core.IProtocolMessage;
import org.jprotocol.framework.handler.IHandler;


public class LogSniffer implements ISniffedProtocol {
    private final List<HandlerProtocolTuple> tuples = new ArrayList<HandlerProtocolTuple>(); 
    
    @Override
    public IProtocolMessage sniff(IProtocolMessage protocol, IHandler handler) {
        tuples.add(new HandlerProtocolTuple(protocol.createSnapshot(), handler));
        return null;
    }
    
    @Override public String toString() {
        return getSniffedProtocol().getProtocolType().getName();
    }

    @Override
    public IProtocolMessage getSniffedProtocol() {
        return tuples.get(tuples.size() - 1).protocol;
    }

    @Override
    public void clear() {
        tuples.clear();
    }

    @Override
    public List<HandlerProtocolTuple> getProtocols() {
        return new ArrayList<HandlerProtocolTuple>(tuples);
    }
    
	@Override
	public void sniffSend(IProtocolMessage protocol, IHandler handler) {
		//do nothing
	}

}
