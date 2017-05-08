package org.matt.autocode.domain.generator;

import org.matt.autocode.conf.Config;




import org.matt.autocode.conf.GeneratorConfig;
import org.matt.autocode.conf.Table;
import org.matt.autocode.util.IOUtil;
import org.matt.autocode.util.StringUtil;
import org.matt.util.StringUtils;

public class DomainGenerator extends Generator {

	public DomainGenerator(Config config, GeneratorConfig gConfig, Table table) {
		super(config, gConfig, table);
		this.targetPackage = this.targetPackage+"."+table.getModuleName()+".domain";
	}

	private String getPath() {
		StringBuffer buf = new StringBuffer();
		buf.append(targetProject).append("/src/main/java/")
				.append(StringUtil.packge2path(targetPackage)).append("/");
		buf.append(table.getClassName());
		buf.append(".java");
		return buf.toString();
	}

	@Override
	public void generate() {
		try {
			System.out.print("Generating [domain] class...");
			String code = mergeTemplate(StringUtils.defaultString(gConfig.getTemplate(),"/template/domain.vm"), null);
			IOUtil.writeCodeFile(getPath(), code);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Generate fail："+e.getMessage());
		}
	}
}
