package org.jprotocol.example.handler;
import org.jprotocol.framework.core.*;
import org.jprotocol.framework.handler.*;
import org.jprotocol.example.api.*;
/**
* This class is generated by DefaultHandlerGenerator.groovy
* @author Anders Eliasson
*/
public class DefaultMyMiddleProtocolAHandler extends Handler<MyMiddleProtocolA_Request_API, MyMiddleProtocolA_Response_API> {
    protected DefaultMyMiddleProtocolAHandler(HandlerContext context) {
        super(new org.jprotocol.example.protocols.MyMiddleProtocolA(), "MiddleSwitch", "MiddleSwitch", context);
    }
    @Override public final MyMiddleProtocolA_Request_API createRequest(IProtocolMessage p) {
        return new MyMiddleProtocolA_Request_API(p);
    }
    @Override public final MyMiddleProtocolA_Response_API createResponse(IProtocolMessage p) {
        return new MyMiddleProtocolA_Response_API(p);
    }
}
