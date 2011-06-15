package org.jprotocol.example.dsl;

import static org.jprotocol.quantity.Unit.hz;

import org.jprotocol.framework.core.ProtocolLayoutFactory;
import org.jprotocol.quantity.Unit;

public class MyLeafProtocolA extends ProtocolLayoutFactory {
	public MyLeafProtocolA() {
		super("MyLeafProtocolA");
		protocols(
		  request(
			argInt("Leaf", offset(0)),
			iArg("Array", 10,
			  args(
				argByte("ArrayEnum", offset(4), value("Off", 1), value("On", 2)),
				argInt("ArrayInt", offset(5), 0, 2, hz)
			  )
			)
		  ), 
		  response()
		);
	}
}	
	

