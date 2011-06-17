package org.jprotocol.example.protocols;

import static org.jprotocol.quantity.Quantity.quantity;
import static org.jprotocol.quantity.Unit.byteSize;
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
		  response(
			argShort("AShort", offset(0), realOffset(0), resolution(.5), Unit.A),
			arg("AnEnum", size(1), offset(2), value("no", 0), value("yes", 1)),
			argStr("AString", quantity(3, byteSize), quantity(100, byteSize))
		  )
		);
	}
}	
	

