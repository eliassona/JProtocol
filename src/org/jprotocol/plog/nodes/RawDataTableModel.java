package org.jprotocol.plog.nodes;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.jprotocol.framework.core.IProtocolMessage;
import org.jprotocol.quantity.Quantity;
import org.jprotocol.quantity.Unit;

class RawDataTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private final IProtocolMessage protocol;
	private final int offset;
	
	static JTable createTable(IProtocolMessage protocol, Quantity offset) {
		return new JTable(new RawDataTableModel(protocol, offset));
	}
	
	RawDataTableModel(IProtocolMessage protocol, Quantity offset) {
		this.protocol = protocol;
		this.offset = (int) offset.convert(Unit.byteSize).getValue();
	}
    public String getColumnName(int columnIndex) { 
        if (columnIndex == 0) return "Index"; else return "Value";
    }

    public int getColumnCount() { return 2; }
    public int getRowCount() { 
        return protocol.getDataAsInts().length - offset; 
    }
    public Object getValueAt(int row, int col) { 
        if (col == 0) return "" + row;
        String res = Integer.toHexString(protocol.getDataAsInts()[row + offset]);
        if (res.length() == 1) {
        	res = "0" + res;
        }
        return "0x" + res;
    }
}
