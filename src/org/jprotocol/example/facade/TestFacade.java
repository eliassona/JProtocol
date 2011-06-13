package org.jprotocol.example.facade;
import org.jprotocol.framework.facade.AbstractFacade;
import org.jprotocol.framework.handler.IFlushable;
import org.jprotocol.framework.logger.IProtocolLogger.NullProtocolLogger;
/**
* This class is generated by DefaultTestFacadeGenerator.groovy
* @author Anders Eliasson
*/
public class TestFacade {
    public final ClientFacade client;
    public final ServerFacade server;
    public TestFacade() {
        FlushableClientServer cf = new FlushableClientServer();
        FlushableClientServer sf = new FlushableClientServer();
        client = createClientFacade(cf);
        server = createServerFacade(sf);
        cf.setTarget(server);
        sf.setTarget(client);
    }
    /**
    * Override to provide a specialized version of ClientFacade
    */
    protected ClientFacade createClientFacade(IFlushable flushable) {
        return new ClientFacade(flushable, new NullProtocolLogger());
    }
    /**
    * Override to provide a specialized version of ServerFacade
    */
    protected ServerFacade createServerFacade(IFlushable flushable) {
        return new ServerFacade(flushable);
    }
}
class FlushableClientServer implements IFlushable {
    private AbstractFacade server;
    @Override public void flush(byte[] data) {
        server.receive(data);
    }
    public void setTarget(AbstractFacade server) {
        this.server = server;
    }
}
