package org.jprotocol.protocol.tools

import com.sjm.protocol.framework.core.*
import com.sjm.tools.quantity.*

/**
 * @author Anders Eliasson 
 *
 */
class TestAPIGenerator extends AbstractAPIGenerator {
	TestAPIGenerator() {
		super(new TestFactory().memoryLayout, "com.sjm.protocol.tools")
		generate()
		save("tools")
	}
	public static void main(String[] args) {
		new TestAPIGenerator()
	}

    String getInterfaceType(String name){
        return "ITestable";
    }
}

