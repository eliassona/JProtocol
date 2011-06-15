package org.jprotocol.protocol.tools

import org.jprotocol.example.protocols.MyProtocols

class ExampleGenerator {
	public static void main(String[] args) {
		ProtocolCodeGenerator.generate(new MyProtocols(), "org.jprotocol.example", "src")
	}

}
