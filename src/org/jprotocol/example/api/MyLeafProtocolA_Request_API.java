package org.jprotocol.example.api;
import org.jprotocol.framework.api.*;
import org.jprotocol.framework.dsl.*;
import org.jprotocol.framework.test.*;
import org.jprotocol.quantity.Quantity;
import java.util.Iterator;
import org.jprotocol.framework.test.ITestable;
/**
* MyLeafProtocolA Request
* @note This class is generated by DefaultAPIGenerator.groovy
*/
@SuppressWarnings("all")
public class MyLeafProtocolA_Request_API extends AbstractDecoratedProtocolMessage implements Request {
    public static final String NAME = "MyLeafProtocolA Request";
    private final MyLeafProtocolA_Request_API parent;
    public MyLeafProtocolA_Request_API(IProtocolLayoutType type) {
        this(new ProtocolMessage(type, false));
    }
    public MyLeafProtocolA_Request_API(IProtocolMessage protocol) {
        this(protocol, false);
    }
    private MyLeafProtocolA_Request_API(IProtocolMessage protocol, boolean strBuilder) {
        super(protocol, strBuilder);
        this.parent = this;
    }
    static MyLeafProtocolA_Request_API_Test createTest() {
        return new MyLeafProtocolA_Request_API_Test(new StringBuilderProtocolMessage(new org.jprotocol.example.dsl.MyLeafProtocolA().getRequestProtocol()));
    }
    static MyLeafProtocolA_Request_API_Test createTest(StringBuilderProtocolMessage protocol) {
        return new MyLeafProtocolA_Request_API_Test(protocol);
    }
    public static class MyLeafProtocolA_Request_API_Test extends MyLeafProtocolA_Request_API {
        MyLeafProtocolA_Request_API_Test(StringBuilderProtocolMessage protocol) {
            super(protocol, true);
        }
    }
    /**
    * Argument: Leaf
    */
    public Leaf getLeaf() {
        return new Leaf(parent, protocol);
    }
    public static class Leaf extends AbstractDecoratedArgument {
        public static final String Leaf_ArgName = "Leaf";
        private final MyLeafProtocolA_Request_API parent;
        Leaf(MyLeafProtocolA_Request_API parent, IProtocolMessage protocol, int...indexes) {
            super(protocol, "Leaf", indexes);
            this.parent = parent;
        }
        public int getBitValue() {
            return _getBitValue(indexes);
        }
        public MyLeafProtocolA_Request_API setBitValue(int value) {
            _setBitValue(value, indexes);
            return parent;
        }
        public int getValue() {
            return _getValue(indexes);
        }
        public MyLeafProtocolA_Request_API setValue(int value) {
            _setValue(value + "", indexes);
            return parent;
        }
        public Quantity getRealQuantity() {
            return _getRealQuantity(indexes);
        }
        public MyLeafProtocolA_Request_API setRealQuantity(Quantity value) {
            _setRealQuantity(value, indexes);
            return parent;
        }
        public double getRealValue() {
            return _getRealValue(indexes);
        }
        public MyLeafProtocolA_Request_API setRealValue(double value) {
            _setRealValue(value, indexes);
            return parent;
        }
    }
    public <T extends APICommand<MyLeafProtocolA_Request_API>> MyLeafProtocolA_Request_API execute(T...commands) {
        for (APICommand<MyLeafProtocolA_Request_API> c: commands) {
            c.execute(this);
        }
        return this;
    }
}
