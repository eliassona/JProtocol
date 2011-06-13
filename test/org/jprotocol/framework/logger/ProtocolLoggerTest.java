package org.jprotocol.framework.logger;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.Date;

import org.jprotocol.framework.dsl.IProtocolLayoutType.Direction;
import org.junit.Test;

public class ProtocolLoggerTest {
	
	@Test
	public void request() throws FileNotFoundException {
		StringWriter out = new StringWriter();
		ProtocolLogger pl = new ProtocolLogger(out);
		Date timeStamp = new Date();
		pl.write(timeStamp, Direction.Request, new byte[]{0, 127, -1});
		assertEquals(timeStamp.getTime() + ",0,0,127,255\n", out.getBuffer().toString());
		pl.write(timeStamp, Direction.Response, new byte[]{0, 127, -1});
	}
	@Test
	public void response() throws FileNotFoundException {
		StringWriter out = new StringWriter();
		ProtocolLogger pl = new ProtocolLogger(out);
		Date timeStamp = new Date();
		pl.write(timeStamp, Direction.Response, new byte[]{0, 127, -1});
		assertEquals(timeStamp.getTime() + ",1,0,127,255\n", out.getBuffer().toString());
	}
}
