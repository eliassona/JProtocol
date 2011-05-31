package org.jprotocol.protocol.tools
  

import org.jprotocol.codegen.*       
import org.jprotocol.example.api.RequestAPIFactory;
import org.jprotocol.example.api.ResponseAPIFactory;
import org.jprotocol.example.handler.DefaultHandlerHierarchyWithMockery;
import org.jprotocol.framework.handler.Handler;
import org.jprotocol.framework.handler.IFlushable;
import org.jprotocol.framework.handler.Handler.Type;
import org.jprotocol.framework.test.ProtocolMockery;
import org.jprotocol.framework.dsl.ProtocolLayoutType.Direction

public class DefaultAPIGenerator extends AbstractAPIGenerator {
	 
	final factory
	public static void create(protocolLayouts, pack, dir) {
		 protocolLayouts.each {
 			 new DefaultAPIGenerator(it, it.requestProtocol, pack, dir)
			 new DefaultAPIGenerator(it, it.responseProtocol, pack, dir)
			 new DefaultHandlerGenerator(it, pack, dir)
			 new DefaultFacadeGenerator(Handler.Type.Server, pack, dir)
			 new DefaultFacadeGenerator(Handler.Type.Client, pack, dir)
		 }
		 new DefaultAPIFactoryGenerator(protocolLayouts, Direction.Request, pack, dir)
		 new DefaultAPIFactoryGenerator(protocolLayouts, Direction.Response, pack, dir)
	}
	
	public DefaultAPIGenerator(factory, protocol, String pack, String dir) {
		super(protocol, pack + ".api")
		this.factory = factory
		println("Saving to ${new File(dir)}")
		generate()
		save(dir)
	}
	
	def additionalConstructors() {
		 
		block("static ${name}_Test createTest()") {
			line "return new ${name}_Test(new StringBuilderProtocolMessage(new ${factory.class.name}().getRequestProtocol()))"
		}
	
	} 

	public String getInterfaceType(String name) {
		println "============================================" + name
		""
	}
	
}


class DefaultFacadeGenerator extends JavaGenerator {
	DefaultFacadeGenerator(Handler.Type type, pack, dir) {
		super(pack + ".handler",  "${type}Facade")
		stdPackage()
		line "import org.jprotocol.framework.test.ProtocolMockery"
		line "import org.jprotocol.example.api.*"
		line "import org.jprotocol.framework.handler.Handler.Type"
		line "import org.jprotocol.framework.handler.IFlushable"
		
		line "import org.jprotocol.framework.facade.*"
		stdJavaDoc()
		block("public class $name extends Abstract${type}Facade") {
			line "private final DefaultHandlerHierarchyWithMockery hierarchy"
			line "private final RequestAPIFactory requestFactory"
			line "private final ResponseAPIFactory responseFactory"
			block("public ${name}(IFlushable flushable)") {
				line "super(flushable, Type.Server)"
				line "requestFactory = new RequestAPIFactory()"
				line "responseFactory = new ResponseAPIFactory()"
				line "hierarchy = createHierarchy()"
			}
			javadoc() {
				comment "Override to provide specialized implementation"
			}
			block("protected DefaultHandlerHierarchyWithMockery createHierarchy()") {
				line "return new DefaultHandlerHierarchyWithMockery(type, flushable)"
			}
			block("public RequestAPIFactory requests()") {
				line "return requestFactory"
			}
			block("public ResponseAPIFactory responses()") {
				line "return responseFactory"
			}
		
			block("@Override protected ProtocolMockery getMockery()") {
				line "return hierarchy.mockery"
			}
			block("@Override public void receive(byte[] data)") {
				line "hierarchy.receive(data)"
			}
		
		
		}
		save(dir)
	}
}

class DefaultAPIFactoryGenerator extends JavaGenerator {
	DefaultAPIFactoryGenerator(protocolLayouts, Direction direction, pack, dir) {
		super(pack + ".api", "${direction}APIFactory")
		stdPackage()
		stdJavaDoc()
		block("public class $name") {
			protocolLayouts.each {
				final classNameUtil = new ClassNameUtil(it)
				String className = classNameUtil.requestApiClass
				if (direction == Direction.Response) {
					className = classNameUtil.responseApiClass
				}
				block("public ${className} ${className}()") {
					 line "return ${className}.createTest()"
				}
			} 
		}
		save(dir)
	} 
		  
} 

class DefaultHandlerGenerator extends JavaGenerator {
	final layout 
	final classNameUtil
	DefaultHandlerGenerator(layout, pack, dir) { 
		super(pack + ".handler", "Default" + NameFormatter.formatName(layout.name) + "Handler")
		this.layout = layout
		this.classNameUtil = new ClassNameUtil(layout)
		stdPackage()
		line "import org.jprotocol.framework.dsl.*"
		line "import org.jprotocol.framework.handler.*"
		line "import ${pack}.api.*"
		stdJavaDoc()
		block("public class $name extends Handler<${requestApiClass}, ${responseApiClass}>") {
			
			block("protected ${name}(HandlerContext context)") {
				line "super(new ${layout.class.name}(), context)"
			}
		
	
			
			block("@Override public final ${requestApiClass} createRequest(IProtocolMessage p)") {
				line "return new ${requestApiClass}(p)"
			}
			block("@Override public final ${responseApiClass} createResponse(IProtocolMessage p)") {
				line "return new ${responseApiClass}(p)"
			}

		}
		save(dir) 
	}
	
	String getRequestApiClass() {
		classNameUtil.getRequestApiClass()
	}
	String getResponseApiClass() { 
		classNameUtil.getResponseApiClass()
	}
}
 
class ClassNameUtil {
	final layout
	ClassNameUtil(layout) {
		this.layout = layout
	}
	String getRequestApiClass() {
		apiClassNameOf(Direction.Request)
	}
	String getResponseApiClass() {
		apiClassNameOf(Direction.Response)
	}
	String apiClassNameOf(Direction dir) {
		"${layout.name}_${dir}_API"
	}
}