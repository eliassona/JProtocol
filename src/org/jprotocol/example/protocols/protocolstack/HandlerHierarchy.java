package org.jprotocol.example.protocols.protocolstack;
import static org.jprotocol.framework.handler.HandlerDsl.handler;

import org.jprotocol.framework.handler.Handler.Type;
import org.jprotocol.framework.handler.HandlerDsl.UpperHandler;
import org.jprotocol.framework.handler.*;
import org.jprotocol.framework.logger.IProtocolLogger;
import org.jprotocol.example.api.MyMiddleProtocolA_Request_API;
import org.jprotocol.example.api.MyMiddleProtocolA_Response_API;
import org.jprotocol.example.api.MyMiddleProtocolB_Request_API;
import org.jprotocol.example.api.MyMiddleProtocolB_Response_API;
import org.jprotocol.example.api.MyRootProtocol_Request_API;
import org.jprotocol.example.api.MyRootProtocol_Response_API;
import org.jprotocol.example.handler.AbstractDefaultHandlerHierarchy;
abstract public class HandlerHierarchy extends AbstractDefaultHandlerHierarchy {
    public HandlerHierarchy(Type type, final IFlushable flushable, IProtocolState protocolState, IProtocolSniffer sniffer, IProtocolLogger logger) {
        super(type, flushable, protocolState, sniffer, logger);
    }
    @Override protected UpperHandler[] upperHandlers() {
		return upperHandlers( 
			handler(createMyMiddleProtocolAHandler(new HandlerContext(this, MyRootProtocol_Request_API.RootSwitch.A, MyRootProtocol_Response_API.RootSwitchResp.A)), 
			  handler(createMyLeafProtocolAHandler(new LeafHandlerContext(this, MyMiddleProtocolA_Request_API.MiddleSwitch.A, MyMiddleProtocolA_Response_API.MiddleSwitch.A))),
			  handler(createMyLeafProtocolBHandler(new LeafHandlerContext(this, MyMiddleProtocolB_Request_API.MiddleSwitch.B, MyMiddleProtocolB_Response_API.MiddleSwitch.B)))
			),
			handler(createMyMiddleProtocolBHandler(new HandlerContext(this, MyRootProtocol_Request_API.RootSwitch.B, MyRootProtocol_Response_API.RootSwitchResp.B)), 
			  handler(createMyLeafProtocolAHandler(new LeafHandlerContext(this, MyMiddleProtocolA_Request_API.MiddleSwitch.A, MyMiddleProtocolA_Response_API.MiddleSwitch.A))),
			  handler(createMyLeafProtocolBHandler(new LeafHandlerContext(this, MyMiddleProtocolB_Request_API.MiddleSwitch.B, MyMiddleProtocolB_Response_API.MiddleSwitch.B)))
			)
		);
    }
}
