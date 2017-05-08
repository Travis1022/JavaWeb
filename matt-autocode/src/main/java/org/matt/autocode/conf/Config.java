package org.matt.autocode.conf;

import java.util.ArrayList;

import java.util.List;

import org.matt.autocode.domain.generator.Generator;
import org.matt.autocode.domain.generator.GeneratorFactory;

/**
 * 
 * <p>
 * 内容摘要: 代码生成配置信息
 * </p>
 * <p>
 * 完成日期: 2013年9月7日 下午5:13:53
 * </p>
 * <p>
 * 修改记录:
 * </p>
 * 
 * <pre>
 *    修改日期:
 *    修 改 人:
 *    修改内容:
 * </pre>
 * 
 * @author matt
 */
public class Config {
	private String targetProject;
	private String targetPackage;
	private String targetPage;
	private JdbcConnection jdbcConnection;
	private List<GeneratorConfig> generators;
	private List<Table> tables;

	public Config() {
	}

	

	public String getTargetPage() {
		return targetPage;
	}
	public JdbcConnection getJdbcConnection() {
		return jdbcConnection;
	}
	
	public String getTargetProject() {
		return targetProject;
	}

	public List<Generator> getGeneratorList(Table table) {
		List<Generator> generatorList = new ArrayList<Generator>();
		if (generators != null) {
			for (GeneratorConfig c : generators) {
				try {
					generatorList.add(GeneratorFactory.createGenerator(this, c,table));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return generatorList;
	}

	public List<Table> getTables() {
		return tables;
	}

	public String getTargetPackage() {
		return targetPackage;
	}
}
