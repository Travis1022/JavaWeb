package org.matt.autocode.conf;


import java.util.List;







import org.matt.autocode.domain.column.Column;
import org.matt.autocode.util.ActionUtil;
import org.matt.autocode.util.StringUtil;
import org.matt.util.StringUtils;

/**
 * 
 * <p>
 * 内容摘要: 表信息
 * </p>
 * <p>
 * 完成日期: 2013年9月7日 下午5:10:49
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
 */
public class Table {
	private String schema;
	private String tableName;
	private String name;
	private String className;
	private String fieldName;
	private String idFieldName;

	private String idCoulmnName;
	
	private List<Column> columns;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */

	public Table() {
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getModuleName() {
		return tableName.substring(0,tableName.indexOf("_"));
	}

	public String getClassName() {
		if(StringUtils.isEmpty(className)) {
			className = StringUtil.toCamel(tableName.substring(tableName.indexOf("_")));
		}
		return className;
	}
	
	public String getUrlName() {
		return ActionUtil.build(className,true, "-");
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getIdFieldName() {
		return idFieldName;
	}

	public void setIdFieldName(String idFieldName) {
		this.idFieldName = idFieldName;
	}

	public String getIdCoulmnName() {
		return idCoulmnName;
	}

	public void setIdCoulmnName(String idCoulmnName) {
		this.idCoulmnName = idCoulmnName;
	}

	public String getIdFieldGetName() {
		return StringUtil.toCamel("get_"
				+ ActionUtil.build("idFieldName", true, "_"),
				false);
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	public String getName() {
		return name;
	}
	
}
