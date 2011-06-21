package org.jprotocol.plog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import org.jprotocol.example.ClientQualifiedNames;
import org.jprotocol.example.ServerQualifiedNames;
import org.jprotocol.example.api.MyLeafProtocolA_Request_API;
import org.jprotocol.example.api.MyMiddleProtocolA_Request_API;
import org.jprotocol.example.api.MyMiddleProtocolA_Response_API;
import org.jprotocol.example.api.MyRootProtocol_Request_API;
import org.jprotocol.example.facade.ClientFacade;
import org.jprotocol.example.facade.ServerFacade;
import org.jprotocol.example.facade.TestFacade;
import org.jprotocol.framework.core.IProtocolMessage;
import org.jprotocol.framework.handler.IFlushable;
import org.jprotocol.framework.logger.IProtocolLogger;
import org.jprotocol.framework.logger.ProtocolLogger;
import org.jprotocol.plog.gui.ITextLog;
import org.jprotocol.plog.gui.PLogViewer;
import org.jprotocol.plog.nodes.RootNode;
import org.junit.Test;

public class ParserTest {
	
	@Test
	public void logRequestParser() throws Exception {
		Parser p = new Parser(new BufferedReader(new StringReader(getRequestLogText())));
		assertEquals(2, p.getLoggedProtocol().size());
		
		LoggedProtocols requests = p.getLoggedProtocol().get(0);
		assertEquals(0, requests.timestamp);
		assertEquals(3, requests.protocols.size());
		
		IProtocolMessage rootProtocol = requests.protocols.get(0).protocol;
		assertEquals("MyRootProtocol Request", rootProtocol.getProtocolType().getName());
		MyRootProtocol_Request_API rootApi = new MyRootProtocol_Request_API(rootProtocol);
		assertTrue(rootApi.getRootSwitch().isA());
		
		IProtocolMessage middleProtocol = requests.protocols.get(1).protocol;
		assertEquals("MyMiddleProtocolA Request", middleProtocol.getProtocolType().getName());
		
		MyMiddleProtocolA_Request_API middleReqApi = new MyMiddleProtocolA_Request_API(middleProtocol);
		assertTrue(middleReqApi.getMiddleSwitch().isA());
		
		IProtocolMessage leafProtocol = requests.protocols.get(2).protocol;
		assertEquals("MyLeafProtocolA Request", leafProtocol.getProtocolType().getName());
		MyLeafProtocolA_Request_API leafApi = new MyLeafProtocolA_Request_API(leafProtocol);
		assertEquals(10, leafApi.getLeaf().getBitValue());
	}
	
	@Test
	public void logResponseParser() throws Exception {
		Parser p = new Parser(new BufferedReader(new StringReader(getResponseLogText())));
		assertEquals(2, p.getLoggedProtocol().size());
		LoggedProtocols responses = p.getLoggedProtocol().get(0);
		assertEquals(0, responses.timestamp);
		
		assertEquals(3, responses.protocols.size());
		IProtocolMessage rootProtocol = responses.protocols.get(0).protocol;
		assertEquals("MyRootProtocol Response", rootProtocol.getProtocolType().getName());
		
		IProtocolMessage middleProtocol = responses.protocols.get(1).protocol;
		assertEquals("MyMiddleProtocolA Response", middleProtocol.getProtocolType().getName());
		MyMiddleProtocolA_Response_API middleResApi = new MyMiddleProtocolA_Response_API(middleProtocol);
		assertTrue(middleResApi.getMiddleSwitch().isA());
		assertTrue(middleResApi.getMiddleHeader().isZ());
	}
	
	@Test
	public void testNodes() throws Exception {
		Writer clientWriter = new StringWriter();
		Writer serverWriter = new StringWriter();
		TestFacade tf = new TestFacade(new ProtocolLogger(clientWriter), new ProtocolLogger(serverWriter));
		tf.client.send(tf.client.requests().MyLeafProtocolA_Request_API().getLeaf().setBitValue(10), ClientQualifiedNames.MyMiddleProtocolA_Response_A.NAME);
		tf.client.send(tf.client.requests().MyLeafProtocolB_Request_API().getLeaf().setBitValue(10), ClientQualifiedNames.MyMiddleProtocolB_Response_B.NAME);
		testNodesOf(clientWriter);
		testNodesOf(serverWriter);
	}
	
