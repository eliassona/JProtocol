package org.jprotocol.plog.gui;

import java.util.List;


public interface ITextLog {
    boolean hasLines();
    int lineNrOf(long timestamp);
    List<String> getLines();

    public static class NullTextLog  implements ITextLog {
		@Override
		public boolean hasLines() {
			return false;
		}

		@Override
		public int lineNrOf(long timestamp) {
			return 0;
		}

		@Override
		public List<String> getLines() {
			return null;
		}
    }
}
