package org.jprotocol.framework.logger;

import static org.jprotocol.util.Contract.notNull;
import static org.jprotocol.util.Contract.require;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import org.jprotocol.framework.dsl.IProtocolLayoutType.Direction;
import org.jprotocol.framework.dsl.ProtocolMessage;
import org.jprotocol.quantity.Quantity;



/**
 * Writes byte array information to a file called protocol.log that can be used by protocol analyzers afterwards.
 * @author eliasa01
 */
public class ProtocolLogger implements IProtocolLogger {    
    private final static Logger logger = Logger.getLogger(ProtocolLogger.class.getName());
    private final Writer out;
   
    public ProtocolLogger() throws FileNotFoundException {
    	this(getWriter());
    	
    }
    
    public ProtocolLogger(Writer out) throws FileNotFoundException {
    	this.out = out;
        activateFlushTimer();
    }

    private static Writer getWriter() throws FileNotFoundException {
        final int sizeOfRow = 250; 
        final int noOfLines = 300;
        final int bufferSize = sizeOfRow * noOfLines;
        if (doesLoggerPathExist()) {
            return new BufferedWriter(new PrintWriter(getLoggerFileNameAndPath()), bufferSize);
        }
        return new NullWriter();
    }
    
    private static boolean doesLoggerPathExist() {
        return new File(getLoggerPath()).exists();
    }

    public static String getLoggerFileNameAndPath() {
        return getLoggerPath() + "/protocol.log";
    }
    private static String getLoggerPath() {
        return System.getProperty("user.home", ".");
    }
    
    private void activateFlushTimer() {
        final TimerTask task = createFlushTimerTask();
        createFlushTimer(task);
    }

    private void createFlushTimer(final TimerTask task) {
        final long timeBetweenFlushes = 1;
        final long delay = timeBetweenFlushes;  // time before first call to "flush()"
        final long period = timeBetweenFlushes; // time between calls to "flush()"        
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, delay, period);
    }

    private TimerTask createFlushTimerTask() {
        return new TimerTask() {
            public void run() {
                flush();
            }
        };
    }
    
    public synchronized boolean flush() {
        boolean status = true;
        try {
            out.flush();
        }
        catch (final IOException e) {
            status = false;
        }
        
        return status;
    }
    

    @Override
    public synchronized void write(
            final String client,
            final Direction dir, 
            final byte[] data) {
    	require(notNull(client));
    	require(notNull(dir));
    	require(notNull(data));
        try {
        	out.write(client);
        	out.write(",");
        	out.write(dir.toString());
        	out.write(",");
        	out.write(format(data));
        } catch (Throwable t) {
        	logger.warning(t.getMessage());
        }
    }

	private String format(byte[] data) {
		StringBuffer buf = new StringBuffer(200);
		for (byte b: data) {
			if (buf.length() != 0) {
				buf.append(',');
			}
			buf.append(ProtocolMessage.intOf(b));
		}
		return buf.toString();
	}

	@Override
	public void writeInfo(String infoMessage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeTest(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeSuccessfulExpect(String expectStr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeFailedExpect(String expectStr, String failureMessage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeSuccessfulAllow(String allowStr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeFailedAllow(String allowStr, String failureMessage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writePendingVerify(Quantity timeout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeSuccessfulVerify() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeFailedVerify(String errorMessage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeAddResponse(String request, String response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeInject(String response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeAllowRequests() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeSpecifyRequests() {
		// TODO Auto-generated method stub
		
	}

}

class NullWriter extends Writer {
    @Override
    public void close() throws IOException {
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
    }
}



