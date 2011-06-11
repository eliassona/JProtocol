package org.jprotocol.protocol.tools
  

import org.jprotocol.codegen.*
import org.jprotocol.example.handler.DefaultMyLeafProtocolBHandler;
import org.jprotocol.framework.dsl.IRoot
import org.jprotocol.framework.dsl.IProtocolLayoutType.Direction
import org.jprotocol.framework.dsl.argiters.FindSwitchIter
import org.jprotocol.framework.handler.Handler
import org.jprotocol.framework.handler.HandlerContext;
import org.jprotocol.framework.handler.IFlushable;
import org.jprotocol.framework.handler.IProtocolSniffer;
import org.jprotocol.framework.handler.IProtocolState;
import org.jprotocol.framework.handler.Handler.Type
import org.jprotocol.framework.logger.IProtocolLogger;
 
public class DefaultAPIGenerator extends AbstractAPIGenerator {
	 
	final factory
	public static void create(protocolLayouts, pack, dir) {
		 protocolLayouts.each {
 			 new DefaultAPIGenerator(it, it.requestProtocol, pack, dir)
			 new DefaultAPIGenerator(it, it.responseProtocol, pack, dir)
			 new DefaultHandlerGenerator(it, pack, dir)
			 new DefaultFacadeGenerator(Handler.Type.Server, pack, dir)
			 new DefaultFacadeGenerator(Handler.Type.Client, pack, dir)
			 new DefaultTestFacadeGenerator(pack, dir)
		 }
		 new DefaultHandlerHierarchyGenerator(protocolLayouts, pack, dir)
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
		""
	}
	  
} 
 
class DefaultTestFacadeGenerator extends JavaGenerator {
	DefaultTestFacadeGenerator(pack, dir) {
		super(pack + ".facade",  "TestFacade")
		stdPackage()
		line "import org.jprotocol.framework.facade.AbstractFacade"
		line "import org.jprotocol.framework.handler.IFlushable"
		line "import org.jprotocol.framework.logger.IProtocolLogger.NullProtocolLogger"
		stdJavaDoc()
		
		block("public class $name") {
			line "public final ClientFacade client"
			line "public final ServerFacade server"
		
			block("public ${name}()") {
				line "FlushableClientServer cf = new FlushableClientServer()"
				line "FlushableClientServer sf = new FlushableClientServer()"
				line "client = createClientFacade(cf)"
				line "server = createServerFacade(sf)"
				line "cf.setTarget(server)"
				line "sf.setTarget(client)"
			}
		

			javadoc() {
			 	comment "Override to provide a specialized version of ClientFacade"
			}
			block("protected ClientFacade createClientFacade(IFlushable flushable)") {
				line "return new ClientFacade(flushable, new NullProtocolLogger())"
			}
			javadoc() {
			 	comment "Override to provide a specialized version of ServerFacade"
			}
			block("protected ServerFacade createServerFacade(IFlushable flushable)") {
				line "return new ServerFacade(flushable)"
			}
		}
		
		block("class FlushableClientServer implements IFlushable") {
		
			line "private AbstractFacade server"
		
			block("@Override public void flush(byte[] data)") {
				line "server.receive(data)"
			} 
		
			block("public void setTarget(AbstractFacade server)") {
				line "this.server = server"
			}
			
		}
		save(dir)
	}
}
class DefaultFacadeGenerator extends JavaGenerator {
	DefaultFacadeGenerator(Handler.Type type, pack, dir) {
		super(pack + ".facade",  "${type}Facade")
		stdPackage()
		line "import org.jprotocol.framework.test.ProtocolMockery"
		line "import org.jprotocol.example.api.*"
		line "import org.jprotocol.framework.handler.Handler.Type"
		line "import org.jprotocol.framework.handler.IFlushable"
		line "import org.jprotocol.example.handler.DefaultHandlerHierarchyWithMockery"
		
		line "import org.jprotocol.framework.facade.*"
		line "import org.jprotocol.framework.logger.*"
		stdJavaDoc()
		block("public class $name extends Abstract${type}Facade") {
			line "private final DefaultHandlerHierarchyWithMockery hierarchy"
			line "private final RequestAPIFactory requestFactory"
			line "private final ResponseAPIFactory responseFactory"
			block("public ${name}(IFlushable flushable)") {
				line "this(flushable, new ProtocolLogger())"
			}
			block("public ${name}(IFlushable flushable, IProtocolLogger logger)") {
				line "super(flushable, Type.${type}, logger)"
				line "requestFactory = new RequestAPIFactory()"
				line "responseFactory = new ResponseAPIFactory()"
				line "hierarchy = createHierarchy()"
			}
			javadoc() {
				comment "Override to provide specialized implementation"
			}
			block("protected DefaultHandlerHierarchyWithMockery createHierarchy()") {
				line "return new DefaultHandlerHierarchyWithMockery(type, flushable, logger)"
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

class DefaultHandlerHierarchyGenerator extends JavaGenerator {
	final layout
	final classNameUtil
	DefaultHandlerHierarchyGenerator(protocolLayouts, pack, dir) {
		super(pack + ".handler", "AbstractDefaultHandlerHierarchy")
		stdPackage()
		line "import org.jprotocol.framework.handler.*"
		line "import org.jprotocol.framework.logger.IProtocolLogger"
		line "import org.jprotocol.framework.handler.Handler.Type"
		stdJavaDoc()
		block("abstract public class $name extends AbstractHandlerHierarchy") {
			block("public ${name}(Type type, final IFlushable flushable, IProtocolState protocolState, IProtocolSniffer sniffer, IProtocolLogger logger)") {
				line "super(type, flushable, protocolState, sniffer, logger)"
			}
			protocolLayouts.each {
				def methodName = NameFormatter.formatName(it.name) + "Handler"
				if (it instanceof IRoot) {
					block("@Override protected Handler<?, ?> createRoot()") {
						line "return new Default${methodName}(getRootContext(), flushable, logger)"
					}
				} else {
					block("protected Handler<?, ?> create${methodName}(HandlerContext context)") {
						line "return new Default${methodName}(context)"
					}
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
		if (isRoot()) line "import org.jprotocol.framework.logger.IProtocolLogger"
		stdJavaDoc()
		def rootStr = isRoot() ? "Root" : ""
		block("public class $name extends ${rootStr}Handler<${requestApiClass}, ${responseApiClass}>") {
			if (isRoot()) {
				block("protected ${name}(HandlerContext context, IFlushable flushable, IProtocolLogger logger)") {
					line(/super(new ${layout.class.name}(), ${fieldNameOf(new FindSwitchIter(layout.requestProtocol))}, ${fieldNameOf(new FindSwitchIter(layout.responseProtocol))}, context, flushable, logger)/)
				}
			} else {
				block("protected ${name}(HandlerContext context)") {
					line(/super(new ${layout.class.name}(), ${fieldNameOf(new FindSwitchIter(layout.requestProtocol))}, ${fieldNameOf(new FindSwitchIter(layout.responseProtocol))}, context)/)
				}
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
	
	boolean isRoot() {
		layout instanceof IRoot
	}
	String fieldNameOf(FindSwitchIter iter) {
		if (iter.foundSwitch == null) {
			return "null"
		}
		(/"${iter.foundSwitch.name}"/)
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