package org.matt.autocode.domain.generator;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import org.matt.autocode.conf.Config;
import org.matt.autocode.conf.GeneratorConfig;
import org.matt.autocode.conf.Table;
import org.matt.autocode.util.IOUtil;
import org.matt.autocode.util.StringUtil;

public class ThriftBaseServiceGenerator extends Generator {

	private Map<String, Object> generatorContext;

	public ThriftBaseServiceGenerator(Config config, GeneratorConfig gConfig, Table table) {
		super(config, gConfig, table);
		generatorContext = new HashMap<String, Object>();
		generatorContext.put("domain",
				this.targetPackage + "." + table.getModuleName() + ".domain.thrift."
						+ table.getClassName());
		generatorContext.put("javaClassName", 
				this.targetPackage + "." + table.getModuleName() + ".domain."
				+table.getClassName());
		
		generatorContext.put("ibaseservice", 
				this.targetPackage + "." + table.getModuleName() + ".service.thrift.base.I"
				+table.getClassName()+"BaseService");

		this.targetPackage = this.targetPackage + "." + table.getModuleName()
				+ ".service.thrift.base.impl";
	}

	private String getPath() {
		StringBuffer buf = new StringBuffer();
		buf.append(targetProject).append("/src/main/java/")
				.append(StringUtil.packge2path(targetPackage)).append("/");
		buf.append(table.getClassName());
		buf.append("BaseService.java");
		return buf.toString();
	}

	private String getTestPath() {
		StringBuffer buf = new StringBuffer();
		buf.append(targetProject).append("/src/test/java/")
				.append(StringUtil.packge2path(targetPackage)).append("/");
		buf.append(table.getClassName());
		buf.append("BaseServiceTest.java");
		return buf.toString();
	}

	@Override
	public void generate() {
		try {
			System.out.print("Generating [service] class...");
			String code = mergeTemplate(StringUtils.defaultIfEmpty(
					gConfig.getTemplate(), "/template/thriftBaseService.vm"),
					generatorContext);
			IOUtil.writeCodeFile(getPath(), code);
//			System.out.print("Generating [service test] class...");
//			code = mergeTemplate(StringUtils.defaultIfEmpty(
//					gConfig.getTemplate(), "/template/serviceTest.vm"),
//					generatorContext);
//			IOUtil.writeCodeFile(getTestPath(), code);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Generate fail：" + e.getMessage());
		}
	}

}
