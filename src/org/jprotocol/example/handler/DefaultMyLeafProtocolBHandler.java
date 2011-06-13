package org.jprotocol.example.handler;
import org.jprotocol.framework.dsl.*;
import org.jprotocol.framework.handler.*;
import org.jprotocol.example.api.*;
/**
* This class is generated by DefaultHandlerGenerator.groovy
* @author Anders Eliasson
*/
public class DefaultMyLeafProtocolBHandler extends Handler<MyLeafProtocolB_Request_API, MyLeafProtocolB_Response_API> {
    protected DefaultMyLeafProtocolBHandler(HandlerContext context) {
        super(new org.jprotocol.example.dsl.MyLeafProtocolB(), null, null, context);
    }
    @Override public final MyLeafProtocolB_Request_API createRequest(IProtocolMessage p) {
        return new MyLeafProtocolB_Request_API(p);
    }
    @Override public final MyLeafProtocolB_Response_API createResponse(IProtocolMessage p) {
        return new MyLeafProtocolB_Response_API(p);
    }
}
