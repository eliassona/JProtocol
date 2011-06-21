package org.jprotocol.plog;

import java.util.List;


public class LoggedProtocols {
    public final List<HandlerProtocolTuple> protocols;
    public final  long timestamp;
    public final  long absoluteTimestamp;

    public LoggedProtocols(List<HandlerProtocolTuple> protocols, long timestamp, long absoluteTimestamp) {
        this.protocols = protocols;
        this.timestamp = timestamp;
        this.absoluteTimestamp = absoluteTimestamp;
    }
}
