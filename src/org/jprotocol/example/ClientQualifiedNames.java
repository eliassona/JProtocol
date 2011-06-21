package org.jprotocol.example;
import org.jprotocol.framework.handler.QualifiedName;
/**
* This class is generated by QualifiedNameAPIGenerator.groovy
* @author Anders Eliasson
*/
public class ClientQualifiedNames {
    public static final class MyMiddleProtocolA_Response_A {
        public static final QualifiedName NAME = new QualifiedName("//A");
        public static final class MyLeafProtocolA_Response_A {
            public static final QualifiedName NAME = new QualifiedName("//A//A");
        }
    }
    public static final class MyMiddleProtocolB_Response_B {
        public static final QualifiedName NAME = new QualifiedName("//B");
        public static final class MyLeafProtocolB_Response_B {
            public static final QualifiedName NAME = new QualifiedName("//B//B");
        }
    }
    public final static String A_Simple = "A";
    public final static String B_Simple = "B";
}