package org.matt.autocode.domain.generator;

import org.matt.autocode.conf.Config;

import org.matt.autocode.conf.GeneratorConfig;
import org.matt.autocode.conf.Table;

public class GeneratorFactory {

	public static Generator createGenerator(Config config,GeneratorConfig gConfig,Table table) throws Exception {
		if(GeneratorConfig.TYPE_DOMAIN.equals(gConfig.getType())) {
			return new DomainGenerator(config, gConfig,table);
		} else if(GeneratorConfig.TYPE_ACTION.equals(gConfig.getType())) {
			return new ActionGenerator(config, gConfig,table);
		}  else if(GeneratorConfig.TYPE_THRIFT_ACTION.equals(gConfig.getType())) {
			return new ThriftActionGenerator(config, gConfig,table);
		}else if(GeneratorConfig.TYPE_SERVICE.equals(gConfig.getType())) {
			return new ServiceGenerator(config, gConfig,table);
		} else if(GeneratorConfig.TYPE_ISERVICE.equals(gConfig.getType())) {
			return new IServiceGenerator(config, gConfig,table);
		} else if(GeneratorConfig.TYPE_XML.equals(gConfig.getType())) {
			return new XmlGenerator(config, gConfig,table);
		} else if(GeneratorConfig.TYPE_PAGE.equals(gConfig.getType())) {
			return new PageGenerator(config, gConfig,table);
		}else if(GeneratorConfig.TYPE_THRIFT_IBASESERVICE.equals(gConfig.getType())) {
			return new ThriftIBaseServiceGenerator(config, gConfig,table);
		} else if(GeneratorConfig.TYPE_THRIFT_BASESERVICE.equals(gConfig.getType())) {
			return new ThriftBaseServiceGenerator(config, gConfig,table);
		} else if(GeneratorConfig.TYPE_THRIFT_ISERVICE.equals(gConfig.getType())) {
			return new ThriftIServiceGenerator(config, gConfig,table);
		} else if(GeneratorConfig.TYPE_THRIFT_SERVICE.equals(gConfig.getType())) {
			return new ThriftServiceGenerator(config, gConfig,table);
		} 
		throw new Exception("不支持的生成器类型："+gConfig.getType());
	}
	
}
