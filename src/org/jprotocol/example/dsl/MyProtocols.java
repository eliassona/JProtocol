package org.jprotocol.example.dsl;

import org.jprotocol.framework.core.ProtocolLayouts;

public class MyProtocols extends ProtocolLayouts {
	public MyProtocols() {
		super(new MyLeafProtocolA(), 
			  new MyLeafProtocolB(), 
			  new MyMiddleProtocolA(), 
			  new MyMiddleProtocolB(), 
			  new MyRootProtocol());
	}
}
