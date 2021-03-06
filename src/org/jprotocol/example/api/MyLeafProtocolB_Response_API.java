package org.jprotocol.example.api;
import org.jprotocol.framework.api.*;
import org.jprotocol.framework.core.*;
import org.jprotocol.framework.test.*;
import java.util.Iterator;
import org.jprotocol.framework.test.ITestable;
/**
* MyLeafProtocolB Response
* @note This class is generated by ProtocolCodeGenerator.groovy
*/
@SuppressWarnings("all")
public class MyLeafProtocolB_Response_API extends AbstractDecoratedProtocolMessage implements Response {
    public static final String NAME = "MyLeafProtocolB Response";
    private final MyLeafProtocolB_Response_API parent;
    public MyLeafProtocolB_Response_API(IProtocolLayoutType type) {
        this(new ProtocolMessage(type, false));
    }
    public MyLeafProtocolB_Response_API(IProtocolMessage protocol) {
        this(protocol, false);
    }
    private MyLeafProtocolB_Response_API(IProtocolMessage protocol, boolean strBuilder) {
        super(protocol, strBuilder);
        this.parent = this;
    }
    static MyLeafProtocolB_Response_API_Test createTest() {
        return new MyLeafProtocolB_Response_API_Test(new StringBuilderProtocolMessage(new org.jprotocol.example.protocols.MyLeafProtocolB().getResponseProtocol()));
    }
    static MyLeafProtocolB_Response_API_Test createTest(StringBuilderProtocolMessage protocol) {
        return new MyLeafProtocolB_Response_API_Test(protocol);
    }
    public static class MyLeafProtocolB_Response_API_Test extends MyLeafProtocolB_Response_API {
        MyLeafProtocolB_Response_API_Test(StringBuilderProtocolMessage protocol) {
            super(protocol, true);
        }
    }
    public <T extends APICommand<MyLeafProtocolB_Response_API>> MyLeafProtocolB_Response_API execute(T...commands) {
        for (APICommand<MyLeafProtocolB_Response_API> c: commands) {
            c.execute(this);
        }
        return this;
    }
}
