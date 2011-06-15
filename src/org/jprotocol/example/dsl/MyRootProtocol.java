package org.jprotocol.example.dsl;

import org.jprotocol.framework.core.IRoot;
import org.jprotocol.framework.core.ProtocolLayoutFactory;
import org.jprotocol.framework.core.IArgumentType.SwitchEnum;

public class MyRootProtocol extends ProtocolLayoutFactory implements IRoot {
	public MyRootProtocol() {
		super("MyRootProtocol");
		protocols(
		  request(
			argInt("RootHeaderA", offset(0)),
			argByte("RootSwitch", 4, SwitchEnum.Switch, value("A", 0), value("B", 1))
		  ), 
		  response(
			argInt("RootHeaderA", offset(0)),
			argByte("RootSwitchResp", 4, SwitchEnum.Switch, value("A", 0), value("B", 1))
		  )
		);
	}
	
}
