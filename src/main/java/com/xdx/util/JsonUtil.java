package com.xdx.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;

public class JsonUtil {
	public static JSONObject mapToJsonObject(Map<String, Object> maps) {
		JSONObject jsonObject = null;
		try {
			JsonConfig jf = new JsonConfig();
			// 在产生JSON对象时传入目标对象和JSON配置对象
			// 设置Timestamp和Date的json格式
			jf.registerJsonValueProcessor(java.sql.Timestamp.class,
					new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
			jf.registerJsonValueProcessor(java.util.Date.class,
					new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
			jsonObject = JSONObject.fromObject(maps, jf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * list转化为JsonArray对象
	 * 
	 * @param list
	 * @return
	 */
	public static JSONArray listToJsonArray(List<Map<String, Object>> list) {
		JSONArray jsonArray = null;
		try {
			JsonConfig jf = new JsonConfig();
			// 在产生JSON对象时传入目标对象和JSON配置对象
			// 设置Timestamp和Date的格式
			jf.registerJsonValueProcessor(java.sql.Timestamp.class,
					new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
			jf.registerJsonValueProcessor(java.util.Date.class,
					new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
			jf.registerDefaultValueProcessor(Integer.class,
			    new DefaultValueProcessor(){
			        public Object getDefaultValue(Class type){
			            return JSONNull.getInstance();
			        }
			    });
			jf.registerDefaultValueProcessor(String.class,
				    new DefaultValueProcessor(){
				        public Object getDefaultValue(Class type){
				            return "";
				        }
				    });
			jsonArray = JSONArray.fromObject(list, jf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	public static JSONObject stringMapToJsonObject(Map<String, String> maps) {
		JSONObject jsonObject = null;
		try {
			JsonConfig jf = new JsonConfig();
			// 在产生JSON对象时传入目标对象和JSON配置对象
			// 设置Timestamp和Date的格式
			jf.registerJsonValueProcessor(java.sql.Timestamp.class,
					new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
			jf.registerJsonValueProcessor(java.util.Date.class,
					new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
			jsonObject = JSONObject.fromObject(maps, jf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}
