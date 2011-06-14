package org.jprotocol.protocol.tools;

import org.apache.tools.ant.Task;
import org.jprotocol.framework.dsl.ProtocolLayouts;

public class ProtocolGeneratorTask extends Task {
	private String rootDir = "src";
	private String protocolClass;
	private String pack;
	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}
	
	public void setProtocolClass(String protocolClass) {
		this.protocolClass = protocolClass;
	}
	
	public void setPackage(String pack) {
		this.pack = pack;
	}
	@Override
	public void execute() {
		try {
			ProtocolCodeGenerator.generate((ProtocolLayouts) Class.forName(protocolClass).newInstance(), pack, rootDir);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
