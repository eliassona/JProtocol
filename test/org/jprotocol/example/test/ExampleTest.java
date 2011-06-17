package org.jprotocol.example.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jprotocol.example.api.MyLeafProtocolA_Request_API;
import org.jprotocol.example.api.MyLeafProtocolA_Request_API.Array;
import org.jprotocol.example.api.MyLeafProtocolA_Response_API;
import org.jprotocol.example.facade.TestFacade;
import org.junit.Before;
import org.junit.Test;

public class ExampleTest {

	private TestFacade tf;

	@Before
	public void before() {
		tf = new TestFacade();
	}
	
	
	
	@Test
	public void expectSuccessA() {
		tf.server.expect(tf.server.requests().MyLeafProtocolA_Request_API());
		tf.client.expect(tf.client.responses().MyLeafProtocolA_Response_API());
		tf.client.send(tf.client.requests().MyLeafProtocolA_Request_API());
		assertTrue(tf.server.getErrorMessage(), tf.server.isOk());
		assertTrue(tf.client.getErrorMessage(), tf.client.isOk());
	}
	@Test
	public void expectSuccessB() {
		tf.server.expect(tf.server.requests().MyLeafProtocolB_Request_API());
		tf.client.expect(tf.client.responses().MyLeafProtocolB_Response_API());
		tf.client.send(tf.client.requests().MyLeafProtocolB_Request_API());
		assertTrue(tf.server.getErrorMessage(), tf.server.isOk());
		assertTrue(tf.client.getErrorMessage(), tf.client.isOk());
	}

	
	@Test 
	public void expectArgDoNotMatchInArray() {
		MyLeafProtocolA_Request_API expectedRequest = tf.server.requests().MyLeafProtocolA_Request_API();
		int i = 0;
		for (Array a: expectedRequest.getArray()) {
			a.getArrayInt().setBitValue(i);
			i++;
		}
		tf.server.expect(expectedRequest);
		tf.client.send(tf.server.requests().MyLeafProtocolA_Request_API());
		assertFalse(tf.server.getErrorMessage(), tf.server.isOk());
		assertEquals("The Request \"MyLeafProtocolA\" arguments did not match:, expected \"ArrayInt\"=\"1\" but was \"0\", expected \"ArrayInt\"=\"2\" but was \"0\", expected \"ArrayInt\"=\"3\" but was \"0\", expected \"ArrayInt\"=\"4\" but was \"0\", expected \"ArrayInt\"=\"5\" but was \"0\", expected \"ArrayInt\"=\"6\" but was \"0\", expected \"ArrayInt\"=\"7\" but was \"0\", expected \"ArrayInt\"=\"8\" but was \"0\", expected \"ArrayInt\"=\"9\" but was \"0\"", tf.server.getErrorMessage());
	}
	
	@Test
	public void protocolAHasNotBeenReceived() {
		tf.server.expect(tf.server.requests().MyLeafProtocolB_Request_API());
		assertFalse(tf.server.getErrorMessage(), tf.server.isOk());
		assertFalse(tf.server.getErrorMessage(), tf.server.isOk());
	}
	
	@Test
	public void respondSuccess() {
		MyLeafProtocolA_Request_API request = tf.server.requests().MyLeafProtocolA_Request_API();
		MyLeafProtocolA_Response_API response = tf.server.responses().MyLeafProtocolA_Response_API().getAShort().setRealValue(2).getAnEnum().setyes();
		tf.server.when(request).thenRespond(response);
		tf.client.expect(response);
		tf.client.send(request);
		assertTrue(tf.client.getErrorMessage(), tf.client.isOk());
	}
	@Test
	public void respondArgDoNotMatch() {
		MyLeafProtocolA_Request_API request = tf.server.requests().MyLeafProtocolA_Request_API();
		MyLeafProtocolA_Response_API realResponse = tf.server.responses().MyLeafProtocolA_Response_API().getAShort().setRealValue(4).getAnEnum().setno().getAString().setValue("asdf");
		MyLeafProtocolA_Response_API specResponse = tf.server.responses().MyLeafProtocolA_Response_API().getAShort().setRealValue(2).getAnEnum().setyes().getAString().setValue("fdsa");
		tf.server.when(request).thenRespond(realResponse);
		tf.client.expect(specResponse);
		tf.client.send(request);
		assertFalse(tf.client.getErrorMessage(), tf.client.isOk());
		assertEquals("The Response \"MyLeafProtocolA\" arguments did not match: expected \"AShort\"=\"4\" but was \"8\", expected \"AnEnum\"=\"yes\" but was \"no\", expected \"AString\"=\"fdsa\" but was \"asdf\"", tf.client.getErrorMessage());
	}
	
	@Test
	public void specializedMakeHeaderForMiddleA() {
		tf.client.expect(tf.client.responses().MyMiddleProtocolA_Response_API().getMiddleSwitch().setA().getMiddleHeader().setZ());
		tf.client.send(tf.client.requests().MyLeafProtocolA_Request_API());
		assertTrue(tf.client.getErrorMessage(), tf.client.isOk());
		
	}
	
}
