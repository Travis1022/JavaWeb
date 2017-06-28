package com.travis.common.utils;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.travis.common.domain.BaseEntity;
import com.travis.common.utils.json.BaseProcessor;
import com.travis.common.utils.json.SqlDateProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.travis.common.utils.json.DateJsonValueProcessor;
import org.matt.persistent.db.mybatis.MybatisPage;

public class JsonUtils {
	protected static String[] EXCLUDES = { "start", "limit", "sort", "dir", "data", "span", "order", "offset" };

	public static JSONObject bean2JSONObject(Object o) {

		if (o == null) {
			throw new IllegalArgumentException("object is null while write the Json string...");
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new SqlDateProcessor());
		jsonConfig.registerJsonValueProcessor(Integer.class, new BaseProcessor());
		jsonConfig.registerJsonValueProcessor(Long.class, new BaseProcessor());
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor());
		return JSONObject.fromObject(o, jsonConfig);
	}

	public static JSONObject bean2JSONObject(Object o, String dateFormat) {
		if (o == null) {
			throw new IllegalArgumentException("object is null while write the Json string...");
		}

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new SqlDateProcessor());

		jsonConfig.registerJsonValueProcessor(Integer.class, new BaseProcessor());

		jsonConfig.registerJsonValueProcessor(Long.class, new BaseProcessor());

		if (!StringUtils.isBlank(dateFormat)) {
			jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor(dateFormat));
		} else {
			jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor());
		}
		return JSONObject.fromObject(o, jsonConfig);
	}

	public static JSONArray bean2JSONArray(Object o) {

		if (o == null) {
			throw new IllegalArgumentException("object is null while write the Json string...");
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new SqlDateProcessor());
		jsonConfig.registerJsonValueProcessor(Integer.class, new BaseProcessor());
		jsonConfig.registerJsonValueProcessor(Long.class, new BaseProcessor());
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor());
		return JSONArray.fromObject(o, jsonConfig);
	}

	public static JSONArray bean2JSONArray(Object o, String dateFormat) {
		if (o == null) {
			throw new IllegalArgumentException("object is null while write the Json string...");
		}

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new SqlDateProcessor());

		jsonConfig.registerJsonValueProcessor(Integer.class, new BaseProcessor());

		jsonConfig.registerJsonValueProcessor(Long.class, new BaseProcessor());

		if (!StringUtils.isBlank(dateFormat)) {
			jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor(dateFormat));
		} else {
			jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor());
		}
		return JSONArray.fromObject(o, jsonConfig);
	}

	public static String bean2Json(Object o) {
		return bean2Json(o, EXCLUDES);
	}

	public static String bean2Json(Object o, String dateFormat) {
		return bean2Json(o, EXCLUDES, dateFormat);
	}

	public static String bean2Json(Object o, Integer count) {
		return appendResult(o, count, EXCLUDES);
	}

	public static String bean2Json(MybatisPage page) {
		return bean2Json(page, EXCLUDES);
	}

	public static String bean2Json(MybatisPage page, String dateFormat) {
		return bean2Json(page, EXCLUDES, dateFormat);
	}

	public static String bean2Json(Object o, String[] excludes) {
		if (o == null) {
			throw new IllegalArgumentException("object is null while write the Json string...");
		}

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new SqlDateProcessor());
		jsonConfig.registerJsonValueProcessor(Integer.class, new BaseProcessor());
		jsonConfig.registerJsonValueProcessor(Long.class, new BaseProcessor());
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor());
		String jsonString = JSONObject.fromObject(o, jsonConfig).toString();
		jsonString = jsonString.replace("result", "rows");
		return jsonString;
	}

	public static String bean2JsonArray(Object o) {
		if (o == null) {
			throw new IllegalArgumentException("object is null while write the Json string...");
		}

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new SqlDateProcessor());
		jsonConfig.registerJsonValueProcessor(Integer.class, new BaseProcessor());
		jsonConfig.registerJsonValueProcessor(Long.class, new BaseProcessor());
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor());
		String jsonString = JSONArray.fromObject(o, jsonConfig).toString();
		return jsonString;
	}

	public static String bean2JsonArray(Object o, String dateFormat) {
		if (o == null) {
			throw new IllegalArgumentException("object is null while write the Json string...");
		}

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new SqlDateProcessor());

		jsonConfig.registerJsonValueProcessor(Integer.class, new BaseProcessor());

		jsonConfig.registerJsonValueProcessor(Long.class, new BaseProcessor());

		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor(dateFormat));

		String jsonString = JSONArray.fromObject(o, jsonConfig).toString();
		return jsonString;
	}

	public static String bean2Json(Object o, String[] excludes, String dateFormat) {
		if (o == null) {
			throw new IllegalArgumentException("object is null while write the Json string...");
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new SqlDateProcessor());
		jsonConfig.registerJsonValueProcessor(Integer.class, new BaseProcessor());
		jsonConfig.registerJsonValueProcessor(Long.class, new BaseProcessor());
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor(dateFormat));
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor(dateFormat));
		String jsonString = JSONArray.fromObject(o, jsonConfig).toString();

		jsonString = jsonString.substring(1, jsonString.length());
		jsonString = jsonString.substring(0, jsonString.length() - 1);

		return jsonString;
	}

	public static String appendResult(Object o, Integer count, String[] excludes) {
		String jsonString = bean2Json(o, excludes);
		jsonString = "{\"results\":" + count + ",\"rows\":[" + jsonString + "]}";
		return jsonString;
	}

	public static <T extends BaseEntity> T json2Bean(String jsonData, Class<T> clazz) throws Exception {
		if (StringUtils.isBlank(jsonData)) {
			return clazz.newInstance();
		}

		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		return json2Bean(jsonObject, clazz);
	}

	public static <T extends BaseEntity> List<T> json2List(String jsonData, Class<T> clazz) throws Exception {
		if (StringUtils.isBlank(jsonData)) {
			return new ArrayList(0);
		}

		JSONArray jsonArray = JSONArray.fromObject(jsonData);
		List list = new ArrayList(jsonArray.size());

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			list.add(json2Bean(jsonObject, clazz));
		}

		return list;
	}

	public static String getJsonCombox(Map<String, String> map) {
		StringBuffer strBuffer = new StringBuffer();
		if (map.isEmpty()) {
			strBuffer.append("{\"result\":[],").append("\"totalCount\":0}");
			return strBuffer.toString();
		}

		strBuffer.append("{\"result\":[");

		for (Iterator i$ = map.entrySet().iterator(); i$.hasNext();) {
			Object object = i$.next();
			Map.Entry entry = (Map.Entry) object;
			String propertyName = entry.getKey().toString();
			String propertyValue = entry.getValue().toString();
			strBuffer.append("{\"id\":\"" + propertyName + "\",\"text\":\"" + propertyValue + "\"},");
		}
		String buffer = strBuffer.substring(0, strBuffer.length() - 1);
		buffer = buffer + "],\"totalCount\":" + map.size() + "}";
		return buffer;
	}

	private static <T extends BaseEntity> T json2Bean(JSONObject jsonObject, Class<T> clazz) throws Exception {
		BaseEntity entity = (BaseEntity) clazz.newInstance();

		for (Iterator i$ = jsonObject.entrySet().iterator(); i$.hasNext();) {
			Object object = i$.next();
			Map.Entry entry = (Map.Entry) object;
			String propertyName = entry.getKey().toString();
			String propertyValue = entry.getValue().toString();
			BeanUtils.setProperty(entity, propertyName, propertyValue);
		}

		return (T) entity;
	}
}