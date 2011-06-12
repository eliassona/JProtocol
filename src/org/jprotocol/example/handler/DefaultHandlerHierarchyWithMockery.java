package org.jprotocol.example.handler;
import org.jprotocol.framework.handler.Handler.Type;
import org.jprotocol.framework.handler.*;
import org.jprotocol.framework.logger.IProtocolLogger;
import org.jprotocol.framework.logger.IProtocolLogger.NullProtocolLogger;
import org.jprotocol.framework.test.ProtocolMockery;
/**
* This class is generated by DefaultHandlerHierarchyWithMockeryGenerator.groovy
* @author eliasa01
*/
public class DefaultHandlerHierarchyWithMockery extends DefaultHandlerHierarchy {
    public final ProtocolMockery mockery;
    public DefaultHandlerHierarchyWithMockery(Type type, final IFlushable flushable, IProtocolLogger logger) {
        this(type, flushable, logger, new ProtocolSnifferProxy());
    }
    private DefaultHandlerHierarchyWithMockery(Type type, final IFlushable flushable, IProtocolLogger logger, ProtocolSnifferProxy sniffer) {
        super(type, flushable, new ProtocolState(), sniffer, logger);
        this.mockery = new ProtocolMockery(getRoot(), new NullProtocolLogger(), true);
        sniffer.init(mockery);
    }
}
