package com.xuequ.cmoc.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;

public class JsonUtils {
	public final static ObjectMapper mapper = new ObjectMapper();
	
	static{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(df);
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
		mapper.configure(Feature.WRITE_NULL_MAP_VALUES, false);
		mapper.configure(Feature.WRITE_EMPTY_JSON_ARRAYS, false);
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}
	
	private static JavaType getCollectionType(Class<?> collectionClass,Class<?>... classes){
		return mapper.getTypeFactory().constructParametricType(collectionClass,classes);
	}
	
	/**
	 * json字符串转对象
	 * @param json
	 * @param c
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws Exception
	 */
	public static <T> T jsonToObject(String json,Class<T> c) throws JsonParseException, JsonMappingException, IOException{
		if(null != json && !"".equals(json)){
			return mapper.readValue(json,c);
		}
		return null;
	}
	
	/**
	 * json字符串转泛型集合
	 * @param json
	 * @param c
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws Exception
	 */
	public static <T> List<T> jsonToObjectList(String json,Class<T> c) throws JsonParseException, JsonMappingException, IOException{
		List<T> result = null;
		if(null != json && !"".equals(json)){
			result = mapper.readValue(json,getCollectionType(ArrayList.class,c));
		}
		return result;
	}	
	
	/**
	 * json字符串转map
	 * @param json
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
    public static Map jsonToMap(String json) throws JsonParseException, JsonMappingException, IOException{
		return jsonToObject(json,Map.class);
	}
	
	/**
	 * map转json
	 * @auther 胡启萌
	 * @Date 2017年4月17日
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception
	 */
	public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass)
            throws Exception {
        if (map == null)
            return null;
        Object obj = beanClass.newInstance();
        org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        return (T) obj;
    }
	
	/**
	 * 集合转json字符串
	 * @param list
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
    public static String objectListToJson(List list) throws JsonGenerationException, JsonMappingException, IOException{
		if(null != list && list.size() > 0){
			return mapper.writeValueAsString(list);
		}
		return null;
	}
	
	/**
	 * 对象转json字符串
	 * @param o
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 * @throws Exception
	 */
	public static String objectToJson(Object o) throws JsonGenerationException, JsonMappingException, IOException{
		if(null != o){
			return mapper.writeValueAsString(o);
		}
		return "";
	}
	public static String objectToJsonString(Object object, final String ...strings) {
		PropertyFilter filter = new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				for(String s : strings){  
		            if(s.equals(name)){
						return false;
					}
			    }  
				return true;
			}
		};
		SerializeWriter sw = new SerializeWriter();
		JSONSerializer serializer = new JSONSerializer(sw);
		serializer.getPropertyFilters().add(filter);
		serializer.write(object);
		return sw.toString();
	}

}
