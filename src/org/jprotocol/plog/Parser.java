package org.jprotocol.plog;

import static org.jprotocol.framework.handler.Handler.Type.Client;
import static org.jprotocol.framework.handler.Handler.Type.Server;
import static org.jprotocol.util.DBC.check;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jprotocol.framework.handler.IHandlerHierarchy;
import org.jprotocol.framework.handler.IProtocolSniffer;
import org.jprotocol.framework.handler.IProtocolState;
import org.jprotocol.framework.handler.ProtocolState;

public class Parser {
	private final IHandlerHierarchy server;
	private final IHandlerHierarchy client;
	private final IProtocolState protocolState;
	private final LogSniffer logSniffer;
	private final List<LoggedProtocols> loggedProtocols = new ArrayList<LoggedProtocols>();
	private long firstTimeStamp = -1;
	public Parser(String filename) throws Exception {
		this(new File(filename));
	}
	public Parser(File file) throws Exception {
		this(new BufferedReader(new FileReader(file)));
	}
	public Parser(BufferedReader in) throws Exception {
		try {
			this.protocolState = new ProtocolState();
			this.logSniffer = new LogSniffer();
			IHandlerHierarchy h = hierarchyOf(in.readLine());
			if (h.getType() == Client) {
				client = h;
				server = hierarchyOf(in.readLine());
				check(server.getType() == Server);
			} else {
				server = h;
				client = hierarchyOf(in.readLine());
				check(client.getType() == Client);
			}
			String line = in.readLine();
			while (line != null) {
				String[] tokens = line.split(",");
				if (tokens.length > 1) {
					logSniffer.clear();
					long timeStamp = Long.valueOf(tokens[0]);
					if (firstTimeStamp < 0) {
						firstTimeStamp = timeStamp;
					}
					if (tokens[1].equals("0")) {
						handleRequest(toBytes(tokens));
					} else {
						check(tokens[1].equals("1"));
						handleResponse(toBytes(tokens));
					}
			        loggedProtocols.add(new LoggedProtocols(logSniffer.getProtocols(), timeStamp - firstTimeStamp, firstTimeStamp));
				}
				line = in.readLine();
			}
		} finally {
			in.close();
		}
	}
	
    public List<LoggedProtocols> getLoggedProtocol() {
        return Collections.unmodifiableList(loggedProtocols);
    }

	
	private byte[] toBytes(String[] tokens) {
		byte[] result = new byte[tokens.length - 1];
		for (int i = 2; i < tokens.length; i++) {
			result[i - 2] = Byte.valueOf(tokens[i]);
		}
		return result;
	}
	private void handleResponse(byte[] data) {
		client.getRoot().receive(data);
	}
	private void handleRequest(byte[] data) {
		server.getRoot().receive(data);
	}
	private IHandlerHierarchy hierarchyOf(String className) throws Exception {
		@SuppressWarnings("unchecked")
		Class<IHandlerHierarchy> clazz = (Class<IHandlerHierarchy>) Class.forName(className);
		Method m = clazz.getMethod("pLog", IProtocolState.class, IProtocolSniffer.class);
		return (IHandlerHierarchy) m.invoke(null, protocolState, logSniffer);
	}
}
