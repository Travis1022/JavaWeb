package org.matt.autocode.domain.generator;

import java.io.StringWriter;


import java.util.Map;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import org.matt.autocode.conf.Config;
import org.matt.autocode.conf.GeneratorConfig;
import org.matt.autocode.conf.Table;

public abstract class Generator {

	protected GeneratorConfig gConfig;
	protected Table table;

	protected String targetProject;
	protected String targetPackage;
	protected String basePackage;
	protected String targetPage;

	public Generator(Config config, GeneratorConfig gConfig, Table table) {
		this.gConfig = gConfig;
		this.table = table;
		this.basePackage = config.getTargetPackage();
		this.targetProject = config.getTargetProject();
		this.targetPackage = config.getTargetPackage();
		this.targetPage = config.getTargetPage();
	}

	protected String mergeTemplate(String targetTemplate,
			Map<String, Object> generatorContext) throws Exception {
		Properties p = new Properties();
		p.setProperty("resource.loader", "class");
		p.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		Velocity.init(p);
		VelocityContext context = new VelocityContext();
		context.put("table", table);
		context.put("basePackage", basePackage);
		context.put("targetPackage", targetPackage);
		context.put("tableName", table.getTableName());
		context.put("className", table.getClassName());
		context.put("generatorContext", generatorContext);

		StringWriter w = new StringWriter();
		Velocity.mergeTemplate(targetTemplate, "UTF-8", context, w);

		return w.getBuffer().toString();
	}

	public abstract void generate();

}
