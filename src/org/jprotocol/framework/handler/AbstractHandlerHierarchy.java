package org.jprotocol.framework.handler;
import static org.jprotocol.framework.handler.HandlerDsl.root;
import static org.jprotocol.util.DBC.neverGetHere;

import java.io.IOException;

import org.jprotocol.framework.handler.Handler.Type;
import org.jprotocol.framework.handler.HandlerDsl.UpperHandler;
import org.jprotocol.framework.logger.IProtocolLogger;
/**
 * @author Anders Eliasson
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
		try {
			return (Class<? extends IHandlerHierarchy>) Class.forName(getClass().getPackage().getName() + "." + getClass().getSimpleName().replace(type.toString(), otherType().toString()));
		} catch (ClassNotFoundException e) {
			neverGetHere(e.getMessage());
			return null;
		}
	}
    
    private Type otherType() {
    	if (type == Type.Server) {
    		return Type.Client;
    	}
    	return Type.Server;
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
