package org.jprotocol.protocol.tools

import org.jprotocol.example.dsl.MyProtocols

class ExampleGenerator {
	public static void main(String[] args) {
		generate(new MyProtocols(), "org.jprotocol.example", "src")
	}

	private static void generate(protocols, String pack, String dir) {
		ProtocolCodeGenerator.generate(protocols, pack, dir)
	}	 
	 

}
