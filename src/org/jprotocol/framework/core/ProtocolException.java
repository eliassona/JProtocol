package org.jprotocol.framework.core;


@SuppressWarnings("serial")
public class ProtocolException extends RuntimeException {

    public ProtocolException() {
        //
    }
    public ProtocolException(String msg) {
        super(msg);
    }
}
