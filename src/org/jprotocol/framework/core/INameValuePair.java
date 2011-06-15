package org.jprotocol.framework.core;



public interface INameValuePair {
    String getName();
    
    int getValue();
    
    IArgumentType[] getArgTypes();
    IArgumentType argOf(String name);
    
    boolean hasPayload();
    
    int getPayloadStartIndex();
}
