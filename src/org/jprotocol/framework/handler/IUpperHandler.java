package org.jprotocol.framework.handler;


/**
 * An upper handler is a handler closer to a leaf handler, or is a leaf handler
 * @author Anders Eliasson
 *
 */
public interface IUpperHandler extends IHandler {
    int getHeaderSendValue();
    void receive(byte[] data);
    void resetState();
    
}
