package org.jprotocol.example.dsl;

import org.jprotocol.framework.core.ProtocolLayoutFactory;

public class MyLeafProtocolB extends ProtocolLayoutFactory {
	public MyLeafProtocolB() {
		super("MyLeafProtocolB");
		protocols(
		  request(
			argInt("Leaf", offset(0))
		  ), 
		  response()
		);
	}
	
}
