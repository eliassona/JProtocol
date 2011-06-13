package org.jprotocol.framework.handler;
import static org.jprotocol.framework.handler.HandlerDsl.root;
import static org.jprotocol.util.Contract.neverGetHere;

import java.io.IOException;

import org.jprotocol.framework.handler.Handler.Type;
import org.jprotocol.framework.handler.HandlerDsl.UpperHandler;
import org.jprotocol.framework.logger.IProtocolLogger;
import org.jprotocol.util.Contract;
/**
* This class is generated by DefaultHierarchyGenerator.groovy
* @author eliasa01
*/
abstract public class AbstractHandlerHierarchy implements IHandlerHierarchy {
    protected final Type type;
    protected final boolean msbFirst;
    protected final IProtocolState protocolState;
    protected final IProtocolSniffer sniffer;
    private final Handler<?, ?> root;
	protected final IProtocolLogger logger;
	protected final IFlushable flushable;
    public AbstractHandlerHierarchy(Type type, final IFlushable flushable, IProtocolState protocolState, IProtocolSniffer sniffer, IProtocolLogger logger) {
        this.type = type;
        this.msbFirst = false;
        this.sniffer = sniffer;
        this.flushable = flushable;
        this.logger = logger;
        this.protocolState = protocolState;
        this.root = createRoot();
        try {
			logger.write(this.getClass(), other());
		} catch (IOException e) {
			neverGetHere(e.getMessage());
		}
    }
    @SuppressWarnings("unchecked")
	private Class<? extends IHandlerHierarchy> other() {
    	Type otherType = Type.Server;
    	if (getClass().getSimpleName().startsWith(Type.Server.toString())) {
    		otherType = Type.Client;
    	}
		try {
			return (Class<? extends IHandlerHierarchy>) Class.forName(getClass().getPackage().getName() + "." + otherType + "HandlerHierarchy");
		} catch (ClassNotFoundException e) {
			neverGetHere(e.getMessage());
			return null;
		}
	}
	public void init() {
        root(getRoot(), upperHandlers());
    }
    @Override
    public final Handler<?, ?> getRoot() {
        return root;
    }
    public void receive(byte[] data) {
        root.receive(data);
    }
    abstract protected Handler<?, ?> createRoot();
	
    protected final HandlerContext getRootContext() {
		return new RootHandlerContext(this);
	}
    abstract protected UpperHandler[] upperHandlers();
	protected final UpperHandler[] upperHandlers(UpperHandler...handlers) {
		return handlers;
	}
	public boolean isMsbFirst() {
		return msbFirst;
	}
	public IProtocolSniffer getSniffer() {
		return sniffer;
	}
	public IProtocolLogger getLogger() {
		return logger;
	}
	public IProtocolState getProtocolState() {
		return protocolState;
	}
	public Type getType() {
		return type;
	}
}
