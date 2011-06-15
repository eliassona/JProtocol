package org.jprotocol.framework.dsl;

import org.jprotocol.framework.core.MemoryLayoutFactory;

public class SizeArgTest {

}


class SizeArgFactory extends MemoryLayoutFactory {

	protected SizeArgFactory(String name) {
		super(name);
		layout(
//		  sizeArg("One Size", size(8), offset(0),
//			args(
//			  argByte("One Adress", size(3), offset(2)),
//			  argByte("One Data", size(1), offset(5))
//			)
//		  ),
//		  sizeArg("Three Size", size(8), offset(8), "One Size"
//			args(
//			  argByte("One Adress", size(3), offset(2)),
//			  argByte("One Data", size(3), offset(5))
//			)
//		  )
		);
	}
	
}