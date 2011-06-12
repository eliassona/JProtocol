package org.jprotocol.example.test;
import static org.junit.Assert.assertTrue;

import org.jprotocol.example.facade.TestFacade;
import org.junit.Before;
import org.junit.Test;

public class SpecializedExampleTest {
	private TestFacade o;

	@Before
	public void before() {
		o = new TestFacade();
	}
	
	@Test
	public void specializedMakeHeaderForMiddleA() {
		o.client.expect(o.client.responses().MyMiddleProtocolA_Response_API().getMiddleSwitch().setA().getMiddleHeader().setZ());
		o.client.send(o.client.requests().MyLeafProtocolA_Request_API());
		assertTrue(o.client.getErrorMessage(), o.client.isOk());
		
	}
}


