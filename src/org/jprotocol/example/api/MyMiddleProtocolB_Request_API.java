package org.jprotocol.example.api;
import org.jprotocol.framework.api.*;
import org.jprotocol.framework.dsl.*;
import org.jprotocol.framework.test.*;
import java.util.Iterator;
import org.jprotocol.framework.test.ITestable;
/**
* MyMiddleProtocolB Request
* @note This class is generated by DefaultAPIGenerator.groovy
*/
@SuppressWarnings("all")
public class MyMiddleProtocolB_Request_API extends AbstractDecoratedProtocolMessage implements Request {
    public static final String NAME = "MyMiddleProtocolB Request";
    private final MyMiddleProtocolB_Request_API parent;
    public MyMiddleProtocolB_Request_API(IProtocolLayoutType type) {
        this(new ProtocolMessage(type, false));
    }
    public MyMiddleProtocolB_Request_API(IProtocolMessage protocol) {
        this(protocol, false);
    }
    private MyMiddleProtocolB_Request_API(IProtocolMessage protocol, boolean strBuilder) {
        super(protocol, strBuilder);
        this.parent = this;
    }
    static MyMiddleProtocolB_Request_API_Test createTest() {
        return new MyMiddleProtocolB_Request_API_Test(new StringBuilderProtocolMessage(new org.jprotocol.example.dsl.MyMiddleProtocolB().getRequestProtocol()));
    }
    static MyMiddleProtocolB_Request_API_Test createTest(StringBuilderProtocolMessage protocol) {
        return new MyMiddleProtocolB_Request_API_Test(protocol);
    }
    public static class MyMiddleProtocolB_Request_API_Test extends MyMiddleProtocolB_Request_API {
        MyMiddleProtocolB_Request_API_Test(StringBuilderProtocolMessage protocol) {
            super(protocol, true);
        }
    }
    /**
    * Argument: MiddleHeader
    */
    public MiddleHeader getMiddleHeader() {
        return new MiddleHeader(parent, protocol);
    }
    public static class MiddleHeader extends AbstractDecoratedArgument {
        public static final String MiddleHeader_ArgName = "MiddleHeader";
        private final MyMiddleProtocolB_Request_API parent;
        MiddleHeader(MyMiddleProtocolB_Request_API parent, IProtocolMessage protocol, int...indexes) {
            super(protocol, "MiddleHeader", indexes);
            this.parent = parent;
        }
        public int getBitValue() {
            return _getBitValue(indexes);
        }
        public MyMiddleProtocolB_Request_API setBitValue(int value) {
            _setBitValue(value, indexes);
            return parent;
        }
        public static interface MiddleHeader_Command extends APICommand<MyMiddleProtocolB_Request_API> {
        }
        public static final int X = 1;
        public static final String X_NAME = "X";
        /**
        */
        public boolean isX() {
            return _isValue("X", indexes);
        }
        /**
        */
        public MyMiddleProtocolB_Request_API setX() {
            _setValue("X", indexes);
            return parent;
        }
        public static MiddleHeader_Command getX_Command() {
            return new MiddleHeader_Command() { @Override public MyMiddleProtocolB_Request_API execute(MyMiddleProtocolB_Request_API target) { target.getProtocol().setValue("MiddleHeader", "X"); return target; }};
        }
        public static final int Z = 2;
        public static final String Z_NAME = "Z";
        /**
        */
        public boolean isZ() {
            return _isValue("Z", indexes);
        }
        /**
        */
        public MyMiddleProtocolB_Request_API setZ() {
            _setValue("Z", indexes);
            return parent;
        }
        public static MiddleHeader_Command getZ_Command() {
            return new MiddleHeader_Command() { @Override public MyMiddleProtocolB_Request_API execute(MyMiddleProtocolB_Request_API target) { target.getProtocol().setValue("MiddleHeader", "Z"); return target; }};
        }
    }
    /**
    * Argument: MiddleSwitch
    */
    public MiddleSwitch getMiddleSwitch() {
        return new MiddleSwitch(parent, protocol);
    }
    public static class MiddleSwitch extends AbstractDecoratedArgument {
        public static final String MiddleSwitch_ArgName = "MiddleSwitch";
        private final MyMiddleProtocolB_Request_API parent;
        MiddleSwitch(MyMiddleProtocolB_Request_API parent, IProtocolMessage protocol, int...indexes) {
            super(protocol, "MiddleSwitch", indexes);
            this.parent = parent;
        }
        public int getBitValue() {
            return _getBitValue(indexes);
        }
        public MyMiddleProtocolB_Request_API setBitValue(int value) {
            _setBitValue(value, indexes);
            return parent;
        }
        public static interface MiddleSwitch_Command extends APICommand<MyMiddleProtocolB_Request_API> {
        }
        public static final int A = 1;
        public static final String A_NAME = "A";
        /**
        */
        public boolean isA() {
            return _isValue("A", indexes);
        }
        /**
        */
        public MyMiddleProtocolB_Request_API setA() {
            _setValue("A", indexes);
            return parent;
        }
        public static MiddleSwitch_Command getA_Command() {
            return new MiddleSwitch_Command() { @Override public MyMiddleProtocolB_Request_API execute(MyMiddleProtocolB_Request_API target) { target.getProtocol().setValue("MiddleSwitch", "A"); return target; }};
        }
        public static final int B = 2;
        public static final String B_NAME = "B";
        /**
        */
        public boolean isB() {
            return _isValue("B", indexes);
        }
        /**
        */
        public MyMiddleProtocolB_Request_API setB() {
            _setValue("B", indexes);
            return parent;
        }
        public static MiddleSwitch_Command getB_Command() {
            return new MiddleSwitch_Command() { @Override public MyMiddleProtocolB_Request_API execute(MyMiddleProtocolB_Request_API target) { target.getProtocol().setValue("MiddleSwitch", "B"); return target; }};
        }
    }
    public <T extends APICommand<MyMiddleProtocolB_Request_API>> MyMiddleProtocolB_Request_API execute(T...commands) {
        for (APICommand<MyMiddleProtocolB_Request_API> c: commands) {
            c.execute(this);
        }
        return this;
    }
}
