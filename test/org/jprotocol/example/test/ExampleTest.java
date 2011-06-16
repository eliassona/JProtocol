package org.jprotocol.example.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jprotocol.example.api.MyLeafProtocolA_Request_API;
import org.jprotocol.example.api.MyLeafProtocolA_Response_API;
import org.jprotocol.example.facade.TestFacade;
import org.junit.Before;
import org.junit.Test;

public class ExampleTest {

	private TestFacade o;

	@Before
	public void before() {
		o = new TestFacade();
	}
	
	
	
	@Test
	public void expectSuccessA() {
		o.server.expect(o.server.requests().MyLeafProtocolA_Request_API());
		o.client.expect(o.client.responses().MyLeafProtocolA_Response_API());
		o.client.send(o.client.requests().MyLeafProtocolA_Request_API());
		assertTrue(o.server.getErrorMessage(), o.server.isOk());
		assertTrue(o.client.getErrorMessage(), o.client.isOk());
	}
	@Test
	public void expectSuccessB() {
		o.server.expect(o.server.requests().MyLeafProtocolB_Request_API());
		o.client.expect(o.client.responses().MyLeafProtocolB_Response_API());
		o.client.send(o.client.requests().MyLeafProtocolB_Request_API());
		assertTrue(o.server.getErrorMessage(), o.server.isOk());
		assertTrue(o.client.getErrorMessage(), o.client.isOk());
	}
	
	@Test
	public void protocolAHasNotBeenReceived() {
		o.server.expect(o.server.requests().MyLeafProtocolB_Request_API());
		assertFalse(o.server.getErrorMessage(), o.server.isOk());
		assertFalse(o.server.getErrorMessage(), o.server.isOk());
	}
	
	@Test
	public void respondSuccess() {
		MyLeafProtocolA_Request_API request = o.server.requests().MyLeafProtocolA_Request_API();
		MyLeafProtocolA_Response_API response = o.server.responses().MyLeafProtocolA_Response_API().getAShort().setRealValue(2).getAnEnum().setyes();
		o.server.when(request).thenRespond(response);
		o.client.expect(response);
		o.client.send(request);
		assertTrue(o.client.getErrorMessage(), o.client.isOk());
	}
	@Test
	public void respondArgDoNotMatch() {
		MyLeafProtocolA_Request_API request = o.server.requests().MyLeafProtocolA_Request_API();
		MyLeafProtocolA_Response_API realResponse = o.server.responses().MyLeafProtocolA_Response_API().getAShort().setRealValue(4).getAnEnum().setno();
		MyLeafProtocolA_Response_API specResponse = o.server.responses().MyLeafProtocolA_Response_API().getAShort().setRealValue(2).getAnEnum().setyes();
		o.server.when(request).thenRespond(realResponse);
		o.client.expect(specResponse);
		o.client.send(request);
		assertFalse(o.client.getErrorMessage(), o.client.isOk());
		assertEquals("The Response \"MyLeafProtocolA\" arguments did not match: expected \"AShort\"=\"4\" but was \"8\", expected \"AnEnum\"=\"yes\" but was \"no\"", o.client.getErrorMessage());
	}
	
	
}
