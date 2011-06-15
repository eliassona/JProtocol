package org.jprotocol.example.api;
import org.jprotocol.framework.api.*;
import org.jprotocol.framework.core.*;
import org.jprotocol.framework.test.*;
import org.jprotocol.quantity.Quantity;
import java.util.Iterator;
import org.jprotocol.framework.test.ITestable;
/**
* MyLeafProtocolA Request
* @note This class is generated by ProtocolCodeGenerator.groovy
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
        return new MyLeafProtocolA_Request_API_Test(new StringBuilderProtocolMessage(new org.jprotocol.example.protocols.MyLeafProtocolA().getRequestProtocol()));
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
    /**
    * Indexed argument: Array
    */
    public Array_Iterable getArray() {
        return new Array_Iterable(parent, protocol);
    }
    public Array getArray(int index) {
        return new Array(parent, protocol, index);
    }
    public int getNrOfArray() {
        return getArray(0).getNrOfArray();
    }
    public static class Array_Iterable implements Iterable<Array> {
        private final MyLeafProtocolA_Request_API parent;
        private final IProtocolMessage protocol;
        private final int[] indexes;
        Array_Iterable(MyLeafProtocolA_Request_API parent, IProtocolMessage protocol, int...indexes) {
            this.parent = parent;
            this.protocol = protocol;
            this.indexes = indexes;
        }
        @Override public Iterator<Array> iterator() {
            return new Array_Iterator();
        }
        class Array_Iterator extends AbstractIterator<Array> {
            private Array obj;
            private int _index;
            private final int lastIndex;
            Array_Iterator() {
                this.obj = new Array(parent, protocol, indexArrayOf(indexes, _index));
                this.lastIndex = obj.getNrOfArray() - 1;
            }
            @Override public boolean hasNext() {
                return _index <= lastIndex;
            }
            @Override public Array next() {
                assert hasNext();
                Array result = obj;
                _index++;
                obj = new Array(parent, protocol, indexArrayOf(indexes, _index));
                return result;
            }
        }
    }
    public static class Array extends AbstractDecoratedIndexedArgument {
        private final MyLeafProtocolA_Request_API parent;
        private final int[] indexes;
        public Array(MyLeafProtocolA_Request_API parent, IProtocolMessage protocol, int...indexes) {
            super(protocol);
            this.indexes = indexes;
            this.parent = parent;
        }
        int getNrOfArray() {
            return nrOf("Array");
        }
        /**
        * Argument: ArrayEnum
        */
        public ArrayEnum getArrayEnum() {
            return new ArrayEnum(parent, protocol, indexes);
        }
        public static class ArrayEnum extends AbstractDecoratedArgument {
            public static final String ArrayEnum_ArgName = "ArrayEnum";
            private final MyLeafProtocolA_Request_API parent;
            ArrayEnum(MyLeafProtocolA_Request_API parent, IProtocolMessage protocol, int...indexes) {
                super(protocol, "ArrayEnum", indexes);
                this.parent = parent;
            }
            public int getBitValue() {
                return _getBitValue(indexes);
            }
            public MyLeafProtocolA_Request_API setBitValue(int value) {
                _setBitValue(value, indexes);
                return parent;
            }
            public static interface ArrayEnum_Command extends APICommand<MyLeafProtocolA_Request_API> {
            }
            public static final int Off = 1;
            public static final String Off_NAME = "Off";
            /**
            */
            public boolean isOff() {
                return _isValue("Off", indexes);
            }
            /**
            */
            public MyLeafProtocolA_Request_API setOff() {
                _setValue("Off", indexes);
                return parent;
            }
            public static ArrayEnum_Command getOff_Command() {
                return new ArrayEnum_Command() { @Override public MyLeafProtocolA_Request_API execute(MyLeafProtocolA_Request_API target) { target.getProtocol().setValue("ArrayEnum", "Off"); return target; }};
            }
            public static final int On = 2;
            public static final String On_NAME = "On";
            /**
            */
            public boolean isOn() {
                return _isValue("On", indexes);
            }
            /**
            */
            public MyLeafProtocolA_Request_API setOn() {
                _setValue("On", indexes);
                return parent;
            }
            public static ArrayEnum_Command getOn_Command() {
                return new ArrayEnum_Command() { @Override public MyLeafProtocolA_Request_API execute(MyLeafProtocolA_Request_API target) { target.getProtocol().setValue("ArrayEnum", "On"); return target; }};
            }
        }
        /**
        * Argument: ArrayInt
        */
        public ArrayInt getArrayInt() {
            return new ArrayInt(parent, protocol, indexes);
        }
        public static class ArrayInt extends AbstractDecoratedArgument {
            public static final String ArrayInt_ArgName = "ArrayInt";
            private final MyLeafProtocolA_Request_API parent;
            ArrayInt(MyLeafProtocolA_Request_API parent, IProtocolMessage protocol, int...indexes) {
                super(protocol, "ArrayInt", indexes);
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
    }
    public <T extends APICommand<MyLeafProtocolA_Request_API>> MyLeafProtocolA_Request_API execute(T...commands) {
        for (APICommand<MyLeafProtocolA_Request_API> c: commands) {
            c.execute(this);
        }
        return this;
    }
}
