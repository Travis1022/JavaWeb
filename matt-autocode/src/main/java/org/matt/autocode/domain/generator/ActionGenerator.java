package org.matt.autocode.domain.generator;

import java.util.HashMap;




import java.util.Map;

import org.matt.autocode.conf.Config;
import org.matt.autocode.conf.GeneratorConfig;
import org.matt.autocode.conf.Table;
import org.matt.autocode.util.IOUtil;
import org.matt.autocode.util.StringUtil;
import org.matt.util.StringUtils;

public class ActionGenerator extends Generator {

	private Map<String, Object> generatorContext;

	public ActionGenerator(Config config, GeneratorConfig gConfig, Table table) {
		super(config, gConfig, table);
		generatorContext = new HashMap<String, Object>();
		generatorContext.put("module", table.getModuleName());
		generatorContext.put("domain",
				this.targetPackage + "." + table.getModuleName() + ".domain."
						+ table.getClassName());
		generatorContext.put("service",
				this.targetPackage + "." + table.getModuleName() + ".service.impl."
						+ table.getClassName() + "Service");
		this.targetPackage = this.targetPackage + "." + table.getModuleName()
				+ ".action";
	}

	private String getPath() {
		StringBuffer buf = new StringBuffer();
		buf.append(targetProject).append("/src/main/java/")
				.append(StringUtil.packge2path(targetPackage)).append("/");
		buf.append(table.getClassName());
		buf.append("Action.java");
		return buf.toString();
	}

	private String getTestPath() {
		StringBuffer buf = new StringBuffer();
		buf.append(targetProject).append("/src/test/java/")
				.append(StringUtil.packge2path(targetPackage)).append("/");
		buf.append(table.getClassName());
		buf.append("ActionTest.java");
		return buf.toString();
	}

	@Override
	public void generate() {
		try {
			System.out.print("Generating [action] class...");
			String code = mergeTemplate(StringUtils.defaultString(
					gConfig.getTemplate(), "/template/action.vm"),
					generatorContext);
			IOUtil.writeCodeFile(getPath(), code);
//			System.out.print("Generating [action test] class...");
//			code = mergeTemplate(StringUtils.defaultString(
//					gConfig.getTemplate(), "/template/actionTest.vm"),
//					generatorContext);
//			IOUtil.writeCodeFile(getTestPath(), code);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Generate fail：" + e.getMessage());
		}
	}

}
