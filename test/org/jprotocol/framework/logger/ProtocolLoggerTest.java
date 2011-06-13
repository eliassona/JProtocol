package org.jprotocol.framework.logger;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.StringWriter;

import org.jprotocol.framework.dsl.IProtocolLayoutType.Direction;
import org.junit.Test;

public class ProtocolLoggerTest {
	
	@Test
	public void request() throws FileNotFoundException {
		StringWriter out = new StringWriter();
		ProtocolLogger pl = new ProtocolLogger(out);
		pl.write(Direction.Request, new byte[]{0, 127, -1});
		assertEquals("0,0,127,255\n", out.getBuffer().toString());
		pl.write(Direction.Response, new byte[]{0, 127, -1});
	}
	@Test
	public void response() throws FileNotFoundException {
		StringWriter out = new StringWriter();
		ProtocolLogger pl = new ProtocolLogger(out);
		pl.write(Direction.Response, new byte[]{0, 127, -1});
		assertEquals("1,0,127,255\n", out.getBuffer().toString());
	}
}