	private void testNodesOf(Writer writer) throws Exception {
		RootNode root = rootNodeOf(writer);
		assertEquals("Root", root.toString());
		assertEquals(4, root.children().length);
		assertEquals("-> 1 MyLeafProtocolA Request:  0 ms", root.children()[0].toString());
		assertEquals(2, root.children()[0].children().length);
		assertEquals("RootHeaderA: Raw=0, Real=0.0 ", root.children()[0].children()[0].toString());
		assertEquals("RootSwitch: A", root.children()[0].children()[1].toString());
		assertEquals(0, root.children()[0].children()[0].children().length);
		assertEquals(2, root.children()[0].children()[1].children().length);
		assertEquals("MiddleHeader: X", root.children()[0].children()[1].children()[0].toString());
		assertEquals("MiddleSwitch: A", root.children()[0].children()[1].children()[1].toString());
		
		assertEquals(2, root.children()[0].children()[1].children()[1].children().length);
		assertEquals("Leaf: Raw=10, Real=10.0 ", root.children()[0].children()[1].children()[1].children()[0].toString());
		assertEquals("Array", root.children()[0].children()[1].children()[1].children()[1].toString());

		assertEquals(0, root.children()[0].children()[1].children()[1].children()[0].children().length);
		assertEquals(10, root.children()[0].children()[1].children()[1].children()[1].children().length);

		assertEquals("<- 2 MyLeafProtocolA Response:  0 ms", root.children()[1].toString());
	}
	
	private RootNode rootNodeOf(Writer writer) throws Exception {
		return new RootNode(new Parser(new BufferedReader(new StringReader(writer.toString()))).getLoggedProtocol());
	}
	
	private String getRequestLogText() {
		Writer out = new StringWriter();
		IProtocolLogger logger = new ProtocolLogger(out);
		ClientFacade facade = new ClientFacade(new IFlushable.NullFlushable(), logger);
		facade.send(facade.requests().MyLeafProtocolA_Request_API().getLeaf().setBitValue(10), ClientQualifiedNames.MyMiddleProtocolA_Response_A.NAME);
		facade.send(facade.requests().MyLeafProtocolB_Request_API().getLeaf().setBitValue(20), ClientQualifiedNames.MyMiddleProtocolB_Response_B.NAME);
		return out.toString();
	}
	private String getResponseLogText() {
		Writer out = new StringWriter();
		IProtocolLogger logger = new ProtocolLogger(out);
		ServerFacade facade = new ServerFacade(new IFlushable.NullFlushable(), logger);
		facade.send(facade.responses().MyLeafProtocolA_Response_API(), ServerQualifiedNames.MyMiddleProtocolA_Request_A.NAME);
		facade.send(facade.responses().MyLeafProtocolB_Response_API(), ServerQualifiedNames.MyMiddleProtocolB_Request_B.NAME);
		return out.toString();
	}
	
	
	public static void main(String[] args) throws Exception {
		Writer out = new StringWriter();
		TestFacade tf = new TestFacade(new IProtocolLogger.NullProtocolLogger(), new ProtocolLogger(out));
		tf.client.send(tf.client.requests().MyLeafProtocolA_Request_API().getLeaf().setBitValue(10), ClientQualifiedNames.MyMiddleProtocolA_Response_A.NAME);
		tf.client.send(tf.client.requests().MyLeafProtocolB_Request_API(), ClientQualifiedNames.MyMiddleProtocolB_Response_B.NAME);
		new PLogViewer(new RootNode(new Parser(new BufferedReader(new StringReader(out.toString()))).getLoggedProtocol()), "PLog", 0, new ITextLog.NullTextLog());
	}
}
