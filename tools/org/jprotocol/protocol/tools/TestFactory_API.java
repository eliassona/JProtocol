package org.jprotocol.protocol.tools;
import java.util.Iterator;

import org.jprotocol.framework.api.AbstractIterator;
import org.jprotocol.framework.dsl.AbstractDecoratedArgument;
import org.jprotocol.framework.dsl.AbstractDecoratedIndexedArgument;
import org.jprotocol.framework.dsl.AbstractDecoratedProtocolMessage;
import org.jprotocol.framework.dsl.IProtocolMessage;
import org.jprotocol.framework.dsl.IProtocolLayoutType;
import org.jprotocol.framework.dsl.ProtocolMessage;
import org.jprotocol.framework.dsl.StringBuilderProtocolMessage;
import org.jprotocol.framework.test.ITestable;

import org.jprotocol.quantity.Quantity;
/**
* TestFactory
* @note This class is generated by TestAPIGenerator.groovy
*/
@SuppressWarnings("all")
public class TestFactory_API extends AbstractDecoratedProtocolMessage implements ITestable {
    public static final String NAME = "TestFactory";
    private final TestFactory_API parent;
    private final boolean strBuilder;
    public TestFactory_API(IProtocolLayoutType type) {
        this(new ProtocolMessage(type, false));
    }
    public TestFactory_API(IProtocolMessage protocol) {
        this(protocol, false);
    }
    private TestFactory_API(IProtocolMessage protocol, boolean strBuilder) {
        super(protocol);
        this.parent = this;
        this.strBuilder = strBuilder;
    }
    static TestFactory_API_Test createTest(StringBuilderProtocolMessage protocol) {
        return new TestFactory_API_Test(protocol);
    }
    public static final class TestFactory_API_Test extends TestFactory_API {
        TestFactory_API_Test(StringBuilderProtocolMessage protocol) {
            super(protocol, true);
        }
    }
    /**
    * Argument: Switch
    */
    public Switch getSwitch() {
        return new Switch(parent, protocol);
    }
    public static class Switch extends AbstractDecoratedArgument {
        public static final String Switch_ArgName = "Switch";
        private final TestFactory_API parent;
        private final int[] indexes;
        Switch(TestFactory_API parent, IProtocolMessage protocol, int...indexes) {
            super(protocol, "Switch");
            this.parent = parent;
            this.indexes = indexes;
        }
        public int getBitValue() {
            return _getBitValue(indexes);
        }
        public TestFactory_API setBitValue(int value) {
            _setBitValue(value, indexes);
            return parent;
        }
        public static final int Off = 0;
        public static final String Off_NAME = "Off";
        /**
        */
        public boolean isOff() {
            return _isValue("Off", indexes);
        }
        /**
        */
        public TestFactory_API setOff() {
            _setValue("Off", indexes);
            return parent;
        }
        public static final int On = 1;
        public static final String On_NAME = "On";
        /**
        */
        public boolean isOn() {
            return _isValue("On", indexes);
        }
        /**
        */
        public TestFactory_API setOn() {
            _setValue("On", indexes);
            return parent;
        }
        /**
        * Argument: Count
        */
        public Count getCount() {
            return new Count(parent, protocol);
        }
        public static class Count extends AbstractDecoratedArgument {
            public static final String Count_ArgName = "Count";
            private final TestFactory_API parent;
            private final int[] indexes;
            Count(TestFactory_API parent, IProtocolMessage protocol, int...indexes) {
                super(protocol, "Count");
                this.parent = parent;
                this.indexes = indexes;
            }
            public int getBitValue() {
                return _getBitValue(indexes);
            }
            public TestFactory_API setBitValue(int value) {
                _setBitValue(value, indexes);
                return parent;
            }
            public int getValue() {
                return _getValue(indexes);
            }
            public TestFactory_API setValue(int value) {
                _setValue(value + "", indexes);
                return parent;
            }
            public Quantity getRealQuantity() {
                return _getRealQuantity(indexes);
            }
            public TestFactory_API setRealQuantity(Quantity value) {
                _setRealQuantity(value, indexes);
                return parent;
            }
            public double getRealValue() {
                return _getRealValue(indexes);
            }
            public TestFactory_API setRealValue(double value) {
                _setRealValue(value, indexes);
                return parent;
            }
            @Override
            public String toString() {
                return _getValueAsStr();
            }
        }
        /**
        * Argument: Str
        */
        public Str getStr() {
            return new Str(parent, protocol);
        }
        public static class Str extends AbstractDecoratedArgument {
            public static final String Str_ArgName = "Str";
            private final TestFactory_API parent;
            private final int[] indexes;
            Str(TestFactory_API parent, IProtocolMessage protocol, int...indexes) {
                super(protocol, "Str");
                this.parent = parent;
                this.indexes = indexes;
            }
            public String getValue() {
                return _getValueAsStr(indexes);
            }
            public TestFactory_API setValue(String value) {
                _setValue(value, indexes);
                return parent;
            }
            @Override
            public String toString() {
                return _getValueAsStr();
            }
        }
        @Override
        public String toString() {
            return _getValueAsStr();
        }
    }
    /**
    * Indexed argument: Indexed
    */
    public Indexed_Iterable getIndexed() {
        return new Indexed_Iterable(parent, protocol);
    }
    public Indexed getIndexed(int index) {
        return new Indexed(parent, protocol, index);
    }
    public int getNrOfIndexed() {
        return getIndexed(0).getNrOfIndexed();
    }
    public static class Indexed_Iterable implements Iterable<Indexed> {
        private final TestFactory_API parent;
        private final IProtocolMessage protocol;
        private final int[] indexes;
        Indexed_Iterable(TestFactory_API parent, IProtocolMessage protocol, int...indexes) {
            this.parent = parent;
            this.protocol = protocol;
            this.indexes = indexes;
        }
        @Override public Iterator<Indexed> iterator() {
            return new Indexed_Iterator();
        }
        class Indexed_Iterator extends AbstractIterator<Indexed> {
            private Indexed obj;
            private int _index;
            private final int lastIndex;
            Indexed_Iterator() {
                this.obj = new Indexed(parent, protocol, indexArrayOf(indexes, _index));
                this.lastIndex = obj.getNrOfIndexed() - 1;
            }
            @Override public boolean hasNext() {
                return _index <= lastIndex;
            }
            @Override public Indexed next() {
                assert hasNext();
                Indexed result = obj;
                _index++;
                obj = new Indexed(parent, protocol, indexArrayOf(indexes, _index));
                return result;
            }
        }
    }
    public static class Indexed extends AbstractDecoratedIndexedArgument {
        private final TestFactory_API parent;
        private final int[] indexes;
        public Indexed(TestFactory_API parent, IProtocolMessage protocol, int...indexes) {
            super(protocol);
            this.indexes = indexes;
            this.parent = parent;
        }
        int getNrOfIndexed() {
            return nrOf("Indexed");
        }
        /**
        * Argument: iArg0
        */
        public iArg0 getiArg0() {
            return new iArg0(parent, protocol, indexes);
        }
        public static class iArg0 extends AbstractDecoratedArgument {
            public static final String iArg0_ArgName = "iArg0";
            private final TestFactory_API parent;
            private final int[] indexes;
            iArg0(TestFactory_API parent, IProtocolMessage protocol, int...indexes) {
                super(protocol, "iArg0");
                this.parent = parent;
                this.indexes = indexes;
            }
            public int getBitValue() {
                return _getBitValue(indexes);
            }
            public TestFactory_API setBitValue(int value) {
                _setBitValue(value, indexes);
                return parent;
            }
            public int getValue() {
                return _getValue(indexes);
            }
            public TestFactory_API setValue(int value) {
                _setValue(value + "", indexes);
                return parent;
            }
            public Quantity getRealQuantity() {
                return _getRealQuantity(indexes);
            }
            public TestFactory_API setRealQuantity(Quantity value) {
                _setRealQuantity(value, indexes);
                return parent;
            }
            public double getRealValue() {
                return _getRealValue(indexes);
            }
            public TestFactory_API setRealValue(double value) {
                _setRealValue(value, indexes);
                return parent;
            }
            @Override
            public String toString() {
                return _getValueAsStr();
            }
        }
        /**
        * Argument: iArg1
        */
        public iArg1 getiArg1() {
            return new iArg1(parent, protocol, indexes);
        }
        public static class iArg1 extends AbstractDecoratedArgument {
            public static final String iArg1_ArgName = "iArg1";
            private final TestFactory_API parent;
            private final int[] indexes;
            iArg1(TestFactory_API parent, IProtocolMessage protocol, int...indexes) {
                super(protocol, "iArg1");
                this.parent = parent;
                this.indexes = indexes;
            }
            public int getBitValue() {
                return _getBitValue(indexes);
            }
            public TestFactory_API setBitValue(int value) {
                _setBitValue(value, indexes);
                return parent;
            }
            public static final int Good = 0;
            public static final String Good_NAME = "Good";
            /**
            */
            public boolean isGood() {
                return _isValue("Good", indexes);
            }
            /**
            */
            public TestFactory_API setGood() {
                _setValue("Good", indexes);
                return parent;
            }
            public static final int Bad = 1;
            public static final String Bad_NAME = "Bad";
            /**
            */
            public boolean isBad() {
                return _isValue("Bad", indexes);
            }
            /**
            */
            public TestFactory_API setBad() {
                _setValue("Bad", indexes);
                return parent;
            }
            @Override
            public String toString() {
                return _getValueAsStr();
            }
        }
        /**
        * Indexed argument: Indexed2
        */
        public Indexed2_Iterable getIndexed2() {
            return new Indexed2_Iterable(parent, protocol, indexes);
        }
        public Indexed2 getIndexed2(int index) {
            return new Indexed2(parent, protocol, AbstractIterator.indexArrayOf(indexes, index));
        }
        public int getNrOfIndexed2() {
            return getIndexed2(0).getNrOfIndexed2();
        }
        public static class Indexed2_Iterable implements Iterable<Indexed2> {
            private final TestFactory_API parent;
            private final IProtocolMessage protocol;
            private final int[] indexes;
            Indexed2_Iterable(TestFactory_API parent, IProtocolMessage protocol, int...indexes) {
                this.parent = parent;
                this.protocol = protocol;
                this.indexes = indexes;
            }
            @Override public Iterator<Indexed2> iterator() {
                return new Indexed2_Iterator();
            }
            class Indexed2_Iterator extends AbstractIterator<Indexed2> {
                private Indexed2 obj;
                private int _index;
                private final int lastIndex;
                Indexed2_Iterator() {
                    this.obj = new Indexed2(parent, protocol, indexArrayOf(indexes, _index));
                    this.lastIndex = obj.getNrOfIndexed2() - 1;
                }
                @Override public boolean hasNext() {
                    return _index <= lastIndex;
                }
                @Override public Indexed2 next() {
                    assert hasNext();
                    Indexed2 result = obj;
                    _index++;
                    obj = new Indexed2(parent, protocol, indexArrayOf(indexes, _index));
                    return result;
                }
            }
        }
        public static class Indexed2 extends AbstractDecoratedIndexedArgument {
            private final TestFactory_API parent;
            private final int[] indexes;
            public Indexed2(TestFactory_API parent, IProtocolMessage protocol, int...indexes) {
                super(protocol);
                this.indexes = indexes;
                this.parent = parent;
            }
            int getNrOfIndexed2() {
                return nrOf("Indexed2");
            }
            /**
            * Argument: iArg3
            */
            public iArg3 getiArg3() {
                return new iArg3(parent, protocol, indexes);
            }
            public static class iArg3 extends AbstractDecoratedArgument {
                public static final String iArg3_ArgName = "iArg3";
                private final TestFactory_API parent;
                private final int[] indexes;
                iArg3(TestFactory_API parent, IProtocolMessage protocol, int...indexes) {
                    super(protocol, "iArg3");
                    this.parent = parent;
                    this.indexes = indexes;
                }
                public int getBitValue() {
                    return _getBitValue(indexes);
                }
                public TestFactory_API setBitValue(int value) {
                    _setBitValue(value, indexes);
                    return parent;
                }
                public int getValue() {
                    return _getValue(indexes);
                }
                public TestFactory_API setValue(int value) {
                    _setValue(value + "", indexes);
                    return parent;
                }
                public Quantity getRealQuantity() {
                    return _getRealQuantity(indexes);
                }
                public TestFactory_API setRealQuantity(Quantity value) {
                    _setRealQuantity(value, indexes);
                    return parent;
                }
                public double getRealValue() {
                    return _getRealValue(indexes);
                }
                public TestFactory_API setRealValue(double value) {
                    _setRealValue(value, indexes);
                    return parent;
                }
                @Override
                public String toString() {
                    return _getValueAsStr();
                }
            }
        }
    }
    /**
    * Argument: Real
    */
    public Real getReal() {
        return new Real(parent, protocol);
    }
    public static class Real extends AbstractDecoratedArgument {
        public static final String Real_ArgName = "Real";
        private final TestFactory_API parent;
        private final int[] indexes;
        Real(TestFactory_API parent, IProtocolMessage protocol, int...indexes) {
            super(protocol, "Real");
            this.parent = parent;
            this.indexes = indexes;
        }
        public int getBitValue() {
            return _getBitValue(indexes);
        }
        public TestFactory_API setBitValue(int value) {
            _setBitValue(value, indexes);
            return parent;
        }
        public int getValue() {
            return _getValue(indexes);
        }
        public TestFactory_API setValue(int value) {
            _setValue(value + "", indexes);
            return parent;
        }
        public Quantity getRealQuantity() {
            return _getRealQuantity(indexes);
        }
        public TestFactory_API setRealQuantity(Quantity value) {
            _setRealQuantity(value, indexes);
            return parent;
        }
        public double getRealValue() {
            return _getRealValue(indexes);
        }
        public TestFactory_API setRealValue(double value) {
            _setRealValue(value, indexes);
            return parent;
        }
        @Override
        public String toString() {
            return _getValueAsStr();
        }
    }
    /**
    * Argument: vArg
    */
    public vArg getvArg() {
        return new vArg(parent, protocol);
    }
    public static class vArg extends AbstractDecoratedArgument {
        public static final String vArg_ArgName = "vArg";
        private final TestFactory_API parent;
        private final int[] indexes;
        vArg(TestFactory_API parent, IProtocolMessage protocol, int...indexes) {
            super(protocol, "vArg");
            this.parent = parent;
            this.indexes = indexes;
        }
        public int getBitValue() {
            return _getBitValue(indexes);
        }
        public TestFactory_API setBitValue(int value) {
            _setBitValue(value, indexes);
            return parent;
        }
        public static final int vValue1 = 0;
        public static final String vValue1_NAME = "vValue1";
        /**
        */
        public boolean isvValue1() {
            return _isValue("vValue1", indexes);
        }
        /**
        */
        public TestFactory_API setvValue1() {
            _setValue("vValue1", indexes);
            return parent;
        }
        public static final int vValue2 = 1;
        public static final String vValue2_NAME = "vValue2";
        /**
        */
        public boolean isvValue2() {
            return _isValue("vValue2", indexes);
        }
        /**
        */
        public TestFactory_API setvValue2() {
            _setValue("vValue2", indexes);
            return parent;
        }
        @Override
        public String toString() {
            return _getValueAsStr();
        }
    }
    @Override public String toString() {
        if (strBuilder) {
            return getProtocol().toString();
        }
        return super.toString();
    }
}
