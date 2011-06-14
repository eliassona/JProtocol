package org.jprotocol.example.api;
import org.jprotocol.framework.api.*;
import org.jprotocol.framework.dsl.*;
import org.jprotocol.framework.test.*;
import java.util.Iterator;
import org.jprotocol.framework.test.ITestable;
/**
* MyMiddleProtocolA Response
* @note This class is generated by ProtocolCodeGenerator.groovy
*/
@SuppressWarnings("all")
public class MyMiddleProtocolA_Response_API extends AbstractDecoratedProtocolMessage implements Response {
    public static final String NAME = "MyMiddleProtocolA Response";
    private final MyMiddleProtocolA_Response_API parent;
    public MyMiddleProtocolA_Response_API(IProtocolLayoutType type) {
        this(new ProtocolMessage(type, false));
    }
    public MyMiddleProtocolA_Response_API(IProtocolMessage protocol) {
        this(protocol, false);
    }
    private MyMiddleProtocolA_Response_API(IProtocolMessage protocol, boolean strBuilder) {
        super(protocol, strBuilder);
        this.parent = this;
    }
    static MyMiddleProtocolA_Response_API_Test createTest() {
        return new MyMiddleProtocolA_Response_API_Test(new StringBuilderProtocolMessage(new org.jprotocol.example.dsl.MyMiddleProtocolA().getRequestProtocol()));
    }
    static MyMiddleProtocolA_Response_API_Test createTest(StringBuilderProtocolMessage protocol) {
        return new MyMiddleProtocolA_Response_API_Test(protocol);
    }
    public static class MyMiddleProtocolA_Response_API_Test extends MyMiddleProtocolA_Response_API {
        MyMiddleProtocolA_Response_API_Test(StringBuilderProtocolMessage protocol) {
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
        private final MyMiddleProtocolA_Response_API parent;
        MiddleHeader(MyMiddleProtocolA_Response_API parent, IProtocolMessage protocol, int...indexes) {
            super(protocol, "MiddleHeader", indexes);
            this.parent = parent;
        }
        public int getBitValue() {
            return _getBitValue(indexes);
        }
        public MyMiddleProtocolA_Response_API setBitValue(int value) {
            _setBitValue(value, indexes);
            return parent;
        }
        public static interface MiddleHeader_Command extends APICommand<MyMiddleProtocolA_Response_API> {
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
        public MyMiddleProtocolA_Response_API setX() {
            _setValue("X", indexes);
            return parent;
        }
        public static MiddleHeader_Command getX_Command() {
            return new MiddleHeader_Command() { @Override public MyMiddleProtocolA_Response_API execute(MyMiddleProtocolA_Response_API target) { target.getProtocol().setValue("MiddleHeader", "X"); return target; }};
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
        public MyMiddleProtocolA_Response_API setZ() {
            _setValue("Z", indexes);
            return parent;
        }
        public static MiddleHeader_Command getZ_Command() {
            return new MiddleHeader_Command() { @Override public MyMiddleProtocolA_Response_API execute(MyMiddleProtocolA_Response_API target) { target.getProtocol().setValue("MiddleHeader", "Z"); return target; }};
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
        private final MyMiddleProtocolA_Response_API parent;
        MiddleSwitch(MyMiddleProtocolA_Response_API parent, IProtocolMessage protocol, int...indexes) {
            super(protocol, "MiddleSwitch", indexes);
            this.parent = parent;
        }
        public int getBitValue() {
            return _getBitValue(indexes);
        }
        public MyMiddleProtocolA_Response_API setBitValue(int value) {
            _setBitValue(value, indexes);
            return parent;
        }
        public static interface MiddleSwitch_Command extends APICommand<MyMiddleProtocolA_Response_API> {
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
        public MyMiddleProtocolA_Response_API setA() {
            _setValue("A", indexes);
            return parent;
        }
        public static MiddleSwitch_Command getA_Command() {
            return new MiddleSwitch_Command() { @Override public MyMiddleProtocolA_Response_API execute(MyMiddleProtocolA_Response_API target) { target.getProtocol().setValue("MiddleSwitch", "A"); return target; }};
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
        public MyMiddleProtocolA_Response_API setB() {
            _setValue("B", indexes);
            return parent;
        }
        public static MiddleSwitch_Command getB_Command() {
            return new MiddleSwitch_Command() { @Override public MyMiddleProtocolA_Response_API execute(MyMiddleProtocolA_Response_API target) { target.getProtocol().setValue("MiddleSwitch", "B"); return target; }};
        }
    }
    public <T extends APICommand<MyMiddleProtocolA_Response_API>> MyMiddleProtocolA_Response_API execute(T...commands) {
        for (APICommand<MyMiddleProtocolA_Response_API> c: commands) {
            c.execute(this);
        }
        return this;
    }
}
