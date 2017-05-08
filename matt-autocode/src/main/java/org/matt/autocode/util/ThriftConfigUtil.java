package org.matt.autocode.util;


public class ThriftConfigUtil {

	/**
	 * 
	 * @date：2013年9月7日
	 * @Description：数据库类型到java数据类型转换
	 * @param type
	 * @return
	 */
	public static String type2Suffix(String type) {
		/*
		 * xml domain dao service action vm
		 */
		if ("xml".equals(type)) {
			return ".xml";
		} else if ("dao".equals(type)) {
			return "Dao.java";
		} else if ("domain".equals(type)) {
			return ".java";
		} else if ("service".equals(type)) {
			return "Service.java";
		} else if ("action".equals(type)) {
			return "Action.java";
		} else if ("page".equals(type)) {
			return ".vm";
		}else if ("thriftservice".equals(type)) {
			return "ThriftService.java";
		} 
		else {
			return "." + type;
		}
	}

}
