package org.jprotocol.protocol.tools

import org.jprotocol.example.protocols.MyProtocols
import org.jprotocol.example.protocols.protocolstack.ClientHandlerHierarchy
import org.jprotocol.example.protocols.protocolstack.ServerHandlerHierarchy
import org.jprotocol.framework.handler.IHandlerHierarchy
import org.jprotocol.framework.handler.IProtocolSniffer
import org.jprotocol.framework.handler.ProtocolState

class ExampleGenerator {
	static pack = "org.jprotocol.example"
	static src = "src"
	public static void main(String[] args) {
		ProtocolCodeGenerator.generate(new MyProtocols(), pack, src)
		
		generateQNames(ClientHandlerHierarchy.pLog(new ProtocolState(), new IProtocolSniffer.NullProtocolSniffer()))
		generateQNames(ServerHandlerHierarchy.pLog(new ProtocolState(), new IProtocolSniffer.NullProtocolSniffer()))
		
	}
	
	static generateQNames(IHandlerHierarchy hh) {
		new QualifiedNameAPIGenerator(hh.getRoot(), "${hh.getType()}QualifiedNames", pack, src)
	}

}
